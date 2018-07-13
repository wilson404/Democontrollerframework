package com.wilson404.demo.dto;

import com.wilson404.demo.base.HttpMethod;

import java.io.Serializable;
import java.util.Objects;

public class RequestKey implements Serializable{

    private static final long serialVersionUID = 3029709210941707546L;

    private String URI;

    private HttpMethod httpMethod;

    public RequestKey(String URI, HttpMethod httpMethod) {
        this.URI = URI;
        this.httpMethod = httpMethod;
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
