package com.risc.boot.config;

import com.risc.boot.common.constant.SystemConstants;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        /**
         * 资源映射路径
         * addResourceHandler：访问映射路径
         * addResourceLocations：资源绝对路径
         */
        ApplicationHome h = new ApplicationHome(this.getClass());
        registry.addResourceHandler(SystemConstants.FILE_ROOT_PATH +"/**").addResourceLocations("file://"+h.getSource().getParent().toString()+ SystemConstants.FILE_ROOT_PATH + "/");
    }
    
}
