package com.wilson404.demo.base;

public enum HttpMethod {

    GET("GET"),POST("POST");
    String name;

    HttpMethod(String name) {
        this.name = name;
    }
}
