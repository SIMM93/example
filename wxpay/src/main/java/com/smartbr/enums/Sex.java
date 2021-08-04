package com.smartbr.enums;

/**
 * 性别 枚举
 */

public enum Sex {
    WOMAN("女", 0),
    MAN("男", 1),
    SECRET("保密", 2);

    public final String type;
    public final Integer value;

    Sex(String type, Integer value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
