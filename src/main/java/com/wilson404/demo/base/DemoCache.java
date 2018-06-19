package com.wilson404.demo.base;

import com.google.common.collect.Maps;
import com.wilson404.demo.annotation.Controller;
import com.wilson404.demo.annotation.RequestMapper;
import com.wilson404.demo.dto.RequestKey;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

public class DemoCache {
    private static final Map<RequestKey, RequestKey> requestKeyMap;

    static {
        requestKeyMap = Maps.newHashMap();
    }
    public static void makeCache(String backPackage){
        Reflections reflections = new Reflections(backPackage);
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Controller.class);
        for (Class clazz : classes) {
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                RequestMapper annotation = method.getAnnotation(RequestMapper.class);
                if (annotation == null) continue;
                String uri = annotation.uri();
                HttpMethod httpMethod = annotation.httpMethod();
                requestKeyMap.put(new RequestKey(uri, httpMethod), new RequestKey(uri, httpMethod, clazz, method));
            }
        }
    }

    public static RequestKey getRequestKey(RequestKey requestKey){
        return requestKeyMap.get(requestKey);
    }
}
