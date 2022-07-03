package com.zjjzfy.order.service.impl;

import com.google.gson.Gson;
import com.zjjzfy.common.config.ReturnMsg;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.common.utils.DateUtil;
import com.zjjzfy.common.utils.IDUtils;
import com.zjjzfy.interfaces.*;
import com.zjjzfy.order.service.PadExchangeService;
import com.zjjzfy.pojo.*;
import com.zjjzfy.pojo.dto.CartDto;
import com.zjjzfy.pojo.dto.JsonType;
import com.zjjzfy.repository.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Saintyun on 2019/4/2.
 */
@Service
public class PadExchangeServiceImpl implements PadExchangeService {
    @Autowired
    private PublicTableMapper publicTableMapper;
    @Autowired
    private TbProductMapper tbProductMapper;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private JfDeliverOrderMapper jfDeliverOrderMapper;
    @Autowired
    private TbUserInfoMapper tbUserInfoMapper;
    @Autowired
    private JfDeliverDetailMapper jfDeliverDetailMapper;

    @Override
    public Map<String, Object> selectGiftList(Integer employeeid,Integer productId, String orderBy, String orderCase) {
        List<HashMap<String,Object>> gifts = publicTableMapper.selectGiftList(employeeid,productId,orderBy,orderCase);
        Map<String,Object> map = new HashMap<>();
        map.put("employeeid",employeeid);
        map.put("gifts",gifts);
        map.put("msg","获取数据成功！");
        map.put("orderBy",orderBy);
        map.put("orderCase",orderCase);
        map.put("result",0);
        return map;
    }

    /**
     * 购物车兑换接口
     * @param cartJson 购物车json
     * @param employeeid 柜员id
     * @param clientno 客户内码
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> pay(String cartJson, Integer employeeid, String clientno) {
        Gson gson = new Gson();
        JsonType type = gson.fromJson(cartJson,JsonType.class);
        BigDecimal costPoint = new BigDecimal(0);
        List<TbCart> carts = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        if(type.getList().size()>0){
            for (CartDto cart : type.getList()) {
                TbProduct tbProduct = tbProductMapper.selectByPrimaryKey(cart.getId());
                costPoint = costPoint.add(new BigDecimal(cart.getCount()).multiply(tbProduct.getPurchasePrice()));
                TbCart c = new TbCart();
                c.setProductId(tbProduct.getId());
                c.setProductQuality(cart.getCount());
                c.setSupplierId(employeeid);
                carts.add(c);
            }

            //查询出网点号
            TbUserInfo user = tbUserInfoMapper.selectByPrimaryKey(employeeid);
            //生成出库信息
            String billno = createDeliveryInfo(employeeid,user.getOrgid(),clientno);
            //生成订单信息
            createOrderInfo(type.getList(),billno,clientno,employeeid);

            //扣减库存
            repositoryService.purchaserStockOutputBatch(carts,employeeid);
            //检查是否有负库存
            Boolean result = repositoryService.checkPurchaserFakeStock(carts,employeeid);
            if (result) {
                throw new RuntimeException(ReturnMsg.FAKE_QUANTITY.getMsg());
            }
            map.put("billno",billno);
            map.put("branchId",user.getOrgid());
            map.put("occurdate",DateUtil.getCurrentDate());
            map.put("result",200);
            map.put("msg","支付成功！");
        }else{
            map.put("result",300);
            map.put("msg","支付失败");
        }
        return map;
    }

    /**
     * 生成出库信息 对应表 JF_DELIVERY_ORDER
     */
    public String createDeliveryInfo(Integer employeeid,Integer branchid,String clientno){
        String billno = IDUtils.genOrderId();
        //插入JF_DELIVERY_ORDER
        JfDeliverOrder jfDeliveryOrder = new JfDeliverOrder();

        jfDeliveryOrder.setBillno(billno);
        jfDeliveryOrder.setClientno(clientno);
        jfDeliveryOrder.setCreateDate(DateUtil.getCurrentDate());
        jfDeliveryOrder.setBranchid(branchid);
        jfDeliveryOrder.setEmployeeid(employeeid);
        jfDeliveryOrder.setType(0);
        jfDeliveryOrder.setStatus((byte) 1);
        jfDeliveryOrder.setRemark("");

        jfDeliverOrderMapper.insertSelective(jfDeliveryOrder);
        return billno;
    }

    /**
     * 生成订单信息 对应表 JF_ORDER_DETAIL
     * @param dtos
     */
    public void createOrderInfo(List<CartDto> dtos,String billno,String clientno,Integer employeeid){
        //插入JF_ORDER_DETAIL
        Date date = DateUtil.getCurrentDate();
        for (CartDto cart : dtos) {
            JfDeliverDetail odt = new JfDeliverDetail();
            TbProduct product = tbProductMapper.selectByPrimaryKey(cart.getId());
            odt.setBillno(billno);
            odt.setEmployeeid(employeeid);
            odt.setProductId(cart.getId());
            odt.setCreateDate(date);
            odt.setQuantity(cart.getCount());
            //单价
            odt.setPurchasePrice(product.getPurchasePrice());
            odt.setPoint(new BigDecimal(cart.getCount()).multiply(product.getPurchasePrice()));
            odt.setClientno(clientno);
            if (cart.getCount() > 0) {
                jfDeliverDetailMapper.insertSelective(odt);
            }
        }
    }
}
