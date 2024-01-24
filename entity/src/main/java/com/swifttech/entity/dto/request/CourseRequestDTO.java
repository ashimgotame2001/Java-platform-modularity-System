package com.swifttech.entity.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
