package com.zl.webService.dao.base;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.ibatis.sqlmap.engine.scope.StatementScope;
import com.ibatis.sqlmap.engine.mapping.statement.RowHandlerCallback;
import com.ibatis.sqlmap.engine.execution.DefaultSqlExecutor;
import org.aspectj.lang.JoinPoint;

import java.sql.SQLException;
import java.sql.Connection;

/**
 * @author <a href="mailto:luckylucky3210@163.com">ZL</a>
 * @version $Id$
 *          Date: 2014-12-19
 *          Time: 13:09:46
 */

//自定义SQL
public class CustomExecutor extends DefaultSqlExecutor{
    private static final Log LOG = LogFactory.getLog(CustomExecutor.class);
    private boolean enablePage;//支持分页
    private Dialect dialect;//数据库方言

    public boolean isEnablePage() {
        return enablePage;
    }

    public void setEnablePage(boolean enablePage) {
        this.enablePage = enablePage;
    }

    public Dialect getDialect() {
        return dialect;
    }

    public void setDialect(Dialect dialect) {
        this.dialect = dialect;    }


    /**
     * 重写查询方法
     * @param statementScope
     * @param conn
     * @param sql
     * @param parameters
     * @param skipResults
     * @param maxResults
     * @param callback
     * @throws SQLException
     */
    public void executeQuery(StatementScope statementScope, Connection conn, String sql, Object[] parameters,
                             int skipResults, int maxResults, RowHandlerCallback callback) throws SQLException {
        //若进行分页
        if ((skipResults != DefaultSqlExecutor.NO_SKIPPED_RESULTS
                && maxResults != DefaultSqlExecutor.NO_MAXIMUM_RESULTS)
                && isEnablePage()) {
            //封装SQL语句
            sql = getDialect().getPageString(sql, skipResults, maxResults);
            if (LOG.isDebugEnabled()) {
                LOG.debug(sql);
            }
            //设置新的SQL语句
            //将sql执行模式设为不进行分页
            skipResults = NO_SKIPPED_RESULTS;
            maxResults = NO_MAXIMUM_RESULTS;
        }
        //执行sql语句【以不分页的模式】
        super.executeQuery(statementScope, conn, sql, parameters, skipResults, maxResults, callback);
    }
}
