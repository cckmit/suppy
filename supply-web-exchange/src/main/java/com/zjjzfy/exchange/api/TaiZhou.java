package com.zjjzfy.exchange.api;

import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.pojo.JfDeliverOrder;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: jackshenonly
 * Date: 2019/5/7
 * Time: 14:14
 */
@Slf4j
public class TaiZhou implements Business {
    @Override
    public SupplyResult pay(JfDeliverOrder deliverOrder) {
        //TODO  等待台州提供接口
        try {
            Thread.sleep(3000);
        }catch (Exception e){
            e.printStackTrace();
            log.error("支付返回异常",e);
        }
        TaiZhouBusinessDto dto = new TaiZhouBusinessDto(200,"支付成功");

        return SupplyResult.build(dto.getCode(),dto.getMsg(),dto);
    }

    @Override
    public SupplyResult query() {
        //TODO  等待台州提供接口
        return SupplyResult.ok();
    }

    public static class TaiZhouBusinessDto{
        private Integer code;
        private String msg;

        public TaiZhouBusinessDto(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public TaiZhouBusinessDto() {
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}