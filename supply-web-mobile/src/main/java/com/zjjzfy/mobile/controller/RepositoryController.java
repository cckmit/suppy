package com.zjjzfy.mobile.controller;

import com.github.pagehelper.PageInfo;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.interfaces.TbDeliverFormMapper;
import com.zjjzfy.interfaces.TbPurchaseFormMapper;
import com.zjjzfy.interfaces.TbRepositoryMapper;
import com.zjjzfy.order.service.DeliverService;
import com.zjjzfy.order.service.PurchaseService;
import com.zjjzfy.pojo.TbPurchaseForm;
import com.zjjzfy.pojo.TbRepository;
import com.zjjzfy.pojo.TbRepositoryExample;
import com.zjjzfy.pojo.dto.ProductDto;
import com.zjjzfy.product.service.impl.ProductService;
import com.zjjzfy.repository.service.RepositoryService;
import com.zjjzfy.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: hsmz
 * @date: 2019/3/21 下午2:28
 */
@Controller
@RequestMapping("/purchase")
@Slf4j
public class RepositoryController {

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private DeliverService deliverService;
    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private TbPurchaseFormMapper tbPurchaseFormMapper;



    @RequestMapping("/stockListInit")
    public String deliverProductInit() {
        return "/purchase/stock/stockList";
    }

    @RequestMapping("stockList")
    @ResponseBody
    public PageInfo<ProductDto> selectProductBySupplierId(Integer pageNo, Integer pageSize) {
        Integer supplierId = userService.getCurrentTbUserInfo().getUid();
        return productService.selectProductBySupplierId(supplierId, pageNo, pageSize);
    }

    /**
     * 重置采购方库存 s
     * @return
     */
    @RequestMapping("resetPurchaseRepository")
    @ResponseBody
    public String resetPurchaseRepository() {

        List<TbRepository> repositoryList = deliverService.queryPurchaseRepository();
        repositoryService.resetPurchaseRepository(repositoryList);
        return "success";
    }

    /**
     * 重置发货方库存
     * @return
     */
    @RequestMapping("resetDeliverRepository")
    @ResponseBody
    public String resetDeliverRepository() {

        List<TbRepository> repositoryList = deliverService.queryDeliverRepository();
        repositoryService.resetDeliverRepository(repositoryList);
        return "success";
    }


    /**
     * 采购方入库
     * @param ids
     * @param nums
     * @return
     */
    @RequestMapping("stockInput")
    @ResponseBody
    public SupplyResult addProductRepository(@RequestParam("ids[]") ArrayList<Integer> ids, @RequestParam("nums[]") ArrayList<Integer> nums) {
        Integer supplierId = userService.getCurrentTbUserInfo().getUid();
        return repositoryService.purchaserStockInputList(ids,nums,supplierId);
    }

    /**
     * 采购方入库
     * @param ids
     * @param nums
     * @return
     */
    @RequestMapping("checkPurchaseFormFinishByQuantity")
    @ResponseBody
    public String checkPurchaseFormFinishByQuantity(@RequestParam("ids[]") ArrayList<Integer> ids, @RequestParam("nums[]") ArrayList<Integer> nums) {

        List<TbPurchaseForm> tbPurchaseFormList = purchaseService.selectAll();
        for (TbPurchaseForm tbPurchaseForm : tbPurchaseFormList) {
            purchaseService.checkPurchaseFormFinishByQuantity(tbPurchaseForm.getId());
        }
        return "success";
    }
}
