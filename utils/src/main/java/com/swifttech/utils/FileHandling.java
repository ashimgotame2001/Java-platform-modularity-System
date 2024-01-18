package com.swifttech.utils;

import com.swifttech.entity.FileStorage;
import com.swifttech.entity.dto.request.FileRequestDTO;
import com.swifttech.exceptions.BaseException;
import com.swifttech.repository.FileStorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class FileHandling {

    private static final String NOT_FOUND = "Encoded data not found";
    private static final String NOT_FOUND_DETAILS = "Encoded data must not be null";
    private static final String basePath = "/home/ashimgotame/Ashim/demo/files/";

    private final FileStorageRepository fileStorageRepository;

    public FileStorage handleFile(FileRequestDTO fileRequestDTO) {
        if (fileRequestDTO.getEncodedData() == null) {
            throw new BaseException(
                    NOT_FOUND,
                    HttpStatus.NOT_FOUND.value(),
                    NOT_FOUND_DETAILS);
        }
        byte[] data = Base64.getDecoder().decode(fileRequestDTO.getEncodedData());
        String path = basePath + fileRequestDTO.getFileName();
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FileStorage fileStorage = FileStorage.builder()
                .fileType(fileRequestDTO.getFileType())
                .filePath(path)
                .createdDate(LocalDateTime.now())
                .url(path)
                .fileName(fileRequestDTO.getFileName())
                .build();
        this.fileStorageRepository.save(fileStorage);
        return fileStorage;
    }

}


