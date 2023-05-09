package com.risc.boot.common.bo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * 点击位置
 * @athor 李良发
 * @date 2021/8/24 14:29
 * @sine 1.0.0
 **/
@Data
@JsonIgnoreProperties(value = {"handler"})
public class Point implements Serializable {

    private static final long serialVersionUID = 1L;

    private int x;

    private int y;

}
