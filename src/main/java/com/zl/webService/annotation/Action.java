package com.zl.webService.annotation;

import java.lang.annotation.*;

/**
 * Created by ZL on 2015/9/2.
 * 自定义注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Action {
    /**
     * 交易名称
     * @return
     */
    String busCode();

    /**
     * 交易名称
     * @return
     */
    String busName();
}
