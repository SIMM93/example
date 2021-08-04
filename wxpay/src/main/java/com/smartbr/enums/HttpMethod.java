package com.smartbr.enums;

public enum HttpMethod {

    GET(1, "GET"),
    POST(2, "POST"),
    PUT(3, "PUT"),
    DELETE(4, "DELETE");

    public final Integer type;
    public final String value;

    HttpMethod(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
