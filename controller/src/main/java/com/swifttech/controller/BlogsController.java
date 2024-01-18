package com.swifttech.controller;

import com.swifttech.entity.Blogs;
import com.swifttech.response.BaseResponse;
import com.swifttech.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/blogs")
@RequiredArgsConstructor
public class BlogsController {

    private final BlogService blogService;

    @GetMapping
    public BaseResponse getAllBlogs() {
        return BaseResponse.builder()
                .message("Blogs fetch successfully")
                .httpStatus(HttpStatus.OK.value())
                .data(blogService.getAllBlogs())
                .build();
    }

    @GetMapping("/{code}")
    public BaseResponse getBlogByCODE(@PathVariable UUID code) {
        return BaseResponse.builder()
                .message("Blogs fetch successfully")
                .httpStatus(HttpStatus.OK.value())
                .data(blogService.getBlogByCode(code))
                .build();
    }
    @GetMapping("/user/{userCode}")
    public BaseResponse getBlogByUserCODE(@PathVariable UUID userCode) {
        return BaseResponse.builder()
                .message("Blogs fetch successfully")
                .httpStatus(HttpStatus.OK.value())
                .data(blogService.getBlogByCreator(userCode))
                .build();
    }

    @PostMapping
    public BaseResponse addBlogs(@RequestBody Blogs blogs) {
        return BaseResponse.builder()
                .message("Blogs added successfully")
                .httpStatus(HttpStatus.OK.value())
                .data(blogService.addBlogs(blogs))
                .build();
    }
}
