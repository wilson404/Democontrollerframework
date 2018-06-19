package com.wilson404.demo.test.controller;

import com.wilson404.demo.annotation.Controller;
import com.wilson404.demo.annotation.RequestBody;
import com.wilson404.demo.annotation.RequestMapper;
import com.wilson404.demo.base.HttpMethod;
import com.wilson404.demo.test.dto.DemoDto;

@Controller
public class TestController {

    @RequestMapper(uri = "/t1", httpMethod = HttpMethod.GET)
    public String t1() {
        return "catch t1!";
    }

    @RequestMapper(uri = "/t2", httpMethod = HttpMethod.GET)
    public String t2(@RequestBody("p1") String p1) {
        return "catch t2! p1 = " + p1;
    }

    @RequestMapper(uri = "/t3", httpMethod = HttpMethod.POST)
    public String t3(@RequestBody("p1") DemoDto p1) {
        return "catch t3! p1 = " + p1;
    }
}
