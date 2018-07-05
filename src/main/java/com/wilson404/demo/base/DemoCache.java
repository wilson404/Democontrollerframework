package com.wilson404.demo.base;

import com.google.common.io.Resources;
import com.wilson404.demo.annotation.Controller;
import com.wilson404.demo.annotation.RequestMapper;
import com.wilson404.demo.dto.RequestKey;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.xml.XmlConfiguration;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.Set;

public class DemoCache {
    private static final Cache<RequestKey, RequestKey> urlCache;

    static {
        URL ehcache = Resources.getResource("/ehcache.xml");
        CacheManager cacheManager = CacheManagerBuilder.newCacheManager(new XmlConfiguration(ehcache));
        cacheManager.init();
        urlCache = cacheManager.getCache("urlCache", RequestKey.class, RequestKey.class);

    }

    public static void makeURLMapCache(String backPackage) {
        Reflections reflections = new Reflections(backPackage);
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Controller.class);
        for (Class clazz : classes) {
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                RequestMapper annotation = method.getAnnotation(RequestMapper.class);
                if (annotation == null) continue;
                String uri = annotation.uri();
                HttpMethod httpMethod = annotation.httpMethod();
                urlCache.put(new RequestKey(uri, httpMethod), new RequestKey(uri, httpMethod, clazz, method));
            }
        }
    }

    public static RequestKey getRequestKey(RequestKey requestKey) {
        return urlCache.get(requestKey);
    }
}
