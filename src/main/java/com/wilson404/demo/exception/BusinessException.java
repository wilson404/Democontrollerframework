package com.wilson404.demo.exception;

public class BusinessException extends RuntimeException {
    BusinessException(String msg){
        super(msg);
    }
}
