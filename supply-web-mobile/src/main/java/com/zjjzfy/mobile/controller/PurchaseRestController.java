package com.zjjzfy.mobile.controller;

import com.github.pagehelper.PageInfo;
import com.zjjzfy.common.config.OrderEnum;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.interfaces.TbUserInfoMapper;
import com.zjjzfy.order.service.CartService;
import com.zjjzfy.order.service.PurchaseService;
import com.zjjzfy.pojo.TbUserInfo;
import com.zjjzfy.pojo.dto.ProductDto;
import com.zjjzfy.product.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by Saintyun on 2019/3/13.
 */
@RequestMapping("/purchase")
@RestController
public class PurchaseRestController {

    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;
    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private TbUserInfoMapper tbUserInfoMapper;

    @RequestMapping("/test")
    public SupplyResult test() {
        List<TbUserInfo> userInfos = tbUserInfoMapper.selectAll();

        return SupplyResult.build(1, "sdasadas", userInfos);
    }

    @RequestMapping("/selectProductAndStock")
    private List<ProductDto> selectProductAndStock(String order, String name, Integer categoryId,Integer prefecture) {
        OrderEnum oe = null;
        if (order != null) {
            oe = OrderEnum.valueOf(order);
        }

        if (name != null && name.length() == 0) {
            name = null;
        }

        return productService.selectProductAndStock(null, oe, name, categoryId,prefecture);
    }

    @RequestMapping("/selectProdcut")
    public Object selectProdcut(Integer pageIndex, Integer pageSize, Integer type) {
        PageInfo<ProductDto> pageInfo = productService.selectProduct(pageIndex, pageSize, type);
        return pageInfo.getList();
    }
    @RequestMapping("/selectProdcutSp")
    public Object selectProdcut(Integer pageIndex, Integer pageSize, Integer type,Integer id) {
        PageInfo<ProductDto> pageInfo = productService.selectProductSp(pageIndex, pageSize, type,id);
        return pageInfo.getList();
    }
    /**
     * 通过订货单号查询发货单
     *
     * @param pBillno
     * @return
     */
    @RequestMapping("/queryPurchaseForm")
    public Object queryPurchaseForm(String pBillno) {
        List<Map<String, Object>> productDtos = purchaseService.getDeliverForm(pBillno);
        return productDtos;
    }
}