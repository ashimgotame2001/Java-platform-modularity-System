package com.swifttech.controller;


import com.swifttech.response.BaseResponse;
import com.swifttech.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;


    @PostMapping("/{paymentCode}")
    public BaseResponse requestForPayment(@PathVariable UUID paymentCode){
        return paymentService.validatePayment(paymentCode);
    }


}
