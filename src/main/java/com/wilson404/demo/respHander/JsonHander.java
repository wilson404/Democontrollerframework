package com.wilson404.demo.respHander;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wilson404.demo.ResponseHander;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JsonHander implements ResponseHander {

    public void doresp(HttpServletResponse resp,Object o){
        resp.setHeader("Content-Type", "application/json");
        Gson gson =new GsonBuilder().setLenient().create();
       String json = gson.toJson(o);
        try (PrintWriter writer = resp.getWriter()){
            writer.print(json);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
