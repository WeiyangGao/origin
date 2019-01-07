package com.tcps.origin.appcore.exception;


import com.tcps.origin.appcore.utils.Result;
import com.tcps.origin.appcore.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author gaowy @date 2018/8/17
 * @describe 异常集中处理
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandle {

    private static final String SYS_EXC = "服务器出错，Connect us 317326646@qq.com";
    private static final String SYS_404 = "无效的连接地址";

    /**
     * @describe 意料之外
     * @author gaowy @date 2018/8/17
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //返回的状态码
    public Result handlerException(Exception e) {
        log.error("系统异常:{}", e);//日志处理
        return ResultUtil.failureResult(SYS_EXC);
    }

    /**
     * @describe 意料之中：业务异常
     * @author gaowy @date 2018/8/17
     */
    @ExceptionHandler(value = AppException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handlerAppException(AppException e) {
        log.warn("业务异常:{}", e.getMessasge());//日志处理
        return ResultUtil.failureResult(e.getMessasge());
    }

    /**
     * @describe 未授异常
     * @author gaowy @date 2018/8/17
     */
    @ExceptionHandler(value = AuthException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handlerAppException(AuthException e) {
        log.warn("授权异常:{}", e.getMessasge());//日志处理
        return ResultUtil.failureResult(e.getMessasge());
    }

    /**
     * @describe 转发异常
     * @author gaowy @date 2018/8/17
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result noHandlerFound(AuthException e) {
        log.warn("404!:{}", e.getMessasge());//日志处理
        return ResultUtil.failureResult(SYS_404);
    }


}
