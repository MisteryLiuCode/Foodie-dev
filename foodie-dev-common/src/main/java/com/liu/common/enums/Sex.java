package com.liu.common.enums;

/**
 * @author liushuaibiao
 * @date 2023/4/28 10:14
 * 性别枚举
 */
public enum Sex {
    woman(0,"女"),
    man(1,"男"),
    serect(2,"保密");

    public final Integer type;

    public final String value;

    Sex(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
