package com.zjjzfy.interfaces;

import com.zjjzfy.pojo.TbPermission;
import com.zjjzfy.pojo.TbPermissionExample;
import java.util.List;
import java.util.Map;

import com.zjjzfy.pub.PublicMapper;
import org.apache.ibatis.annotations.Param;

public interface TbPermissionMapper extends PublicMapper<TbPermission> {

    List<Map<String,Object>> selectPermissionAll();

    //查询全部子菜单
    List<TbPermission>  queryPermissionAll();
}