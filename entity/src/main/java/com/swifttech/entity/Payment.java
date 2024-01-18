package com.swifttech.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID paymentCode;
    private String paymentStatus;
    private String paymentType;
    private LocalDateTime createdDate;
    private Integer requestedAmount;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private User requestedBy;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Course course;

}
