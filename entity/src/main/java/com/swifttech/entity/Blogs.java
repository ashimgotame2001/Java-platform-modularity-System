package com.swifttech.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "blogs")

public class Blogs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdDate;
    private UUID blogCode;
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    private User createdBy;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BlogContent> blogContents;
}
