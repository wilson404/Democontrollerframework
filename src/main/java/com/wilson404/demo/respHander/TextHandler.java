package com.wilson404.demo.respHander;

import com.wilson404.demo.ResponseHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class TextHandler implements ResponseHandler {
    @Override
    public void doResp(HttpServletResponse resp, Object o) {
        resp.setHeader("Content-Type", "text/plain");
        String out = o.toString();
        try (PrintWriter writer = resp.getWriter()){
            writer.print(out);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
