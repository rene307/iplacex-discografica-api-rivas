package com.iplacex.discografia;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  public record ErrorMsg(String message) {}

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorMsg> handleIllegalArgument(IllegalArgumentException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMsg(ex.getMessage()));
  }
}
