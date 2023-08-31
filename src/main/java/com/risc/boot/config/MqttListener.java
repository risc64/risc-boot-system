package com.risc.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.MessagingException;

import javax.annotation.Resource;

/**
 * @description: 消息监听器配置类
 * @author: 李良发
 * @since: 2023/8/24
 */
@Configuration
public class MqttListener {
    
    public static final String CHANNEL_NAME_IN = "mqttInboundChannel";
    
    private static String clientId = "test_mqtt/consumer";
    
    private static String listenTopic = "test_mqtt_topic";
    
    @Resource
    MqttPahoClientFactory mqttClientFactory;
    
    /**
     * MQTT消息通道（消费者）
     */
    @Bean(name = CHANNEL_NAME_IN)
    public MessageChannel mqttInboundChannel() {
        return new DirectChannel();
    }
    
    /**
     * MQTT消息订阅绑定（消费者）
     */
    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(clientId,
                mqttClientFactory,
                listenTopic);
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        //设置消息质量：0->至多一次；1->至少一次；2->只有一次
        adapter.setQos(1);
        adapter.setOutputChannel(mqttInboundChannel());
        return adapter;
    }
    
    /**
     * MQTT消息监听器（消费者）
     * MessageHandler: org.springframework:spring-messaging
     */
    @Bean
    @ServiceActivator(inputChannel = CHANNEL_NAME_IN)
    public MessageHandler handlerMessage() {
        return message -> {
            try {
                MessageHeaders messageHeaders = message.getHeaders();
                System.out.println("messageHeaders>>" + messageHeaders);
                String string = message.getPayload().toString();
                System.out.println("接收到消息：" + string);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        };
    }
}
