package com.wilson404.demo.controller;

import com.wilson404.demo.test.controller.TestController;
import com.wilson404.demo.test.dto.DemoDto;
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
        System.out.println(controller.t2("p1"));
    }
    @Test
    public void t3() {
        System.out.println(controller.t3(new DemoDto("n1","nn1")));
    }
    @Test
    public void t4() {
        System.out.println(controller.t4());
    }

}