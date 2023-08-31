package com.risc.boot.common.service;

import com.risc.boot.config.MqttProducerConfig;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * @description: 消息发送服务
 * @author: 李良发
 * @since: 2023/8/24
 */
@Component
@MessagingGateway(defaultRequestChannel = MqttProducerConfig.CHANNEL_NAME_OUT)
public interface MqttSenderService {
    
    /**
     * 发送信息到MQTT服务器
     *
     * @param data 发送的文本
     */
    void sendToMqtt(String data);
    
    /**
     * 发送信息到MQTT服务器
     *
     * @param topic   主题
     * @param payload 消息主体
     */
    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, String payload);
    
    /**
     * 发送信息到MQTT服务器
     * <p>
     * qos: 0 至多一次，数据可能丢失 1 至少一次，数据可能重复 2 只有一次，且仅有一次，最耗性能
     *
     * @param topic   主题
     * @param qos     服务质量
     * @param payload 消息主体
     */
    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) int qos,
                    String payload);
}
