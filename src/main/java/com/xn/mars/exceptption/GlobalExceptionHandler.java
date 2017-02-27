package com.xn.mars.exceptption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
 * 全局异常
 * author:Liang.qinjie
 * on 2017-02-17 08:36
 */
@ControllerAdvice
public
class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {

        return "你请求的["+req.getRequestURI()+"]出错啦！！ "+e.getMessage();
    }

    @ExceptionHandler(value = { NoHandlerFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String noHandlerFoundException(Exception ex) {
        return  HttpStatus.NOT_FOUND.value()+ "没找到"+ HttpStatus.NOT_FOUND.getReasonPhrase() ;
    }

    @ExceptionHandler(value = { ConstraintViolationException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String constraintViolationException(ConstraintViolationException ex) {
        return HttpStatus.BAD_REQUEST.value()+ HttpStatus.BAD_REQUEST.getReasonPhrase();
    }


}