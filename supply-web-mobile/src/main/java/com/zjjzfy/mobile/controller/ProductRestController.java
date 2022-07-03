package com.zjjzfy.mobile.controller;

import com.github.pagehelper.PageInfo;
import com.zjjzfy.common.config.ProductCheckStatus;
import com.zjjzfy.common.config.ProductStatus;
import com.zjjzfy.common.config.ReturnMsg;
import com.zjjzfy.common.config.ReturnStatus;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.pojo.TbCategory;
import com.zjjzfy.pojo.TbOrgInfo;
import com.zjjzfy.pojo.TbProduct;
import com.zjjzfy.pojo.TbUserInfo;
import com.zjjzfy.pojo.dto.ProductDto;
import com.zjjzfy.product.service.impl.CategoryService;
import com.zjjzfy.product.service.impl.ProductService;
import com.zjjzfy.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import java.util.ArrayList;

/**
 * @author: hsmz
 * @date: 2019/3/26 下午2:20
 */
@RequestMapping("/deliver")
@RestController
public class ProductRestController {

    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/productEditSave")
    public SupplyResult commodityEdit(TbProduct product) {//TODO

        try {
            productService.updateProduct(product);
        } catch (Exception e) {
            e.printStackTrace();
            return SupplyResult.build(ReturnStatus.FAILURE.getStatus(), ReturnMsg.FAILURE.getMsg());
        }

        return SupplyResult.build(ReturnStatus.SUCCESS.getStatus(), ReturnMsg.SUCCESS.getMsg());
    }

    @RequestMapping("selectProductBySupplyOrgId")
    public PageInfo<ProductDto> selectProductBySupplyOrgId(String content, Integer pageNo, Integer pageSize) {
        Integer orgId = userService.getCurrentTbUserInfo().getOrgid();
        return productService.selectProductDtoBySupplyOrgId(orgId, content, pageNo, pageSize);
    }

    /*商品入库*/
    @RequestMapping("addProductRepository")
    public SupplyResult addProductRepository(@RequestParam("ids[]") ArrayList<Integer> ids, @RequestParam("nums[]") ArrayList<Integer> nums) {
        Integer orgId = userService.getCurrentTbUserInfo().getOrgid();
        try {
            return productService.addProductRepository(orgId, ids, nums);
        } catch (Exception e) {
            e.printStackTrace();
            return SupplyResult.build(2, e.getMessage());
        }
    }

    /**
     * 获取商品列表
     *
     * @param productName 商品名称
     * @param status      1 上架/ 2 下架
     * @param page        第几页
     * @param size        一页显示条数
     * @return
     */
    @RequestMapping("/productListByOrg")
    public SupplyResult productListByOrg(String productName, Integer status, Integer categoryId, Integer page, Integer size) {
        TbOrgInfo org = userService.getOrg();
        List<Byte> statusList = new ArrayList<>();
        PageInfo<TbProduct> products = null;
        if (status != null) {
            if (status == 1) {
                //在售
                statusList.add(ProductCheckStatus.CHECK_PASS.getStatus());
                products = productService.selectProduct(org.getId(), productName, ProductStatus.ON.getStatus(), statusList, categoryId, false, page, size);
            } else if (status == 2) {
                //其他
                products = productService.selectProduct(org.getId(), productName, null, statusList, categoryId, true, page, size);
            }
        }
        return SupplyResult.ok(products);
    }

    @RequestMapping("/updateProductStatus")
    public SupplyResult updateProductStatus(Integer id) {
        SupplyResult result = productService.updateProductStatus(id);
        return result;
    }

    @RequestMapping("/getCategoryChlid")
    public SupplyResult getCategoryChlid(Integer categoryId) {
        List<TbCategory> categoryList = categoryService.getCategoryList(categoryId,0);
        return SupplyResult.ok(categoryList);
    }

    /**
     * 上传图片
     *
     * @param base64Img
     * @param fileName
     * @return
     */
    @RequestMapping("/uploadImg")
    public SupplyResult uploadImg(String base64Img, String fileName) {
        SupplyResult result = productService.uploadImg(base64Img, fileName);
        System.out.println(result);
        return result;
    }

    /**
     * 商品新增
     *
     * @param product
     * @param imageList  展示图
     * @param imgDetails 详情图
     * @return
     */
    @RequestMapping("/productAdd")
    public SupplyResult productAdd(TbProduct product, String imageList, String imgDetails) {
        SupplyResult result = checkProduct(product, imageList, imgDetails);
        if (result.getStatus() != 200) {
            return result;
        }
        TbUserInfo userInfo = userService.getCurrentTbUserInfo();
        product.setSupplyOrgId(userInfo.getOrgid());
        result = productService.insert(product, imageList, imgDetails);

        return result;
    }

    /**
     * 商品修改
     *
     * @param product
     * @param imageList  展示图
     * @param imgDetails 详情图
     * @return
     */
    @RequestMapping("/productUpdate")
    public SupplyResult productUpdate(TbProduct product, String imageList, String imgDetails) {
        SupplyResult result = checkProduct(product, imageList, imgDetails);
        if (result.getStatus() != 200) {
            return result;
        }
        if (product.getId() == null) {
            return SupplyResult.build(300, "商品不明确");
        }
        TbUserInfo userInfo = userService.getCurrentTbUserInfo();
        product.setSupplyOrgId(userInfo.getOrgid());
        result = productService.modify(product, imageList, imgDetails);

        return result;
    }

    public SupplyResult checkProduct(TbProduct product, String imageList, String imgs) {
        if (product == null) {
            return SupplyResult.build(300, "商品为空");
        }
        if (product.getName() == null || product.getName().equals("")) {
            return SupplyResult.build(300, "商品名称为空");
        }
        if (product.getImage() == null || product.getImage().equals("")) {
            return SupplyResult.build(300, "商品缩略图为空");
        }
        if (imageList == null || imageList.equals("")) {
            return SupplyResult.build(300, "商品展示图图片为空");
        }
        if (imgs == null || imgs.equals("")) {
            return SupplyResult.build(300, "商品详情图图片为空");
        }
        if (product.getCategoryId() == null || product.getCategoryId().equals("")) {
            return SupplyResult.build(300, "商品分类为空");
        }
        if (product.getBarcode() == null || product.getBarcode().equals("")) {
            return SupplyResult.build(300, "商品编号为空");
        }
        if (product.getBrand() == null || product.getBrand().equals("")) {
            return SupplyResult.build(300, "商品品牌为空");
        }
        if (product.getUnit() == null || product.getUnit().equals("")) {
            return SupplyResult.build(300, "商品单位为空");
        }
        if (product.getPrice() == null || product.getPrice().equals("")) {
            return SupplyResult.build(300, "商品订货价为空");
        }
        if (product.getReferencePrice() == null || product.getReferencePrice().equals("")) {
            return SupplyResult.build(300, "商品市场参考价为空");
        }
        /*if (product.getSettlePrice() == null || product.getSettlePrice().equals("")) {
            return SupplyResult.build(300, "商品清算价为空");
        }*/
        /*if (product.getRemark() == null || product.getRemark().equals("")) {
            return SupplyResult.build(300, "商品说明为空");
        }*/
        return SupplyResult.ok();
    }

    /**
     * 获取二级分类
     *
     * @return
     */
    @RequestMapping("/getTwoCategory")
    public SupplyResult getTwoCategory(Integer categoryId) {
        List<TbCategory> categoryList = categoryService.getCategoryList(categoryId,0);
        return SupplyResult.ok(categoryList);
    }

    /**
     * 增加商品点击
     * @param id
     */
    @RequestMapping("/addProductClick")
    public void addProductClick(Integer id){
        productService.addClickRate(id);
    };
}