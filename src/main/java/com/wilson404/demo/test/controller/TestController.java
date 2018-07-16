package com.wilson404.demo.test.controller;

import com.wilson404.demo.annotation.*;
import com.wilson404.demo.base.HttpMethod;
import com.wilson404.demo.test.dto.DemoDto;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Controller
@Resource
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

    @RequestMapper(uri = "/t4", httpMethod = HttpMethod.GET)
    @ResponseBody
    public DemoDto t4() {
        System.out.println("catch t4!");
        DemoDto demoDto = new DemoDto();
        demoDto.setName("t4");
        return demoDto;
    }

    @RequestMapper(uri = "/t5", httpMethod = HttpMethod.GET)
    @FileResponse
    public File t5() {
        File file = null;
        try {
            BufferedImage bufferedImage = new BufferedImage(100,100,BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics2D = bufferedImage.createGraphics();
            graphics2D.drawString("test",50,50);
            file = File.createTempFile("temp",".jpg");
            ImageIO.write(bufferedImage,"JPG",file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
