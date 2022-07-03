package com.zjjzfy.mobile.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: hsmz
 * @date: 2019-12-13 11:00
 */
@Configuration
@ConfigurationProperties(prefix = "supply.overpayment")
public class SupplyProperties {

    private Boolean enable;

    private Boolean orderCheck;

    private Boolean orderConfirm;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Boolean getOrderCheck() {
        return orderCheck;
    }

    public void setOrderCheck(Boolean orderCheck) {
        this.orderCheck = orderCheck;
    }

    public Boolean getOrderConfirm() {
        return orderConfirm;
    }

    public void setOrderConfirm(Boolean orderConfirm) {
        this.orderConfirm = orderConfirm;
    }
}
