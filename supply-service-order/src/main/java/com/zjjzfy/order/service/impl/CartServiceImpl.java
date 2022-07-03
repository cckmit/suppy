package com.zjjzfy.order.service.impl;

import com.zjjzfy.common.config.CartStatus;
import com.zjjzfy.common.config.ProductStatus;
import com.zjjzfy.common.config.ReturnMsg;
import com.zjjzfy.common.config.ReturnStatus;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.interfaces.TbCartMapper;
import com.zjjzfy.interfaces.TbProductMapper;
import com.zjjzfy.order.service.CartService;
import com.zjjzfy.pojo.TbCart;
import com.zjjzfy.pojo.TbCartExample;
import com.zjjzfy.pojo.TbProduct;
import com.zjjzfy.pojo.TbProductExample;
import com.zjjzfy.pojo.dto.ProductFormDto;
import com.zjjzfy.repository.service.RepositoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Saintyun on 2019/3/13.
 */
@Service
@Slf4j
public class CartServiceImpl implements CartService {
    @Autowired
    private TbCartMapper tbCartMapper;
    @Autowired
    private TbProductMapper tbProductMapper;
    @Autowired
    private RepositoryService repositoryService;


    /**
     * 添加到购物车
     *
     * @param productId  产品id
     * @param count      产品数量
     * @param purchaseId 订货方id
     */
    @Override
    @Transactional
    public SupplyResult addToCart(Integer productId, Integer count, Integer purchaseId,String model) {
        String msg = "";
        //1.判断是否下架
        TbProductExample e = new TbProductExample();
        e.createCriteria().andIdEqualTo(productId);
        List<TbProduct> products = tbProductMapper.selectByExample(e);
        if (products.size() > 0 && products.get(0).getProductStatus().equals(ProductStatus.OFF.getStatus())) {
            return SupplyResult.build(ReturnStatus.PRODUCT_OFF.getStatus(), ReturnMsg.PRODUCT_OFF.getMsg());
        }
        TbProduct product = products.get(0);
        //2.根据 purchaseid 判断是否有相同产品
        TbCartExample example = new TbCartExample();

        TbCartExample.Criteria criteria = example.createCriteria().andPurchaserIdEqualTo(purchaseId).andProductIdEqualTo(productId).andStatusEqualTo(CartStatus.NORMAL.getStatus());

        if(model != null && !model .equals("")){
           criteria.andProductColorEqualTo(model);
        }
        List<TbCart> carts = tbCartMapper.selectByExample(example);
        Integer surplus = 0 ;
        if (carts.size() == 0) {
            //判断库存是否充足
            TbCart cart = new TbCart();
            cart.setProductId(productId);
            cart.setProductPrice(product.getPurchasePrice());
            cart.setProductQuality(count);
            cart.setStatus(CartStatus.NORMAL.getStatus());
            cart.setSupplyOrgId(product.getSupplyOrgId());
            cart.setCreateDate(new Date());
            cart.setPurchaserId(purchaseId);
            cart.setProductColor(model);

            SupplyResult r = repositoryService.checkStock(new ArrayList<TbCart>() {{
                add(cart);
            }});
            if (r.getStatus().equals(ReturnStatus.NOT_ENOUGH_QUANTITY.getStatus())) {
                return r;
            }

            tbCartMapper.insertSelective(cart);
        } else {
            surplus = carts.get(0).getProductQuality() + count;
            if (surplus > 0) {
                //判断库存是否充足
                //判断count是不是为
                TbCart cart = new TbCart();
                cart.setId(carts.get(0).getId());
                cart.setProductQuality(carts.get(0).getProductQuality() + count);
                cart.setProductId(productId);
                cart.setSupplyOrgId(product.getSupplyOrgId());
                cart.setProductColor(model);
                SupplyResult r = repositoryService.checkStock(new ArrayList<TbCart>() {{
                    add(cart);
                }});
                if (r.getStatus().equals(ReturnStatus.NOT_ENOUGH_QUANTITY.getStatus())) {
                    return r;
                }

                tbCartMapper.updateByPrimaryKeySelective(cart);
            }
        }
        return SupplyResult.build(ReturnStatus.SUCCESS.getStatus(), ReturnMsg.SUCCESS.getMsg(),surplus);
    }

    /**
     * 通过订购方id 找到 购物车实体
     *
     * @param purchaseId
     * @return
     */
    @Override
    @Transactional
    public List<HashMap<String, Object>> generCarts(Integer purchaseId) {
        //进入购物车界面更新 价格
        tbCartMapper.updateByPurchaseId(purchaseId);
        //除去下架的商品
        return tbCartMapper.selectByPurchaseId(purchaseId, CartStatus.NORMAL.getStatus());
    }


    /**
     * 清除购物车 如果carts 只有一个，则是删除一个
     *
     * @param purchaser_id 订货方 id
     * @param productids   购物车商品list id
     */
    @Override
    @Transactional
    public SupplyResult clearCart(Integer purchaser_id, List<Integer> productids, Byte flag) {
        TbCartExample example = new TbCartExample();
        example.createCriteria().andPurchaserIdEqualTo(purchaser_id).andProductIdIn(productids).andStatusEqualTo(CartStatus.NORMAL.getStatus());
        TbCart cart = new TbCart();
        cart.setStatus(flag);
        tbCartMapper.updateByExampleSelective(cart, example);
        return SupplyResult.build(ReturnStatus.SUCCESS.getStatus(), ReturnMsg.SUCCESS.getMsg());
    }

    @Override
    public List<TbCart> selectCartByExample(TbCartExample example) {
        return tbCartMapper.selectByExample(example);
    }

    @Override
    public Integer obtainCartCount(Integer purchaserId) {
        TbCartExample example = new TbCartExample();
        example.createCriteria().andPurchaserIdEqualTo(purchaserId)
                .andStatusEqualTo(CartStatus.NORMAL.getStatus());
        List<TbCart> carts = tbCartMapper.selectByExample(example);
        Integer count = 0;
        for (int i = 0; i < carts.size(); i++) {
            count += carts.get(i).getProductQuality();
        }
        return count;
    }

    /**
     * 通过商品id、数量返回购物车list
     *
     * @param ids
     * @param nums
     * @return
     */
    @Override
    public List<TbCart> generateCart(List<Integer> ids, List<Integer> nums) {

        List<TbCart> cartList = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            TbCart cart = new TbCart();
            TbProduct product = tbProductMapper.selectByPrimaryKey(ids.get(i));
            cart.setProductId(ids.get(i));
            cart.setProductPrice(product.getPrice());
            cart.setProductQuality(nums.get(i));
            cart.setSupplyOrgId(product.getSupplyOrgId());
            cartList.add(cart);
        }
        return cartList;
    }

    /**
     * 通过商品id、数量返回商品表单对象
     *
     * @param ids
     * @param numbers
     * @return
     */
    @Override
    @Transactional
    public ProductFormDto generateProductForm(List<Integer> ids, List<Integer> numbers) {

        List<TbCart> cartList = new ArrayList<>();
        ProductFormDto productFormDto = new ProductFormDto();
        int totalProductNumber = 0;
        BigDecimal totalProductPrice = BigDecimal.ZERO;
        BigDecimal totalProductPurchasePrice = BigDecimal.ZERO;
        for (int i = 0; i < ids.size(); i++) {
            TbCart cart = new TbCart();
            TbProduct product = tbProductMapper.selectByPrimaryKey(ids.get(i));
            cart.setProductId(ids.get(i));
            cart.setProductPrice(product.getPrice());
            cart.setProductQuality(numbers.get(i));
            cart.setSupplyOrgId(product.getSupplyOrgId());
            cartList.add(cart);
            totalProductNumber += numbers.get(i);
            totalProductPrice = totalProductPrice.add(product.getPrice().multiply(new BigDecimal(numbers.get(i))));
            totalProductPurchasePrice = totalProductPurchasePrice.add(product.getPurchasePrice().multiply(new BigDecimal(numbers.get(i))));
        }
        productFormDto.setCartList(cartList);
        productFormDto.setTotalProductNumber(totalProductNumber);
        //totalProductPrice平台跟商户之间的价格
        productFormDto.setTotalProductPrice(totalProductPrice);
        //totalProductPurchasePrice平台跟行社之间的价格
        productFormDto.setTotalProductPurchasePrice(totalProductPurchasePrice);
        return productFormDto;

    }

    @Override
    @Transactional
    public SupplyResult modifyCart(Integer productid, Integer count, Integer uid) {
        //1.判断是否下架
        TbProductExample e = new TbProductExample();
        e.createCriteria().andIdEqualTo(productid);
        List<TbProduct> products = tbProductMapper.selectByExample(e);
        if (products.size() > 0 && products.get(0).getProductStatus().equals(ProductStatus.OFF.getStatus())) {
            return SupplyResult.build(ReturnStatus.PRODUCT_OFF.getStatus(), ReturnMsg.PRODUCT_OFF.getMsg());
        }
        TbProduct product = products.get(0);
        //2.根据 purchaseid 判断是否有相同产品
        TbCartExample example = new TbCartExample();
        example.createCriteria().andPurchaserIdEqualTo(uid).andProductIdEqualTo(productid).andStatusEqualTo(CartStatus.NORMAL.getStatus());
        List<TbCart> carts = tbCartMapper.selectByExample(example);
        if (count > 0) {
            //判断库存是否充足
            TbCart cart = new TbCart();
            cart.setId(carts.get(0).getId());
            cart.setProductQuality(count);
            cart.setProductId(productid);
            cart.setSupplyOrgId(product.getSupplyOrgId());
            tbCartMapper.updateByPrimaryKeySelective(cart);
            SupplyResult r = repositoryService.checkStock(new ArrayList<TbCart>() {{
                add(cart);
            }});
            if (r.getStatus().equals(ReturnStatus.NOT_ENOUGH_QUANTITY.getStatus())) {
                return r;
            }
        }
        return SupplyResult.build(ReturnStatus.SUCCESS.getStatus(), ReturnMsg.SUCCESS.getMsg());
    }

    /**
     * 下单时候判断商品 是否满足 起购数量
     *
     * @param ids
     * @return
     */
    @Override
    public SupplyResult productnumOverLimit(String ids) {

        if(ids.equals("") || ids == null){
            return SupplyResult.build(300,"id不能为null");
        }
        String[] catids = ids.split(",");
        for (int i = 0; i < catids.length; i++) {
            TbCart tbCart = tbCartMapper.selectByPrimaryKey(catids[i]);
            TbProduct product = tbProductMapper.selectByPrimaryKey(tbCart.getProductId());
            if(tbCart.getProductQuality() < product.getProductLimit()){
                return SupplyResult.build(300,"商品："+product.getName()+" 购买数量不能小与限购数量");
            }
        }
        return SupplyResult.ok();
    }
}
