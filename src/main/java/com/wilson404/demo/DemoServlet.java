package com.wilson404.demo;

import com.google.common.collect.Maps;
import com.wilson404.demo.annotation.Controller;
import com.wilson404.demo.annotation.RequestBody;
import com.wilson404.demo.annotation.RequestMapper;
import com.wilson404.demo.base.Const;
import com.wilson404.demo.base.HttpMethod;
import com.wilson404.demo.dto.RequestKey;
import org.reflections.Reflections;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;
import java.util.Set;

public class DemoServlet extends HttpServlet {

    Map<RequestKey, RequestKey> requestKeyMap;

    @Override
    public void init() throws ServletException {
        requestKeyMap = Maps.newHashMap();
        Reflections reflections = new Reflections(getInitParameter(Const.BACE_PACKAGE));
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

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpMethod httpMethod = HttpMethod.valueOf(req.getMethod());
        String uri = req.getRequestURI();
        RequestKey requestKey = requestKeyMap.get(new RequestKey(uri, httpMethod));
        if (requestKey == null) resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        Class clazz = requestKey.getClazz();
        Object object = null;
        try {
            object = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Method method = requestKey.getMethod();
        String[] strings = new String[method.getParameterCount()];
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            Annotation[] parameter = parameterAnnotations[i];
            for (Annotation annotation : parameter) {
                if (annotation instanceof RequestBody) {
                    RequestBody requestBody = (RequestBody) annotation;
                    String parameter1 = req.getParameter(requestBody.value());
                    strings[i] = parameter1;
                }
            }
        }
        Object s = null;
        try {
            s = method.invoke(object, strings);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        PrintWriter writer = resp.getWriter();
        writer.print(s.toString());
        writer.flush();
        writer.close();
    }
}
