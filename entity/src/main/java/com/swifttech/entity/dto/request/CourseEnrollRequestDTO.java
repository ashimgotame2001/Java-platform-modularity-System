package com.swifttech.entity.dto.request;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CourseEnrollRequestDTO {
    private UUID courseCode;
    private UUID userCode;
}
