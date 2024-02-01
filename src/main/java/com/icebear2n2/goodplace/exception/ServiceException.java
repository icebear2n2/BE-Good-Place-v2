package com.icebear2n2.goodplace.exception;


import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException{
    private final ErrorCode errorCode;
    private final String message;

    public ServiceException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
    }
}
