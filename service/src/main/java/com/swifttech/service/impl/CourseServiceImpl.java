package com.swifttech.service.impl;

import com.swifttech.entity.Course;
import com.swifttech.entity.FileStorage;
import com.swifttech.entity.Payment;
import com.swifttech.entity.User;
import com.swifttech.entity.dto.request.CourseEnrollRequestDTO;
import com.swifttech.entity.dto.request.CourseRequestDTO;
import com.swifttech.entity.dto.response.CourseResponseDTO;
import com.swifttech.entity.dto.response.PaymentResponseCustomDTO;
import com.swifttech.entity.mapper.CourseMapper;
import com.swifttech.entity.mapper.PaymentMapper;
import com.swifttech.exceptions.BaseException;
import com.swifttech.repository.CourseRepository;
import com.swifttech.repository.PaymentRepository;
import com.swifttech.repository.UserRepository;
import com.swifttech.service.CourseService;
import com.swifttech.service.UserService;
import com.swifttech.utils.FileHandling;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;
    private final UserService userService;
    private final FileHandling fileHandling;

    @Override
    public CourseResponseDTO addNewCourse(CourseRequestDTO courseRequestDTO) {
        if (courseRequestDTO == null){
            throw  new BaseException("Required field Not found",400,"Required field Not found");
        }

        Course course = CourseMapper.INSTANCE.toEntity(courseRequestDTO);
        FileStorage fileStorage = fileHandling.handleFile(courseRequestDTO.getFile());
        course.setCourseCode(UUID.randomUUID());
        course.setFile(fileStorage);
        this.courseRepository.save(course);
        return CourseMapper.INSTANCE.toResponse(course);
    }

    @Override
    public List<CourseResponseDTO> getAllCourses() {
        return CourseMapper.INSTANCE.toResponses(this.courseRepository.findAll());
    }

    @Override
    public CourseResponseDTO getCourseByCourseCode(UUID code) {
        Optional<Course> course = this.courseRepository.findByCourseCode(code);
        if (course.isEmpty()) {
            throw new BaseException(
                    "Course  not found",
                    HttpStatus.NOT_FOUND.value(),
                    "INVALID COURSE CODE"
            );
        }
        return CourseMapper.INSTANCE.toResponse(course.get());
    }

    @Override
    public PaymentResponseCustomDTO enrollUserOnCourse(CourseEnrollRequestDTO courseEnrollRequestDTO) {
        if (courseEnrollRequestDTO.getUserCode() == null) {
            throw new BaseException(
                    "Course code not found",
                    HttpStatus.NOT_FOUND.value(),
                    "INVALID COURSE CODE"
            );
        }
        if (courseEnrollRequestDTO.getCourseCode() == null) {
            throw new BaseException(
                    "User code not found",
                    HttpStatus.NOT_FOUND.value(),
                    "INVALID USER CODE"
            );
        }
        Optional<User> userOptional = this.userRepository.findByUserCode(courseEnrollRequestDTO.getUserCode());
        if (userOptional.isEmpty()) {
            throw new BaseException(
                    "User code not found",
                    HttpStatus.NOT_FOUND.value(),
                    "INVALID USER CODE"
            );
        }
        Optional<Course> courseOptional = this.courseRepository.findByCourseCode(courseEnrollRequestDTO.getCourseCode());
        if (courseOptional.isEmpty()) {
            throw new BaseException(
                    "Course not found",
                    HttpStatus.NOT_FOUND.value(),
                    "INVALID COURSE CODE"
            );
        }
        Payment  payment ;
        Optional<Payment> optionalPayment = this.paymentRepository.findByRequestedBy_UserCodeAndCourse_CourseCode(
                userOptional.get().getUserCode(),
                courseOptional.get().getCourseCode()
        );
        if (optionalPayment.isPresent()){
            optionalPayment.get().setCreatedDate(LocalDateTime.now());
            this.paymentRepository.save(optionalPayment.get());
            payment = optionalPayment.get();
        }else{
            payment = Payment
                    .builder()
                    .paymentCode(UUID.randomUUID())
                    .paymentStatus("PAYMENT_REQUESTED")
                    .createdDate(LocalDateTime.now())
                    .requestedBy(userOptional.get())
                    .course(courseOptional.get())
                    .paymentType("CASH")
                    .requestedAmount(courseOptional.get().getPrice())
                    .build();
            this.paymentRepository.save(payment);
        }
        return PaymentMapper.INSTANSE.toEnrollmentCustomResponse(payment);
    }

    @Override
    public List<Course> getEnrolledCoursesByUser() {
        User user = userService.getLoggedUserDetails();
        return switch (user.getRole()) {
            case USER -> courseRepository.getAllEnrolledCourseByUser(user.getId());
            case ADMIN -> courseRepository.findAll();
            default -> Collections.emptyList();
        };
    }

}
