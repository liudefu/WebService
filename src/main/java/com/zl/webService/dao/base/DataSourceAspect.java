package com.zl.webService.dao.base;

import com.zl.webService.annotation.CustomDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * @author <a href="mailto:luckylucky3210@163.com">ZL</a>
 * @version $Id$
 *          Date: 2016/5/31
 *          Time: 11:06
 */
public class DataSourceAspect {

     private static Log LOG = LogFactory.getLog(DataSourceAspect.class);

    /**
     * 在切面中设置当前线程数据源路由的key
     * @param point
     */
    public void doBefore(JoinPoint point)
    {
        //获取注解
        CustomDataSource dataSource =
                ((MethodSignature)point.getSignature()).getMethod().getAnnotation(
                        CustomDataSource.class);
        LOG.debug("设置数据源：" + dataSource.key());
        RouteHolder.setRouteKey(dataSource.key());
    }

    /**
     * 在切面中清除当前线程数据源路由的key
     * @param point
     */
    public void doAfter(JoinPoint point)
    {
        //获取注解
        CustomDataSource dataSource =
                ((MethodSignature)point.getSignature()).getMethod().getAnnotation(
                        CustomDataSource.class);
        LOG.debug("清除数据源：" + dataSource.key());
        RouteHolder.removeRouteKey();
    }
}
