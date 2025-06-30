package com.poetry.common.header;


import com.poetry.common.response.ResponseResult;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author moZiA
 * @date 2025/5/19 15:03
 * @description
 */
@Slf4j
@RestControllerAdvice
@Controller
public class GlobalExceptionHandler {

  // 参数校验异常
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseResult<String> handleValidation(MethodArgumentNotValidException e) {
    String errorMsg = Objects.requireNonNull(e.getBindingResult().getFieldError())
                             .getDefaultMessage();
    log.warn("参数校验失败: {}", errorMsg);
    return ResponseResult.error(errorMsg);
  }


  // 默认兜底异常
  @ExceptionHandler(Exception.class)
  public ResponseResult<String> handleGlobal(Exception e) {
    log.error("运行时异常: {}", e.getMessage(), e);
    return ResponseResult.error(e.getMessage());
  }


}