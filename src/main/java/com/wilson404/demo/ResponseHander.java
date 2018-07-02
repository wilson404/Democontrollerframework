package com.wilson404.demo;

import javax.servlet.http.HttpServletResponse;

public interface ResponseHander {
    void doResp(HttpServletResponse resp, Object o);
}
