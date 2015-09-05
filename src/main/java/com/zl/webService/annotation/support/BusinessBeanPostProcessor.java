package com.zl.webService.annotation.support;

import com.zl.webService.annotation.Action;
import com.zl.webService.annotation.BusinessComponent;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by ZL on 2015/9/2.
 */
public class BusinessBeanPostProcessor implements BeanPostProcessor {

    /**
     * Action所对应的方法的集合
     */
    private Map<String, Method> methodContexts;

    public Map<String, Method> getMethodContexts() {
        return methodContexts;
    }

    public void setMethodContexts(Map<String, Method> methodContexts) {
        this.methodContexts = methodContexts;
    }

    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(final Object o, String s) throws BeansException {
        ReflectionUtils.doWithMethods (o.getClass (),
                new ReflectionUtils.MethodCallback()
                {
                    public void doWith (Method method)
                    {
                        //解析存在Action注解的方法
                        if (Action.class != null
                                && method.isAnnotationPresent (Action.class)
                                && method.equals (ClassUtils.getMostSpecificMethod(method, o.getClass())))
                        {
                            if (method.getParameterTypes ().length > 1)
                            {
                                throw new IllegalStateException (
                                        "Action requires not more than 1 arguments method: " + method);
                            }
                            //获取注解内容
                            Action action = (Action)method.getAnnotation(Action.class);
                            methodContexts.put (action.busCode (), method);
                        }

                    }
                });
        return o;
    }
}
