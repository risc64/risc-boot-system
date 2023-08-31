package com.risc.boot.modules.system.controller;

import com.risc.boot.common.bo.MqMessage;
import com.risc.boot.common.bo.Result;
import com.risc.boot.common.service.MqttSenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @description: Mqtt发送数据
 * @author: 李良发
 * @since: 2023/8/24
 */
@RestController
public class MqttController {
    
    private Logger logger =  LoggerFactory.getLogger(this.getClass());
    
    @Resource
    private MqttSenderService mqttSender;
    
    /**
     * 发送MQTT消息
     */
    @PostMapping(value = "/mqtt", produces = "application/json;charset=UTF-8")
    public Result<String> sendMqtt(@RequestBody MqMessage message) {
        Result<String> result = new Result<>();
        mqttSender.sendToMqtt(message.toString());
        return result;
    }
    
    /**
     * 发送MQTT消息
     */
    @PostMapping(value = "/mqtttopic", produces = "application/json;charset=UTF-8")
    public Result<String> sendMqttTopic(@RequestBody MqMessage message) {
        Result<String> result = new Result<>();
        mqttSender.sendToMqtt("test_mqtt_topic", message.toString());
        return result;
    }
    
}
