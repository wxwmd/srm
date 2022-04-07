package com.jaezi.web.config;

import org.apache.ibatis.logging.stdout.StdOutImpl;

/**
 * @author Warren
 * @version v1.0
 * @corporation copyright by iuyy.net
 * @date 2020/4/20  11:33
 * @description
 *
 * 控制台输出：日志 没有结果集
 */
public class NoResultSetOutImpl extends StdOutImpl {

    public NoResultSetOutImpl(String clazz) {
        super(clazz);
    }

    @Override
    public boolean isTraceEnabled() {
        return false;
    }
}
