package com.zjjzfy.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjjzfy.common.config.OrgType;
import com.zjjzfy.common.config.ReturnStatus;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.interfaces.TbOrgInfoMapper;
import com.zjjzfy.interfaces.TbUserInfoMapper;
import com.zjjzfy.interfaces.TbUserOrgMapper;
import com.zjjzfy.pojo.*;
import com.zjjzfy.user.service.RoleService;
import com.zjjzfy.user.service.UserOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserOrgServiceImpl implements UserOrgService {

    @Autowired
    private RoleService roleService;
    @Autowired
    private TbUserOrgMapper tbUserOrgMapper;
    @Autowired
    private TbOrgInfoMapper tbOrgInfoMapper;
    @Autowired
    private TbUserInfoMapper tbUserInfoMapper;

    /**
     * 所有订货商用户
     *
     * @param username
     * @param nickname
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public SupplyResult getIndentorUser(String username, String nickname, String branchNo, Integer pageNum, Integer pageSize) {

        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> users = tbUserOrgMapper.getIndentorUser(username, nickname, branchNo);
        PageInfo<Map<String, Object>> page = new PageInfo<>(users);
        return SupplyResult.ok(page);
    }

    /**
     * 查询用户可审核单位 下的用户
     *
     * @param uId
     * @return
     */
    @Override
    public SupplyResult getUserOrgUserId(Integer uId) {
        List<Integer> list = tbUserOrgMapper.selectChildId(uId);
        if (list == null || list.size() <= 0) {
            return SupplyResult.build(ReturnStatus.NOT_AUTHORIZATION.getStatus(), "未具有审核权限");
        }
        return SupplyResult.ok(list);
    }

    /**
     * 查询用户下可审核单位
     *
     * @param uId
     * @return
     */
    @Override
    public SupplyResult getUserOrgId(Integer uId) {
        List<Integer> list = tbUserOrgMapper.selectUserOrgId(uId);
        return SupplyResult.ok(list);
    }

    /**
     * 分页查询所有商户
     *
     * @param pageSize
     * @param pageNo
     * @param ot
     * @return
     */
    @Override
    public PageInfo<TbOrgInfo> selectOrgPageByType(Integer pageSize, Integer pageNo, OrgType ot) {
        if (pageNo == null) {
            pageNo = 1;
        }

        if (pageSize == null) {
            pageSize = 10;
        }

        if (ot == null) {
            return null;
        }

        PageHelper.startPage(pageNo, pageSize);
        TbOrgInfo org = new TbOrgInfo();
        org.setType(ot.getType());
        org.setIsDelete(false);
        final TbOrgInfoMapper tbOrgInfoMapper = this.tbOrgInfoMapper;
        List<TbOrgInfo> orgs = tbOrgInfoMapper.select(org);
        return new PageInfo(orgs);
    }

    @Override
    public List<TbOrgInfo> selectOrg(OrgType ot) {
        TbOrgInfo org = new TbOrgInfo();
        org.setType(ot.getType());
        org.setIsDelete(false);
        return tbOrgInfoMapper.select(org);
    }

    /**
     * 用户可审核单位修改
     *
     * @param id
     * @param orgIdStr
     * @return
     */
    @Override
    @Transactional
    public SupplyResult updateUserCheckOrg(Integer id, String orgIdStr) {
        if (id == null || id <= 0) {
            return SupplyResult.build(300, "用户不明确");
        }
//        if (orgIdStr == null || orgIdStr.equals("")) {
//            return SupplyResult.build(300, "请选择单位");
//        }
        TbUserInfo tbUserInfo = tbUserInfoMapper.selectByPrimaryKey(id);
        if (tbUserInfo == null) {
            return SupplyResult.build(300, "用户不存在");
        }
        TbUserOrgExample example = new TbUserOrgExample();
        example.createCriteria().andUserIdEqualTo(id);
        tbUserOrgMapper.deleteByExample(example);

        if (orgIdStr != null && orgIdStr != "") {
            TbUserOrg userOrg = null;
            String[] orgIds = orgIdStr.split(",");
            for (String orgId : orgIds) {
                userOrg = new TbUserOrg();
                userOrg.setUserId(id);
                userOrg.setOrgId(Integer.valueOf(orgId));
                tbUserOrgMapper.insertSelective(userOrg);
            }
        }
        return SupplyResult.ok();
    }

    @Override
    public SupplyResult checkUserOrg(TbOrgInfo currentOrg, TbOrgInfo addOrModifyOrg, Integer uId) {
        //供货方
        if (currentOrg.getType().toString().equals("1")) {

            return SupplyResult.build(300, "您所属的单位为供货方\n暂无相关权限");
        } else if (currentOrg.getType().toString().equals("2")) {

            //订货方
            List<String> data = (List<String>) roleService.getRoleByUid(uId).getData();
            if (data == null || data.size() <= 0) {

                return SupplyResult.build(300, "当前账户角色异常");
            } else {

                String userRole = data.get(0);
                if (userRole.equals("6")) {
                    //行社财务
                    if (addOrModifyOrg.getParentBranchno().equals(currentOrg.getBranchno())
                            || addOrModifyOrg.getBranchno().equals(currentOrg.getBranchno())) {
                        //添加/修改的单位为子或当前单位
                        return SupplyResult.ok();
                    } else {
                        return SupplyResult.build(300, "仅能创建子单位");
                    }
                } else {
                    //其他
                    return SupplyResult.build(300, "暂无相关权限");
                }
            }
        } else if (currentOrg.getType().toString().equals("3")) {

            //平台
            return SupplyResult.ok();
        } else {

            return SupplyResult.build(300, "暂无相关权限");
        }
    }
}