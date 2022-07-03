package com.zjjzfy.mgt.controller;

import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.pojo.TbCategory;
import com.zjjzfy.pojo.TbProduct;
import com.zjjzfy.pojo.TbUserInfo;
import com.zjjzfy.product.service.impl.CategoryService;
import com.zjjzfy.product.service.impl.ProductService;
import com.zjjzfy.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @author zyx
 * @date 2019/11/29 下午1:03
 */
@RestController
@RequestMapping("/mgt")
public class ProductRestController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Value("${spring.pcImgAddr}")
    private String pcImgAddr;

    /*商品分类菜单查询*/
    @GetMapping("/queryShopClassInit")
    public SupplyResult queryShopClassInit(){
        HashMap<String,Object> map = new HashMap<>();
        List<TbCategory> class1 =  categoryService.queryOneClass();
        List<TbCategory> categories= null;
        if(class1 != null){
            categories= categoryService.queryTwoClass(class1.get(0).getId());
        }
        map.put("oneClass",class1);
        map.put("twoClass",categories);
        return SupplyResult.ok(map);
    }

    @GetMapping("/queryClassTwo")
    public SupplyResult queryClassTwo(Integer id){
        List<TbCategory> categoryList = categoryService.queryTwoClass(id);
        return SupplyResult.ok(categoryList);
    }


    /*上传图片*/
    @RequestMapping("/uploadImgs")
    public SupplyResult uploadImg(MultipartFile file) {
        if(file.isEmpty()){
            return SupplyResult.ok();
        }
        SupplyResult result = null;
        try {
            BASE64Encoder base64Encoder =new BASE64Encoder();
            /*String base64EncoderImg = file.getOriginalFilename()+","+ base64Encoder.encode(file.getBytes());*/
            String base64Img = base64Encoder.encode(file.getBytes()).replaceAll("[\\s*\t\n\r]", "");
            result = productService.uploadImgPc(pcImgAddr,base64Img,file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

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
       /* if (product.getBarcode() == null || product.getBarcode().equals("")) {
            return SupplyResult.build(300, "商品编号为空");
        }*/
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

    @GetMapping("/countShopClickRate")
    public SupplyResult countShopClickRate(){
        return  productService.countShopClickRate();
    }
}
