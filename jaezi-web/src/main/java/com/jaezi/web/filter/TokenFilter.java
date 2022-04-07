package com.jaezi.web.filter;

import com.jaezi.common.constant.RequestConst;
import com.jaezi.common.util.*;
import com.jaezi.web.LicenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author jaezi.com
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2020/8/28  13:07
 * @description
 */
public class TokenFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(TokenFilter.class);

//    @Autowired
//    private UserService userService;

    @Autowired
    private LicenseService licenseService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        log.info(IpUtil.getIpAddress(request));
//        if (!ObjectUtils.isEmpty(IpUtils.getIpValueCache(IpUtil.getIpAddress(request)))) {
//            log.warn("您的IP已被锁定,请稍后再试！");
//            request.getRequestDispatcher("/ac/416").forward(request, response);
//            return;
//        } else {
//            if (IpUtils.getIpListCacheSum(IpUtil.getIpAddress(request)).equals(IpUtils.getIpLimited())) {
//                IpUtils.setIpMapCache(IpUtil.getIpAddress(request), System.currentTimeMillis());
//            }
//        }

        // license 校验
        licenseService.afterPropertiesSet();

        String path = request.getServletPath();
        String method = request.getMethod();

        if (validationRules(path, method)) {
            chain.doFilter(request, response);
        } else {
            String token = request.getHeader("Credential");

            if (StringUtil.isNotEmpty(token)) {
                if (JwtUtil.verify(token)) {
                    chain.doFilter(request, response);
                    return;
//                    String userToken = userService.getToken(JwtUtil.getUserId(token));
//                    if (StringUtil.isNotEmpty(userToken)) {
//                        if (token.equals(userToken)) {
//                            chain.doFilter(request, response);
//                            return;
//                        }
//                        log.warn("您的账号已在别处登录！");
//                        request.getRequestDispatcher("/ac/412").forward(request, response);
//                        return;
//                    } else {
//                        log.warn("Token不存在！");
//                    }
                } else {
                    log.warn("Token已过期！");
                }
            } else {
                log.warn("Token不存在！");
            }
            request.getRequestDispatcher("/ac/401").forward(request, response);
        }
    }

    /**
     * 放行规则校验
     *
     * @param path   请求路径
     * @param method 请求方法
     * @return Boolean
     * @author warren
     * @date 2021/8/4
     * @since 1.0
     */
    private Boolean validationRules(String path, String method) {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        return path.endsWith("/login")
                || path.endsWith("/logout")
                || path.endsWith("/register")
                || (antPathMatcher.match("/info/message/{id}", path) && RequestConst.GET.getMethod().equals(method))
                || (antPathMatcher.match("/sys/dict/{dictType}", path) && RequestConst.GET.getMethod().equals(method))
                || (antPathMatcher.match("/sys/user/forget", path) && RequestConst.PUT.getMethod().equals(method))
                || (antPathMatcher.match("/sys/resource", path) && RequestConst.GET.getMethod().equals(method))
                || (path.endsWith("/info/message") && RequestConst.GET.getMethod().equals(method));
    }
}