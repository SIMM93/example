package com.smartbr.enums;

import java.util.Arrays;
import java.util.Optional;

public enum PermissionType {

    PERMISSION_MENU("menu", 1),
    PERMISSION_POINT("point", 2),
    PERMISSION_API("api", 3);

    public final String name;
    public final Integer value;

    PermissionType(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public static Optional<PermissionType> getInstance(Integer value) {
        return Arrays.stream(PermissionType.values())
                .filter((type) -> type.value.equals(value))
                .findFirst();
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

}
