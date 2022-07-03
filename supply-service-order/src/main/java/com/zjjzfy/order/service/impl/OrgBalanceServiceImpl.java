package com.zjjzfy.order.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjjzfy.common.config.OrgType;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.interfaces.TbBalanceRecordMapper;
import com.zjjzfy.interfaces.TbOrgBalanceMapper;
import com.zjjzfy.interfaces.TbOrgInfoMapper;
import com.zjjzfy.order.service.OrgBalanceService;
import com.zjjzfy.pojo.*;
import com.zjjzfy.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OrgBalanceServiceImpl implements OrgBalanceService {

    @Autowired
    private UserService userService;
    @Autowired
    private TbOrgInfoMapper tbOrgInfoMapper;
    @Autowired
    private TbOrgBalanceMapper tbOrgBalanceMapper;
    @Autowired
    private TbBalanceRecordMapper tbBalanceRecordMapper;

    /**
     * 消费
     *
     * @param orgId
     * @param money
     * @return
     */
    @Override
    @Transactional
    public SupplyResult usedOrgBalance(Integer orgId, BigDecimal money, Boolean overpayment) {
        SupplyResult result = getBalance(orgId);
        TbOrgBalance balance = null;
        if (result.getStatus() == 200) {
            balance = (TbOrgBalance) result.getData();
        } else {
            return result;
        }
        //判断余额是否足够
        if(overpayment){
            if ((balance.getBalance().subtract(balance.getUsedBanlance())).compareTo(money) == -1) {
            //余额不足
            return SupplyResult.build(300, "当前账户不足，余额："
                    + balance.getBalance().subtract(balance.getUsedBanlance()));
            }
        }


        TbUserInfo currentUser = userService.getCurrentTbUserInfo();
        Date currentDate = new Date();

        TbBalanceRecord record = new TbBalanceRecord();
        record.setUserId(currentUser.getUid());
        record.setOccurdate(currentDate);
        record.setOrgId(orgId);
        record.setMoney(money);
        record.setBalance(balance.getBalance().subtract(balance.getUsedBanlance().add(money)));
        record.setRemark("消费");
        tbBalanceRecordMapper.insertSelective(record);

        balance.setUsedBanlance(balance.getUsedBanlance().add(money));
        balance.setLastUsedTime(new Date());
        tbOrgBalanceMapper.updateByPrimaryKeySelective(balance);

        return SupplyResult.ok();
    }

    /**
     * 获取行社余额
     *
     * @param orgId
     * @return
     */
    @Override
    public SupplyResult getOrgBalance(Integer orgId) {
        SupplyResult result = getBalance(orgId);
        TbOrgBalance balance = null;
        if (result.getStatus() == 200) {
            balance = (TbOrgBalance) result.getData();
        } else {
            return result;
        }
        return SupplyResult.ok(balance.getBalance().subtract(balance.getUsedBanlance()));
    }

    /**
     * 获取消费记录
     *
     * @param userId
     * @return
     */
    @Override
    public SupplyResult getBalanceRecord(Integer userId) {
        TbBalanceRecordExample example = new TbBalanceRecordExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<TbBalanceRecord> records = tbBalanceRecordMapper.selectByExample(example);
        return SupplyResult.ok(records);
    }

    /**
     * 获取所有已分配的额度
     *
     * @param branch
     * @return
     */
    @Override
    public SupplyResult getOrgAllBalance(String branch, String branch2) {
        Double usedBalance = tbOrgInfoMapper.selectOrgUsedBalance(branch, branch2);
        return SupplyResult.ok(usedBalance);
    }

    public SupplyResult getBalance(Integer orgId) {
        if (orgId == null || orgId <= 0) {
            return SupplyResult.build(300, "单位不存在");
        }
        TbOrgInfo org = tbOrgInfoMapper.selectByPrimaryKey(orgId);
        if (org == null) {
            return SupplyResult.build(300, "单位不存在");
        }
        if (org.getType().equals(OrgType.SUPPLIER.getType())) {
            return SupplyResult.build(300, "该单位为商户（供货商），无消费额度！");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date currentDate = new Date();

        TbOrgBalanceExample example = new TbOrgBalanceExample();
        example.createCriteria().andOrgIdEqualTo(orgId).andMonthEqualTo(sdf.format(currentDate));
        List<TbOrgBalance> list = tbOrgBalanceMapper.selectByExample(example);
        TbOrgBalance balance = null;
        if (list == null || list.size() <= 0) {
            //不存在当月余额信息
            balance = new TbOrgBalance();
            balance.setMonth(sdf.format(currentDate));
            balance.setOrgId(orgId);
            balance.setBranchno(org.getBranchno());
            balance.setBalance(org.getBalance());
            balance.setUsedBanlance(new BigDecimal(0));
            balance.setLastUsedTime(currentDate);
            tbOrgBalanceMapper.insertUseGeneratedKeys(balance);
        } else {
            balance = list.get(0);
        }
        if (balance.getBalance().compareTo(org.getBalance()) != 0) {

            TbUserInfo currentUser = userService.getCurrentTbUserInfo();
            TbBalanceRecord record = new TbBalanceRecord();
            record.setUserId(currentUser.getUid());
            record.setOccurdate(currentDate);
            record.setOrgId(orgId);
            if (balance.getBalance().compareTo(org.getBalance()) == -1) {
                //记录额度 小于（<） 行社额度 积分增加了
                record.setMoney(org.getBalance().subtract(balance.getBalance()));//增加了多少
                record.setBalance(org.getBalance().subtract(balance.getUsedBanlance()));
                record.setRemark("额度增加");
            } else {
                //记录额度 大于（>） 行社额度 积分减少
                record.setMoney(balance.getBalance().subtract(org.getBalance()));
                if (org.getBalance().compareTo(balance.getUsedBanlance()) == -1) {
                    //行社额度小于已使用的额度
                    record.setBalance(new BigDecimal(0));
                } else {
                    record.setBalance(org.getBalance().subtract(balance.getUsedBanlance()));
                }
                record.setRemark("额度减少");
            }
            tbBalanceRecordMapper.insertSelective(record);

            //最后更正额度
            balance.setBalance(org.getBalance());
            tbOrgBalanceMapper.updateByPrimaryKeySelective(balance);
        }
        return SupplyResult.ok(balance);
    }

    @Override
    public PageInfo<Map<String, Object>> getOrgBalanceRecord(Integer orgId, String orgName, Integer pageIndex, Integer pageSize) {
        pageIndex = pageIndex == null ? 0 : pageIndex;
        pageSize = pageSize == null ? 0 : pageSize;

        PageHelper.startPage(pageIndex, pageSize);
        List<Map<String, Object>> maps = tbBalanceRecordMapper.selectOrgBalance(orgId, orgName);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(maps);
        return pageInfo;
    }
}