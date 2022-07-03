package com.zjjzfy.exchange.api;

import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.pojo.JfDeliverOrder;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: jackshenonly
 * Date: 2019/5/7
 * Time: 14:12
 */
public interface Business {
    SupplyResult pay(JfDeliverOrder deliverOrder);
    SupplyResult query();
}
