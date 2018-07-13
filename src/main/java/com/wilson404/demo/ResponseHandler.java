package com.wilson404.demo;

import javax.servlet.http.HttpServletResponse;

public interface ResponseHandler {
    void doResp(HttpServletResponse resp, Object o);
}
