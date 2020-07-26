package top.quhailong.operation.ascpect;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import top.quhailong.operation.annotation.OperationLog;
import top.quhailong.operation.dao.ControllerOperationLogDao;
import top.quhailong.operation.dao.ExceptionOperationLogDao;
import top.quhailong.operation.entity.ControllerOperationLogDO;
import top.quhailong.operation.entity.ExceptionOperationLogDO;
import top.quhailong.operation.utils.IDUtils;
import top.quhailong.operation.utils.IPUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * aop配置
 *
 * @author: quhailong
 * @date: 2020/7/26
 */
@Aspect
@Component
public class OperationLogHandler {
    @Autowired
    private ExceptionOperationLogDao exceptionOperationLogDao;
    @Autowired
    private ControllerOperationLogDao controllerOperationLogDao;

    /**
     * 设置操作日志切入点 记录操作日志 在注解的位置切入代码
     */
    @Pointcut("@annotation(top.quhailong.operation.annotation.OperationLog)")
    public void operationLogPointCut() {
    }

    /**
     * 设置操作异常切入点记录异常日志 扫描所有controller包下操作
     */
    @Pointcut("execution(* top.quhailong.operation.controller..*.*(..))")
    public void operationExceptionLogPointCut() {
    }


    /**
     * 正常返回通知，拦截用户操作日志，连接点正常执行完成后执行， 如果连接点抛出异常，则不会执行
     *
     * @author: quhailong
     * @date: 2020/7/26
     */
    @AfterReturning(value = "operationLogPointCut()", returning = "keys")
    public void saveControllerOperationLog(JoinPoint joinPoint, Object keys) {
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes
                .resolveReference(RequestAttributes.REFERENCE_REQUEST);
        ControllerOperationLogDO controllerOperationLogDO = new ControllerOperationLogDO();
        try {
            controllerOperationLogDO.setOperationId(IDUtils.showNextId(new Random().nextInt(30)).toString()); // 主键ID

            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            // 获取切入点所在的方法
            Method method = signature.getMethod();
            // 获取操作
            OperationLog operationLog = method.getAnnotation(OperationLog.class);
            if (operationLog != null) {
                String operationModule = operationLog.operationModule();
                String operationType = operationLog.operationType();
                String operationDesc = operationLog.operationDesc();
                controllerOperationLogDO.setOperationModule(operationModule); // 操作模块
                controllerOperationLogDO.setOperationType(operationType); // 操作类型
                controllerOperationLogDO.setOperationDesc(operationDesc); // 操作描述
            }
            // 获取请求的类名
            String className = joinPoint.getTarget().getClass().getName();
            // 获取请求的方法名
            String methodName = method.getName();
            methodName = className + "." + methodName;

            controllerOperationLogDO.setOperationMethod(methodName); // 请求方法
            Object[] args = joinPoint.getArgs();
            String queryString = request.getQueryString();
            String params = "";
            //获取请求参数集合并进行遍历拼接
            if (args.length > 0) {
                Object object = args[0];
                Map map = getKeyAndValue(object);
                if (map != null && map.size() > 0) {
                    params = JSON.toJSONString(map);
                } else {
                    params = queryString;
                }
            }
            controllerOperationLogDO.setOperationRequestParam(params); // 请求参数
            controllerOperationLogDO.setOperationResponseParam(JSON.toJSONString(keys)); // 返回结果
            String logContent = MDC.get("logContent");
            controllerOperationLogDO.setOperationContent(logContent);
            controllerOperationLogDO.setOperationIp(IPUtils.getLocalIp(request)); // 请求IP
            controllerOperationLogDO.setOperationUri(request.getRequestURI()); // 请求URI
            controllerOperationLogDO.setOperationCreateTime(new Date()); // 创建时间
            controllerOperationLogDao.insert(controllerOperationLogDO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 异常返回通知，用于拦截异常日志信息 连接点抛出异常后执行
     *
     * @author: quhailong
     * @date: 2020/7/26
     */
    @AfterThrowing(pointcut = "operationExceptionLogPointCut()", throwing = "e")
    public void saveExceptionLog(JoinPoint joinPoint, Throwable e) {
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes
                .resolveReference(RequestAttributes.REFERENCE_REQUEST);
        ExceptionOperationLogDO exceptionOperationLogDO = new ExceptionOperationLogDO();
        try {
            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            // 获取切入点所在的方法
            Method method = signature.getMethod();
            exceptionOperationLogDO.setExceptionId(IDUtils.showNextId(new Random().nextInt(30)).toString());
            // 获取请求的类名
            String className = joinPoint.getTarget().getClass().getName();
            // 获取请求的方法名
            String methodName = method.getName();
            methodName = className + "." + methodName;
            Object[] args = joinPoint.getArgs();
            String queryString = request.getQueryString();
            String params = "";
            //获取请求参数集合并进行遍历拼接
            if (args.length > 0) {
                if ("POST".equals(method)) {
                    Object object = args[0];
                    Map map = getKeyAndValue(object);
                    params = JSON.toJSONString(map);
                    ;
                } else if ("GET".equals(method)) {
                    params = queryString;
                }
            }
            exceptionOperationLogDO.setExceptionRequestParam(params); // 请求参数
            exceptionOperationLogDO.setOperationMethod(methodName); // 请求方法名
            exceptionOperationLogDO.setExceptionName(e.getClass().getName()); // 异常名称
            exceptionOperationLogDO.setExceptionMessage(stackTraceToString(e.getClass().getName(), e.getMessage(), e.getStackTrace())); // 异常信息
            exceptionOperationLogDO.setOperationUri(request.getRequestURI()); // 操作URI
            exceptionOperationLogDO.setOperationIp(IPUtils.getLocalIp(request)); // 操作IP
            exceptionOperationLogDO.setOperationCreateTime(new Date()); // 发生异常时间
            exceptionOperationLogDao.insert(exceptionOperationLogDO);
        } catch (Exception operE) {
            operE.printStackTrace();
        }

    }

    /**
     * 处理参数
     *
     * @author: quhailong
     * @date: 2020/7/26
     */
    public static Map<String, Object> getKeyAndValue(Object obj) {
        Map<String, Object> map = new HashMap<>();
        // 得到类对象
        Class userCla = (Class) obj.getClass();
        /* 得到类中的所有属性集合 */
        Field[] fs = userCla.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            Field f = fs[i];
            f.setAccessible(true); // 设置些属性是可以访问的
            Object val = new Object();
            try {
                val = f.get(obj);
                // 得到此属性的值
                map.put(f.getName(), val);// 设置键值
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        return map;
    }

    /**
     * 转换异常信息为字符串
     *
     * @author: quhailong
     * @date: 2020/7/26
     */
    public String stackTraceToString(String exceptionName, String exceptionMessage, StackTraceElement[] elements) {
        StringBuffer strbuff = new StringBuffer();
        for (StackTraceElement stet : elements) {
            strbuff.append(stet + "\n");
        }
        String message = exceptionName + ":" + exceptionMessage + "\n\t" + strbuff.toString();
        return message;
    }
}

