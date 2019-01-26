package com.qiqi.annotation;

import org.springframework.stereotype.Component;
import java.lang.annotation.*;

/**
 * 自定义注解: instruction:指令类型
 */
@Target(ElementType.TYPE)//对类或接口等的注解
@Retention(RetentionPolicy.RUNTIME) //运行时注解依然会存在
@Documented
@Component
public @interface Handler {
    /*
       指令
     */
    short instruction() default 0;
}
