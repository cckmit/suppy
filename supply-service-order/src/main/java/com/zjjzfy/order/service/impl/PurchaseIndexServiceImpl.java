package com.zjjzfy.order.service.impl;

import com.zjjzfy.common.config.SupplyPubDef;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.common.utils.Base64ToImageUtil;
import com.zjjzfy.interfaces.TbAdvertisementMapper;
import com.zjjzfy.order.service.PurchaseIndexService;
import com.zjjzfy.pojo.TbAdvertisement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PurchaseIndexServiceImpl implements PurchaseIndexService {

    private static final Logger log = LoggerFactory.getLogger(PurchaseIndexServiceImpl.class);

    @Autowired
    private TbAdvertisementMapper tdAdvertisementMapper;
    @Override
    public SupplyResult addAdvertisement(TbAdvertisement ad) {
        Assert.notNull(ad, "广告数据为空");
//        Assert.notNull(ad.getHref(), "请输入活动链接");
        Assert.notNull(ad.getImg(), "请选择广告图片");
        Assert.notNull(ad.getStartTime(), "请选择广告播放开始时间");
        Assert.notNull(ad.getEndTime(), "请选择广告播放结束时间");
        Assert.notNull(ad.getImg(), "请选择广告图片");

        ad.setId(null);

        if (ad.getRank() == null) {
            ad.setRank(1);
        }

        ad.setHref("#");
        ad.setStatus("1");
        ad.setCreateTime(new Date());
        ad = setInfo(ad);
        try {
            int iad = tdAdvertisementMapper.insertUseGeneratedKeys(ad);
            if (iad != 1) {
                return SupplyResult.build(202, "广告保存失败");
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }

        return SupplyResult.ok();
    }

    public TbAdvertisement setInfo(TbAdvertisement ad) {
        ad.setAdvClassification("1");
        ad.setAdvPosition(1);
        return ad;
    }

    @Override
    public SupplyResult uploadImg(String base64Img, String fileName) {

        try {
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            if (!path.exists()) {
                path = new File("");
            }
            System.out.println("path:" + path.getAbsolutePath());

            File upload = new File(path.getAbsolutePath(), SupplyPubDef.PEAD_IMG_PATH);
            if (!upload.exists()) {
                upload.mkdirs();
            }
            String absPath = upload.getAbsolutePath();
            System.err.println("xxx-"+absPath);
            String imgName = UUID.randomUUID().toString().replace("-", "") + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
            //将 base64 生成图片
            boolean flag = Base64ToImageUtil.yzxGenerateImage(base64Img, absPath + File.separator + imgName);
            if (flag) {
                //结果入库
                System.out.println("上传成功！！！");

                return SupplyResult.ok((File.separator + SupplyPubDef.PEAD_IMG_PATH + File.separator + imgName).replace("\\", "/"));
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
    @Transactional
    public SupplyResult doAllAdvEdit(List<TbAdvertisement> pagelist, List<TbAdvertisement> dbList) {
        //设置rank
        for (int i = 0,j= pagelist.size();i<j;i++){
            TbAdvertisement ta  = pagelist.get(i).setRank(i);
            pagelist.set(i,ta);
        }
        //与数据库数据为主的遍历
       for (int i = 0,j= dbList.size();i<j;i++){
           TbAdvertisement e = dbList.get(i);
           if(exitOrNot(e,pagelist)){
               //更新
               tdAdvertisementMapper.updateByPrimaryKey(e);
           }else{
               //数据库里有但界面没有
               //删除
               e.setStatus("0");
               tdAdvertisementMapper.updateByPrimaryKey(e);
           };
       }
        //与界面数据为主的遍历
        for (int i = 0,j= pagelist.size();i<j;i++){
            TbAdvertisement e = pagelist.get(i);
            if(exitOrNot(e,dbList)){
                //更新
                //tdAdvertisementMapper.updateByPrimaryKey(e);
            }else{
                //界面里有但数据库没有
                //插入
                tdAdvertisementMapper.insert(e);
            };
        }
       return SupplyResult.ok();
    }

    @Override
    public SupplyResult uploadImgPc(String imgUrl,String base64Img, String fileName) {

        try {
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            if (!path.exists()) {
                path = new File("");
            }
            System.out.println("path:" + path.getAbsolutePath());

            File upload = new File(/*path.getAbsolutePath()*/imgUrl+File.separator+ SupplyPubDef.PRODUCT_IMG_PATH);
            if (!upload.exists()) {
                upload.mkdirs();
            }
            String absPath = upload.getAbsolutePath();
            System.err.println("pathFile:"+absPath);
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

    private boolean exitOrNot(TbAdvertisement ta, List<TbAdvertisement> list) {
        for (int i = 0,j= list.size();i<j;i++){
            if(ta.getId()==list.get(i).getId()){
                return true;
            }
        }
        return false;
    }

}