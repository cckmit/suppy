package com.zjjzfy.exchange.service.impl;

import com.alibaba.fastjson.JSON;
import com.zjjzfy.common.config.*;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.common.utils.StringUtil;
import com.zjjzfy.exchange.cache.OrgCache;
import com.zjjzfy.exchange.common.SessionKey;
import com.zjjzfy.exchange.exception.RsaCheckException;
import com.zjjzfy.exchange.pojo.Cart4Exch;
import com.zjjzfy.exchange.pojo.CartItem;
import com.zjjzfy.exchange.pojo.OrderSubmitBody;
import com.zjjzfy.exchange.pojo.Param;
import com.zjjzfy.exchange.service.BaseService;
import com.zjjzfy.exchange.service.PayService;
import com.zjjzfy.exchange.service.ShoppingCartService;
import com.zjjzfy.exchange.utils.BillnoHelper;
import com.zjjzfy.exchange.utils.RsaUtil;
import com.zjjzfy.interfaces.JfDeliverDetailMapper;
import com.zjjzfy.interfaces.JfDeliverOrderMapper;
import com.zjjzfy.interfaces.TbProductMapper;
import com.zjjzfy.interfaces.TbRepositoryMapper;
import com.zjjzfy.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: jackshenonly
 * Date: 2019/4/30
 * Time: 13:41
 */
@Service
public class ShoppingCartServiceImpl extends BaseService implements ShoppingCartService{

    /**
     * 当前支持的 订单类型
     */
    public static List<Integer> SUPPORT_ORDER_TYPES = Arrays.asList(
            DeliverOrderType.EXCHANGE.getValue(),
            DeliverOrderType.PAY_NOTHING.getValue(),
            DeliverOrderType.CASH.getValue()
    );

    @Value("${client.public-key}")
    private String clientPublicKey;

    @Autowired
    private JfDeliverOrderMapper jfDeliverOrderMapper;
    @Autowired
    private JfDeliverDetailMapper jfDeliverDetailMapper;
    @Autowired
    private TbProductMapper tbProductMapper;
    @Autowired
    private PayService payService;

    @Autowired
    private TbRepositoryMapper repositoryMapper;

    @Override
    public SupplyResult cartAdd(int productId, int count, HttpServletRequest request) {

        try {
            Object cartObj = request.getSession().getAttribute(SessionKey.SHOPPING_CART);
            Cart4Exch cart = cartObj == null ? new Cart4Exch() : (Cart4Exch) cartObj;
            TbProduct product = tbProductMapper.selectByPrimaryKey(productId);
            cart.add(product, count);
            request.getSession().setAttribute(SessionKey.SHOPPING_CART,cart);
            return SupplyResult.ok();
        }catch (Exception e){
            log.error("添加到购物车异常："+e.getMessage());
            e.printStackTrace();
            return SupplyResult.build(300,"加入购物车异常"+e.getMessage());
        }

    }


    @Override
    public SupplyResult cartDel(int productId, HttpServletRequest request) {
        try {

            Object cartObj = request.getSession().getAttribute(SessionKey.SHOPPING_CART);
            Cart4Exch cart = cartObj == null ? new Cart4Exch() : (Cart4Exch) cartObj;
            cart.del(productId);
            request.getSession().setAttribute(SessionKey.SHOPPING_CART,cart);
            return SupplyResult.ok();
        }catch (Exception e){
            log.error("删除购物车异常："+e.getMessage());
            return SupplyResult.build(300,"删除购物车异常"+e.getMessage());
        }

    }

    @Override
    public SupplyResult amountGet(HttpServletRequest request) {
        try {
            Object cartObj = request.getSession().getAttribute(SessionKey.SHOPPING_CART);
            Cart4Exch cart = cartObj == null ? new Cart4Exch() : (Cart4Exch) cartObj;
            int sum = cart.getBasket().values().stream().map(CartItem::getAmount).reduce(0,(a, b) -> a + b );
            return SupplyResult.ok(sum);
        }catch (Exception e){
            log.error("获取购物车总产品数异常："+e.getMessage());
            return SupplyResult.build(300,"获取购物车总产品数异常"+e.getMessage(),-1);
        }
    }

    @Override
    public void putSubmitToken(HttpServletRequest request) {
        Object cartObj = request.getSession().getAttribute(SessionKey.SHOPPING_CART);
        if(cartObj == null){
            request.getSession().setAttribute(SessionKey.SHOPPING_CART,new Cart4Exch());
        }
        String token = StringUtil.getRandomString(32);
        request.getSession().setAttribute(SessionKey.SUBMIT_TOKEN, token);
        log.info("重新生成token:"+ token);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SupplyResult submit(HttpServletRequest request, String token,List<Integer> productIds, List<Integer> counts) {
        //校验参数
        if(!isParamCorrect(productIds,counts)){
            return SupplyResult.build(301,"参数异常，请重新打开购物篮提交");
        }
        //校验 submit Token
        if(null == token || !token.equals(request.getSession().getAttribute(SessionKey.SUBMIT_TOKEN))){
            return SupplyResult.build(302,"表单已过期，请重新打开购物篮页面进行提交");
        }else{
            //使用过后，置空
            request.getSession().setAttribute(SessionKey.SUBMIT_TOKEN,null);
        }


        TbOrgInfo orgInfo = OrgCache.getInstance().getOrgInfobyRequest(request);
        if(null == orgInfo){
            return SupplyResult.build(303,"未知的访问地址，请录入终端地址");
        }
        String billno = createOrder(productIds,counts,token,orgInfo);

        // 订单提交后，置空购物车。
        request.getSession().setAttribute(SessionKey.SHOPPING_CART,null);

        return SupplyResult.ok(billno);
    }

    @Override
    public SupplyResult submit(Param param) throws RsaCheckException {
        if(!RsaUtil.verify(param,clientPublicKey)){
            return SupplyResult.build(800,"是你吗？","");
        }

        OrderSubmitBody body = JSON.parseObject(param.getCs(),OrderSubmitBody.class);
        //校验参数
        if(!isParamCorrect(body.getProductIds(),body.getCounts())){
            return SupplyResult.build(801,"参数异常，请检查");
        }
        TbOrgInfo orgInfo =  OrgCache.getInstance().getOrgInfobyBranchno(body.getBranchno());
        if(orgInfo ==  null){
            return SupplyResult.build(802,"未知的访问网点，请检查网点设置");
        }
        /**订单中 库存不足的产品*/
        List<TbProduct> tbProductsNotEnough = checkStock(body.getProductIds(),body.getCounts(),orgInfo.getId());
        /**提示客户：库存充足的产品  和 库存不足的产品 请分开订单提交*/
        if(tbProductsNotEnough.size() == 0 || tbProductsNotEnough.size() == body.getProductIds().size() ){
            // 如果 全部产品库存充足，或者 全部产品都库存不足；允许生成订单。
        }else {
            // 部分充足，部分不足，不允许生产订单
            return SupplyResult.build(804,"当前订单产品中:["+concatProductNames(tbProductsNotEnough)+"]库存不足，请分开提交订单！");
        }

        /**对库存不足的产品进行 检测，是否是 允许预购状态*/
        PurchaseResult pr = checkPrePurchase(tbProductsNotEnough);
        if(!pr.getResult()){
            return SupplyResult.build(803,"订单产品中:["+pr.getProductNames()+"]库存不足，且不满足预购条件，请重新选择礼品！");
        }

        String msg = "订单生成成功！";
        if(tbProductsNotEnough.size() > 0){
            // checkMsg 用于前端判定是否库存不足 ，在和@胡慧昊移动端沟通之前，请勿修改 checkMsg。
            String checkMsg = "库存不足";
            msg += "注意：订单中["+concatProductNames(tbProductsNotEnough)+"]"+checkMsg+",当前生成预购单，请谨慎支付！";
        }
        String billno = createOrder(body.getProductIds(),body.getCounts(),StringUtil.getRandomString(32),orgInfo);
        // 生成订单后检查下，该网点是否有足够的库存。以给客户端提示。


        return SupplyResult.build(200,msg,billno);
    }

    private Map<Integer,TbProduct> abtainProducts(List<Integer> productIds){
        Map<Integer,TbProduct> map = new HashMap<>();
        TbProductExample tbpe = new TbProductExample();
        tbpe.createCriteria().andIdIn(productIds);
        tbProductMapper.selectByExample(tbpe).stream().forEach(a -> map.put(a.getId(),a));

        return map;
    }

    /**
     * 创建订单
     * @param productIds
     * @param counts
     * @param token
     * @param orgInfo
     * @return
     */
    @Transactional
    public String createOrder(List<Integer> productIds, List<Integer> counts,String token,TbOrgInfo orgInfo){

        /** 表示无对应的内码*/
        String clientno = "00000000000";
        /** -1 表示订单 没有归属到某个柜员下面*/
        int employeeid = -1;

        Map<Integer,TbProduct> map = abtainProducts(productIds);

        String billno = BillnoHelper.build("zz",new Date(),token);
        Integer total_quantity = counts.stream().reduce(0,(a,b) -> a+b);
        BigDecimal total_price = new BigDecimal(0);

        /**
         * echType 为 tb_product.exchange_type
         */
        Integer echType = null;
        // 检验兑换类型 且是否一致
        for (int i = 0; i < productIds.size() ; i++){
            //获得礼品的兑换 价格类型，且判定礼品 只能属于同一种 价格类型
            if(echType == null){
                echType = map.get(productIds.get(i)).getExchangeType();
            }else if(!echType.equals(map.get(productIds.get(i)).getExchangeType())){
                // 兑换类型不一致，抛出异常；
                throw new RuntimeException("所选礼品兑换类型不一致，请选购兑换类型一致的礼品！");
            }
        }


        for (int i = 0; i < productIds.size() ; i++){
            //计算 兑换价格
//            if(ExchangeTypeStatus.JIFEN.getStatus().equals(echType)){
//                // exchange_price
//                total_price = total_price.add(map.get(productIds.get(i)).getExchangePrice().multiply(BigDecimal.valueOf(counts.get(i))));
//            }else if(ExchangeTypeStatus.CASH.getStatus().equals(echType)){
//                // exchange_cash
//                total_price = total_price.add(map.get(productIds.get(i)).getExchangeCash().multiply(BigDecimal.valueOf(counts.get(i))));
//            }
            total_price = total_price.add(findPrice(map.get(productIds.get(i)),echType).multiply(BigDecimal.valueOf(counts.get(i))));

        }
        Integer orderType = switchProductEchType2DeliverOrderType(echType);
        if(!isSupportType(orderType)){
            throw new RuntimeException("不支持的订单类型！");
        }

        Date ctime = new Date();
        //订单生成
        JfDeliverOrder order = new JfDeliverOrder();
        order.setBranchid(orgInfo.getId());
        order.setBillno(billno);
        order.setEmployeeid(employeeid);
        order.setTotalPrice(total_price);
        order.setStatus(DeliverOrderStatus.UNPAID.getValue());
        order.setCreateDate(ctime);
        order.setType(orderType);
        order.setRemark("");
        order.setTotalQuantity(total_quantity);
        order.setClientno(clientno);
        jfDeliverOrderMapper.insertSelective( order);
        //订单详情
        JfDeliverDetail detail = new JfDeliverDetail();
        detail.setEmployeeid(employeeid);
        detail.setClientno(clientno);
        detail.setBillno(billno);
        detail.setStatus(DeliverDetailStatus.UNDO.getValue());
        detail.setCreateDate(ctime);
        for (int i = 0; i < productIds.size() ; i++){
            detail.setProductId(productIds.get(i));
            detail.setQuantity(counts.get(i));
            //记录：兑换价格 exchange_price or exchange_cash
            detail.setPurchasePrice(findPrice(map.get(productIds.get(i)),echType));
            //记录：小计价格
            detail.setPoint(detail.getPurchasePrice().multiply(BigDecimal.valueOf(counts.get(i))));
            jfDeliverDetailMapper.insertSelective(detail);
        }

        return billno;

    }

    /**
     * 主体 参数是否正确。 true 正确，false 异常
     * @param productIds
     * @param counts
     * @return
     */
    private boolean isParamCorrect(List<Integer> productIds, List<Integer> counts){

        if(productIds == null || counts == null || productIds.size() != counts.size()){
            return false;
        }
        return true;
    }


    /**
     * 检测该机构库存是否充足。
     * 充足 -> size() = 0;不充足-> size() > 0;
     * @param pids
     * @param nums
     * @param orgid
     * @return
     */
    private List<TbProduct> checkStock(List<Integer> pids, List<Integer> nums, Integer orgid){
        List<TbProduct>  tbps = new ArrayList<>();

        for (int i = 0; i < pids.size(); i ++) {
            TbRepository repository = new TbRepository();
            Integer pid = pids.get(i);
            Integer num = nums.get(i);
            repository.setProductId(pid);
            repository.setSupplyOrgId(orgid);
            TbRepository tmp = repositoryMapper.selectOne(repository);
            if(tmp == null || tmp.getQuantity() < num){
                tbps.add(tbProductMapper.selectByPrimaryKey(pid));
            }
        }
        return tbps;
    }

    /**
     * 检测产品是否是：可预购的状态。
     * @return
     */
    private PurchaseResult checkPrePurchase(List<TbProduct> tbProducts){
        PurchaseResult pr = new PurchaseResult();
        for (TbProduct tbp : tbProducts){
            if (ProductPrePurchase.FORBIDDEN.getValue().equals(tbp.getPurchaseOrnot())){
                pr.addNotPurchase(tbp);
            }
        }
        return pr;
    }

    /**
     * 连接产品名称
     * @return
     */
    private String concatProductNames(List<TbProduct> tbProducts){
        String names = null;
        if(tbProducts == null){
            return names;
        }else {
            for (TbProduct tbp : tbProducts) {
                if(names == null){
                    names = tbp.getName();
                }else {
                    names += "," + tbp.getName();
                }

            }
            return names;
        }

    }

    class PurchaseResult{
        /**
         * 是否允许预购，true允许 ，false不允许
         */
        private Boolean result;
        private String productNames;
        private List<TbProduct> productsWithOutPurchase;

        public Boolean getResult() {
            return productsWithOutPurchase==null ? true : false;
        }

        public String getProductNames() {
            return ShoppingCartServiceImpl.this.concatProductNames(productsWithOutPurchase);
        }

        /**加入不可预购的产品*/
        public void addNotPurchase(TbProduct product) {
            if(productsWithOutPurchase == null){
                productsWithOutPurchase = new ArrayList<>();
                productsWithOutPurchase.add(product);
            }else {
                productsWithOutPurchase.add(product);
            }
        }
    }

    /**
     * 是否为当前支持的订单类型
     * @return true =支持； false=不支持
     */
    private boolean isSupportType(Integer type){
        return SUPPORT_ORDER_TYPES.contains(type);
    }

    /**
     * 将产品兑换类型转化成订单类型。
     * @return
     */
    private Integer switchProductEchType2DeliverOrderType(Integer productEchType){
        Integer rt = -99;
        if(productEchType == null) {
            return rt;
        }

        if(productEchType.equals(ExchangeTypeStatus.JIFEN.getStatus())){
            rt = DeliverOrderType.EXCHANGE.getValue();
        }else if(productEchType.equals(ExchangeTypeStatus.CASH.getStatus())){
            rt = DeliverOrderType.CASH.getValue();
        }else if(productEchType.equals(ExchangeTypeStatus.MIX.getStatus())) {
            rt = DeliverOrderType.MIX_CASH_EXCHANGE.getValue();
        }

        return rt;
    }

    /**
     * 更具兑换类型，获取其价格
     * @param product
     * @param echType
     * @return
     */
    private BigDecimal findPrice(TbProduct product, Integer echType){
        if(ExchangeTypeStatus.JIFEN.getStatus().equals(echType)){
            return product.getExchangePrice();
        }else if(ExchangeTypeStatus.CASH.getStatus().equals(echType)){
            return product.getExchangeCash();
        }else {
            return null;
        }

    }

}
