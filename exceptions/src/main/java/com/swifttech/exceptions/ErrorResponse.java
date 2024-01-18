package com.swifttech.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ErrorResponse {
    private String message;
    private String details;
    private Integer httpStatus;

    public ErrorResponse(String message, String details, Integer httpStatus) {
        this.message = message;
        this.details = details;
        this.httpStatus = httpStatus;
    }

    public ErrorResponse() {
    }

}
