package com.jaezi.log.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.bean.ResponseEnum;
import com.jaezi.log.common.LoginType;
import com.jaezi.log.common.ProcessResult;
import com.jaezi.log.model.UserAuth;
import com.jaezi.log.service.UserAuthService;
import com.jaezi.log.vo.UserInfoUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author iuyy
 * @version v1.0
 * @corporation Copyright by iuyy.net
 * @date 2021/6/3 16:02
 * @description
 */
@Aspect
@Component
public class UserLoginAspect {

    private static final Logger log = LoggerFactory.getLogger(UserLoginAspect.class);

    private final UserAuthService userAuthService;

    private final DataSourceTransactionManager dataSourceTransactionManager;

    private final DefaultTransactionDefinition defaultTransactionDefinition;

    public UserLoginAspect(UserAuthService userAuthService, DataSourceTransactionManager dataSourceTransactionManager,
                           DefaultTransactionDefinition defaultTransactionDefinition) {
        this.userAuthService = userAuthService;
        this.dataSourceTransactionManager = dataSourceTransactionManager;
        this.defaultTransactionDefinition = defaultTransactionDefinition;
        // 事物隔离级别，开启新事务
        this.defaultTransactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
    }

    /**
     * 用户登陆拦截
     *
     * @param point point
     * @return Object
     * @author iuyy
     * @date 2021/07/23
     * @since 1.0
     */
    @Around("execution(public * com.jaezi.*.api..login(..))")
    public Object aroundUserLogin(ProceedingJoinPoint point) throws Throwable {
        // 前置操作
        UserAuth userAuth = getUserLogin(point, LoginType.LOGIN);

        Object result = point.proceed();

        // 后置操作
        saveUserLog(userAuth, result);
        return result;
    }

    /**
     * 用户退出登陆拦截
     *
     * @param point point
     * @return Object
     * @author iuyy
     * @date 2021/07/23
     * @since 1.0
     */
    @Around("execution(public * com.jaezi.*.api..logout(..))")
    public Object aroundUserLogout(ProceedingJoinPoint point) throws Throwable {
        // 前置操作
        UserAuth userAuth = getUserLogin(point, LoginType.LOGOUT);

        Object result = point.proceed();

        // 后置操作
        saveUserLog(userAuth, result);
        return result;
    }

    /**
     * 将用户登陆日志保存到数据库
     *
     * @param userAuth 用户登陆日志
     * @param result   登陆结果
     * @author iuyy
     * @date 2021/07/23
     * @since 1.0
     */
    private void saveUserLog(UserAuth userAuth, Object result) {
        // 开启事务
        // 获得事务状态
        TransactionStatus transaction = dataSourceTransactionManager.getTransaction(defaultTransactionDefinition);
        try {
            if (userAuth != null) {
                JsonResult jsonResult = JSON.parseObject(JSON.toJSONString(result), JsonResult.class);
                if (jsonResult.getCode() == ResponseEnum.SUCCESS.getCode()) {
                    userAuth.setResult(ProcessResult.SUCCESS);
                } else {
                    userAuth.setResult(ProcessResult.FAILED);
                }
                userAuthService.add(userAuth);
                //手动提交事务
                dataSourceTransactionManager.commit(transaction);
            }
        } catch (Exception e) {
            //手动回滚事务
            dataSourceTransactionManager.rollback(transaction);
            log.error(e.getMessage());
        }
    }

    /**
     * 获取用户登陆信息
     *
     * @param login 用户的动作类型，是登陆还是登出
     * @return UserLogin
     * @author iuyy
     * @date 2021/07/23
     * @since 1.0
     */
    private UserAuth getUserLogin(ProceedingJoinPoint point, String login) {
        try {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (requestAttributes != null) {
                HttpServletRequest request = requestAttributes.getRequest();
                UserAuth userAuth = UserInfoUtil.build(request, login);
                for (Object obj : point.getArgs()) {
                    if (!(obj instanceof HttpServletRequest) && !(obj instanceof HttpServletResponse)) {
                        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(obj));
                        userAuth.setUsername(jsonObject.get("username").toString());
                    }
                }

                return userAuth;
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

}
