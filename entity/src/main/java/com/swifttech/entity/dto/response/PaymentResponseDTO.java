package com.swifttech.entity.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponseDTO {
    private Long id;
    private UUID paymentCode;
    private String paymentStatus;
    private String paymentType;
    private LocalDateTime createdDate;
    private Integer requestedAmount;
    private UserResponseCustomDTO requestedBy;
}
