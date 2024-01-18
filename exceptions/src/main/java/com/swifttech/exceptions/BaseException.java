package com.swifttech.exceptions;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseException extends RuntimeException {

    private final Integer httpStatus;
    private final String details;
    public BaseException(String message, Integer httpStatus, String details) {
        super(message);
        this.httpStatus = httpStatus;
        this.details = details;
    }


}
