package com.zjjzfy.order.asynctask;

import com.alibaba.fastjson.JSON;
import com.zjjzfy.common.entity.StockInResult;
import com.zjjzfy.common.utils.HttpClientTool;
import com.zjjzfy.order.service.DeliverService;
import com.zjjzfy.pojo.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * 异步任务
 * @author: hsmz
 * @date: 2019-11-26 15:00
 */
@Service
@Slf4j
public class StockInAsyncTask {

    @Autowired
    private DeliverService deliverService;

    private static final String stockInUrl = "http://localhost:90/VShop/acceptStockFromSupply.action";

    /**
     * 永康采购方接收库存后入老的积分系统库存
     * @param openId
     * @param deliverFormId
     * @return
     */
    @Async
    public Future<StockInResult> acceptStock(String openId, Integer deliverFormId){

        List<ProductDto> productDtoList =  deliverService.queryDeliverFormProductList(deliverFormId);
        Map<String, String> paramMap = new HashMap<>(2);
        String productDtoListStr = JSON.toJSONString(productDtoList);
        paramMap.put("openId",openId);
        paramMap.put("productListStr", productDtoListStr);
        return new AsyncResult<>(JSON.parseObject(HttpClientTool.doGet(stockInUrl, paramMap), StockInResult.class));
    }
}
