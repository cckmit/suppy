package com.zjjzfy.interfaces;

import com.zjjzfy.pojo.TbNetworker;
import com.zjjzfy.pojo.TbNetworkerExample;
import java.util.List;
import java.util.Map;

import com.zjjzfy.pub.PublicMapper;
import org.apache.ibatis.annotations.Param;

public interface TbNetworkerMapper extends PublicMapper<TbNetworker> {

    List<Map<String,Object>> selectNetWorker(@Param("orgId")Integer orgId);


    List<Map<String,Object>> selectNetWorkRepertory(@Param("orgId")Integer orgId);
}