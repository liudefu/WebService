package com.zl.webService.dao.base;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * @author <a href="mailto:luckylucky3210@163.com">ZL</a>
 * @version $Id$
 *          Date: 2016/5/31
 *          Time: 10:31
 */
public class DbRouteDataSource extends AbstractRoutingDataSource {
    /**
     * 获取与数据源相关的key
     * 此key是Map<String,DataSource> resolvedDataSources中与数据源绑定的key值
     * 在通过determineTargetDataSource获取目标数据源时使用
     */
    @Override
    protected Object determineCurrentLookupKey() {

        return RouteHolder.getRouteKey();
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
