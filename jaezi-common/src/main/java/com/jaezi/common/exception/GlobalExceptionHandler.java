package com.jaezi.common.exception;

import com.jaezi.common.bean.ResponseEnum;
import com.jaezi.common.bean.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author warren
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2020/10/27 15:37
 * @description 全局的请求参数数据校验处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * @param e Exception
     * @return JsonResult
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity defaultErrorHandler(Exception e, HttpServletRequest request) {
        e.printStackTrace();
        if (e instanceof NoHandlerFoundException) {
            return ResponseEntity.ok(new JsonResult(ResponseEnum.FAILURE_NOT_FOUND));

        } else if (e instanceof MethodArgumentNotValidException) {
            // 参数校验统一异常处理
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
            FieldError fieldError = ex.getBindingResult().getFieldError();
            log.info("MethodArgumentNotValidException, URI:【{}】,Method:【{}】", request.getRequestURI(), request.getMethod());
            log.info("请求方法:【" + ex.getParameter().getMethod() + "】");
            log.info("参数校验异常:{}({})", fieldError.getDefaultMessage(), fieldError.getField());
            return ResponseEntity.ok(new JsonResult(ResponseEnum.FAILURE.getCode(), fieldError.getDefaultMessage()));

        } else if (e instanceof HttpMessageConversionException) {
            // 校验错误拦截处理
            log.info("HttpMessageConversionException, 参数转换异常API:【{}】", request.getAttribute("org.springframework.web.servlet.HandlerMapping.bestMatchingHandler"));
            log.info("HttpMessageConversionException, 参数转换异常URI:【{}】,Method:【{}】", request.getRequestURI(), request.getMethod());
            log.error("HttpMessageConversionException handler", e);
            return ResponseEntity.ok(new JsonResult(ResponseEnum.FAILURE_PARAMETER_TYPE_WRONG));
        } else {
            log.error(e.getMessage());
            log.info("未知错误URI:【{}】,Method:【{}】", request.getRequestURI(), request.getMethod());
            return ResponseEntity.ok(new JsonResult(ResponseEnum.UNKNOWN));
        }
    }

}
