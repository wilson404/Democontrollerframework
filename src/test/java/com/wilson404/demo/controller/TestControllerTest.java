package com.wilson404.demo.controller;

import com.wilson404.demo.test.controller.TestController;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

public class TestControllerTest {

    @Inject
    private TestController controller;

    @Before
    public void before() {
        controller = new TestController();
    }

    @Test
    public void t1() {
        System.out.println(controller.t1());
    }

    @Test
    public void t2() {

    }

    @Test
    public void t4() {
        System.out.println(controller.t4());
    }

}