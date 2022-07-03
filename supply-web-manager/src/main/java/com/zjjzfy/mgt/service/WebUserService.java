package com.zjjzfy.mgt.service;

import java.util.Date;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjjzfy.common.config.OrgType;
import com.zjjzfy.common.config.UserState;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.common.utils.StringUtil;
import com.zjjzfy.interfaces.TbOrgInfoMapper;
import com.zjjzfy.interfaces.TbUserInfoMapper;
import com.zjjzfy.interfaces.TbUserRoleMapper;
import com.zjjzfy.pojo.*;
import com.zjjzfy.shiro.service.PasswordService;
import com.zjjzfy.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class WebUserService {

    @Autowired
    private RoleService roleService;
    @Autowired
    private WebUserService webUserService;
    @Autowired
    private PasswordService passwordService;

    @Autowired
    private TbOrgInfoMapper tbOrgInfoMapper;
    @Autowired
    private TbUserInfoMapper tbUserInfoMapper;
    @Autowired
    private TbUserRoleMapper tbUserRoleMapper;

    public PageInfo<Map<String, Object>> getUser(String username, String name, Integer orgId, String branchNo, Integer roleId, Integer pageNum, Integer pageSize) {
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> userInfos = tbUserInfoMapper.selectUser(username, name, orgId, branchNo, roleId);
        PageInfo<Map<String, Object>> page = new PageInfo<>(userInfos);

        return page;
    }

    public TbUserInfo getOne(Integer uid) {
        return tbUserInfoMapper.selectByPrimaryKey(uid);
    }

    @Transactional
    public SupplyResult insertUser(TbUserInfo tbUserInfo, TbUserInfo currentUser, TbOrgInfo currentOrg, Integer roleId) {

        TbUserRole userRole = new TbUserRole();
        userRole.setRoleId(roleId);

        TbUserInfoExample example = new TbUserInfoExample();
        example.createCriteria().andUsernameEqualTo(tbUserInfo.getUsername());
        List<TbUserInfo> list = tbUserInfoMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return SupplyResult.build(300, "账号已存在，请重新输入！");
        }

        TbOrgInfo orgInfo = tbOrgInfoMapper.selectByPrimaryKey(tbUserInfo.getOrgid());
        if (orgInfo == null) {
            return SupplyResult.build(300, "未找到对应单位！");
        }
        if (orgInfo.getType().equals(OrgType.PLATFORM.getType())) {
            //平台用户
            if (roleId == 5 || roleId == 6 || roleId == 7) {
                return SupplyResult.build(300, "新增用户单位为【"
                        + orgInfo.getOrgName()
                        + "】<br>该单位为平台方<br>请勿选择订货商、供货商角色");
            }
        } else if (orgInfo.getType().equals(OrgType.PURCHASER.getType())) {
            //行社用户/订货方用户
            if (roleId != 6 && roleId != 7) {
                return SupplyResult.build(300, "新增用户单位为【"
                        + orgInfo.getOrgName()
                        + "】<br>该单位为订货商<br>角色请选择【行社财务】或【支行采购员】");
            }
        } else {
            //商户用户/供货方用户
//            if (roleId != 5) {
//                return SupplyResult.build(300, "新增用户单位为【"
//                        + orgInfo.getOrgName()
//                        + "】<br>该单位为供货方<br>角色请选择【供货方用户】");
//            }
            userRole.setRoleId(5);
        }

        SupplyResult result = webUserService.checkUser(currentUser, currentOrg, orgInfo);
        if (result.getStatus() != 200) {
            return result;
        }

        Date currentDate = new Date();
        String salt = StringUtil.getRandomString(20);
        String password = passwordService.doGetPassword(tbUserInfo.getPassword(), tbUserInfo.getUsername() + salt);

        tbUserInfo.setPassword(password);
        tbUserInfo.setSalt(salt);
        tbUserInfo.setState(UserState.NORMAL.getType());
        tbUserInfo.setAddtime(currentDate);
        tbUserInfo.setOperator(currentUser.getUid());
        tbUserInfo.setLastPwdModifytime(currentDate);
        tbUserInfoMapper.insertUseGeneratedKeys(tbUserInfo);

        userRole.setUid(tbUserInfo.getUid());

        tbUserRoleMapper.insert(userRole);

        return SupplyResult.ok();
    }

    @Transactional
    public SupplyResult userModify(TbUserInfo tbUserInfo, TbUserInfo currentUser, TbOrgInfo currentOrg, Integer roleId) {
        if (tbUserInfo.getUid() == null) {
            return SupplyResult.build(300, "用户信息为空");
        }
        TbOrgInfo orgInfo = tbOrgInfoMapper.selectByPrimaryKey(tbUserInfo.getOrgid());
        if (orgInfo == null) {
            return SupplyResult.build(300, "未找到对应单位！");
        }

        TbUserRole userRole = new TbUserRole();
        userRole.setUid(tbUserInfo.getUid());
        userRole.setRoleId(roleId);

        if (orgInfo.getType().equals(OrgType.PLATFORM.getType())) {
            //平台用户
            if (roleId == 5 || roleId == 6 || roleId == 7) {
                return SupplyResult.build(300, "新增用户单位为【"
                        + orgInfo.getOrgName()
                        + "】<br>该单位为平台方<br>请勿选择订货商、供货商角色");
            }
        } else if (orgInfo.getType().equals(OrgType.PURCHASER.getType())) {
            //行社用户/订货方用户
            if (roleId != 6 && roleId != 7) {
                return SupplyResult.build(300, "新增用户单位为【"
                        + orgInfo.getOrgName()
                        + "】<br>该单位为订货商<br>角色请选择【行社财务】或【支行采购员】");
            }
        } else {
            //商户用户/供货方用户
            userRole.setRoleId(5);
        }

        TbUserInfoExample example = new TbUserInfoExample();
        example.createCriteria().andUsernameEqualTo(tbUserInfo.getUsername());
        List<TbUserInfo> list = tbUserInfoMapper.selectByExample(example);
        if (list != null && list.size() > 0 && list.get(0).getUid().intValue() != tbUserInfo.getUid().intValue()) {
            return SupplyResult.build(300, "账号已存在，请重新输入！");
        }

        SupplyResult result = webUserService.checkUser(currentUser, currentOrg, orgInfo);
        if (result.getStatus() != 200) {
            return result;
        }

        Date currentDate = new Date();

        tbUserInfo.setPassword(null);
        tbUserInfo.setOperator(currentUser.getUid());
        tbUserInfo.setLastPwdModifytime(currentDate);
        tbUserInfoMapper.updateByPrimaryKeySelective(tbUserInfo);

        TbUserRoleExample tbUserRoleExample = new TbUserRoleExample();
        tbUserRoleExample.createCriteria().andUidEqualTo(tbUserInfo.getUid());
        tbUserRoleMapper.deleteByExample(tbUserRoleExample);

        tbUserRoleMapper.insert(userRole);

        return SupplyResult.ok();
    }

    @Transactional
    public SupplyResult updateUserState(Integer uid) {
        if (uid == null || uid <= 0) {
            return SupplyResult.build(300, "用户信息为空");
        }
        TbUserInfo tbUserInfo = tbUserInfoMapper.selectByPrimaryKey(uid);
        byte state = tbUserInfo.getState();
        if (state == UserState.DISABLE.getType()) {
            tbUserInfo.setState(UserState.NORMAL.getType());
        } else {
            tbUserInfo.setState(UserState.DISABLE.getType());
        }
        tbUserInfoMapper.updateByPrimaryKeySelective(tbUserInfo);
        return SupplyResult.ok();
    }

    @Transactional
    public SupplyResult unbind(Integer uid) {
        if (uid == null || uid <= 0) {
            return SupplyResult.build(300, "用户信息为空");
        }
        TbUserInfo tbUserInfo = tbUserInfoMapper.selectByPrimaryKey(uid);
        tbUserInfo.setColumn1(null);
        tbUserInfoMapper.updateByPrimaryKey(tbUserInfo);
        return SupplyResult.ok();
    }

    /**
     * 检查当前用户是否用户管理用户权限
     *
     * @param userInfo
     * @return
     */
    public SupplyResult checkUser(TbUserInfo userInfo, TbOrgInfo currentOrg, TbOrgInfo addOrg) {
        List<String> data = (List<String>) roleService.getRoleByUid(userInfo.getUid()).getData();
        if (data == null || data.size() <= 0) {
            return SupplyResult.build(300, "当前用户角色不明确");
        }
        String userRole = data.get(0);
        if (userRole.equals("5")) {
            //供货方角色
            return SupplyResult.build(300, "暂未拥有相关权限");

        } else if (userRole.equals("6")) {
            //行社财务
            //只能新增当前单位和子单位用户
            if (addOrg.getBranchno().equals(currentOrg.getBranchno())
                    || addOrg.getParentBranchno().equals(currentOrg.getBranchno())) {
                return SupplyResult.ok();
            } else {
                return SupplyResult.build(300, "暂未拥有相关权限");
            }
        } else if (userRole.equals("7")) {
            //支行采购员
            return SupplyResult.build(300, "暂未拥有相关权限");
        } else {
            //其他
            return SupplyResult.ok();
        }
    }
}