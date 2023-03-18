package com.example.system.base.exception;


import com.example.system.base.Result.ResultBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.net.BindException;
import java.util.Set;

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
