package com.zjjzfy.mgt.controller;

import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.order.service.PurchaseIndexService;
import com.zjjzfy.order.service.TbAdvertisementService;
import com.zjjzfy.pojo.*;
import com.zjjzfy.pojo.dto.ProductDto;
import com.zjjzfy.product.service.impl.CategoryService;
import com.zjjzfy.product.service.impl.EventsService;
import com.zjjzfy.product.service.impl.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/mgtBack")
@Slf4j
@CrossOrigin
public class PurchaseIndexController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TbAdvertisementService tbAdvertisementService;
    @Autowired
    private PurchaseIndexService purchaseIndexService;
    @Autowired
    private ProductService productService;
    @Autowired
    private EventsService eventsService;


    //    @Value("${ads.url}")
//    private String adsUrl;
    @Value("${spring.pcImgAds}")
    private String pcImgAds;
    @Value("${spring.pcImgPath}")
    private String pcImgPath;
    //所有的最大级商品专题
    @RequestMapping("/doGetCategory")
    //Model model
    public List<TbCategory> doGetCategory() {
        List<TbCategory> categoryList = categoryService.getCategoryList(0,0);
        return categoryList;
    }

    @RequestMapping("/getIndexEdit")
    @ResponseBody
    /**
     * //getAdsByPositionExpire//Dto
     */
    public Map<String, Object> getIndexEdit() {
        Map<String,Object> map1 = new HashMap<>();
        //所有商品
        List<ProductDto> product = productService.selectProductAndStock(null, null, null, null,null);
        //4位置:分类(新品上架)ctgr_class=1(热卖推荐)ctgr_class=2
        //->doAllCategoryEdit
        //ByIndex->status in (0,2)
        List<TbCategory> categoryList = categoryService.getCategoryListSpByIndex(0,null);
        categoryList.addAll(categoryService.getCategoryListTitleByIndex(0,0));
        //1位置:图片广告
        List<TbAdvertisementDto> tbAdvertisements=getAdsByPositionExpire(1, null,"1",1);
        //2位置:文字广告:公告
        List<TbAdvertisementDto> textAdvertisements=getAdsByPositionExpire(1, null,"0",2);
        //3位置:图片广告:超值组合,限时特卖doAllAdvRecommendEdit
        List<TbAdvertisementDto> textAdvertisements3=getAdsByPositionExpire(4, null,"1",3);
        //2位置和4位置:文字接口 advClassification =2
        List<TbAdvertisementDto> textInterface=getAdsByPositionExpire(4, null,"2",null);
        //2位置和4位置
        map1.put("textItfc", textInterface);
        //3位置
        map1.put("textads3", textAdvertisements3);
        //2位置
        map1.put("textads", textAdvertisements);
        //1位置
        map1.put("ads", tbAdvertisements);
        //4位置
        map1.put("categoryList", categoryList);
        //所有商品
        map1.put("product",product);
        //购物车物品数量
        map1.put("count", 0);
        map1.put("pcImgPath",pcImgPath);
        //活动专区
//        map1.put("eventsList", eventsList);
        //4位置 分类
//        List<TbCategory> categoryList = categoryService.getCategoryListByIndex(0,null);
        //4位置 活动
//        List<TbEvents> eventsList = eventsService.getEventsList(null,null,4);
        return map1;
    }
    /**
     * 轮播图的新增
     * @return
     */
    @RequestMapping("/doLoopImgAdd")
    @ResponseBody
    public SupplyResult doCategoryAdd(MultipartFile file) {
        String base64Img = null;
        String fileName = file.getOriginalFilename();
        BASE64Encoder base64Encoder = new BASE64Encoder();
        try {
            base64Img = base64Encoder.encode(file.getBytes()).replaceAll("[\\s*\t\n\r]", "");
            ;
        } catch (IOException e) {
            return SupplyResult.build(300, "图片为空");
        }
        SupplyResult result = purchaseIndexService.uploadImg(base64Img, fileName);
        return result;
    }
    @RequestMapping("/doAdvAdd")
    @ResponseBody
    public SupplyResult addAdvertisement(TbAdvertisement ad) {
        return purchaseIndexService.addAdvertisement(ad);
    }
    //所有图片的新增
    @RequestMapping("/doAllAdvEdit")
    @ResponseBody
    public SupplyResult doAllAdvEdit(@RequestBody List<TbAdvertisement> list) {
        //1图片广告
        List<TbAdvertisement> tbAdvertisements=getAdsByPosition(1, null,"1",1);
        return tbAdvertisementService.doAllTbAdvertisementEdit(list,tbAdvertisements,1, "1","1",1);
    }



    //--文字广告和公告TbAdvertisement
    //=============================
    @RequestMapping("/doTextAdvAdd")
    @ResponseBody
    public SupplyResult doTextAdvAdd(TbAdvertisement ta) {
        return tbAdvertisementService.doTbAdvertisementAdd(ta);
    }
    @RequestMapping("/doTextAdvEdit")
    @ResponseBody
    public SupplyResult doTextAdvEdit(TbAdvertisement ta) {
        return tbAdvertisementService.doTbAdvertisementEdit(ta);
    }

    /**
     * rank
     * @param  list
     */
    @RequestMapping("/doAllTextAdvEdit")
    @ResponseBody
    public void doAllTextAdvEdit(@RequestBody List<TbAdvertisement> list) {
        //0文字广告
        List<TbAdvertisement> tbAdvertisements=getAdsByPosition(1, null,"0",2);
        tbAdvertisementService.doAllTbAdvertisementEdit(list,tbAdvertisements,1, "1","0",2);
    }
    @RequestMapping("/doAllTextAdvDlt")
    @ResponseBody
    /*
    伪删除
     */
    public SupplyResult doAllTextAdvDlt(Integer id) {
        return tbAdvertisementService.doTbAdvertisementDlt(id);
    }
    //--超值组合限时特卖TbAdvertisement(3位置的两图片)
    //=============================
    @RequestMapping("/doAllAdvRecommendEdit")
    @ResponseBody
    public SupplyResult doAllAdvRecommendEdit(@RequestBody List<TbAdvertisement> list) {
        //图片广告:超值组合,限时特卖
        List<TbAdvertisement> textAdvertisements3=getAdsByPosition(4, null,"1",3);
        return tbAdvertisementService.doAllTextAdv3Edit(list,textAdvertisements3,1, "1","1",3);
    }
    //--接口:更多TbAdvertisement
    //=============================
    @RequestMapping("/doAllAdvMoreEdit")
    @ResponseBody
    public SupplyResult doAllAdvMoreEdit(@RequestBody List<TbAdvertisement> list) {
        //更多
        List<TbAdvertisement> textInterface=getAdsByPosition(4, null,"0",null);
        return tbAdvertisementService.doAllAdvMoreEdit(list,textInterface,4, "1","0",null);
    }
    //--活动专区events
    //=============================
    @RequestMapping("/doAllEventsEdit")
    @ResponseBody
    public SupplyResult doAllEventsEdit(@RequestBody List<TbEvents> te) {
        //4位置 活动
        List<TbEvents> eventsList = eventsService.getEventsList(null,null,4);
        return eventsService.doAllEventsEdit(te,eventsList,0,0,4);
    }
    //--商品专题Category
    //=============================
    /**
     * 商品专题的新增
     * @return
     */
    @RequestMapping("/doCategoryAdd")
    @ResponseBody
    public int doCategoryAdd(TbCategory tc) {
        return categoryService.insert(tc.getCategoryName(),tc.getParentId(),tc.getIsEnd());
    }
    /**
     * 商品专题的修改
     * @return
     */
    @RequestMapping("/doCategoryEdit")
    @ResponseBody
    public SupplyResult doCategoryEdit(TbCategory tc) {
        return categoryService.doCategoryEdit(tc);
    }
    /**
     * 商品专题的修改(rank)
     * @return
     */
    @RequestMapping("/doAllCategoryEdit")
    @ResponseBody
    public SupplyResult doAllCategoryEdit(@RequestBody  List<TbCategory>  list) {
        //4位置 分类
//        List<TbCategory> categoryList = categoryService.getCategoryListByIndex(0,null);
        List<TbCategory> categoryList = categoryService.getCategoryListSpByIndex(0,null);
        categoryList.addAll(categoryService.getCategoryListTitleByIndex(0,0));
        return categoryService.doAllCategoryEdit(list,categoryList,0);
    }
    /**
     * 商品专题的删除
     * 1:自有的不能删除,只能删除用户新增的.或伪删除
     * 2:把此专题下对应商品"删除";
     * @return
     */
    @RequestMapping("/doCategoryDlt")
    @ResponseBody
    public SupplyResult doCategoryDlt(Integer id) {
        return categoryService.doCategoryDlt(id);
    }


    //--商品专题的商品Product
    //=============================
    /**
     *  商品专题下所有商品/purchase/selectProdcut
     * "type": type,==${category.id!}
     * "pageIndex": 1,
     * "pageSize": 1000
     *
     * 所有商品专题下所有商品List<ProductDto> news = productService.selectProductAndStock(null, null, null, null);
     */
    /**
     * 商品的新增
     * @return
     */
    @RequestMapping("doProductAdd")
    @ResponseBody
    //ProductDto pd
    //为什么会有一个代表"最新"的字段
    public SupplyResult doProductAdd(Integer pid,Integer cid) {
        return productService.doProductAdd(pid,cid);
    }
    /**
     * 商品的修改(rank)
     * @return
     */
    @RequestMapping("doAllProductEdit")
    @ResponseBody
    public void doAllProductEdit(List<TbProduct> list) {
        productService.doAllProductEdit(list);
    }
    /**
     * 商品的删除
     * @return
     */
    @RequestMapping("doProductDlt")
    @ResponseBody
    //从专题中删除那么它就会不属于任何专题
    public SupplyResult doProductDlt(Integer id) {
        return productService.doProductDlt(id);
    }

    /*上传图片*/
    @RequestMapping("/uploadImgs")
//    @RequestMapping("doLoopImgAdd")
    @ResponseBody
    public SupplyResult uploadImg(MultipartFile file) {
        if(file.isEmpty()){
            return SupplyResult.ok();
        }
        SupplyResult result = null;
        try {
            BASE64Encoder base64Encoder =new BASE64Encoder();
            /*String base64EncoderImg = file.getOriginalFilename()+","+ base64Encoder.encode(file.getBytes());*/
            String base64Img = base64Encoder.encode(file.getBytes()).replaceAll("[\\s*\t\n\r]", "");
            result = purchaseIndexService.uploadImgPc(pcImgAds,base64Img,file.getOriginalFilename());
            System.err.println("result.toString():"+result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    public List<TbAdvertisement> getAdsByPosition(Integer type, String status,String advClassification,Integer advPosition){
        return tbAdvertisementService.getAdsByPositionByIndex(type, status,advClassification,advPosition);
    }
    public List<TbAdvertisementDto> getAdsByPositionExpire(Integer type, String status,String advClassification,Integer advPosition){
        return tbAdvertisementService.getAdsByPositionByIndexExpire(type, status,advClassification,advPosition);
    }
}
