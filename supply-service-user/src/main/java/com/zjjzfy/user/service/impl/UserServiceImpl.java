package com.zjjzfy.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjjzfy.interfaces.TbOrgInfoMapper;
import com.zjjzfy.interfaces.TbUserInfoMapper;
import com.zjjzfy.interfaces.TbUserLogMapper;
import com.zjjzfy.pojo.*;
import com.zjjzfy.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Saintyun on 2019/3/13.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TbOrgInfoMapper tbOrgInfoMapper;
    @Autowired
    private TbUserLogMapper tbUserLogMapper;
    @Autowired
    private TbUserInfoMapper tbUserInfoMapper;

    @Override
    public TbUserInfo findUserByUserId(Integer uid) {
        TbUserInfoExample example = new TbUserInfoExample();
        example.createCriteria().andUidEqualTo(uid);
        List<TbUserInfo> users = tbUserInfoMapper.selectByExample(example);
        if (users.size() > 0) return users.get(0);
        return null;
    }

    @Override
    public TbUserInfo findUserByUsername(String username) {
        TbUserInfoExample example = new TbUserInfoExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<TbUserInfo> users = tbUserInfoMapper.selectByExample(example);
        if (users.size() > 0) return users.get(0);
        return null;
    }

    @Override
    public PageInfo<TbOrgInfo> selectAllSellerPaging(Integer pageSize, Integer pageNo) {
        if (pageNo == null) {
            pageNo = 1;
        }

        if (pageSize == null) {
            pageSize = 10;
        }

        PageHelper.startPage(pageNo, pageSize);
        TbOrgInfo org = new TbOrgInfo();
        org.setType((byte) 1);
        org.setIsDelete(false);
        List<TbOrgInfo> orgs = tbOrgInfoMapper.select(org);
        return new PageInfo(orgs);
    }

    @Override
    public TbUserInfo getCurrentTbUserInfo() {
        Subject subject = SecurityUtils.getSubject();
        TbUserInfo currentUser = (TbUserInfo) subject.getPrincipal();
        System.out.println("用户信息：" + currentUser);
        return currentUser;
    }

    @Override
    public TbOrgInfo getOrg() {
        TbUserInfo tbUserInfo = getCurrentTbUserInfo();
        TbOrgInfo org = tbOrgInfoMapper.selectByPrimaryKey(tbUserInfo.getOrgid());
        return org;
    }

    @Override
    public TbUserInfo getUser(String openId) {
        TbUserInfoExample example = new TbUserInfoExample();
        example.createCriteria().andColumn1EqualTo(openId);
        List<TbUserInfo> tbUserInfos = tbUserInfoMapper.selectByExample(example);
        if (tbUserInfos != null && tbUserInfos.size() > 0) {
            return tbUserInfos.get(0);
        }
        return null;
    }

    @Override
    public void wxUserLogin(String username, String openId) {
        try {
            TbUserInfo info = null;
            if (openId != null && !openId.equals("")) {
                info = getUser(openId);
            }
            if (info == null) {
                if (username != null && !username.equals("")) {
                    TbUserInfoExample example = new TbUserInfoExample();
                    example.createCriteria().andUsernameEqualTo(username);
                    List<TbUserInfo> tbUserInfos = tbUserInfoMapper.selectByExample(example);
                    if (tbUserInfos != null && tbUserInfos.size() > 0) {
                        info = tbUserInfos.get(0);
                    }
                }
            }
            if (info != null) {
                TbUserLog log = new TbUserLog();
                log.setUserId(info.getUid());

                if (openId != null && !openId.equals("")) {
                    info.setLastLoginTime(new Date());
                    info.setColumn1(openId);
                    tbUserInfoMapper.updateByPrimaryKeySelective(info);

                    log.setOpenId(openId);
                    log.setRemark("含openId登录");
                } else {
                    log.setRemark("普通登录");
                }
                log.setCreateTime(new Date());
                tbUserLogMapper.insertSelective(log);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
}
