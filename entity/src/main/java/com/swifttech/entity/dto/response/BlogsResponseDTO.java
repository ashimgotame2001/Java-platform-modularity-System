package com.swifttech.entity.dto.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogsResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String blogCode;
    private String status;
    private UserResponseCustomDTO createdBy;

}
