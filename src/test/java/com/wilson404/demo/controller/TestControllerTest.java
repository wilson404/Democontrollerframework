package com.wilson404.demo.controller;

import com.wilson404.demo.test.TestService4;
import com.wilson404.demo.test.controller.TestController;
import org.junit.Before;

import javax.annotation.Resource;

public class TestControllerTest {

    @Resource
    private TestController controller;

    @Before
    public void before(){
        controller = new TestController();
    }

    @org.junit.Test
    public void t1() {
        System.out.println(controller.t1());
    }

    @org.junit.Test
    public void t2() {
    }

    @org.junit.Test
    public void t3() {
    }
}