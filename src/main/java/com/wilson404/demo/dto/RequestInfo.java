package com.wilson404.demo.dto;

import java.io.Serializable;
import java.lang.reflect.Method;

public class RequestInfo implements Serializable {
    private static final long serialVersionUID = 9151727048429123273L;
    private RequestKey requestKey;
    private Class clazz;
    private Method method;

    public RequestInfo(RequestKey requestKey, Class clazz, Method method) {
        this.requestKey = requestKey;
        this.clazz = clazz;
        this.method = method;
    }

    public RequestKey getRequestKey() {
        return requestKey;
    }

    public void setRequestKey(RequestKey requestKey) {
        this.requestKey = requestKey;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
