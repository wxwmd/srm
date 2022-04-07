package com.jaezi.common.base;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/14/58 15:37
 * @description 自定义异常处理类
 */

public class BaseException extends Exception {

    public BaseException(String msg) throws BaseException {
        super(msg);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

}
