package com.swifttech.service;

import com.swifttech.entity.Course;
import com.swifttech.entity.dto.request.CourseEnrollRequestDTO;
import com.swifttech.entity.dto.request.CourseRequestDTO;
import com.swifttech.entity.dto.response.CourseResponseDTO;
import com.swifttech.entity.dto.response.PaymentResponseCustomDTO;

import java.util.List;
import java.util.UUID;

public interface CourseService {

    CourseResponseDTO addNewCourse(CourseRequestDTO course);

    List<CourseResponseDTO> getAllCourses();

    CourseResponseDTO getCourseByCourseCode(UUID code);

    PaymentResponseCustomDTO enrollUserOnCourse(CourseEnrollRequestDTO courseEnrollRequestDTO);

    List<Course> getEnrolledCoursesByUser();

}
