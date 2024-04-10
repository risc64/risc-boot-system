//package com.risc.boot.config;
//
//import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
//import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
//
///**
// * @description: mqtt连接配置类
// * @author: 李良发
// * @since: 2023/8/24
// */
//@Configuration
//public class MqttConfig {
//    private static String servers[] = { "tcp://192.168.125.11:1883","tcp://192.168.125.12:1883","tcp://192.168.125.13:1883" };
//
//    private static String username = "admin";
//
//    private static String password = "llfadmin123";
//
//    @Bean
//    public MqttConnectOptions getMqttConnectOptions() {
//        MqttConnectOptions options = new MqttConnectOptions();
//        // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，
//        // 这里设置为true表示每次连接到服务器都以新的身份连接
//        options.setCleanSession(true);
//        // 设置连接的用户名
//        options.setUserName(username);
//        // 设置连接的密码
//        options.setPassword(password.toCharArray());
//        options.setServerURIs(servers);
//        // 设置超时时间 单位为秒
//        options.setConnectionTimeout(10);
//        // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送心跳判断客户端是否在线，但这个方法并没有重连的机制
//        options.setKeepAliveInterval(20);
//        // 设置“遗嘱”消息的话题，若客户端与服务器之间的连接意外中断，服务器将发布客户端的“遗嘱”消息。
//        //options.setWill("willTopic", WILL_DATA, 2, false);
//        return options;
//    }
//
//    @Bean
//    public MqttPahoClientFactory mqttClientFactory() {
//        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
//        factory.setConnectionOptions(getMqttConnectOptions());
//        return factory;
//    }
//}
