package com.swifttech.service;

import com.swifttech.response.BaseResponse;

import java.util.UUID;

public interface PaymentService {

    BaseResponse validatePayment(UUID paymentCode);


}
