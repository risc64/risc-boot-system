package com.risc.boot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Security不过滤路径配置
 * @author 李良发
 * @version v1.0.0
 * @since 5/8/2023 11:42 AM
 */
@Configuration
@ConfigurationProperties(prefix = "security.ignored")
@Data
public class IgnoreUrlsConfig {
    
    private List<String> urls = new ArrayList<>();
    
}
