package com.zjjzfy.mgt.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjjzfy.common.config.OrgType;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.interfaces.TbOrgInfoMapper;
import com.zjjzfy.order.service.OrgBalanceService;
import com.zjjzfy.pojo.TbOrgInfo;
import com.zjjzfy.pojo.TbOrgInfoExample;
import com.zjjzfy.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class OrganizeService {

    @Autowired
    private UserService userService;
    @Autowired
    private TbOrgInfoMapper tbOrgInfoMapper;
    @Autowired
    private OrgBalanceService orgBalanceService;

    /**
     * 获取单位
     *
     * @param branch
     * @param branchName
     * @param contact
     * @param telephone
     * @param type
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Map<String, Object>> getOrg(String userBranchNo, String branch, String branchName,
                                                String contact, String telephone,
                                                String type, Integer pageNum, Integer pageSize,Integer id) {
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }

        PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> list = tbOrgInfoMapper.selectOrg(userBranchNo, branch, branchName, contact, telephone, type,id);
        PageInfo<Map<String, Object>> page = new PageInfo<>(list);
        return page;
    }

    /**
     * 获取所有网点
     *
     * @return
     */
    public List<TbOrgInfo> getOrgAll(String branchno, Byte type) {
        TbOrgInfoExample example = new TbOrgInfoExample();
        TbOrgInfoExample.Criteria criteria = example.createCriteria();
        if (type != null) {
            criteria.andTypeEqualTo(type);
        }
        if (branchno != null && !branchno.equals("")) {
            criteria.andBranchnoNotEqualTo(branchno);
        }
        List<TbOrgInfo> list = tbOrgInfoMapper.selectByExample(example);
        return list;
    }

    /**
     * 获取所有总行单位（除自身外）
     *
     * @return
     */
    public List<TbOrgInfo> getBranch(String branchNo) {
        TbOrgInfoExample example = new TbOrgInfoExample();
        TbOrgInfoExample.Criteria criteria = null;
        if (branchNo != null && !branchNo.equals("")) {
            example.createCriteria().andParentBranchnoEqualTo("-1")
                    .andTypeEqualTo(OrgType.PURCHASER.getType()).andBranchnoNotEqualTo(branchNo);

            criteria = example.createCriteria();
            criteria.andParentBranchnoEqualTo("0")
                    .andTypeEqualTo(OrgType.PURCHASER.getType())
                    .andBranchnoNotEqualTo(branchNo);
        } else {
            example.createCriteria().andParentBranchnoEqualTo("-1")
                    .andTypeEqualTo(OrgType.PURCHASER.getType());

            criteria = example.createCriteria();
            criteria.andParentBranchnoEqualTo("0")
                    .andTypeEqualTo(OrgType.PURCHASER.getType());
        }

        example.or(criteria);
        List<TbOrgInfo> list = tbOrgInfoMapper.selectByExample(example);
        return list;
    }

    /**
     * 获取当前单位和子单位
     *
     * @param branchno
     * @return
     */
    public List<TbOrgInfo> getOrgOrParent(String branchno) {
        TbOrgInfoExample example = new TbOrgInfoExample();
        example.createCriteria().andBranchnoEqualTo(branchno);

        TbOrgInfoExample.Criteria criteria = example.createCriteria();
        criteria.andParentBranchnoEqualTo(branchno);
        example.or(criteria);
        List<TbOrgInfo> list = tbOrgInfoMapper.selectByExample(example);
        return list;
    }

    public TbOrgInfo getOne(Integer id, String branchNo) {
        if (id != null && id > 0) {
            return tbOrgInfoMapper.selectByPrimaryKey(id);
        } else {
            TbOrgInfoExample example = new TbOrgInfoExample();
            example.createCriteria().andBranchnoEqualTo(branchNo);
            List<TbOrgInfo> list = tbOrgInfoMapper.selectByExample(example);
            if (list != null && list.size() > 0) {
                return list.get(0);
            }
            return null;
        }
    }

    public SupplyResult insertOrg(TbOrgInfo tbOrgInfo) {
        if (tbOrgInfo == null) {
            return SupplyResult.build(300, "单位为空");
        }
        TbOrgInfoExample example = new TbOrgInfoExample();
        example.createCriteria().andBranchnoEqualTo(tbOrgInfo.getBranchno());
        List<TbOrgInfo> list = tbOrgInfoMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return SupplyResult.build(300, "单位编号重复，请重新填写！");
        }
        Date currentDate = new Date();
        if (tbOrgInfo.getType() == OrgType.SUPPLIER.getType() || tbOrgInfo.getType() == OrgType.PLATFORM.getType()) {
            //平台/商户单位
            tbOrgInfo.setParentBranchno("-1");
        }

        TbOrgInfo org = userService.getOrg();
        if (!org.getType().equals(OrgType.PURCHASER)) {
            tbOrgInfo.setBalance(new BigDecimal("0"));
        }

        tbOrgInfo.setCreateDate(currentDate);
        tbOrgInfo.setIsDelete(false);
        tbOrgInfo.setSupplierType(new Byte("1"));
        tbOrgInfo.setUpdateDate(currentDate);
        tbOrgInfoMapper.insertSelective(tbOrgInfo);
        return SupplyResult.ok();
    }

    @Transactional
    public SupplyResult updateOrg(TbOrgInfo tbOrgInfo, TbOrgInfo currentOrg) {
        if (tbOrgInfo == null) {
            return SupplyResult.build(300, "单位为空");
        }
        TbOrgInfoExample example = new TbOrgInfoExample();
        example.createCriteria().andBranchnoEqualTo(tbOrgInfo.getBranchno());
        List<TbOrgInfo> list = tbOrgInfoMapper.selectByExample(example);
        Integer orgId = tbOrgInfo.getId();
        if (list != null && list.size() > 0 && list.get(0).getId().intValue() != orgId.intValue()) {

            return SupplyResult.build(300, "单位编号重复，请重新填写！");
        }
        if (tbOrgInfo.getBranchno().equals(tbOrgInfo.getParentBranchno())) {
            return SupplyResult.build(300, "父单位不能与修改单位一致！");
        }
        TbOrgInfo info = tbOrgInfoMapper.selectByPrimaryKey(orgId);
        if (currentOrg != null && currentOrg.getType().equals(OrgType.PURCHASER.getType())) {
            //订货方
            if (orgId.intValue() != currentOrg.getId()) {
                log.info("info:"+info);
                log.info("tbOrgInfo:"+tbOrgInfo);
                if (info.getBalance().doubleValue() <= tbOrgInfo.getBalance().doubleValue()) {
                    Double usedBalance = (Double) orgBalanceService.getOrgAllBalance(currentOrg.getBranchno(), tbOrgInfo.getBranchno()).getData();//已使用额度
                    log.info("usedBalance:"+usedBalance);
                    log.info("currentOrg:"+currentOrg);
                    if(usedBalance == null){
                        usedBalance = Double.valueOf("0");
                    }
                    if (currentOrg.getBalance().doubleValue() < (tbOrgInfo.getBalance().doubleValue() + usedBalance)) {
                        Double surplus = (currentOrg.getBalance().doubleValue() - usedBalance) >= 0 ? (currentOrg.getBalance().doubleValue() - usedBalance) : 0;
                        return SupplyResult.build(300, "额度不足<br>当前总额度：" + currentOrg.getBalance() + "<br>可用余额：" + surplus);
                    }
                }
            }
        }
        Date currentDate = new Date();
        tbOrgInfo.setUpdateDate(currentDate);
        tbOrgInfoMapper.updateByPrimaryKeySelective(tbOrgInfo);

        orgBalanceService.getOrgBalance(tbOrgInfo.getId());
        return SupplyResult.ok();
    }

    public SupplyResult updateState(Integer id) {
        if (id == null || id <= 0) {
            return SupplyResult.build(300, "更新单位为空");
        }
        TbOrgInfo tbOrgInfo = getOne(id, null);

        if (tbOrgInfo.getIsDelete()) {
            tbOrgInfo.setIsDelete(false);
        } else {
            tbOrgInfo.setIsDelete(true);
        }
        tbOrgInfoMapper.updateByPrimaryKeySelective(tbOrgInfo);
        return SupplyResult.ok();
    }


}
