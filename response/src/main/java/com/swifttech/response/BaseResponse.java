package com.swifttech.response;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseResponse<T> {
    private Integer httpStatus;
    private String message;
    private T data;
}
