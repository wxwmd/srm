package com.jaezi.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhangf
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2020/8/26  15:17
 * @description JWT工具类
 */
public class JwtUtil {

    public static final long TOKEN_EXPIRE_TIME = 30 * 24 * 3600 * 1000L;
    public static final String TOKEN_SECRET = "jaezi.com";

    /**
     * 生成 token
     */
    public static String createToken(String username, Integer userId, String realName, Integer userType, BigDecimal quota) {
        if (ObjectUtils.isEmpty(quota)) {
            quota = BigDecimal.ZERO;
        }
        int quotaData = quota.intValue();
        Date date = new Date(System.currentTimeMillis() + TOKEN_EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        // 附带username信息
        return JWT.create()
                .withClaim("username", username)
                .withClaim("userId", userId)
                .withClaim("realName", realName)
                .withClaim("userType", userType)
                .withClaim("quota", quotaData)
                //到期时间
                .withExpiresAt(date)
                //创建一个新的JWT，并使用给定的算法进行标记
                .sign(algorithm);
    }

    /**
     * 校验 token 是否正确
     */
    public static boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            //验证 token
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息，无需secret解密也能获得
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public static String getUsername(HttpServletRequest request) {
        try {
            String token = request.getHeader("Credential");
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public static Integer getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userId").asInt();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public static Integer getUserId(HttpServletRequest request) {
        try {
            String token = request.getHeader("Credential");
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userId").asInt();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public static Integer getQuota(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("quota").asInt();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public static Integer getQuota(HttpServletRequest request) {
        try {
            String token = request.getHeader("Credential");
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("quota").asInt();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public static String getRealName() {
        try {
            HttpServletRequest request = getRequest();
            String token = request.getHeader("Credential");
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("realName").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public static String getRealName(HttpServletRequest request) {
        try {
            String token = request.getHeader("Credential");
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("realName").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public static Integer getUserType() {
        try {
            HttpServletRequest request = getRequest();
            String token = request.getHeader("Credential");
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userType").asInt();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public static Integer getUserType(HttpServletRequest request) {
        try {
            String token = request.getHeader("Credential");
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userType").asInt();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
}
