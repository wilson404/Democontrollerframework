package com.wilson404.demo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wilson404.demo.annotation.FileResponse;
import com.wilson404.demo.annotation.JpgResponse;
import com.wilson404.demo.annotation.RequestBody;
import com.wilson404.demo.annotation.ResponseBody;
import com.wilson404.demo.base.Const;
import com.wilson404.demo.base.DemoCache;
import com.wilson404.demo.base.HttpMethod;
import com.wilson404.demo.dto.RequestInfo;
import com.wilson404.demo.dto.RequestKey;
import com.wilson404.demo.exception.BusinessException;
import com.wilson404.demo.exception.SystemException;
import com.wilson404.demo.respHander.FileHandler;
import com.wilson404.demo.respHander.JpgHander;
import com.wilson404.demo.respHander.JsonHandler;
import com.wilson404.demo.respHander.TextHandler;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DemoServlet extends HttpServlet {

    private static final long serialVersionUID = 914976221467963220L;
    private Gson gson;

    @Override
    public void init() {
        gson = new GsonBuilder().setLenient().create();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpMethod httpMethod = HttpMethod.valueOf(req.getMethod());
        String uri = req.getRequestURI();
        RequestInfo requestInfo = DemoCache.getRequestKey(new RequestKey(uri, httpMethod));
        if (requestInfo == null) {//没有配置这个url
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        Class clazz = requestInfo.getClazz();
        Object object;
        try {
            object = clazz.newInstance();//实例化method所在的class的对象
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        Method method = requestInfo.getMethod();
        Object[] parObj = new Object[method.getParameterCount()];
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            Annotation[] parameter = parameterAnnotations[i];
            for (Annotation annotation : parameter) {
                if (annotation instanceof RequestBody) {
                    RequestBody requestBody = (RequestBody) annotation;
                    if (httpMethod.equals(HttpMethod.POST)) {
                        if (!req.getHeader("Content-Type").equals("application/json")) {//mime不为json的返回501
                            resp.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);
                            return;
                        }
                        ServletInputStream inputStream = req.getInputStream();
                        String s = IOUtils.toString(inputStream, "utf-8");//以utf-8编码读取请求体
                        parObj[i] = gson.fromJson(s, method.getParameters()[i].getType());//格式化为json对象
                    } else {
                        parObj[i] = req.getParameter(requestBody.value());
                    }
                }
            }
        }
        Object s;
        try {

            s = method.invoke(object, parObj);//利用反射执行这个方法
        } catch (BusinessException e) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        } catch (SystemException e) {
            resp.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            return;
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            return;
        }
        if (s == null) {
            resp.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            return;
        }
        ResponseHandler responseHandler;
        if (method.getAnnotation(ResponseBody.class) != null) {
            responseHandler = new JsonHandler();
        } else if (method.getAnnotation(FileResponse.class) != null) {
            responseHandler = new FileHandler();
        } else if (method.getAnnotation(JpgResponse.class) != null) {
            responseHandler = new JpgHander();
        } else {
            responseHandler = new TextHandler();
        }
        responseHandler.doResp(resp, s);
    }
}
