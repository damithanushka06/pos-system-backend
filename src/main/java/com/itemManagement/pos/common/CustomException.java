package com.itemManagement.pos.common;

import lombok.Getter;

@Getter
public class CustomException extends Exception{

    private final int errorCode;

    public CustomException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

}
