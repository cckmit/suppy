package com.zjjzfy.mgt.controller;

import com.github.pagehelper.PageInfo;
import com.zjjzfy.common.config.ProductCheckStatus;
import com.zjjzfy.common.config.ReturnMsg;
import com.zjjzfy.common.config.ReturnStatus;
import com.zjjzfy.common.entity.LayuiData;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.pojo.TbCategory;
import com.zjjzfy.pojo.TbProduct;
import com.zjjzfy.pojo.TbProductImg;
import com.zjjzfy.pojo.dto.ProductDto;
import com.zjjzfy.product.service.impl.CategoryService;
import com.zjjzfy.product.service.impl.ProductImgService;
import com.zjjzfy.product.service.impl.ProductService;
import com.zjjzfy.repository.service.RepositoryService;
import javafx.print.Printer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/mgt")
public class ProductCheckRestController {

    @Value("${spring.imgAddr}")
    private String imgAddr;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductImgService productImgService;
    @Autowired
    private RepositoryService repositoryService;

    /**
     * 获取所有未审核的商品
     *
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/getUncheckProduct")
    public Object getUncheckProduct(Integer page, Integer limit,String name) {
        List<Byte> statusList = new ArrayList<>();
        statusList.add(ProductCheckStatus.UNCHECK.getStatus());
        PageInfo<TbProduct> pages = productService.selectProduct(null, name, null, statusList, null, false, page, limit);
        return LayuiData.ok(pages.getList(), pages.getTotal());
    }

    /**
     * 修改sort_id
     */
    @RequestMapping("/setSortId")
    public void setSortId(@RequestParam(value = "id", required = true) Integer id,
                          @RequestParam(value = "sortId", required = true) Integer sortId) {
        productService.setSortId(id, sortId);
        System.out.println("平台方修改id[" + id + "]已审核商品的排序编号为[" + sortId + "]");
    }

    /**
     * 获取所有已审核的商品
     *
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/getCheckProduct")
    public Object getCheckProduct(Integer page, Integer limit,String name) {
        List<Byte> statusList = new ArrayList<>();
        statusList.add(ProductCheckStatus.CHECK_PASS.getStatus());
        statusList.add(ProductCheckStatus.CHECK_NOT_PASS.getStatus());
        PageInfo<TbProduct> pages = productService.selectProduct(null, name, null, statusList, null, false, page, limit);
        return LayuiData.ok(pages.getList(), pages.getTotal());
    }

    /**
     * 获取已拒绝的商品
     * @param page
     * @param limit
     * @param name
     * @return
     */
    @RequestMapping("/getRefuseTable")
    public Object getRefuseTable(Integer page, Integer limit,String name) {
        List<Byte> statusList = new ArrayList<>();
        statusList.add(ProductCheckStatus.REFUSE_PASS.getStatus());
        PageInfo<TbProduct> pages = productService.selectProduct(null, name, null, statusList, null, false, page, limit);
        return LayuiData.ok(pages.getList(), pages.getTotal());
    }
    /**
     * 获取商品详情
     *
     * @param id
     * @return
     */
    @RequestMapping("/getProductById")
    public Object getProductById(Integer id) {
        if (id == null || id <= 0) {
            return SupplyResult.build(300, "商品不明确");
        }
        TbProduct product = (TbProduct) productService.selectByExampleOrId(null, id).getData();
        List<TbProductImg> imgs = (List<TbProductImg>) productImgService.selectProductImg(id, null).getData();
        product.setImgs(imgs);
        product.setImgAddr(imgAddr);
        return product;
    }

    /**
     * 商品审核
     *
     * @param id
     * @param purchasePrice
     * @param checkValue    1 审核通过 2 审核不通过
     * @param isDiscount    0 非特惠   1 特惠
     * @return
     */
    @RequestMapping("/checkProduct")
    public Object checkProduct(Integer id, BigDecimal purchasePrice, Integer checkValue,
                               String isDiscount, String newProduct, Integer sales,
                               BigDecimal exchangePrice,Integer exchangeType,BigDecimal exchangeCash,String exchangeRemark,String productStatus,Integer purchaseOrnot,String model,Integer productPrefecture) {
        SupplyResult result = productService.checkProduct(id, purchasePrice, checkValue,
                isDiscount, newProduct, sales, exchangePrice,exchangeType,exchangeCash,exchangeRemark,productStatus,purchaseOrnot,model,productPrefecture);
        return result;
    }

    /**
     * 审核完商品修改
     * @param id
     * @param purchasePrice
     * @param checkValue
     * @param isDiscount
     * @param newProduct
     * @param sales
     * @param exchangePrice
     * @param exchangeType
     * @param exchangeCash
     * @return
     */
    @RequestMapping("/checkedProduct")
    public Object checkedProduct(Integer id, BigDecimal purchasePrice, Integer checkValue,
                               String isDiscount, String newProduct, Integer sales,
                               BigDecimal exchangePrice,Integer exchangeType,BigDecimal exchangeCash,String exchangeRemark,String productStatus,Integer purchaseOrnot,String model,Integer productPrefecture) {
        SupplyResult result = productService.checkedProduct(id, purchasePrice,
                isDiscount, newProduct, sales, exchangePrice,exchangeType,exchangeCash,exchangeRemark,productStatus,purchaseOrnot,model,productPrefecture);
        return result;
    }

    @RequestMapping("statisticsCommoditySales")
    public List<ProductDto> statisticsCommoditySales(Integer supplierId, Integer pId, Date startTime, Date endTime) {
        if (supplierId != null && supplierId == 0) {
            supplierId = null;
        }

        if (pId != null && pId == 0) {
            pId = null;
        }
        return productService.statisticsCommoditySales(supplierId, pId, startTime, endTime);
    }

    @RequestMapping("selectProductBySupplyOrgId")
    public List<TbProduct> selectProductBySupplyOrgId(Integer supplyOrgId) {
        return productService.selectProductBySupplyOrgId(supplyOrgId);
    }

    /**
     * 商品审核表 动态修改保存
     * @param product
     * @return
     */
    @RequestMapping("/productEditSave")
    public SupplyResult commodityEdit(TbProduct product) {
        try {
            product.setUpdateDate(new Date());
            productService.updateProduct(product);
        } catch (Exception e) {
            e.printStackTrace();
            return SupplyResult.build(ReturnStatus.FAILURE.getStatus(), ReturnMsg.FAILURE.getMsg());
        }
        return SupplyResult.build(ReturnStatus.SUCCESS.getStatus(), ReturnMsg.SUCCESS.getMsg());
    }

    @GetMapping("/queryRepository")
    public LayuiData queryRepository(Integer supplyOrgId,String brand,Integer page,Integer limit,String goodsName){
       return  repositoryService.queryRepository(supplyOrgId,brand,page,limit,goodsName);
    }

    @GetMapping("/selectRepository")
    public SupplyResult selectRepository(){
        return repositoryService.selectRepositry();
    }

    @GetMapping("/changeRepositoryNum")
    public Boolean changeRepositoryNum(Integer supplyOrgId,Integer proId,Integer num){
        return repositoryService.changeRepository(supplyOrgId,proId,num);
    }
}