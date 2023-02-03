package com.example.system.base.exception;



import com.example.system.base.Result.ResultBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ProjectExceptionAdvice {

    @ExceptionHandler
    public ResultBody doException(Exception e) {
        e.printStackTrace();
        log.error(e.toString());
        return ResultBody.error("服务器出错了！稍后再试");
    }
}
