package com.example.demo.advice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.filter.FieldFormatFilter;
import com.harry.model.ResponseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Author : Harry
 * @Date :  2018-05-30 10:09
 * @Description : 对相应数据处理
 */
@ControllerAdvice
public class MyResponseBodyAdvice implements ResponseBodyAdvice{

    private static final Logger log = LoggerFactory.getLogger(MyResponseBodyAdvice.class);

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        if (o instanceof ResponseBean){

            String jsonString = JSON.toJSONString(o, new FieldFormatFilter());
            o = JSONObject.parse(jsonString);
            log.info("The field was formatted successfully !");
        }

        return o;
    }
}
