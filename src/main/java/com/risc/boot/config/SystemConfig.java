package com.risc.boot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 系统配置
 * @auhtor 李良发
 * @since 2025/8/6 17:22
 */
@Configuration
@ConfigurationProperties(prefix = "system.config")
@Data
public class SystemConfig {
    /**
     * collabora office在线编辑地址
     */
    private String collaboraUrl;
}
