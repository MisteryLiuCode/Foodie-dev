package com.liu.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author liushuaibiao
 * @date 2023/5/5 14:12
 */
@Aspect
@Component
@Slf4j
public class ServiceLogAspect {

    /**
     * Aop的通知:
     * 前置通知 :在方法执行之前通知
     * 后置通知:在方法执行之后通知
     * 环绕通知:在方法之前和之后都进行通知
     * 异常通知:调用方法的过程张发生异常,则通知
     * 最终通知: 在方法调用之后执行
     */
    /*
    切面表达式:
    第一处 * 代表返回类型 * 代表返回所有类型
    第二处 包名,要监控类所在的包
    第三出 .. 代表改包及其子包下的所有类方法
    第四处 * 代表类名,*代表所有类
    第五处 *(..) *代表类中的方法名,(..)表示方法中的任何参数
     */
    @Around("execution(* com.liu.serviceImpl..*.*(..) )")
    public Object recordTimeLog(ProceedingJoinPoint joinPoint) throws Throwable {
        //打印目标对象和名称
        log.info("====== 开始执行{}.{}=======",joinPoint.getTarget().getClass(),
                joinPoint.getSignature().getName());
        //记录开始执行时间
        long begin = System.currentTimeMillis();
        //执行目标  service
        Object proceed = joinPoint.proceed();
        //记录结束时间
        long end = System.currentTimeMillis();
        //执行时间
        long takeTime = end-begin;
        if (takeTime>3000){
            log.error("=====执行结束====,耗时:{}毫秒======",takeTime);
        }else if (takeTime>2000){
            log.warn("====== 执行结束,耗时:{}");
        }else {
            log.info("===== 执行结束,耗时:{}=====",takeTime);
        }
        return proceed;
    }

}
