package com.swifttech.service.impl;

import com.swifttech.entity.Course;
import com.swifttech.entity.Payment;
import com.swifttech.entity.User;
import com.swifttech.repository.CourseRepository;
import com.swifttech.repository.PaymentRepository;
import com.swifttech.response.BaseResponse;
import com.swifttech.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final CourseRepository courseRepository;
    @Override
    public BaseResponse validatePayment(UUID paymentCode) {
        BaseResponse baseResponse = new BaseResponse<>();
        if (Objects.isNull(paymentCode)){
            return BaseResponse.builder().message("Payment code must not be null").build();
        }
       Optional<Payment> payment = this.paymentRepository.findByPaymentCode(paymentCode);
        payment.ifPresentOrElse(payment1 -> {
              payment1.setPaymentStatus("PAYMENT_SUCCESSED");
              this.paymentRepository.save(payment1);

            Course course = payment1.getCourse();
            course.setEnrolledCount(course.getEnrolledCount() + 1);
            List<User> enrolledUser = course.getUsers();
            enrolledUser.add(payment1.getRequestedBy());
            this.courseRepository.save(course);

              baseResponse.setHttpStatus(HttpStatus.OK.value());
              baseResponse.setMessage("Payment successful");
        },()-> {
              baseResponse.setMessage("Please request for payment");
             baseResponse.setHttpStatus(HttpStatus.BAD_REQUEST.value());
        });
        return baseResponse;
    }
}
