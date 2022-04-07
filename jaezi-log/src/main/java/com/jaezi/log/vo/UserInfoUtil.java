package com.jaezi.log.vo;

import com.jaezi.common.util.JwtUtil;
import com.jaezi.common.util.StringUtil;
import com.jaezi.log.model.UserAuth;
import com.jaezi.log.model.UserOperate;

import javax.servlet.http.HttpServletRequest;

/**
 * @author iuyy
 * @version v1.0
 * @corporation Copyright by iuyy.net
 * @date 2021/07/23 15:30
 * @description
 */
public class UserInfoUtil {

    public static UserAuth build(HttpServletRequest request, String logType){
        UserAuth userAuth = new UserAuth();
        userAuth.setLoginType(logType);

        String token = request.getHeader("credential");
        if(StringUtil.isNotEmpty(token) && JwtUtil.verify(token)){
            String username = JwtUtil.getUsername(token);
            userAuth.setUsername(username);
        }

        userAuth.setIp(com.jaezi.log.vo.UserAgentUtils.getReallyIp(request));
        userAuth.setBrowserName(com.jaezi.log.vo.UserAgentUtils.getBrowserName(request));
        userAuth.setBrowserVersion(com.jaezi.log.vo.UserAgentUtils.getBrowserVersion(request));
        userAuth.setDeviceType(com.jaezi.log.vo.UserAgentUtils.getDeviceType(request));
        userAuth.setDeviceOs(com.jaezi.log.vo.UserAgentUtils.getOs(request));
        userAuth.setOsName(com.jaezi.log.vo.UserAgentUtils.getOsName(request));
        userAuth.setOsVersion(com.jaezi.log.vo.UserAgentUtils.getOsVersion(request));
        userAuth.setCreateTime(System.currentTimeMillis());

        return userAuth;
    }

    public static UserOperate build(HttpServletRequest request){
        UserOperate userOperate = new UserOperate();

        String token = request.getHeader("credential");
        if(StringUtil.isNotEmpty(token) && JwtUtil.verify(token)){
            String username = JwtUtil.getUsername(token);
            userOperate.setUsername(username);
        }

        userOperate.setIp(com.jaezi.log.vo.UserAgentUtils.getReallyIp(request));
        userOperate.setBrowserName(com.jaezi.log.vo.UserAgentUtils.getBrowserName(request));
        userOperate.setBrowserVersion(com.jaezi.log.vo.UserAgentUtils.getBrowserVersion(request));
        userOperate.setDeviceType(com.jaezi.log.vo.UserAgentUtils.getDeviceType(request));
        userOperate.setDeviceOs(com.jaezi.log.vo.UserAgentUtils.getOs(request));
        userOperate.setOsName(com.jaezi.log.vo.UserAgentUtils.getOsName(request));
        userOperate.setOsVersion(com.jaezi.log.vo.UserAgentUtils.getOsVersion(request));
        userOperate.setCreateTime(System.currentTimeMillis());

        userOperate.setApiPath(request.getServletPath());
        userOperate.setRequestMethod(request.getMethod().toUpperCase());

        return userOperate;
    }
}
