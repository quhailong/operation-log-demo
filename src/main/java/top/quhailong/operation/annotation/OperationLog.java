package top.quhailong.operation.annotation;

import java.lang.annotation.*;

/**
 * 操作日志注解
 *
 * @author: quhailong
 * @date: 2020/7/26
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Documented
public @interface OperationLog {
    String operationModule() default ""; // 操作模块

    String operationType() default "";  // 操作类型

    String operationDesc() default "";  // 操作说明
}
