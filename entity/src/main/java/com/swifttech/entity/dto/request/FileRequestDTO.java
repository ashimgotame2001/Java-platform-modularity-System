package com.swifttech.entity.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileRequestDTO {
    private String encodedData;
    private String fileName;
    private String fileType;
}
