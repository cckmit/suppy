package com.zjjzfy.order.service.impl;

import com.zjjzfy.interfaces.TbAddressMapper;
import com.zjjzfy.order.service.AddressService;
import com.zjjzfy.order.service.CartService;
import com.zjjzfy.pojo.TbAddress;
import com.zjjzfy.pojo.TbAddressExample;
import com.zjjzfy.pojo.TbPurchaseForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author mask
 */
@Service
@Slf4j
class AddressServiceImpl implements AddressService {
    @Autowired
    private TbAddressMapper tbAddressMapper;
    @Autowired
    private CartService cartService;
    /**
     * 查出当前用户的所有地址
     * @param isFake 是否伪查询(配合伪删除)
     * @return
     */
    @Override
    @Transactional
    public List<TbAddress> queryAds(Integer uid,boolean isFake) {
        TbAddressExample tbAddressExample =new TbAddressExample();
        TbAddressExample.Criteria tc = tbAddressExample.createCriteria().andUserIdEqualTo(uid);
        if(isFake){
            tc.andDeletedEqualTo((byte)0);
        }
        List<TbAddress> list = tbAddressMapper.selectByExample(tbAddressExample);
        //检查地址,如果默认地址大于1,取消所有默认地址
        reSetAllDefaultLeftOneTool_createList(list,false);
        return list;
    }

    /**
     * 根据id查找一个地址
     * @param isFake
     * @return
     */
    @Override
    @Transactional
    public TbAddress findAdsById(Integer uid,Integer adsId ,boolean isFake) {
        List<TbAddress> ta = null;
        TbAddressExample tbAddressExample =new TbAddressExample();
        TbAddressExample.Criteria tc = tbAddressExample.createCriteria().andUserIdEqualTo(uid).andIdEqualTo(adsId);
        if(isFake){
            tc.andDeletedEqualTo((byte)0);
        }
            ta = tbAddressMapper.selectByExample(tbAddressExample);
        if(ta.size()>0){
            return ta.get(0);
        }
        return new TbAddress();
        //return null;
    }

    /**
     * 找到当前用户的默认地址
     * @return
     */
    @Override
    @Transactional
    public TbAddress findAdsByIsdft(Integer uid,boolean isFake) {
        List<TbAddress> list=findAdsByIsdftTool_all(uid,isFake);
        if(list==null){

        }else{
            int lsize = list.size();
            if(lsize==0){

            }else if(lsize==1){
                return list.get(0);
            }else{
                reSetAllDefaultTool_forEach(list,lsize);
            }
        }
        return new TbAddress();
        //return null;
    }

    /**
     *  如果用户有默认地址,返回默认地址,
     *  如果没有默认地址,返回上一次使用的地址,
     *  如果没有上一次使用的地址,返回第一个地址
     */
    @Override
    @Transactional
    public TbAddress findAdsByIsdftAndIsLastUsed(Integer uid,boolean isFake) {
        List<TbAddress> list=findAdsByIsdftTool_all(uid,isFake);
        if(list==null){
            return findAdsByIsLastUsed(uid,isFake);
        }else{
            int lsize = list.size();
            if(lsize==0){
                return findAdsByIsLastUsed(uid,isFake);
            }else if(lsize==1){
                return list.get(0);
            }else{
                reSetAllDefaultTool_forEach(list,lsize);
            }
        }
        return new TbAddress();
        //return null;
    }
    @Transactional
    public TbAddress findAdsByIsLastUsed(Integer uid,boolean isFake) {
        TbAddressExample tbAddressExample =new TbAddressExample();
        TbAddressExample.Criteria tc = tbAddressExample.createCriteria().andUserIdEqualTo(uid).andIsLastUsedEqualTo((byte)1);
        List<TbAddress> ta = tbAddressMapper.selectByExample(tbAddressExample);
        if(ta==null){
            return findAdsByIsLastUsedTool_first(uid,isFake);
        }else{
            int tsize = ta.size();
            if(tsize==0){
                return findAdsByIsLastUsedTool_first(uid,isFake);
            }else if(tsize==1){
                return ta.get(0);
            }else{
                findAdsByIsLastUsedTool_reset(ta);
                return ta.get(0);
            }
        }
        //return new TbAddress();
    }
    public TbAddress findAdsByIsLastUsedTool_first(int uid,boolean isFake){
        List<TbAddress> list =  queryAds(uid,isFake);
        if(list==null){

        }else{
            int lsize = list.size();
            if(lsize==0){

            }else{
                return list.get(0);
            }
        }
        return new TbAddress();
    }
    public boolean findAdsByIsLastUsedTool_reset(List<TbAddress> list){
        list.forEach(e->{
            skipChangeIfSame_isLastUsed(e,0);
        });
        return true;
    }
    /**
     * 根据用户id查找所有默认地址
     * @param uid
     * @param isFake
     * @return
     */
    @Transactional
    public List<TbAddress> findAdsByIsdftTool_all(Integer uid,boolean isFake) {
        TbAddressExample tbAddressExample =new TbAddressExample();
        TbAddressExample.Criteria tc = tbAddressExample.createCriteria().andUserIdEqualTo(uid).andIsDefaultEqualTo((byte)1);
        if(isFake){
            tc.andDeletedEqualTo((byte)0);
        }
        List<TbAddress> list = null;
            list = tbAddressMapper.selectByExample(tbAddressExample);
        return list;
    }
    /**
     * 增加地址
     * @param ta
     * @return
     */
    @Override
    @Transactional
    public Boolean istAds(TbAddress ta,boolean isFake,String pcc) {
        //切割地址
        ta = splitAds(ta, pcc);
        //默认地址处理
        if(ta.getIsDefault()==1){
            reSetAllDefault(ta.getUserId(),isFake);
        }
        //
        ta.setAddTime(new Date());
        ta.setDeleted((byte)0);
        ta.setIsLastUsed((byte)0);
        tbAddressMapper.insert(ta);
        return true;
    }

    public TbAddress splitAds(TbAddress ta, String pcc) {
        String[] pccAr = pcc.split(" ");
        int pcclength = pccAr.length;
        String province = pccAr[0];
        String city=pccAr[0];
        String county = pccAr[0];
        if(pcclength>1) {
            county = pccAr[1];
            if (pcclength > 2) {
                city = pccAr[1];
                 county = pccAr[2];
            }
        }
        ta.setProvince(province);
        ta.setCity(city);
        ta.setCounty(county);
        return ta;
    }

    /**
     * 更新地址,先把原先的默认地址取消,再设置
     * @param ta
     * @return
     */
    @Override
    @Transactional
    public Boolean updtAds(TbAddress ta,boolean isFake,String pcc) {
        //切割地址
        ta = splitAds(ta, pcc);
        TbAddress ta_db = findAdsById(ta.getUserId(),ta.getId(),isFake);
        if(ta_db.getIsDefault()!=ta.getIsDefault()){
            if(ta.getIsDefault()==1){
                reSetAllDefault(ta.getUserId(),isFake);
            }
        }
        ta.setUpdateTime(new Date());
        ta.setDeleted((byte)0);
        ta.setAddTime(ta_db.getAddTime());
        ta.setIsLastUsed((byte)0);
        System.err.println("xxx"+ta.getId());
        tbAddressMapper.updateByPrimaryKey(ta);
        return true;
    }

    /**
     * 删除地址
     * @param isFake   代表是否伪删除(把状态改为1,并取消默认地址的状态,1:已删除0:正常)
     * @return
     */
    @Override
    @Transactional
    public Boolean dltAds(Integer uid,Integer id,boolean isFake) {
        if(isFake){
            TbAddress ta = findAdsById(uid,id,isFake);
            ta.setDeleted((byte)1);
            ta.setIsDefault((byte)0);
            ta.setIsLastUsed((byte)0);
            tbAddressMapper.updateByPrimaryKey(ta);
            return true;
        }else{
            TbAddressExample tbAddressExample =new TbAddressExample();
            tbAddressExample.createCriteria().andUserIdEqualTo(uid).andIdEqualTo(id);
            tbAddressMapper.deleteByExample(tbAddressExample);
            return true;
        }
    }

    /**
     * 工具方法,设置默认地址时先进行比对,如果相同就跳过,不相同则更改
     * @param ta
     * @param isDft
     * @return
     */
    @Transactional
    public Boolean skipChangeIfSame(TbAddress ta,int isDft){
        int taIsDefault = ta.getIsDefault();
        if(taIsDefault==isDft){
            return null;
        }else{
            ta.setIsDefault((byte)isDft);
            tbAddressMapper.updateByPrimaryKey(ta);
            return true;
        }
    }
    /**
     * 工具方法,上次使用
     * @param ta
     * @return
     */
    @Transactional
    public Boolean skipChangeIfSame_isLastUsed(TbAddress ta,int isLastUsed){
        Byte ilu = ta.getIsLastUsed();
        int taisLastUsed = ilu == null ? 0:ilu;
        if(taisLastUsed==isLastUsed){
            return null;
        }else{
            ta.setIsLastUsed((byte)isLastUsed);
            tbAddressMapper.updateByPrimaryKey(ta);
            return true;
        }
    }
    /**
     * 判断是否全部为默认地址,是则下一步,不是则生成默认地址集合再下一步.下一步:为空和为1判断.下一步:根据集合循环取消默认
     * @param allList 传过来的集合
     * @param isAllDft 集合是否全部为默认地址
     * @return
     */
    public boolean reSetAllDefaultLeftOneTool_createList(List<TbAddress> allList,boolean isAllDft){
        if(isAllDft){
            return reSetAllDefaultTool_judge0and1(allList);
        }else{
            List<TbAddress> list =  new ArrayList<>();
            allList.forEach(e->{
                if(e.getIsDefault()==1){
                    list.add(e);
                }
            });
            return reSetAllDefaultTool_judge0and1(list);
        }
    }

    /**
     * 整理地址的工具方法,判断传过来的集合,进行一下为空判断,不为空则下一步循环集合
     * @param allList
     * @return
     */
    public boolean reSetAllDefaultTool_judge0(List<TbAddress> allList){
        if(allList==null){

        }else{
            int aSize = allList.size();
            if(aSize==0){
                return true;
            }else {
                reSetAllDefaultTool_forEach(allList,aSize);
            }
        }
        return false;
    }
    /**
     * 整理地址的工具方法,根据传过来的集合和size循环取消默认状态
     * @param allList
     * @return
     */
    @Transactional
    public boolean reSetAllDefaultTool_forEach(List<TbAddress> allList,Integer aSize){
        for (int i = 0; i < aSize; i++) {
//            if(!skipChangeIfSame(allList.get(i), 0)){
//                return false;
//            }
            skipChangeIfSame(allList.get(i), 0);
        }
        return true;
    }
    /**
     * 整理地址的工具方法,判断传过来的集合,进行一下为空判断,为0和1时则正常,有多个时则下一步循环集合
     * @param allList
     * @return
     */
    public boolean reSetAllDefaultTool_judge0and1(List<TbAddress> allList){
        if(allList==null){

        }else{
            int aSize = allList.size();
            if(aSize==0){

            }else if(aSize==1) {
                return false;
            }else{
                reSetAllDefaultTool_forEach(allList,aSize);
            }
        }
        return false;
    }
    /**
     * 检查地址,判断当前用户是否只有1个默认地址,大于则取消所有的默认地址
     * @return
     */
    @Override
    @Transactional
    public Boolean checkAds(Integer uid,boolean isFake) {
        //检查出默认地址大于1时,取消所有的默认地址
        return reSetAllDefaultTool_judge0and1(findAdsByIsdftTool_all(uid,isFake));
    }
    /**
     * 工具方法,有默认地址则取消所有的默认地址
     * @return
     */
    public boolean reSetAllDefault(Integer uid,boolean isFake){
        return reSetAllDefaultTool_judge0(findAdsByIsdftTool_all(uid,isFake));
    }
    /**
     * 删除所有伪标记地址
     * @return
     */
    @Override
    @Transactional
    public Boolean cleanAllDltAds(Integer uid){
        TbAddressExample tbAddressExample =new TbAddressExample();
        tbAddressExample.createCriteria().andUserIdEqualTo(uid).andDeletedEqualTo((byte)1);
        tbAddressMapper.deleteByExample(tbAddressExample);
        return true;
    }

    /**
     * 查找当前用户的购物车所有物品,根据用户用户所选物品的id数组,筛选出id相等的物品
     * @param uid 用户id
     * @param ids 购物车物品id数组
     * @return
     */
    @Override
    @Transactional
    public List<HashMap<String, Object>> generCarts(Integer uid,String ids) {
        List<HashMap<String, Object>> lhm = cartService.generCarts(uid);
        List<HashMap<String, Object>> lhm1 = new ArrayList<>();

        List<Integer> list = new ArrayList<>();
        String[] strs = ids.split(",");
        for (int i = 0; i < strs.length; i++) {
            list.add(Integer.valueOf(strs[i]));
        }

        lhm.forEach(e->{
            if(generCartsTool_compare(e.get("id"),list)){
                lhm1.add(e);
            }
        });
        return lhm1;
    }

    public boolean generCartsTool_compare(Object key1,List<Integer> ids){
        Integer key=Integer.parseInt(key1.toString());
        for(int i=0,j=ids.size();i<j;i++){
            Integer e = ids.get(i);
            //System.err.println("========================key.equals(e)"+key+"equals"+e+"=="+key.equals(e));
            if(key.equals(e)){
                return true;
            }
        }
        return false;
    }


    @Override
    @Transactional
    public TbPurchaseForm formSetAds(Integer uId,Integer adsId, TbPurchaseForm form,boolean isFake) {
        form.setSendAddressId(adsId);
        form.setRcvAddressId(adsId);
        //
        TbAddress taDb = findAdsById(uId,adsId,isFake);
        StringBuffer sb = new StringBuffer();
        sb.append(taDb.getProvince())
                .append(taDb.getCity())
                .append(taDb.getCounty())
                .append(taDb.getAddressDetail());
        form.setRcvAddress(sb.toString());
        TbAddress ta = findAdsById(uId,adsId,isFake);
        //
        findAdsByIsLastUsedTool_reset( queryAds(uId,isFake));
        ta.setIsLastUsed((byte) 1);
        tbAddressMapper.updateByPrimaryKeySelective(ta);
        return form;
    }
}
