package com.echo.blog.config.handler;

import com.echo.blog.config.result.Result;
import com.echo.blog.config.result.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author echo
 * @date 2019-12-07 14:16:05
 * desc 全局异常拦截器
 */
@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String LOG_EXCEPTION_FORMAT = "Capture Exception By GlobalExceptionHandler: Code: %s Detail: %s";
    private static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 运行时错误
     * @param re 运行时自定义异常
     * @return Result<String>
     */
    @ExceptionHandler({RuntimeException.class})
    public Result<String> runtimeException(RuntimeException re){
        log.error(String.format(LOG_EXCEPTION_FORMAT, StatusCode.FAIL.getCode(), re.getMessage()));
        Result<String> result = new Result<>();
        result.setStatus(StatusCode.FAIL.getCode());
        result.setMessage(re.getMessage());
        return result;
    }

    /**
     * 其他错误
     *
     * @param ex 错误
     * @return Result<String>
     */
    @ExceptionHandler({Exception.class})
    public Result<String> exception(Exception ex) {
        return resultFormat(StatusCode.ERROR_.getCode(), ex);
    }

    private <T extends Throwable> Result<String> resultFormat(Integer code, T ex) {
        ex.printStackTrace();
        log.error(String.format(LOG_EXCEPTION_FORMAT, code, ex.getMessage()));
        Result<String> result = new Result<>();
        result.setStatus(StatusCode.ERROR_.getCode());
        result.setMessage("服务器貌似开小差了，请您联系我们的开发小哥哥催一催服务器工作！");
        return result;
    }

}