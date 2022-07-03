package com.zjjzfy.order.service.impl;

import com.zjjzfy.common.config.SupplyPubDef;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.common.utils.Base64ToImageUtil;
import com.zjjzfy.common.utils.MergeObject;
import com.zjjzfy.interfaces.TbAdvertisementMapper;
import com.zjjzfy.order.service.TbAdvertisementService;
import com.zjjzfy.pojo.TbAdvertisement;
import com.zjjzfy.pojo.TbAdvertisementDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.*;

@Service
public class TbAdvertisementServiceImpl implements TbAdvertisementService{

    private static final Logger log = LoggerFactory.getLogger(TbAdvertisementServiceImpl.class);

    @Autowired
    private TbAdvertisementMapper tdAdvertisementMapper;
    @Autowired
    private PurchaseIndexServiceImpl PurchaseIndexService;


    @Override
    public List<TbAdvertisement> getAdsByPosition(Integer type, String status,String advClassification,Integer advPosition) {
        return tdAdvertisementMapper.getAdsByPosition(type, status, new Date(),advClassification,advPosition);
    }
    @Override
    public List<TbAdvertisement> getAdsByPositionByIndex(Integer type, String status, String advClassification, Integer advPosition) {
        List<TbAdvertisement> tbAdvertisementDtos = tdAdvertisementMapper.getAdsByPositionByIndex(type, status, new Date(),advClassification,advPosition);
//        Date date = new Date();
//        tbAdvertisementDtos.forEach(e->{
//            Date endTime= e.getEndTime()==null?new Date(0):e.getEndTime();
//            if(endTime.getTime()<date.getTime()){
//                e.setExpire(1);
//            }else{
//                e.setExpire(0);
//            }
//        });
        return tbAdvertisementDtos;
    }
    @Override
    public List<TbAdvertisementDto> getAdsByPositionByIndexExpire(Integer type, String status, String advClassification, Integer advPosition) {
        List<TbAdvertisementDto> tbAdvertisementDtos = new ArrayList<>();
        List<TbAdvertisement> list = tdAdvertisementMapper.getAdsByPositionByIndex(type, status, new Date(),advClassification,advPosition);
        list.forEach(e->{
            TbAdvertisementDto tb = new TbAdvertisementDto();
            BeanUtils.copyProperties(e,tb);
            tbAdvertisementDtos.add(tb);
        });
        Date date = new Date();
        tbAdvertisementDtos.forEach(e->{
            Date endTime= e.getEndTime()==null?new Date(0):e.getEndTime();
            if(endTime.getTime()<date.getTime()){
                e.setExpire(1);
            }else{
                e.setExpire(0);
            }
        });
        return tbAdvertisementDtos;
    }
    @Override
    public Map<String,Object> getAdsByPosition(Integer type,String status) {
        List<TbAdvertisement> list  = tdAdvertisementMapper.getAdsByPositionByStatus(status);
        Map<String,Object> map = new HashMap<>();
        List<TbAdvertisement> list1 = new ArrayList<>();
        List<TbAdvertisement> list2 = new ArrayList<>();
        List<TbAdvertisement> list3 = new ArrayList<>();
        List<TbAdvertisement> list4 = new ArrayList<>();
        list.forEach(e->{
            String classf = e.getAdvClassification();
            Integer position = e.getAdvPosition();
            //广告
            if("1".equals(classf)&&position == 1){
                list1.add(e);
            }
            //公告
            if("0".equals(classf)&&position == 2){
                list2.add(e);
            }
            //超值组合,限时特卖
            if("1".equals(classf)&&position == 3){
                list3.add(e);
            }
            //文字接口
            if("0".equals(classf)&&position == null){
                list4.add(e);
            }
        });
        map.put("adv",list1);
        map.put("textAdv",list2);
        map.put("textAdv3",list2);
        map.put("textItfc",list2);
        return map;
    }

    @Override
    public List<TbAdvertisement> getAdsByTime(Integer type, String status,String advClassification,Integer advPosition) {
        return tdAdvertisementMapper.getAdsByTime(type, status, new Date(),advClassification,advPosition);
    }
    /**
     * 根据广告ID 下架广告
     *
     * @param id
     * @return 作者：ZT
     */
    @Override
    public SupplyResult adsDown(Integer id) {
        if (id == null) {
            return SupplyResult.build(201, "广告信息获取失败");
        }

        Integer iid = tdAdvertisementMapper.adsDown(id);

        if (iid == 0) {
            return SupplyResult.build(202, "下架失败：广告已下架或者广告不存在");
        } else if (iid == 1) {
            return SupplyResult.ok("下架成功");
        } else {
            return SupplyResult.build(202, "下架失败：数据出错");
        }

    }

    @Override
    public SupplyResult addAdvertisement(TbAdvertisement ad) {
        Assert.notNull(ad, "广告数据为空");
//        Assert.notNull(ad.getHref(), "请输入活动链接");
        Assert.notNull(ad.getImg(), "请选择广告图片");
        Assert.notNull(ad.getStartTime(), "请选择广告播放开始时间");
        Assert.notNull(ad.getEndTime(), "请选择广告播放结束时间");
        Assert.notNull(ad.getImg(), "请选择广告图片");
        if (ad.getImg()==null||ad.getImg()==""){
            return SupplyResult.build(201, "没有选择图片");
        }
        ad.setId(null);

        ad.setHrefClass(2);


        if (ad.getRank() == null) {
            ad.setRank(1);
        }

        ad.setHref("#");
        ad.setStatus("1");
        ad.setCreateTime(new Date());
        ad=PurchaseIndexService.setInfo(ad);
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

    @Override
    public SupplyResult modifyAd(TbAdvertisement ad) {
        Assert.notNull(ad, "广告信息获取失败");
        Assert.notNull(ad.getId(), "广告信息获取失败");
        Assert.notNull(ad.getImg(), "请选择广告图片");

        TbAdvertisement select = tdAdvertisementMapper.selectByPrimaryKey(ad.getId());
        select = MergeObject.merge(ad, select);
        if (select == null) {
            return SupplyResult.build(201, "数据拷贝出错");
        }

        try {
            int iad = tdAdvertisementMapper.updateByPrimaryKey(select);
            if (iad != 1) {
                return SupplyResult.build(202, "保存失败");
            }

        } catch (Exception e) {
            log.info(e.getMessage());
        }

        return SupplyResult.ok();
    }


    @Override
    public TbAdvertisement getAdsById(Integer adId) {
        return tdAdvertisementMapper.selectByPrimaryKey(adId);
    }

    @Override
    public SupplyResult uploadImg(String base64Img, String fileName) {

        try {
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            if (!path.exists()) {
                path = new File("");
            }
            System.out.println("path:" + path.getAbsolutePath());

            File upload = new File(path.getAbsolutePath(), SupplyPubDef.AD_IMG_PATH);
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

                return SupplyResult.ok((File.separator + SupplyPubDef.AD_IMG_PATH + File.separator + imgName).replace("\\", "/"));
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
    public SupplyResult doTbAdvertisementAdd(TbAdvertisement ad) {
        Assert.notNull(ad, "公告数据为空");

        ad.setId(null);

        if (ad.getRank() == null) {
            ad.setRank(1);
        }

        ad.setHref("#");
        ad.setStatus("1");
        ad.setCreateTime(new Date());
        ad=PurchaseIndexService.setInfo(ad);
        try {
            int iad = tdAdvertisementMapper.insertUseGeneratedKeys(ad);
            if (iad != 1) {
                return SupplyResult.build(202, "公告保存失败");
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }

        return SupplyResult.ok();
    }

    @Override
    public SupplyResult doTbAdvertisementEdit(TbAdvertisement ad) {
        Assert.notNull(ad, "公告信息获取失败");
        Assert.notNull(ad.getId(), "公告信息获取失败");
        ad.setImg("/");
        TbAdvertisement select = tdAdvertisementMapper.selectByPrimaryKey(ad.getId());
        select = MergeObject.merge(ad, select);
        if (select == null) {
            return SupplyResult.build(201, "数据拷贝出错");
        }

        try {
            int iad = tdAdvertisementMapper.updateByPrimaryKey(select);
            if (iad != 1) {
                return SupplyResult.build(202, "保存失败");
            }

        } catch (Exception e) {
            log.info(e.getMessage());
        }

        return SupplyResult.ok();
    }

    @Override
    public SupplyResult doTbAdvertisementDlt(Integer id) {
        if (id == null) {
            return SupplyResult.build(201, "公告信息获取失败");
        }

        Integer iid = tdAdvertisementMapper.adsDown(id);

        if (iid == 0) {
            return SupplyResult.build(202, "下架失败：公告已下架或者公告不存在");
        } else if (iid == 1) {
            return SupplyResult.ok("下架成功");
        } else {
            return SupplyResult.build(202, "下架失败：数据出错");
        }

    }

    @Override
    @Transactional
    public SupplyResult doAllTbAdvertisementEdit(List<TbAdvertisement> pagelist, List<TbAdvertisement> dbList,Integer type, String status,String advClassification,Integer advPosition) {
        //设置rank
        for (int i = 0,j= pagelist.size();i<j;i++){
            TbAdvertisement ta  = pagelist.get(i);
            ta = setValue("默认",ta,dbList);
            ta.setRank(i);
            ta.setStatus(status);

            ta.setAdvClassification(advClassification);
            ta.setAdvPosition(advPosition);
            pagelist.set(i,ta);
        }
        //与数据库数据为主的遍历
        for (int i = 0,j= dbList.size();i<j;i++){
            TbAdvertisement e = dbList.get(i);
            Integer index = exitOrNot(e,pagelist);
            if(index!=null){
                //更新
                pagelist.get(index).setCreateTime(e.getCreateTime());
                tdAdvertisementMapper.updateByPrimaryKey(pagelist.get(index));
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
            Integer index = exitOrNot(e,pagelist);
            if(index!=null){
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
    public SupplyResult doAllTextAdv3Edit(List<TbAdvertisement> pageList, List<TbAdvertisement> dbList, Integer type, String status, String advClassification, Integer advPosition) {
        if(dbList!=null&&pageList!=null&&dbList.size()!=2&&pageList.size()==2){
            if(dbList.size()==0){
                for (int i = 0,j= pageList.size();i<j;i++){
                    TbAdvertisement ta  = pageList.get(i);
                    ta.setId(null);
                    tdAdvertisementMapper.insertSelective(ta);
                }
            }else if(dbList.size()==1){
                pageList.get(0).setId(dbList.get(0).getId());
                pageList.get(1).setId(null);
                tdAdvertisementMapper.insertSelective(pageList.get(1));
                pageList.remove(1);
            }
        }
        //设置rank
        if(pageList!=null&&pageList.size()==2){
            for (int i = 0,j= pageList.size();i<j;i++){
                TbAdvertisement ta  = pageList.get(i);
                ta = setValue("推荐",ta,dbList);
                ta.setRank(i);
                ta.setStatus(ta.getStatus());
                ta.setAdvClassification(advClassification);
                ta.setAdvPosition(advPosition);
                //防止name和end_time为空
                tdAdvertisementMapper.updateByPrimaryKeySelective(ta);
            }
        }else{
            return SupplyResult.build(300, "数据异常");
        }
        return SupplyResult.ok();
    }

    @Override
    public SupplyResult doAllAdvMoreEdit(List<TbAdvertisement> pageList, List<TbAdvertisement> dbList, Integer type, String status, String advClassification, Integer advPosition) {
        //设置rank
        if(pageList!=null&&pageList.size()==2){
            for (int i = 0,j= pageList.size();i<j;i++){
                TbAdvertisement ta  = pageList.get(i);
                ta = setValue("更多",ta,dbList);
                //ta=defineTime(ta,dbList);
                ta.setRank(i);
                ta.setStatus(status);
                ta.setAdvClassification(advClassification);
                tdAdvertisementMapper.updateByPrimaryKeySelective(ta);
            }
            return SupplyResult.ok();
        }else{
            return SupplyResult.build(300, "数据异常");
        }
    }

    public TbAdvertisement setValue(String TbName,TbAdvertisement ta,List<TbAdvertisement> list){
        Integer id = ta.getId();
        if("0".equals(ta.getAdvClassification())||ta.getImg()==null){
            ta.setImg("#");
        }
        if(new Integer(0).equals(ta.getHrefClass())){
            ta.setHref("#");
        }
        if(id==null){
            String name ="";
            if(ta.getName()==null||ta.getName()==""){
                name = TbName+ta.getId();
                ta.setName(name);
            }
            Calendar c1 = Calendar.getInstance();
            c1.set(9999, 1 - 1, 1);
//            new Date(new Date().getTime()+(999999999l*100l))
            Date date = ta.getEndTime()==null ? c1.getTime():ta.getEndTime();
            ta.setEndTime(date);
            ta.setCreateTime(new Date());
            ta.setStartTime(new Date());
            }else{
            if(ta.getCreateTime()==null||ta.getStartTime()==null){
                ta = defineTime(ta,list);
            }

        }
        return ta;
    }

    private Integer exitOrNot(TbAdvertisement ta, List<TbAdvertisement> list) {
        if(list!=null){
            for (int i = 0,j= list.size();i<j;i++){
                Integer id = list.get(i).getId()==null ?-1:list.get(i).getId();
                if(id.equals(ta.getId())){

                    return i;
                }
            }
        }
        return null;
    }
    private TbAdvertisement defineTime(TbAdvertisement ta, List<TbAdvertisement> list){
        Integer index = exitOrNot(ta,list);
        System.err.println("[xxx--index="+index+"][list.size()="+list.size()+"]");
        TbAdvertisement dbTa = list.get(index);
        ta.setCreateTime(dbTa.getCreateTime());
        ta.setStartTime(dbTa.getStartTime());
        return  ta;
    }
    public List<TbAdvertisement> ImgPathoperation(String peImgPath,List<TbAdvertisement> tbAdvertisements) {
        tbAdvertisements.forEach(e->{
            if(e.getImg()!=null){
                if("upload".equals(e.getImg().substring(1,7))){
                    e.setImg(peImgPath+e.getImg());
                }
            }
        });
        return tbAdvertisements;
    }

}