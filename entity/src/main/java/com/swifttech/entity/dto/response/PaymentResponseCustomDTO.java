package com.swifttech.entity.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponseCustomDTO {
    private UserResponseCustomDTO requestedBy;
    private Integer requestedAmount;
    private CourseResponseDTO course;
}
