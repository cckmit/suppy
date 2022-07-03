package com.zjjzfy.order.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjjzfy.common.config.DeliverDetailStatus;
import com.zjjzfy.common.config.DeliverOrderStatus;
import com.zjjzfy.common.config.DeliverOrderType;
import com.zjjzfy.common.config.ReturnMsg;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.interfaces.*;
import com.zjjzfy.order.service.CartService;
import com.zjjzfy.order.service.ExchangeOrderService;
import com.zjjzfy.pojo.*;
import com.zjjzfy.pojo.dto.ExchangeOrderInfoDto;
import com.zjjzfy.pojo.dto.ProductFormDto;
import com.zjjzfy.repository.service.RepositoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: jackshenonly
 * Date: 2019/5/7
 * Time: 10:39
 */
@Slf4j
@Service
public class ExchangeOrderServiceImpl implements ExchangeOrderService {

    /**
     * POS打印小票的凭证号长度。
     */
    public static final int TICKET_LEN = 6;

    @Autowired
    private TbProductMapper tbProductMapper;
    @Autowired
    private JfDeliverOrderMapper jfDeliverOrderMapper;
    @Autowired
    private JfDeliverDetailMapper jfDeliverDetailMapper;
    @Autowired
    private TbOrgInfoMapper tbOrgInfoMapper;
    @Autowired
    private TbUserInfoMapper tbUserInfoMapper;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private CartService cartService;
    @Autowired
    private JfDeliverOrderExtraMapper jfDeliverOrderExtraMapper;

    @Override
    public SupplyResult orderInfo(String billno) {
        ExchangeOrderInfoDto orderInfoDto = new ExchangeOrderInfoDto();
        JfDeliverOrder deliverOrder = queryDeliverOrderByBillno(billno);
        if(null == deliverOrder){
            return SupplyResult.build(404,"订单不存在");
        }

        List<ExchangeOrderInfoDto.Detail> list = new ArrayList<>();
        JfDeliverDetailExample jdde = new JfDeliverDetailExample();
        jdde.createCriteria().andBillnoEqualTo(billno);
        jdde.setOrderByClause("product_id asc");
        List<JfDeliverDetail> orderDetailList = jfDeliverDetailMapper.selectByExample(jdde);
        if(orderDetailList.size() == 0){
            orderInfoDto.setList(list);
            return SupplyResult.ok(orderInfoDto);
        }

        TbProductExample tbpe = new TbProductExample();
        tbpe.createCriteria().andIdIn(orderDetailList.stream().map(JfDeliverDetail::getProductId).collect(Collectors.toList()));
        tbpe.setOrderByClause("id asc");
        List<TbProduct> productList = tbProductMapper.selectByExample(tbpe);

        for (int i = 0 ; i < productList.size(); i++){
            ExchangeOrderInfoDto.Detail d = ExchangeOrderInfoDto.createDetail();
            d.setProduct(productList.get(i));
            d.setDetail(orderDetailList.get(i));
            list.add(d);
        }

        TbOrgInfo orgInfo = tbOrgInfoMapper.selectByPrimaryKey(deliverOrder.getBranchid());
        TbUserInfo userInfo = tbUserInfoMapper.selectByPrimaryKey(deliverOrder.getEmployeeid());
        JfDeliverOrderExtra extra = jfDeliverOrderExtraMapper.selectByPrimaryKey(billno);

        orderInfoDto.setList(list);
        orderInfoDto.setMain(deliverOrder);
        orderInfoDto.setOrgInfo(orgInfo);
        orderInfoDto.setUserInfo(userInfo);
        orderInfoDto.setExtra(extra);

        return SupplyResult.ok(orderInfoDto);
    }


    @Override
    public SupplyResult orderInfo(String billno, TbUserInfo userInfo) {
        if(billno.trim().length() == TICKET_LEN){
            //如果是凭证号，先用凭照号和当前登陆柜员所属网点查询出去该本地单号（如果有的话）。
            JfDeliverOrderExample jdoe = new JfDeliverOrderExample();
            jdoe.createCriteria().andBranchidEqualTo(userInfo.getOrgid())
                    .andRemarkLike("%"+billno+"%");
            List<JfDeliverOrder> orders = jfDeliverOrderMapper.selectByExample(jdoe);
            if(orders.size() == 0){
                return SupplyResult.build(404,"订单不存在,请检查输入的[凭证号]是否正确，是否是本网点产生的。");
            }else {
                return orderInfo(orders.get(0).getBillno());
            }
        }else {
            //如果不是凭证号，还是按原来的逻辑查询
            return orderInfo(billno);
        }

    }

    @Override
    public JfDeliverOrder queryDeliverOrderByBillno(String billno) {

        JfDeliverOrderExample jdoe = new JfDeliverOrderExample();
        jdoe.createCriteria().andBillnoEqualTo(billno);
        List<JfDeliverOrder> deliverOrderList = jfDeliverOrderMapper.selectByExample(jdoe);
        if(deliverOrderList.size() > 0){
            return deliverOrderList.get(0);
        }else {
            return null;
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SupplyResult orderOut(String billno,TbUserInfo userInfo) throws Exception{

        //1.判断自助兑换单，是否符合发放条件
        ExchangeOrderInfoDto orderInfoDto = (ExchangeOrderInfoDto) orderInfo(billno).getData();

        if(orderInfoDto == null){
            return SupplyResult.build(404,"订单不存在");
        }else {
            // 加行锁，避免重复请求。
            JfDeliverOrder jfdOrderForUpdate = jfDeliverOrderMapper.selectByPrimaryKeyForUpdate(orderInfoDto.getMain().getId());
            if(!jfdOrderForUpdate.getStatus().equals(DeliverOrderStatus.PAID.getValue())) {
                return SupplyResult.build(403, "订单状态不正确，不能发放");
            }
        }


        //2.先扣库存，然后检测库存是否为 负数，否则抛出异常 回滚
        List<Integer> ids = new ArrayList<>();
        List<Integer> nums = new ArrayList<>();
        orderInfoDto.getList().stream().forEach(i -> {ids.add(i.getDetail().getProductId());nums.add(i.getDetail().getQuantity());});
        ProductFormDto productFormDto = cartService.generateProductForm(ids,nums);
        repositoryService.purchaserStockOutputBatch(productFormDto.getCartList(),userInfo.getUid());
        Boolean result = repositoryService.checkPurchaserFakeStock(productFormDto.getCartList(),userInfo.getUid());
        if (result) {
            throw new RuntimeException(ReturnMsg.FAKE_QUANTITY.getMsg());
        }
        //3.更改订单状态、从属，详情，and 明细

        JfDeliverOrder order = orderInfoDto.getMain();
        order.setStatus(DeliverOrderStatus.OUT.getValue());
        order.setBranchid(userInfo.getOrgid());
        order.setEmployeeid(userInfo.getUid());
        order.setOutTime(new Date());
        jfDeliverOrderMapper.updateByPrimaryKeySelective(order);

        for (ExchangeOrderInfoDto.Detail detail : orderInfoDto.getList()){
            JfDeliverDetail idetail = detail.getDetail();
            idetail.setStatus(DeliverDetailStatus.DONE.getValue());
            jfDeliverDetailMapper.updateByPrimaryKeySelective(idetail);
        }

        return SupplyResult.ok("自助兑换单发放成功！");
    }

    @Override
    public SupplyResult orderInfoRecent(Integer recentNo, TbUserInfo userInfo) {
        return orderInfoRecent(recentNo, userInfo.getOrgid());
    }

    @Override
    public SupplyResult orderInfoRecent(Integer recentNo, Integer orgid) {
        PageHelper.startPage(recentNo, 1);
        JfDeliverOrderExample jdoe = new JfDeliverOrderExample();
        jdoe.createCriteria().andBranchidEqualTo(orgid)
                // 不等于"配送"类型的单子
                .andTypeNotEqualTo(DeliverOrderType.DISTRIBUTION.getValue())
                .andBillnoLike("zz%")
                // 客户要求，不想看见未支付的订单@林晓江
                .andStatusNotEqualTo(DeliverOrderStatus.UNPAID.getValue());
        jdoe.setOrderByClause("create_date desc");
        List<JfDeliverOrder> orders = jfDeliverOrderMapper.selectByExample(jdoe);
        PageInfo<JfDeliverOrder> pageInfo = new PageInfo<>(orders);

        if(pageInfo.getList().size() > 0){
            return orderInfo(pageInfo.getList().get(0).getBillno());
        }

        return SupplyResult.build(404,"已经没有最近的订单了");
    }

    @Override
    public SupplyResult orderRecent(Integer pageNo, Integer pageSize, Integer orgid) {

        JfDeliverOrderExample jdoe = new JfDeliverOrderExample();
        jdoe.createCriteria().andBranchidEqualTo(orgid)
                // 不等于"配送"类型的单子
                .andTypeNotEqualTo(DeliverOrderType.DISTRIBUTION.getValue())
                .andBillnoLike("zz%")
                // 客户要求，不想看见未支付的订单@林晓江
                .andStatusNotEqualTo(DeliverOrderStatus.UNPAID.getValue());
        // 排序规则，未发放（即已支付）的排前面，且按时间倒序
        jdoe.setOrderByClause("status asc, create_date desc");
        List<JfDeliverOrder> jdos ;
        PageHelper.startPage(pageNo, pageSize);
        jdos = jfDeliverOrderMapper.selectByExample(jdoe);
        PageInfo<JfDeliverOrder> pageInfo = new PageInfo<>(jdos);
        return SupplyResult.build(200,"获取自助兑换订单列表成功",pageInfo);
    }
}
