package com.swifttech.entity.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDetailsRequestDTO {
    private Long id;
    private String title;
    private String description;
    private FileRequestDTO fileRequestDTO;
}
