package com.dagm.shorter.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.dagm.shorter.dto.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Login check interceptor.
 *
 * @author Guimu
 * @date 2018/04/18
 **/
@Component
@Slf4j
public class LoginInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) {
        //TODO Perform login validation; return true if validation passes, and return false if it fails.
        log.info("clientIp: [{}] Request access to the interface URL: [{}]", request.getHeader("X-Real-Ip"), request.getRequestURL());
        //Perform permission interception.
        return true;
    }

    private void littleAuthor(HttpServletResponse response) throws Exception {
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        BaseResult<String> bwJsonResult = BaseResult.generateFailureResult("You do not have permission to perform this operation.");
        writer.print(JSONObject.toJSONString(bwJsonResult, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat));
        response.flushBuffer();
        writer.close();
    }
}