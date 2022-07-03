package com.zjjzfy.product.service.impl.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjjzfy.common.config.*;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.common.utils.Base64ToImageUtil;
import com.zjjzfy.common.utils.IDUtils;
import com.zjjzfy.interfaces.*;
import com.zjjzfy.pojo.*;
import com.zjjzfy.pojo.dto.ProductDto;
import com.zjjzfy.product.service.impl.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private TbProductMapper tbProductMapper;
    @Autowired
    private TbProductImgMapper tbProductImgMapper;
    @Autowired
    private TbRepositoryMapper tbRepositoryMapper;
    @Autowired
    private TbRepositoryRecordMapper tbRepositoryRecordMapper;
    @Autowired
    private TbRepositoryRecordDetailMapper tbRepositoryRecordDetailMapper;
    @Autowired
    private SortIdMapper sortIdMapper;
    private TbCategoryMapper categoryMapper;

    /**
     * 新增商品
     *
     * @param product
     * @param imageList 展示图
     * @param imgs      详情图
     * @return
     */
    @Override
    @Transactional
    public SupplyResult insert(TbProduct product, String imageList, String imgs) {
        Date currentDate = new Date();
        product.setSettlePrice(product.getPrice());
        product.setCreateDate(currentDate);
        product.setUpdateDate(currentDate);
        product.setProductLabel(new Byte("0"));
        product.setClickrate("0-0-0-0-0-0-0");
        //新增商品需要审核
        product.setCheckStatus(ProductCheckStatus.UNCHECK.getStatus());
        tbProductMapper.insertUseGeneratedKeys(product);

        //插入图片
        insertProductImg(imageList, imgs, product.getId());

        TbRepository repository = new TbRepository();
        repository.setSupplyOrgId(product.getSupplyOrgId());
        repository.setProductId(product.getId());
        repository.setQuantity(0);
        repository.setQuantityTotal(0);
        tbRepositoryMapper.insert(repository);

        return SupplyResult.ok();
    }

    /**
     * 修改商品
     *
     * @param product
     * @param imageList 展示图
     * @param imgs      详情图
     * @return
     */
    @Override
    @Transactional
    public SupplyResult modify(TbProduct product, String imageList, String imgs) {
        Date currentDate = new Date();
        product.setSettlePrice(product.getPrice());
        product.setModel("");
        product.setUpdateDate(currentDate);
        product.setCheckStatus(ProductCheckStatus.UNCHECK.getStatus());
        tbProductMapper.updateByPrimaryKeySelective(product);

        TbProductImgExample example = new TbProductImgExample();
        example.createCriteria().andProductIdEqualTo(product.getId());
        tbProductImgMapper.deleteByExample(example);

        //插入图片
        insertProductImg(imageList, imgs, product.getId());
        return SupplyResult.ok();
    }

    @Transactional
    public void insertProductImg(String imageList, String imgs, Integer producdId) {

        TbProductImg productImg = new TbProductImg();
        productImg.setProductId(producdId);
        productImg.setRemark("");

        int index = 1;
        String[] images = imageList.split(",,");
        for (String img : images) {
            //展示图
            if (img == null || img.equals("")) {
                continue;
            }
            productImg.setId(null);
            productImg.setImgPath(img);
            productImg.setOrderNumber(index);
            productImg.setType(ProductImgType.REVEAL.getType());
            tbProductImgMapper.insertSelective(productImg);
            index++;
        }
        String[] images2 = imgs.split(",,");
        index = 1;
        for (String img : images2) {
            //详情图
            if (img == null || img.equals("")) {
                continue;
            }
            productImg.setId(null);
            productImg.setImgPath(img);
            productImg.setOrderNumber(index);
            productImg.setType(ProductImgType.DETAIL.getType());
            tbProductImgMapper.insertSelective(productImg);
            index++;
        }
    }

    /**
     * 商品查询
     *
     * @param supplyOrgId
     * @param productName
     * @param productStatus
     * @param checkStatus
     * @param categoryId
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<TbProduct> selectProduct(Integer supplyOrgId, String productName, Byte productStatus, List<Byte> checkStatus, Integer categoryId, boolean flag, Integer page, Integer size) {
        List<TbProduct> products;
        if (page == null) {
            page = 1;
        }
        if (size == null) {
            size = 10;
        }
        PageHelper.startPage(page, size);

        if (flag) {
            products = tbProductMapper.selectNotONProduct(supplyOrgId, productName, categoryId);
        } else {

            TbProductExample productExample = new TbProductExample();
            TbProductExample.Criteria criteria = productExample.createCriteria();
            if (supplyOrgId != null && supplyOrgId > 0) {
                criteria.andSupplyOrgIdEqualTo(supplyOrgId);
            }
            if (productName != null && !"".equals(productName)) {
                criteria.andNameLike("%" + productName + "%");
            }
            if (productStatus != null) {
                criteria.andProductStatusEqualTo(productStatus);
            }
            if (checkStatus != null && checkStatus.size() > 0) {
                criteria.andCheckStatusIn(checkStatus);
            }
            if (categoryId != null && categoryId >= 0) {
                criteria.andCategoryIdEqualTo(categoryId);
            }
            productExample.setOrderByClause("update_date desc");

            products = tbProductMapper.selectByExample(productExample);
        }

        PageInfo<TbProduct> pageInfo = new PageInfo<>(products);
        return pageInfo;
    }

    @Override
    public int updateProduct(TbProduct product) {
        return tbProductMapper.updateByPrimaryKeySelective(product);
    }

    @Override
    public List<ProductDto> selectProductAndStock(ProductLabelStatus pls, OrderEnum order, String name, Integer categoryId,Integer prefecture) {
        Byte label = null;
        if (pls != null) {
            label = pls.getStatus();
        }

        String o = null;
        if (order != null) {
            o = order.getOrder();
        }
        if(prefecture == null){
            prefecture = 0;
        }

        return tbProductMapper.selectProductAndStock(label, o, name, categoryId,prefecture);
    }

    @Override
    public ProductDto selectProductDetail(Integer proId) {
        return tbProductMapper.selectProductDetail(proId);
    }

    @Override
    public PageInfo<ProductDto> selectProductDtoBySupplyOrgId(Integer supplyOrgId, String content, Integer pageNo,
                                                              Integer pageSize) {
        if (supplyOrgId == null) {
            return null;
        }

        if (pageNo == null) {
            pageNo = 1;
        }

        if (pageSize == null) {
            pageSize = 10;
        }

        PageHelper.startPage(pageNo, pageSize);
        List<ProductDto> products = tbProductMapper.selectProductBySupplyOrgId(supplyOrgId, content);
        return new PageInfo<>(products);
    }

    @Override
    public List<TbProduct> selectProductBySupplyOrgId(Integer supplyOrgId) {
        if (supplyOrgId == null) {
            return null;
        }

        TbProduct product = new TbProduct();
        product.setSupplyOrgId(supplyOrgId);
        return tbProductMapper.select(product);
    }

    /**
     * 行社通过员工id查看商品列表(包过库存)
     *
     * @param supplierId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<ProductDto> selectProductBySupplierId(Integer supplierId, Integer pageNo, Integer pageSize) {

        PageHelper.startPage(pageNo, pageSize);
        List<ProductDto> products = tbProductMapper.selectProductBySupplierId(supplierId);
        return new PageInfo<>(products);
    }

    @Transactional
    @Override
    public SupplyResult addProductRepository(Integer supplyOrgId, ArrayList<Integer> ids, ArrayList<Integer> nums) {
        if (ids == null || nums == null || ids.size() != nums.size()) {
            return SupplyResult.build(1, "入库失败--请选择入库商品和数量");
        }

        Date date = new Date();
        String billno = IDUtils.genOrderId();

        TbRepositoryRecord rr = new TbRepositoryRecord();
        rr.setStatus((byte) 0);
        rr.setCreateDate(date);
        rr.setSupplyOrgId(supplyOrgId);
        rr.setBillno(billno);
        tbRepositoryRecordMapper.insertUseGeneratedKeys(rr);

        if (rr.getId() == null) {
            throw new RuntimeException("入库失败");
        }

        for (int i = 0; i < ids.size(); i++) {
            Integer proId = ids.get(i);
            Integer num = nums.get(i);
            Integer re = tbRepositoryMapper.addOneProductRepository(supplyOrgId, proId, num);
            if (re != 1) {
                throw new RuntimeException("入库失败--商户与商品不匹配");
            }

            TbRepositoryRecordDetail detail = new TbRepositoryRecordDetail();
            detail.setCreateDate(date);
            detail.setProductId(proId);
            detail.setProductQuantity(num);
            detail.setRecordBillno(billno);
            detail.setRecordId(rr.getId());
            int insert = tbRepositoryRecordDetailMapper.insert(detail);
            if (insert != 1) {
                throw new RuntimeException("入库失败");
            }
        }

        return SupplyResult.ok();
    }

    @Override
    public SupplyResult selectByExampleOrId(TbProductExample example, Integer id) {
        if (id == null || id <= 0) {
            return SupplyResult.ok(tbProductMapper.selectByExample(example));
        } else {
            return SupplyResult.ok(tbProductMapper.selectByPrimaryKey(id));
        }
    }

    @Override
    public SupplyResult updateProductStatus(Integer id) {
        if (id == null || id <= 0) {
            return SupplyResult.build(300, "上/下架商品不明确");
        }
        TbProduct product = tbProductMapper.selectByPrimaryKey(id);
        if (product == null) {
            return SupplyResult.build(300, "上/下架商品不存在");
        }
        product.setProductStatus(product.getProductStatus() == ProductStatus.ON.getStatus() ? ProductStatus.OFF.getStatus() : ProductStatus.ON.getStatus());
        tbProductMapper.updateByPrimaryKeySelective(product);
        return SupplyResult.ok();
    }

    @Override
    public SupplyResult uploadImgPc(String pcImgAddr,String base64Img, String fileName) {

        try {
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            if (!path.exists()) {
                path = new File("");
            }
            System.out.println("path:" + path.getAbsolutePath());

            File upload = new File(/*path.getAbsolutePath()*/pcImgAddr+File.separator+ SupplyPubDef.PRODUCT_IMG_PATH);
            if (!upload.exists()) {
                upload.mkdirs();
            }
            String absPath = upload.getAbsolutePath();
            System.out.println(absPath);
            String imgName = UUID.randomUUID().toString().replace("-", "") + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
            //将 base64 生成图片
            boolean flag = Base64ToImageUtil.yzxGenerateImage(base64Img, absPath + File.separator + imgName);
            if (flag) {
                //结果入库
                System.out.println("上传成功！！！");

                return SupplyResult.ok((File.separator + SupplyPubDef.PRODUCT_IMG_PATH + File.separator + imgName).replace("\\", "/"));
            } else {

                System.out.println("上传失败！！！");
                return SupplyResult.build(300, "上传失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return SupplyResult.build(300, "上传异常");

        }
    }
    @Override
    public SupplyResult uploadImg(String base64Img, String fileName) {

        try {
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            if (!path.exists()) {
                path = new File("");
            }
            System.out.println("path:" + path.getAbsolutePath());

            File upload = new File(path.getAbsolutePath(), SupplyPubDef.PRODUCT_IMG_PATH);
            if (!upload.exists()) {
                upload.mkdirs();
            }
            String absPath = upload.getAbsolutePath();
            System.out.println(absPath);
            String imgName = UUID.randomUUID().toString().replace("-", "") + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
            //将 base64 生成图片
            boolean flag = Base64ToImageUtil.yzxGenerateImage(base64Img, absPath + File.separator + imgName);
            if (flag) {
                //结果入库
                System.out.println("上传成功！！！");

                return SupplyResult.ok((File.separator + SupplyPubDef.PRODUCT_IMG_PATH + File.separator + imgName).replace("\\", "/"));
            } else {

                System.out.println("上传失败！！！");
                return SupplyResult.build(300, "上传失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return SupplyResult.build(300, "上传异常");

        }
    }
    /**
     * 商品审核
     *
     * @param id
     * @param purchasePrice
     * @param checkValue
     * @return
     */
    @Override
    public SupplyResult checkProduct(Integer id, BigDecimal purchasePrice, Integer checkValue,
                                     String isDiscount, String newProduct, Integer sales, BigDecimal exchangePrice,Integer exchangeType,BigDecimal exchangeCash,String exchangeRemark,String productStatus,Integer purchaseOrnot,String model,Integer productPrefecture) {
        if (id == null || id <= 0) {
            return SupplyResult.build(300, "商品不明确");
        }
        if (checkValue == null || (checkValue != 1 && checkValue != 2 && checkValue != 3)) {
            return SupplyResult.build(300, "审核结果不明确");
        }
        if (isDiscount == null || isDiscount.equals("")) {
            isDiscount = "0";
        }
        if (newProduct == null || newProduct.equals("")) {
            newProduct = "0";
        }
        if (sales == null || sales < 0) {
            sales = 0;
        }
        TbProduct product = new TbProduct();
        product.setId(id);
        product.setPurchasePrice(purchasePrice);
        product.setIsdiscount(isDiscount);
        product.setNewproduct(newProduct);
        product.setSales(sales);
        product.setModel(model);
        product.setPurchaseOrnot(purchaseOrnot);
        product.setExchangePrice(exchangeType==1?BigDecimal.ZERO:exchangePrice);
        product.setExchangeCash(exchangeType==0?BigDecimal.ZERO:exchangeCash);
        product.setExchangeType(exchangeType);
        product.setExchangeRemark(exchangeRemark);
        product.setUpdateDate(new Date());
        product.setProductPrefecture(productPrefecture);
        product.setProductStatus((productStatus.equals("on")||productStatus.equals("1"))?ProductStatus.ON.getStatus():ProductStatus.OFF.getStatus());

        if (checkValue == 1) {
            product.setCheckStatus(ProductCheckStatus.CHECK_PASS.getStatus());
        } else if(checkValue == 2) {
            product.setCheckStatus(ProductCheckStatus.CHECK_NOT_PASS.getStatus());
        } else{
            product.setCheckStatus(ProductCheckStatus.REFUSE_PASS.getStatus());
        }
        tbProductMapper.updateByPrimaryKeySelective(product);
        return SupplyResult.ok();
    }

    /**
     * 审核完商品 详情-> 修改
     * @param id
     * @param purchasePrice
     * @param
     * @param isDiscount
     * @param newProduct
     * @param sales
     * @param exchangePrice
     * @param exchangeType
     * @param exchangeCash
     * @return
     */
    @Override
    public SupplyResult checkedProduct(Integer id, BigDecimal purchasePrice,
                                     String isDiscount, String newProduct, Integer sales, BigDecimal exchangePrice,Integer exchangeType,BigDecimal exchangeCash,String exchangeRemark,String productStatus,Integer purchaseOrnot,String model,Integer productPrefecture) {
        if (id == null || id <= 0) {
            return SupplyResult.build(300, "商品不明确");
        }
        if (isDiscount == null || isDiscount.equals("")) {
            isDiscount = "0";
        }
        if (newProduct == null || newProduct.equals("")) {
            newProduct = "0";
        }
        if (sales == null || sales < 0) {
            sales = 0;
        }
        TbProduct product = new TbProduct();
        product.setId(id);
        product.setPurchasePrice(purchasePrice);
        product.setIsdiscount(isDiscount);
        product.setNewproduct(newProduct);
        product.setSales(sales);
        product.setPurchaseOrnot(purchaseOrnot);
        product.setModel(model);
        product.setProductPrefecture(productPrefecture);
        product.setExchangePrice(exchangeType==1?BigDecimal.ZERO:exchangePrice);
        product.setExchangeCash(exchangeType==0?BigDecimal.ZERO:exchangeCash);
        product.setExchangeType(exchangeType);
        product.setExchangeRemark(exchangeRemark);
        product.setProductStatus(productStatus.equals("1")?ProductStatus.ON.getStatus():ProductStatus.OFF.getStatus());
        tbProductMapper.updateByPrimaryKeySelective(product);
        return SupplyResult.ok();
    }

    @Override
    public SupplyResult selectOnlineProducts() {
        return SupplyResult.ok(tbProductMapper.selectAll());
    }

    @Override
    public List<ProductDto> statisticsCommoditySales(Integer supplierId, Integer pId, Date startTime, Date endTime) {
        return tbProductMapper.statisticsCommoditySales(supplierId, pId, startTime, endTime);
    }

    @Override
    public PageInfo<ProductDto> selectProduct(Integer pageIndex, Integer pageSize, Integer type) {
        pageIndex = (pageIndex == null ? 1 : pageIndex);
        pageSize = (pageSize == null ? 8 : pageSize);
        //List<TbCategory> ctgrList = categoryMapper.getCategoryList(0,1);
        List<ProductDto> productDtos = null;
        PageHelper.startPage(pageIndex, pageSize);

            if (type == 123) {
                //新品上架
                productDtos = tbProductMapper.selectNewProduct();
            } else if (type == 124) {
                //热卖商品
                productDtos = tbProductMapper.selectBestSeller();
            }else {
                //特惠快购
                //productDtos = tbProductMapper.selectIsDiscountProduct();
                //其他正常分类
                productDtos = tbProductMapper.selectProductByCategory(type);
            }
//                    if(productDtos==null||productDtos.size()==0){
//                        //??
//                        //productDtos = tbProductMapper.selectProductBySupplyOrgId(null, null);
//                    }



//            }


        return new PageInfo<>(productDtos);
    }
    @Override
    public PageInfo<ProductDto> selectProductSp(Integer pageIndex, Integer pageSize, Integer type,Integer id) {
        pageIndex = (pageIndex == null ? 1 : pageIndex);
        pageSize = (pageSize == null ? 8 : pageSize);
        List<ProductDto> productDtos = null;
        PageHelper.startPage(pageIndex, pageSize);

        if (type == 1) {
            //新品上架
            productDtos = tbProductMapper.selectNewProduct();
        } else if (type == 2) {
            //热卖商品
            productDtos = tbProductMapper.selectBestSeller();
        }else {
            //其他正常分类
            productDtos = tbProductMapper.selectProductByCategory(id);
        }


        return new PageInfo<>(productDtos);
    }
    @Override
    public PageInfo<ProductDto> selectProductByEv(Integer pageIndex, Integer pageSize, Integer id) {
        pageIndex = (pageIndex == null ? 1 : pageIndex);
        pageSize = (pageSize == null ? 8 : pageSize);
        List<ProductDto> productDtos = new ArrayList<>();
        PageHelper.startPage(pageIndex, pageSize);
        //其他正常分类
        productDtos .addAll(tbProductMapper.selectProductByEv(id));
        if (id == 1) {
            //新品上架
            productDtos .addAll(tbProductMapper.selectNewProduct());
        } else if (id == 2) {
            //热卖商品
            productDtos .addAll(tbProductMapper.selectBestSeller());
        }
        return new PageInfo<>(productDtos);
    }

    @Override
    public void setSortId(Integer id, Integer sortId) {
        sortIdMapper.setSortId(id, sortId);
    }

    /**
     * 商品审核 可编辑表格 保存数据
     *
     * @param name
     * @param value
     */
    @Override
    public void addEditProduct(Integer id, String name, String value) {
        tbProductMapper.addEditProduct(id,name,value);
    }

    @Override
    public SupplyResult doProductAdd(Integer pid, Integer cid) {
        TbProduct tp = tbProductMapper.selectByPrimaryKey(pid);
        tp.setCategoryId(cid);
        return SupplyResult.ok(tbProductMapper.updateByPrimaryKey(tp));
    }

    @Override
    public void doAllProductEdit(List<TbProduct> list) {
        for (int i = 0, j = list.size(); i < j; i++) {
            TbProduct e = list.get(i);
            e.setSortId(i);
            tbProductMapper.updateByPrimaryKey(e);
        }
    }

    @Override
    public SupplyResult doProductDlt(Integer id) {
        TbProduct tp = tbProductMapper.selectByPrimaryKey(id);
        return SupplyResult.ok(tbProductMapper.selectByPrimaryKey(id));
        //tp.setCategoryId();
    }

    /**
     * 每天凌晨两点启动
     */
    @Scheduled(cron="0 0 2 * * ?")
    public void changeShopClickRate() {
        countShopClick();
    }
    /**
     * 统计商品点击流量展示
     */
    @Override
    public void countShopClick() {
        List<TbProduct> list = tbProductMapper.selectAll();
        for (TbProduct tbProduct : list) {
            String clickrate = tbProduct.getClickrate();
            String[] split = clickrate.split("-");
            for (int i = 0; i < split.length-1; i++) {
                split[i] = String.valueOf(Integer.valueOf(split[i+1]));
            }
            split[6] = "0";
            tbProduct.setClickrate(StringUtils.join(split,"-"));
            tbProductMapper.updateByPrimaryKey(tbProduct);
        }
    }

    @Override
    public void addClickRate(Integer id) {
        TbProduct tbProduct = tbProductMapper.selectByPrimaryKey(id);
        String[] split = tbProduct.getClickrate().split("-");
        split[split.length-1] = String.valueOf(Integer.valueOf(split[split.length-1])+ 1);
        tbProduct.setClickrate(StringUtils.join(split,"-"));
        tbProductMapper.updateByPrimaryKey(tbProduct);
    }

    @Override
    public SupplyResult countShopClickRate() {
        List<Map<String, Object>> list = tbProductMapper.countShopClickRate();
        return SupplyResult.ok(list);
    }

}