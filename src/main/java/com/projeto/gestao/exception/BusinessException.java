package com.projeto.gestao.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends ApiException{
    public BusinessException(String message) {
        super(message,HttpStatus.BAD_REQUEST);
    }
}
