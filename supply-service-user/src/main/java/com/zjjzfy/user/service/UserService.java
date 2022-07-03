package com.zjjzfy.user.service;

import com.github.pagehelper.PageInfo;
import com.zjjzfy.pojo.TbOrgInfo;
import com.zjjzfy.pojo.TbUserInfo;

public interface UserService {

    TbUserInfo findUserByUserId(Integer uid);

    TbUserInfo findUserByUsername(String username);

    /*分页查询所有商户*/
    PageInfo<TbOrgInfo> selectAllSellerPaging(Integer pageSize, Integer pageNo);

    TbUserInfo getCurrentTbUserInfo();

    TbOrgInfo getOrg();

    /**
     * 通过openId获取用户信息
     *
     * @param openId
     * @return
     */
    TbUserInfo getUser(String openId);

    /**
     * 微信用户登录
     *
     * @param username
     * @param openId
     */
    void wxUserLogin(String username, String openId);


    TbOrgInfo getOne(Integer id, String branchNo);
}