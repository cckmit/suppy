package com.zjjzfy.mgt.controller;

import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.order.service.TbAdvertisementService;
import com.zjjzfy.pojo.TbAdvertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.util.List;

/**
 * @author addict
 * @description
 * @date 2019/9/17 下午4:52
 */
@Controller
@RequestMapping("/mgt")
public class AdvertisementController {

    private String imgAddr ="";
    @Autowired
    private TbAdvertisementService tbAdvertisementService;


    @RequestMapping("/advertisingManagementPage")
    public String advertisingManagementPage(Model model) {
        model.addAttribute("imgAddr",imgAddr);
        return "advertisement/advertisementDetail";
    }

    @RequestMapping("/advertisingAddPage")
    public String advertisingAddPage(Model model) {
        model.addAttribute("imgAddr",imgAddr);
        return "advertisement/advertising-Add";
    }

    //去首页
    @RequestMapping("/toIndexEditA")
    //Model model
    public String toIndexEdit() {
        //return "mbHomePage/mbPcsIndex";
        return "mbHomePage/index";
    }


    @RequestMapping("/advertisingDetail")
    @ResponseBody
    public List<TbAdvertisement> advertisingDetail(Integer type, String status) {
        return tbAdvertisementService.getAdsByTime(type, status,"1",1);
    }

    @RequestMapping("/downAds")
    @ResponseBody
    public SupplyResult downAds(Integer adId) {
        return tbAdvertisementService.adsDown(adId);
    }


    @RequestMapping("modifyAd")
    public String modifyAd(Integer adId, Model model) {

        TbAdvertisement ad = tbAdvertisementService.getAdsById(adId);

        model.addAttribute("ad", ad);
        model.addAttribute("imgAddr",imgAddr);
        return "advertisement/advertising-Modify";
    }


    @RequestMapping("modifyAdsPc")
    @ResponseBody
    public SupplyResult modifyAdsPc(TbAdvertisement ad, Model model) {
        SupplyResult result = tbAdvertisementService.modifyAd(ad);
        return result;
    }

    @RequestMapping("/addAdvertisement")
    @ResponseBody
    public SupplyResult addAdvertisement(TbAdvertisement ad) {
        return tbAdvertisementService.addAdvertisement(ad);
    }

    /**
     * 上传图片
     *
     * @param
     * @param
     * @return
     */
    @RequestMapping("/uploadImg")
    @ResponseBody
    public SupplyResult uploadImg(MultipartFile file) {
        String base64Img = null;
        String fileName = file.getOriginalFilename();
        BASE64Encoder base64Encoder = new BASE64Encoder();
        try {
            base64Img = base64Encoder.encode(file.getBytes()).replaceAll("[\\s*\t\n\r]", "");
            ;
        } catch (IOException e) {
            return SupplyResult.build(300, "图片为空");
        }
        SupplyResult result = tbAdvertisementService.uploadImg(base64Img, fileName);
        return result;
    }


}
