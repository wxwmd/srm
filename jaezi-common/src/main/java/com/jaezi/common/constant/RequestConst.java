package com.jaezi.common.constant;

/**
 * @author iuyy
 * @version v1.0
 * @corporation Copyright by iuyy.net
 * @date 2021/3/24 9:13
 * @description
 */
public enum RequestConst {

    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE");

    private String method;
    private RequestConst(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

}
