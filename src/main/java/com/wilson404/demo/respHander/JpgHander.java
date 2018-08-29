package com.wilson404.demo.respHander;

import com.wilson404.demo.ResponseHandler;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class JpgHander implements ResponseHandler {
    @Override
    public void doResp(HttpServletResponse resp, Object o) {
        if (o instanceof BufferedImage){
            resp.setHeader("Content-Type","image/jpeg");
            BufferedImage bufferedImage = (BufferedImage) o;
            try {
                ImageIO.write(bufferedImage,"jpeg",resp.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
