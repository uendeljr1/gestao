package com.projeto.gestao.exception;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiError> handlerException(ApiException ex , HttpServletRequest request){
        ApiError error = new ApiError(
                ex.getStatus(),
                List.of(ex.getMessage()),
                request.getRequestURI()
                );

        return ResponseEntity.status(ex.getStatus()).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handlerValidationException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request){
        List<String> validationErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e -> e.getField()+ ": " + e.getDefaultMessage()).toList();

        ApiError error = new ApiError(
                HttpStatus.BAD_REQUEST,
                validationErrors,
                request.getRequestURI()
        );
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException(Exception ex , HttpServletRequest request){
        ApiError error = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                List.of("Erro inesperado do servidor"),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }



}
