package com.risc.boot.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author 李良发
 * @version 1.0.0
 * @simth 2023/6/27 10:29
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SexEnum implements IEnum<Integer> {

    MAN(0, "男"),
    WOMAN(1, "女");

    @EnumValue
    @JsonValue    //标记响应json值
    private Integer code;
    private String name;

    SexEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public Integer getValue() {
        return code;
    }

    public String getName() {
        return name;
    }
}
