package com.wilson404.demo.respHander;

import com.wilson404.demo.ResponseHandler;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileHandler implements ResponseHandler {
    @Override
    public void doResp(HttpServletResponse resp, Object o) {
        if (o instanceof File) {
            File file = (File) o;
            if (!file.exists()) {
                try {
                    resp.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Path path = file.toPath();

            try {
                String s = Files.probeContentType(path);
                s = s != null ? s : "application/json";
                resp.setHeader("Content-Type", s);
                resp.setCharacterEncoding("utf-8");
                resp.setHeader("Content-Disposition", "attachment; filename=" + file.getName() + "");
                IOUtils.copy(new FileInputStream(file), resp.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
