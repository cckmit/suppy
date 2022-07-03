package com.zjjzfy.mgt.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: hsmz
 * @date: 2019-12-13 14:08
 */
@Configuration
@ConfigurationProperties(prefix = "supply.wechat")
public class SupplyManagerProperties {

    private Boolean bindButton;

    public Boolean getBindButton() {
        return bindButton;
    }

    public void setBindButton(Boolean bindButton) {
        this.bindButton = bindButton;
    }
}
