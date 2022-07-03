package com.zjjzfy.interfaces;

import org.apache.ibatis.annotations.Param;

public interface SortIdMapper {
    void setSortId(@Param("id") Integer id, @Param("sortId") Integer sortId);
}
