package com.zl.webService.dao.base;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.ibatis.sqlmap.engine.impl.SqlMapClientImpl;
import com.zl.webService.util.ReflectUtil;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;


/**
 * @author <a href="mailto:luckylucky3210@163.com">ZL</a>
 * @version $Id$
 *          Date: 2014-12-19
 *          Time: 14:09:00
 */

public class CustomDaoSupport extends SqlMapClientDaoSupport {

    private SqlExecutor customSqlExecutor;

    public SqlExecutor getCustomSqlExecutor() {
        return customSqlExecutor;
    }

    public void setCustomSqlExecutor(SqlExecutor customSqlExecutor) {
        this.customSqlExecutor = customSqlExecutor;
    }

    /**
     * 初始化执行
     */
    public void initialization(){
        //设置SqlExecutor
        SqlMapClient client = getSqlMapClientTemplate().getSqlMapClient();
        if(client instanceof SqlMapClientImpl){
            ReflectUtil.setFieldValue(
                    ((SqlMapClientImpl) client).getDelegate(),
                    "sqlExecutor",
                    com.zl.webService.dao.base.CustomExecutor.class,
                    customSqlExecutor);
        }
    }
}
