package com.zjjzfy.order.service.impl;

import com.zjjzfy.common.config.ReturnMsg;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.common.utils.DateUtil;
import com.zjjzfy.common.utils.IDUtils;
import com.zjjzfy.interfaces.JfDeliverDetailMapper;
import com.zjjzfy.interfaces.JfDeliverOrderMapper;
import com.zjjzfy.interfaces.TbUserInfoMapper;
import com.zjjzfy.order.service.ExchangeService;
import com.zjjzfy.pojo.JfDeliverDetail;
import com.zjjzfy.pojo.JfDeliverOrder;
import com.zjjzfy.pojo.TbCart;
import com.zjjzfy.pojo.TbUserInfo;
import com.zjjzfy.pojo.dto.ProductFormDto;
import com.zjjzfy.repository.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: hsmz
 * @date: 2019/4/9 上午11:11
 */
@Service
public class ExchangeServiceImpl implements ExchangeService {

    @Autowired
    private JfDeliverOrderMapper jfDeliverOrderMapper;
    @Autowired
    private JfDeliverDetailMapper jfDeliverDetailMapper;
    @Autowired
    private TbUserInfoMapper tbUserInfoMapper;
    @Autowired
    private RepositoryService repositoryService;
    /**
     * 行社人员自主兑换
     *
     * @param productFormDto
     * @param clientNo
     * @param employeeId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public SupplyResult exchange(ProductFormDto productFormDto, String clientNo, Integer employeeId) {
        //1.插入订单表 2.插入订单详情表 3.扣积分 4.扣库存

        String billNo = IDUtils.genOrderId();
        Date nowDate = DateUtil.getCurrentDate();
        TbUserInfo user = tbUserInfoMapper.selectByPrimaryKey(employeeId);

        JfDeliverOrder jfDeliveryOrder = new JfDeliverOrder();
        jfDeliveryOrder.setBillno(billNo);
        jfDeliveryOrder.setClientno(clientNo);
        jfDeliveryOrder.setCreateDate(nowDate);
        jfDeliveryOrder.setBranchid(user.getOrgid());
        jfDeliveryOrder.setEmployeeid(employeeId);
        jfDeliveryOrder.setType(0);
        jfDeliveryOrder.setStatus((byte) 1);
        jfDeliveryOrder.setRemark("");
        jfDeliveryOrder.setType(5);
        jfDeliveryOrder.setTotalQuantity(productFormDto.getTotalProductNumber());
        jfDeliveryOrder.setTotalPrice(productFormDto.getTotalProductPrice());
        jfDeliverOrderMapper.insertSelective(jfDeliveryOrder);

        for (TbCart tbCart : productFormDto.getCartList()) {
            JfDeliverDetail odt = new JfDeliverDetail();
            odt.setBillno(billNo);
            odt.setEmployeeid(employeeId);
            odt.setProductId(tbCart.getProductId());
            odt.setCreateDate(nowDate);
            odt.setQuantity(tbCart.getProductQuality());
            //单价
            odt.setPurchasePrice(tbCart.getProductPrice());
            odt.setPoint(BigDecimal.ZERO);
            odt.setClientno(clientNo);

            jfDeliverDetailMapper.insertSelective(odt);

        }
        repositoryService.purchaserStockOutputBatch(productFormDto.getCartList(),employeeId);

        Boolean result = repositoryService.checkPurchaserFakeStock(productFormDto.getCartList(),employeeId);

        if (result) {
            throw new RuntimeException(ReturnMsg.FAKE_QUANTITY.getMsg());
        }
        //TODO 通过客户内码扣积分扣积分

        return SupplyResult.ok();
    }
}
