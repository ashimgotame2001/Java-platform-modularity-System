package com.swifttech.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileResponseDTO {
    private Long id;
    private String url;
    private String fileType;
    private String filePath;
    private LocalDateTime createdDate;
    private String fileName;
}
