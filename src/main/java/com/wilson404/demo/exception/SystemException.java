package com.wilson404.demo.exception;

public class SystemException extends RuntimeException {
    SystemException(Throwable throwable){
        super(throwable);
    }
}
