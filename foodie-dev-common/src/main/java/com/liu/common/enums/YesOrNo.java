package com.liu.common.enums;

/**
 * @author liushuaibiao
 * @date 2023/4/28 10:14
 * 是否枚举
 */
public enum YesOrNo {
    YES(1,"是"),
    NO(0,"否");

    public final Integer type;

    public final String value;

    YesOrNo(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
