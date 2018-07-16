package com.wilson404.demo.listener;

import com.google.common.io.Resources;
import com.wilson404.demo.base.Const;
import com.wilson404.demo.base.DemoCache;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.util.Properties;

public class DemoListener implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(DemoListener.class);

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("destroy");
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        URL demo = Resources.getResource("/demo.properties");

        try(Reader reader = new FileReader(new File(demo.getFile()))) {
            Properties properties = new Properties();
            properties.load(reader);
            DemoCache.makeURLMapCache(properties.getProperty(Const.BACE_PACKAGE));
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("initialized");
    }
}
