package com.swifttech.repository;

import com.swifttech.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {

    Optional<Payment> findByPaymentCode(UUID paymentCode);


    Optional<Payment> findByRequestedBy_UserCodeAndCourse_CourseCode(UUID userCode, UUID courseCode);

}
