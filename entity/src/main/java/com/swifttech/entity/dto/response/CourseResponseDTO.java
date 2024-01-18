package com.swifttech.entity.dto.response;


import com.swifttech.entity.FileStorage;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseResponseDTO {
    private Long id;
    private String courseName;
    private String description;
    private String slug;
    private Integer enrolledCount;
    private Integer price;
    private UUID courseCode;
    private String status;
    private FileStorage file;
}
