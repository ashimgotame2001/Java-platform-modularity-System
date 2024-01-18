package com.swifttech.controller;

import com.swifttech.entity.dto.request.CourseEnrollRequestDTO;
import com.swifttech.entity.dto.request.CourseRequestDTO;
import com.swifttech.response.BaseResponse;
import com.swifttech.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;


    @PostMapping
    public BaseResponse addCourse(@RequestBody CourseRequestDTO course) {
        return BaseResponse.builder()
                .data(courseService.addNewCourse(course))
                .message("Course successfully added")
                .httpStatus(HttpStatus.CREATED.value())
                .build();
    }

    @GetMapping
    public BaseResponse getAllCourses() {
        return BaseResponse.builder()
                .message("Courses Successfully fetched")
                .httpStatus(HttpStatus.OK.value())
                .data(courseService.getAllCourses())
                .build();
    }

    @GetMapping("/{code}")
    public BaseResponse getCourseByCourseCode(@PathVariable UUID code) {
        return BaseResponse.builder()
                .httpStatus(HttpStatus.OK.value())
                .message("Course successfully fetched")
                .data(courseService.getCourseByCourseCode(code))
                .build();
    }

    @PostMapping("enrollCourseRequest")
    public BaseResponse enrollCourseRequest(@RequestBody CourseEnrollRequestDTO courseEnrollRequestDTO) {
        return BaseResponse.builder()
                .data(courseService.enrollUserOnCourse(courseEnrollRequestDTO))
                .message("payment requested")
                .httpStatus(HttpStatus.OK.value())
                .build();
    }

    @GetMapping("enrolled")
    public BaseResponse getEnrolledCourse() {
        return BaseResponse.builder()
                .data(courseService.getEnrolledCoursesByUser())
                .message("Enrolled course list")
                .httpStatus(HttpStatus.OK.value())
                .build();
    }

}
