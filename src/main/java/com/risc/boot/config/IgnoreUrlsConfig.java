package com.risc.boot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 李良发
 * @version v1.0.0
 * @since 5/8/2023 11:42 AM
 */
@Data
@Component
@ConfigurationProperties(prefix = "security.ignored")
public class IgnoreUrlsConfig {
    
    private List<String> urls = new ArrayList<>();
    
}
