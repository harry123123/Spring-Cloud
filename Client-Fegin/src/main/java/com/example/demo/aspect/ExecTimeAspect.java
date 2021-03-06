package com.example.demo.aspect;

import com.alibaba.fastjson.JSONObject;
import com.harry.annotations.ExecTime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * @Author : Harry
 * @Date :  2018-05-30 10:09
 * @Description : 统计方法执行时长的aop
 */
@Aspect
@Component
public class ExecTimeAspect {

    private static final Logger log = LoggerFactory.getLogger(ExecTime.class);

    @Pointcut("@annotation(com.harry.annotations.ExecTime)")
    public void execTime(){
    }

    @Around("execTime()")
    public Object around(ProceedingJoinPoint joinPoint){
        Object output = null;
        long l = System.currentTimeMillis();
        try {
            output = joinPoint.proceed();
            long elapsedTime = System.currentTimeMillis() - l;
            String simpleName = joinPoint.getTarget().getClass().getSimpleName();
            log.info(String.format("method [ %s.%s() ] execution time : %s ms", simpleName, joinPoint.getSignature().getName(), elapsedTime));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return output;
        }
        return output;
    }

    @Before("execTime()")
    public void before(){

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String remoteHost = request.getRemoteHost();
        log.info("RequestHost : " + remoteHost);
        log.info("RequestURL : " + request.getRequestURL());
        String method = request.getMethod();
        log.info("RequestMethod : " + method);
        Map<String, String[]> parameterMap = request.getParameterMap();
        log.info("RequestParam : " + JSONObject.toJSONString(parameterMap));
    }
}
