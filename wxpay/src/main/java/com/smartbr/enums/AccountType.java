package com.smartbr.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum AccountType {

    SYSTEM_ADMIN(1, "system_admin"),
    TENANT_ADMIN(2, "tenant_admin"),
    TENANT_USER(3, "tenant_user"),
    UNKNOWN(0,"unknown");

    private final Integer code;
    private final String value;

    AccountType(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public static AccountType getInstance(Integer code) {
        return Arrays.stream(AccountType.values())
                .filter( (type) -> type.getCode().equals(code))
                .findFirst()
                .orElse(AccountType.UNKNOWN);
    }

    public static AccountType getInstance(String value) {
        return Arrays.stream(AccountType.values())
                .filter( (type) -> type.getValue().equals(value))
                .findFirst()
                .orElse(AccountType.UNKNOWN);
    }
}
