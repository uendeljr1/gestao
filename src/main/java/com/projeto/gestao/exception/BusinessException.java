package com.projeto.gestao.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends ApiException{
    public BusinessException(String message, HttpStatus status) {
        super(message,HttpStatus.BAD_REQUEST);
    }
}
