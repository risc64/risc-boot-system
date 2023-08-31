package com.risc.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import javax.annotation.Resource;

/**
 * @description: 消息生产者配置类
 * @author: 李良发
 * @since: 2023/8/24
 */
@Configuration
public class MqttProducerConfig {
    public static final String CHANNEL_NAME_OUT = "mqttOutboundChannel";
    
    private static String clientId = "test_mqtt/producer";
    
    private static String topic = "test_mqtt_topic";
    
    @Resource
    MqttPahoClientFactory mqttClientFactory;
    
    /**
     * MQTT信息通道（生产者）
     */
    @Bean(name = CHANNEL_NAME_OUT)
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }
    
    /**
     * MQTT消息处理器（生产者）
     */
    @Bean
    @ServiceActivator(inputChannel = CHANNEL_NAME_OUT)
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(clientId, mqttClientFactory);
        messageHandler.setAsync(false);
        messageHandler.setDefaultTopic(topic);
        return messageHandler;
    }
}
