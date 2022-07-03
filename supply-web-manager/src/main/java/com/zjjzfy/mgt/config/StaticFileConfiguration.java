package com.zjjzfy.mgt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticFileConfiguration implements WebMvcConfigurer {
    @Value("${spring.pcImgAddr}")
    private String pcImgAddr;
    @Value("${spring.pcImgAds}")
    private String pcImgAds;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
      /*  File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String gitPath = path.getParentFile().getParentFile().getParent() + File.separator + "upload" + File.separator;
        registry.addResourceHandler("/upload/**").addResourceLocations(gitPath);*/
        registry.addResourceHandler("/upload/userImg/**").addResourceLocations("file:"+pcImgAddr+"/upload/userImg/");
        registry.addResourceHandler("/upload/adImg/**").addResourceLocations("file:"+pcImgAds+"/upload/adImg/");
        registry.addResourceHandler("/mgt/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/mgt/user/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/mgt/images/**").addResourceLocations("classpath:/static/images/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/**").addResourceLocations("classpath:/");
    }
}