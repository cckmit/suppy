package com.zjjzfy.mobile.controller;

import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.order.service.AddressService;
import com.zjjzfy.order.service.CartService;
import com.zjjzfy.order.service.PurchaseService;
import com.zjjzfy.pojo.TbAddress;
import com.zjjzfy.pojo.TbUserInfo;
import com.zjjzfy.product.service.impl.ProductService;
import com.zjjzfy.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("purchase")
@Slf4j
public class AdressController {
    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private CartService cartService;
    @Autowired
    PurchaseService purchaseService;

    @Value("${ads.url}")
    private String adsUrl;
    private final boolean isFake = true;
    /**
     *
     *地址界面
     * @return
     */
    @RequestMapping("/toAddress")
    public String toAds(Model model) {
        TbUserInfo tbUserInfo =  getUserInfo();
        List<TbAddress> adsList = addressService.queryAds(tbUserInfo.getUid(),isFake);
        model.addAttribute("adsList", adsList);
        return "/purchase/address";
    }

    private void test1() {
        TbAddress ta = new TbAddress(99,"name",52,"xx省","xx市","xx区","xx街xx号","1591111111",(byte)1);
        TbAddress ta1 = new TbAddress(100,"name1",52,"yy省","yy市","yy区","yy街yy号","15922222222",(byte)0);
        //addressService.istAds(ta);
        // TbAddress address = addressService.findAdsByIsdft();model.addAttribute("address", address);
        //addressService.updtAds(ta,true);
        //addressService.checkAds(true);
        // addressService.dltAds(ta1,false);
    }

    /**
     *
     *去新增地址
     * @return
     */
    @RequestMapping("/toAddressAdd")
    public String toAddressAdd(Model model) {
        TbAddress ta1 = new TbAddress();
        model.addAttribute("ads", ta1);
        return "/purchase/addressEdit";
    }
    /**
     *
     *去新增地址
     * @return
     */
    @RequestMapping("/toCartAddressAdd")
    public String toCartAddressAdd(Model model,String ids) {
        TbAddress ta1 = new TbAddress();
        model.addAttribute("ads", ta1);
        model.addAttribute("ids", ids);
        return "/purchase/addressEdit";
    }
    /**
     *
     *新增地址
     * @return
     */
    @RequestMapping("/doAddressAdd")
    @ResponseBody
    public boolean doAddressAdd(Model model,TbAddress ta,@RequestParam("pcc") String pcc) {
        TbUserInfo tbUserInfo =  getUserInfo();
        ta.setUserId(tbUserInfo.getUid());
        System.err.println("xxx"+pcc);
        return addressService.istAds(ta,isFake,pcc);
    }
    /**
     *
     *去编辑地址
     * @return
     */
    @RequestMapping("/toAddressEdit")
    public String toAdsEdit(Model model,Integer id) {
        TbUserInfo tbUserInfo =  getUserInfo();
        TbAddress ta = addressService.findAdsById(tbUserInfo.getUid(),id,isFake);
        model.addAttribute("ads", ta);
        return "/purchase/addressEdit";
    }
    /**
     *
     *编辑地址
     * @return
     */
    @RequestMapping("/doAddressEdit")
    @ResponseBody
    public boolean doAddressEdit(Model model,TbAddress ta,String pcc) {
        TbUserInfo tbUserInfo =  getUserInfo();
        ta.setUserId(tbUserInfo.getUid());
        return addressService.updtAds(ta,isFake,pcc);
    }
    /**
     *
     *删除地址
     * @return
     */
    @RequestMapping("/doAddressDlt")
    @ResponseBody
    public boolean doAddressDlt(Model model,Integer id) {
        TbUserInfo tbUserInfo =  getUserInfo();
        return addressService.dltAds(tbUserInfo.getUid(),id,isFake);
    }
    public void line() {
        System.err.println("=======================================");
    }
    public TbUserInfo getUserInfo() {
        return userService.getCurrentTbUserInfo();
    }

    /**
     * 查询当前用户的默认地址
     * @return
     */
    @RequestMapping("/doFindAdsByIsdft")
    @ResponseBody
    public TbAddress findAdsByIsdft(Model model){
        TbUserInfo tbUserInfo =  getUserInfo();
        return addressService.findAdsByIsdft(tbUserInfo.getUid(),isFake);
    }
    /**
     * 删除所有伪标记地址
     * @return
     */
    @RequestMapping("/doCleanAllDltAds")
    @ResponseBody
    public Boolean cleanAllDltAds(Model model){
        TbUserInfo tbUserInfo =  getUserInfo();
        return addressService.cleanAllDltAds(tbUserInfo.getUid());
    }
    /**
     * 检查地址
     * @return
     */
    @RequestMapping("/doCheckAds")
    @ResponseBody
    public Boolean checkAds(Model model){
        TbUserInfo tbUserInfo =  getUserInfo();
        return addressService.checkAds(tbUserInfo.getUid(),isFake);
    }
    /**
     *
     *去购物车结算界面
     * @return
     */
    @RequestMapping("/toShopCartOrder")
    public String toShopCartOrder(Model model,String ids) {
        TbUserInfo currentTbUserInfo = getUserInfo();
        Integer purchaserId = currentTbUserInfo.getUid();

       /* SupplyResult supplyResult = cartService.productnumOverLimit(ids);
        if(supplyResult.getStatus() != 200){

            List<HashMap<String, Object>> cartList = cartService.generCarts(purchaserId);
            int count = cartService.obtainCartCount(currentTbUserInfo.getUid());

            model.addAttribute("count", count);
            model.addAttribute("cartList", cartList);
            model.addAttribute("purchaserId", purchaserId);
            model.addAttribute("msg",supplyResult.getMsg());
            return "/purchase/shopCart";
        }*/


        // List<HashMap<String, Object>> cartList = cartService.generCarts(purchaserId);
        int count = cartService.obtainCartCount(currentTbUserInfo.getUid());
        TbAddress ta = addressService.findAdsByIsdftAndIsLastUsed(purchaserId,isFake);
        List<TbAddress> taList = addressService.queryAds(purchaserId,isFake);
        model.addAttribute("count", count);
//        model.addAttribute("cartList", cartList);
        model.addAttribute("purchaserId", purchaserId);
        model.addAttribute("ads", ta);
        model.addAttribute("adsList", taList);

        List<HashMap<String, Object>> cartList1 =  addressService.generCarts(currentTbUserInfo.getUid(),ids);
        model.addAttribute("cartList", cartList1);
        model.addAttribute("ids", ids);
        return "/purchase/shopCartOrder";
    }
    /**
     *
     *去选择收货地址
     * @return
     */
    @RequestMapping("/toChangeAds")
    public String toChangeAds(Model model) {
        TbUserInfo tbUserInfo =  getUserInfo();
        List<TbAddress> adsList = addressService.queryAds(tbUserInfo.getUid(),isFake);
        model.addAttribute("adsList", adsList);
        return "/purchase/addressChange";
    }
//    /**
//     *
//     *
//     * @return
//     */
//    @RequestMapping("/doCartToForm")
//    public String doCartToForm(String ids, Integer purchaseId,Integer adsId) {
//        List<Integer> list = new ArrayList<>();
//        String[] strs = ids.split(",");
//        for (int i = 0; i < strs.length; i++) {
//            list.add(Integer.valueOf(strs[i]));
//        }
//        return purchaseService.generPurchaseForm(list, purchaseId);
//    }
}
