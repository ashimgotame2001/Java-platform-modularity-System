package com.swifttech.entity.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequestDTO {
    private Long id;
    private String courseName;
    private String description;
    private String slug;
    private Integer price;
    private String status;
    private List<CourseDetailsRequestDTO> courseDetails;
    private FileRequestDTO file;

}
