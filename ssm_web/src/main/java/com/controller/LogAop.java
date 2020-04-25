package com.controller;

import com.domain.SysLog;
import com.domain.UserInfo;
import com.service.ISysLogService;
import org.apache.log4j.net.SyslogAppender;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.security.util.SecurityConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    @Autowired
    private ISysLogService sysLogService;
    @Autowired
    private HttpServletRequest httpServletResponse;
    private Date visitTime;
    private Class aClass;
    private Method method;

    //前置通知
    @Before("execution(* com.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();//获取访问开始时间
        aClass = jp.getTarget().getClass();//获取具体访问的类对象
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();//获取方法的参数

        //获取执行的方法对象
        if (args == null || args.length == 0) {
            method = aClass.getMethod(methodName);//只能获取无参方法
        } else {
            Class[] classeArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classeArgs[i] = args[i].getClass();
            }
            method =  aClass.getMethod(methodName, classeArgs );
        }

    }

    @After("execution(* com.controller.*.*(..))")
    public void doAfter(JoinPoint jp) {
        if (aClass != SysLogController.class) {//判断是否为日志类
            RequestMapping classAnnotation = (RequestMapping) aClass.getAnnotation(RequestMapping.class);
            if (classAnnotation != null) {//判断类是否为空
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);

                if (methodAnnotation != null) {//判断方法是否为空
                    String url = "";
                    //获取URL
                    url = classAnnotation.value()[0] + methodAnnotation.value()[0];

                    SysLog sysLog = new SysLog();
                    //获取访问时长
                    long time = new Date().getTime() - visitTime.getTime();
                    sysLog.setExecutionTime(time);
                    sysLog.setUrl(url);

                    //获取ip
                    String ip = httpServletResponse.getRemoteAddr();
                    sysLog.setIp(ip);
                    //获取用户
                    //获取用户
                    SecurityContext context = SecurityContextHolder.getContext();//从Security框架的上下文获取当前用户
                    User user =(User) context.getAuthentication().getPrincipal();
                    String username = user.getUsername();
                    sysLog.setUsername(username);

                    //方法名
                    sysLog.setMethod("[类名] " +aClass.getName() + "[方法名] " +method.getName());

                    sysLog.setVisitTime(visitTime);
                    //调用服务层完成操作
                    sysLogService.save(sysLog);
                }
            }
        }
    }
}
