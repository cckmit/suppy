package com.zjjzfy.interfaces;

import com.zjjzfy.pojo.TbDeliverForm;
import com.zjjzfy.pub.PublicMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public interface OverTimeDeliverMapper extends PublicMapper<TbDeliverForm> {
    List<TbDeliverForm> selectOverTimeDelivers(@Param("twoDaysEarly") Date twoDaysEarly);

    void updatePurchaseFormQuantityByDeliverFormId(@Param("deliverFormId")Integer deliverFormId);

    void updateDeliverFormStatusByDeliverFormId(@Param("deliverFormId")Integer deliverFormId);
}
