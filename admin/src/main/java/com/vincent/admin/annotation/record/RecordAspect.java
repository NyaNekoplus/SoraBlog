package com.vincent.admin.annotation.record;

import com.vincent.admin.enums.UserOperation;
import com.vincent.admin.holder.RequestHolder;
import com.vincent.admin.util.AopUtil;
import com.vincent.admin.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author Vincent Tsai
 * @date 2021/4/16 22:46
 */
@Slf4j
@Aspect
@Component("RecordAspect")
public class RecordAspect {
    @Autowired
    RecordHandler recordHandler;

    @Pointcut(value = "@annotation(record)")
    public void pointcut(VisitRecord record) {

    }

    @Around(value = "pointcut(record)")
    public Object doAround(ProceedingJoinPoint joinPoint, VisitRecord record) throws Throwable {

        //先执行业务
        Object result = joinPoint.proceed();

        try {
            // 日志收集
            handle(joinPoint);

        } catch (Exception e) {
            log.error("日志记录出错!", e);
        }

        return result;
    }

    private Method getMethod(JoinPoint point) throws NoSuchMethodException {
        Signature sig = point.getSignature();
        MethodSignature msig = (MethodSignature) sig;
        Object target = point.getTarget();
        return target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
    }

    private void handle(ProceedingJoinPoint point) throws Exception {

        HttpServletRequest request = RequestHolder.getRequest();


        Object object =  point.getArgs();

        Method currentMethod = getMethod(point);
        //获取操作名称
        VisitRecord annotation = currentMethod.getAnnotation(VisitRecord.class);
        boolean save = annotation.isSave();
        String eventName = annotation.value();
        log.info("{} | {}", eventName, IpUtil.getIpAddr(request));
        if (!save) {
            return;
        }

        UserOperation operation = annotation.operation();
        // 获取参数名称和值
        Map<String, Object> nameAndArgsMap = AopUtil.getFieldsName(point);

        String addOn = UserOperation.getAddOn(operation, nameAndArgsMap, eventName);

        /*
        Long userUid = null;
        if (request.getAttribute(SysConf.USER_UID) != null) {
            userUid = request.getAttribute(SysConf.USER_UID).toString();
        }
         */
        // 异步存储日志
        //String token = request.getHeader("Authorization");
        recordHandler.setRecordHandler(operation.getOperation(), addOn);
        recordHandler.onRun();
    }
}
