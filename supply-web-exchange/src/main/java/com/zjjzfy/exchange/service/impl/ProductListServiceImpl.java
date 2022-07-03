package com.zjjzfy.exchange.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjjzfy.common.config.ProductCheckStatus;
import com.zjjzfy.common.config.ProductImgType;
import com.zjjzfy.common.config.ProductStatus;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.exchange.cache.OrgCache;
import com.zjjzfy.exchange.cache.ProductCache;
import com.zjjzfy.exchange.common.SessionKey;
import com.zjjzfy.exchange.pojo.ProductModel;
import com.zjjzfy.exchange.service.BaseService;
import com.zjjzfy.exchange.service.ProductListService;
import com.zjjzfy.exchange.utils.IpUtil;
import com.zjjzfy.interfaces.TbCategoryMapper;
import com.zjjzfy.interfaces.TbIpOrgMapper;
import com.zjjzfy.interfaces.TbProductMapper;
import com.zjjzfy.interfaces.TbRepositoryMapper;
import com.zjjzfy.pojo.*;
import com.zjjzfy.product.service.impl.ProductImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: jackshenonly
 * Date: 2019/4/29
 * Time: 17:26
 */
@Service
public class ProductListServiceImpl extends BaseService implements ProductListService {

    @Autowired
    private TbRepositoryMapper tbRepositoryMapper;
    @Autowired
    private TbProductMapper tbProductMapper;
    @Autowired
    private TbIpOrgMapper tbIpOrgMapper;
    @Autowired
    private ProductImgService productImgService;
    @Autowired
    private TbCategoryMapper tbCategoryMapper;


    @Override
    public PageInfo<TbProduct> selectProduct(HttpServletRequest request, int pageNo, int pageSize, int categoryId, String priceOrder) {
        String branchno = OrgCache.getInstance().getBranchnoByRequest(request);
        return selectProduct(branchno, pageNo, pageSize, categoryId, priceOrder);
    }

    @Override
    public PageInfo<TbProduct> selectProduct(String branchno, int pageNo, int pageSize) {

        return selectProduct(branchno, pageNo, pageSize, -1, null);
    }

    @Override
    public PageInfo<TbProduct> selectProduct(String branchno, int pageNo, int pageSize, int categoryId, String priceOrder) {

        return selectProduct(branchno, pageNo, pageSize, categoryId, priceOrder, null);
    }

    @Override
    public PageInfo<TbProduct> selectProduct(String branchno, int pageNo, int pageSize, int categoryId, String priceOrder, String prodNameLike) {
        TbOrgInfo org = OrgCache.getInstance().getOrgInfobyBranchno(branchno);
        if (null == org) {
            return null;
        }

        TbRepositoryExample tre = new TbRepositoryExample();
        tre.createCriteria().andSupplyOrgIdEqualTo(org.getId())
                .andQuantityGreaterThan(0);
        List<TbRepository> repos = tbRepositoryMapper.selectByExample(tre);
        List<Integer> pids;
        pids = repos.stream().map(rep -> {
            return rep.getProductId();
        }).collect(Collectors.toList());

        List<TbProduct> products = null;

        if (null == pids || pids.size() == 0) {
            return null;
        } else {
            TbProductExample tpe = new TbProductExample();
            TbProductExample.Criteria tpeC = tpe.createCriteria();
            // 已上架的产品，且审核通过
            tpeC.andProductStatusEqualTo(ProductStatus.ON.getStatus()).andCheckStatusEqualTo(ProductCheckStatus.CHECK_PASS.getStatus());
            // 内采专区产品，在自助兑换端不显示。
            tpeC.andProductPrefectureNotEqualTo(1);
            tpeC.andIdIn(pids);
            if (categoryId != -1) {
                //选出该分类，及其子分类的所有礼品；
                List<Integer> cids = new ArrayList<>();
                cids = findAndChildCategory(categoryId);
                tpeC.andCategoryIdIn(cids);
            }
            List legalOrder = Arrays.asList(new String[]{"asc","desc"});
            if(priceOrder != null && legalOrder.contains(priceOrder.toLowerCase())){
                tpe.setOrderByClause("exchange_price "+priceOrder);
            }
            if(prodNameLike != null && !prodNameLike.trim().isEmpty()){
                tpeC.andNameLike("%" + prodNameLike.trim() + "%");
            }

            PageHelper.startPage(pageNo, pageSize);
            products = tbProductMapper.selectByExample(tpe);
        }

        return new PageInfo<>(products);
    }

    @Override
    public PageInfo<TbProduct> selectProduct(int pageNo, int pageSize, int categoryId, String priceOrder) {
        return selectProduct(pageNo, pageSize, categoryId, priceOrder, null);
    }

    @Override
    public PageInfo<TbProduct> selectProduct(int pageNo, int pageSize, int categoryId, String priceOrder, String prodNameLike) {
        TbProductExample tpe = new TbProductExample();
        TbProductExample.Criteria tpeC = tpe.createCriteria();
        // 已上架的产品，且审核通过
        tpeC.andProductStatusEqualTo(ProductStatus.ON.getStatus()).andCheckStatusEqualTo(ProductCheckStatus.CHECK_PASS.getStatus());
        // 内采专区产品，在自助兑换端不显示。
        tpeC.andProductPrefectureNotEqualTo(1);
        if (categoryId != -1) {
            List<Integer> cids = new ArrayList<>();
            cids = findAndChildCategory(categoryId);
            tpeC.andCategoryIdIn(cids);
        }
        List legalOrder = Arrays.asList(new String[]{"asc","desc"});
        if(priceOrder != null && legalOrder.contains(priceOrder.toLowerCase())){
            tpe.setOrderByClause("exchange_price "+priceOrder);
        }
        if(prodNameLike != null && !prodNameLike.trim().isEmpty()){
            tpeC.andNameLike("%" + prodNameLike.trim() + "%");
        }

        List<TbProduct> products = null;
        PageHelper.startPage(pageNo, pageSize);
        products = tbProductMapper.selectByExample(tpe);
        return new PageInfo<>(products);
    }

    @Override
    public ProductModel selectProductModel(int productid, HttpServletRequest request) {
        String branchno = OrgCache.getInstance().getBranchnoByRequest(request);
        return selectProductModel(productid, branchno);
    }

    @Override
    public ProductModel selectProductModel(int productid, String branchno) {
        ProductModel model = new ProductModel();
        TbProduct product = tbProductMapper.selectByPrimaryKey(productid);
        Integer stock = 0;
        SupplyResult result = productImgService.selectProductImg(productid, ProductImgType.DETAIL.getType());
        List<TbProductImg> imgs = (List<TbProductImg>) result.getData();
        SupplyResult result2 = productImgService.selectProductImg(productid, ProductImgType.REVEAL.getType());
        List<TbProductImg> reveal = (List<TbProductImg>) result2.getData();


        TbOrgInfo org = OrgCache.getInstance().getOrgInfobyBranchno(branchno);
        if (null != org) {
            TbRepositoryExample tre = new TbRepositoryExample();
            tre.createCriteria().andSupplyOrgIdEqualTo(org.getId())
                    .andProductIdEqualTo(productid);
            List<TbRepository> repos = tbRepositoryMapper.selectByExample(tre);
            if (null != repos && repos.size() > 0) {
                stock = repos.get(0).getQuantity();
            }
        }

        model.setProduct(product);
        model.setImgs(imgs);
        model.setReveal(reveal);
        model.setStock(stock);
        return model;
    }


    @Override
    public SupplyResult setIp2branchno(HttpServletRequest request, String branchno) {
        String ip = IpUtil.getIpAddr(request);
        return setIp2branchno(ip, branchno);
    }

    @Override
    public SupplyResult setIp2branchno(String ip, String branchno) {
        TbOrgInfo orgInfo = OrgCache.getInstance().getOrgInfobyBranchno(branchno);
        if (orgInfo == null) {
            return SupplyResult.build(404, "输入的机构号不存在");
        }
        TbIpOrg ipOrg = new TbIpOrg();

        if (OrgCache.getInstance().getIp2BranchNo().containsKey(ip)) {
            ipOrg.setIp(ip);
            ipOrg.setBranchno(orgInfo.getBranchno());
            ipOrg.setOrgId(orgInfo.getId());
            ipOrg.setOrgName(orgInfo.getOrgName());
            tbIpOrgMapper.updateByPrimaryKeySelective(ipOrg);
        } else {
            ipOrg.setIp(ip);
            ipOrg.setBranchno(orgInfo.getBranchno());
            ipOrg.setOrgId(orgInfo.getId());
            ipOrg.setOrgName(orgInfo.getOrgName());
            tbIpOrgMapper.insertSelective(ipOrg);
        }

        OrgCache.getInstance().getIp2BranchNo().put(ip, branchno);

        return SupplyResult.ok("");

    }

    @Override
    public SupplyResult setCookie(HttpServletResponse response, String branchno) {
        TbOrgInfo orgInfo = OrgCache.getInstance().getOrgInfobyBranchno(branchno);
        if (orgInfo == null) {
            return SupplyResult.build(404, "输入的机构号不存在");
        }
        Cookie cookie = new Cookie(SessionKey.COOKIE_BRANCHID,branchno);
        //单位秒 修改为1年=31536000 秒
        cookie.setMaxAge(31536000 * 10);
        response.addCookie(cookie);
        return SupplyResult.ok("");
    }

    @Override
    public SupplyResult cacheRefresh() {
        try {
            OrgCache.getInstance().refresh();
            ProductCache.getInstance().refresh();
        } catch (Exception e) {
            return SupplyResult.build(300, e.getMessage(), "");
        }
        return SupplyResult.ok();
    }

    @Override
    public List<Integer> findAndChildCategory(Integer categoryId) {
        //选出指定分类，及其子分类的分类编号；
        List<Integer> cids = new ArrayList<>();
        TbCategoryExample tbce = new TbCategoryExample();
        tbce.createCriteria().andStatusEqualTo(new Byte("0")).andParentIdEqualTo(categoryId);
        tbce.or().andIdEqualTo(categoryId);
        List<TbCategory> cates = tbCategoryMapper.selectByExample(tbce);
        if(cates.size() > 0){
            cates.stream().forEach(x -> cids.add(x.getId()));
        }else {
            cids.add(-1);
        }
        return cids;
    }
}
