package com.smartbr.enums;

public enum YesOrNo {
    NO("否", 0),
    YES("是", 1);

    public final String type;
    public final Integer value;

    YesOrNo(String type, Integer value) {
        this.type = type;
        this.value = value;
    }
}
