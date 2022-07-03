package com.zjjzfy.interfaces;

import com.zjjzfy.pojo.TbUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

public interface UserInfoMapper {
    @Select("select * from tb_user_info where username=#{username}")
    TbUserInfo findUserByUsername(String username);
}
