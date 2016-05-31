package com.zl.webService.annotation;

import java.lang.annotation.*;

/**
 * @author <a href="mailto:luckylucky3210@163.com">ZL</a>
 * @version $Id$
 *          Date: 2016/5/31
 *          Time: 11:09
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CustomDataSource {
    String key() default "master";
}
