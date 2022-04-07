package com.jaezi.log.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.bean.ResponseEnum;
import com.jaezi.log.common.ProcessResult;
import com.jaezi.log.model.UserOperate;
import com.jaezi.log.service.UserOperateService;
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
import org.springframework.web.multipart.MultipartFile;

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
public class UserOperateAspect {

    private static final Logger log = LoggerFactory.getLogger(UserOperateAspect.class);

    private final UserOperateService userOperateService;

    private final DataSourceTransactionManager dataSourceTransactionManager;

    private final DefaultTransactionDefinition defaultTransactionDefinition;

    public UserOperateAspect(UserOperateService userOperateService, DataSourceTransactionManager dataSourceTransactionManager,
                             DefaultTransactionDefinition defaultTransactionDefinition) {
        this.userOperateService = userOperateService;
        this.dataSourceTransactionManager = dataSourceTransactionManager;
        this.defaultTransactionDefinition = defaultTransactionDefinition;
        // 事物隔离级别，开启新事务
        this.defaultTransactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
    }

    private static final String POITCUT_EXPRESSION = "(execution(* com.jaezi.*.api..*.*(..)) || execution(* com.jaezi.*.*.api..*.*(..))) && !(execution(public * com.jaezi.*.api..login(..)) || execution(public * com.jaezi.*.api..logout(..)))";

    /**
     * 用户操作日志拦截
     *
     * @param point point
     * @return Object
     * @author iuyy
     * @date 2021/07/23
     * @since 1.0
     */
    @Around(POITCUT_EXPRESSION)
    public Object aroundUserOperate(ProceedingJoinPoint point) throws Throwable {
        UserOperate userOperate = getUserOperate(point);
        Object result = point.proceed();
        saveUserLog(userOperate, result);
        return result;
    }

    /**
     * 将用户操作日志保存到数据库
     *
     * @param userOperate 用户操作日志
     * @param result      用户操作结果
     * @author iuyy
     * @date 2021/07/23
     * @since 1.0
     */
    private void saveUserLog(UserOperate userOperate, Object result) {
        // 开启事务
        // 获得事务状态
        TransactionStatus transaction = dataSourceTransactionManager.getTransaction(defaultTransactionDefinition);
        try {
            if (userOperate != null) {
                JsonResult jsonResult = JSON.parseObject(JSON.toJSONString(result), JsonResult.class);
                if (jsonResult.getCode() == ResponseEnum.SUCCESS.getCode()) {
                    userOperate.setResult(ProcessResult.SUCCESS);
                } else {
                    userOperate.setResult(ProcessResult.FAILED);
                }
                userOperate.setParams(JSON.toJSONString(userOperate.getParams()));
                userOperateService.add(userOperate);
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
     * 获取用户操作信息
     *
     * @param point point
     * @return UserOperate 用户操作信息
     * @author iuyy
     * @date 2021/07/23
     * @since 1.0
     */
    private UserOperate getUserOperate(ProceedingJoinPoint point) {
        try {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (requestAttributes != null) {
                HttpServletRequest request = requestAttributes.getRequest();
                UserOperate userOperate = UserInfoUtil.build(request);
                for (Object obj : point.getArgs()) {
                    if (!(obj instanceof HttpServletRequest) && !(obj instanceof HttpServletResponse)) {
                        if (obj instanceof MultipartFile) {
                            MultipartFile file = (MultipartFile) obj;
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("file", file.getOriginalFilename());
                            userOperate.setParams(jsonObject);
                        } else {
                            userOperate.setParams(obj);
                        }
                    }
                }
                return userOperate;
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

}
