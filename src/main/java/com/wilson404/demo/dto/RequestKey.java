package com.wilson404.demo.dto;

import com.wilson404.demo.base.HttpMethod;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Objects;

public class RequestKey implements Serializable{
    private String URI;
    private HttpMethod httpMethod;
    private Class clazz;
    private Method method;

    public RequestKey(String URI, HttpMethod httpMethod) {
        this.URI = URI;
        this.httpMethod = httpMethod;
    }

    public RequestKey(String URI, HttpMethod httpMethod, Class clazz, Method method) {
        this.URI = URI;
        this.httpMethod = httpMethod;
        this.clazz = clazz;
        this.method = method;
    }

    public String getURI() {
        return URI;
    }

    public void setURI(String URI) {
        this.URI = URI;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestKey that = (RequestKey) o;
        return Objects.equals(URI, that.URI) &&
                httpMethod == that.httpMethod;
    }

    @Override
    public int hashCode() {
        return Objects.hash(URI, httpMethod);
    }
}
