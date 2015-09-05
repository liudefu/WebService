package com.zl.webService.annotation;

import java.lang.annotation.*;

/**
 * Created by ZL on 2015/9/2.
 * 自定义注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BusinessComponent {
    /**
     * 业务组件名称
     * @return
     */
    String name()  default "BusinessComponent";
}
