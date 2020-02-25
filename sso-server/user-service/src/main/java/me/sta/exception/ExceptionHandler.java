package me.sta.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 全局异常处理 切面类
 *
 */
@ControllerAdvice
@ResponseBody
public class ExceptionHandler {
	@org.springframework.web.bind.annotation.ExceptionHandler(UserLoginException.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.OK);
    }
}
