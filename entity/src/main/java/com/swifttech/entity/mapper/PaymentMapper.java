package com.swifttech.entity.mapper;

import com.swifttech.entity.Payment;
import com.swifttech.entity.dto.request.PaymentRequestDTO;
import com.swifttech.entity.dto.response.PaymentResponseCustomDTO;
import com.swifttech.entity.dto.response.PaymentResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PaymentMapper {

    PaymentMapper INSTANSE = Mappers.getMapper(PaymentMapper.class);
    Payment toEntity(PaymentRequestDTO paymentRequestDTO);

    PaymentResponseDTO toResponse(Payment payment);

    PaymentResponseCustomDTO toEnrollmentCustomResponse(Payment payment);

    List<PaymentResponseDTO> toResponses(List<Payment> payments);


}
