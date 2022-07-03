package com.zjjzfy.shiro.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

/**
 * 
 * @author      jackshenonly
 * @description shiro 参数配置类
 * @date        2019-03-14 22:35
 */

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "shiro")
@PropertySource({"classpath:shiro-config.yml"})
public class ShiroProperties {

    @Value("${hashAlgorithmName}")
    private String hashAlgorithmName;
    @Value("${hashIterations}")
    private int hashIterations;
    @Value("${globalSessionTimeout}")
    private long globalSessionTimeout;
    @Value("${loginUrl}")
    private String loginUrl;
    @Value("${successUrl}")
    private String successUrl;
    @Value("${unauthorizedUrl}")
    private String unauthorizedUrl;
    @Value("${filterChain}")
    private LinkedList<String> filterChain ;
    @Value("${filterChainAuth}")
    private LinkedList<String> filterChainAuth;


    @Override
    public String toString() {
        return "ShiroProperties{" +
                "hashAlgorithmName='" + hashAlgorithmName + '\'' +
                ", hashIterations=" + hashIterations +
                ", globalSessionTimeout=" + globalSessionTimeout +
                ", loginUrl='" + loginUrl + '\'' +
                ", successUrl='" + successUrl + '\'' +
                ", unauthorizedUrl='" + unauthorizedUrl + '\'' +
                ", filterChain=" + filterChain +
                ", filterChainAuth=" + filterChainAuth +
                '}';
    }
}

