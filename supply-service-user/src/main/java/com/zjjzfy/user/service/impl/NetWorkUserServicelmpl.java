package com.zjjzfy.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjjzfy.common.annotation.PagehelpService;
import com.zjjzfy.common.config.NetWorkerStatus;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.interfaces.TbNetworkerMapper;
import com.zjjzfy.interfaces.TbOrgInfoMapper;
import com.zjjzfy.pojo.TbNetworker;
import com.zjjzfy.pojo.TbNetworkerExample;
import com.zjjzfy.pojo.TbOrgInfo;
import com.zjjzfy.pojo.TbOrgInfoExample;
import com.zjjzfy.user.service.NetWorkUserService;
import com.zjjzfy.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author zyx
 * @date 2020/3/27 下午4:27
 */
@Service
@Slf4j
public class NetWorkUserServicelmpl implements NetWorkUserService {

    @Autowired
    private TbNetworkerMapper tbNetworkerMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private TbOrgInfoMapper tbOrgInfoMapper;

    @Override
    public SupplyResult insertNetWorkUser(TbNetworker tbNetworker) {
        if(null == tbNetworker.getOrgId()){
            return  SupplyResult.build(300,"新增失败，请重试");
        }
        tbNetworker.setCreateTime(new Date());
        tbNetworker.setBranchId(System.currentTimeMillis()+"");
        tbNetworker.setUpdateTime(new Date());
        tbNetworker.setStatus(NetWorkerStatus.ON.getValue());
        tbNetworker.setOperator(userService.getCurrentTbUserInfo().getUid());
        tbNetworkerMapper.insert(tbNetworker);
        return SupplyResult.ok("新增成功");
    }

    @Override
    public SupplyResult updateNetWorkUser(TbNetworker tbNetworker) {
        String type = userService.getOrg().getType().toString();
        if(!type.equals("3")){
            return SupplyResult.build(300,"对不起，您不具备相应权限");
        }
        tbNetworker.setUpdateTime(new Date());
        tbNetworker.setOperator(userService.getCurrentTbUserInfo().getUid());
        tbNetworkerMapper.updateByPrimaryKeySelective(tbNetworker);
        return SupplyResult.ok("修改成功");
    }

    @Override
    public SupplyResult deleteNetWorkUser(Integer id) {
        if(null == id){
            return SupplyResult.build(300,"员工id不能为空");
        }
        String type = userService.getOrg().getType().toString();
        if(!type.equals("3")){
            return SupplyResult.build(300,"对不起 您不具备删除员工的权限");
        }
        TbNetworker tbNetworker = tbNetworkerMapper.selectByPrimaryKey(id);
        if(null == tbNetworker){
            return SupplyResult.build(300,"员工信息获取失败，请重试");
        }
        tbNetworker.setOperator(userService.getCurrentTbUserInfo().getUid());
        tbNetworker.setUpdateTime(new Date());
        tbNetworker.setStatus(NetWorkerStatus.DOWN.getValue());
        tbNetworkerMapper.updateByPrimaryKey(tbNetworker);
        return SupplyResult.ok("删除成功");
    }

    @Override
    public SupplyResult selectNetWorkUser(Integer pageNo, Integer pageSize,Integer orgId) {
        if(null == pageNo){
            pageNo = 1;
        }
        if(null == pageSize){
            pageSize = 10;
        }
        String type = userService.getOrg().getType().toString();
        PageHelper.startPage(pageNo,pageSize);
        List<Map<String, Object>> maps = null;
        if(type.equals("3")){
             maps = tbNetworkerMapper.selectNetWorker(orgId);
        }else{
             maps = tbNetworkerMapper.selectNetWorker(userService.getOrg().getId());
        }
        return SupplyResult.ok(new PageInfo<>(maps));
    }

    @Override
    public SupplyResult selectOrgInfo() {
        List<Map<String, Object>> maps = tbOrgInfoMapper.selectNotDeleteOrgInfo();
        return SupplyResult.ok(maps);
    }

    @Override
    public SupplyResult selectOrgInfoByType(Byte type) {

        return null;
    }

    /**
     * 网点库存
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @PagehelpService
    @Override
    public SupplyResult selectNetWorkRepertory(Integer pageNo, Integer pageSize,Integer orgId) {
        List<Map<String, Object>> maps = tbNetworkerMapper.selectNetWorkRepertory(orgId);
        return SupplyResult.ok(maps);
    }
}
