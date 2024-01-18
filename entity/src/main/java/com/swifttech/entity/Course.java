package com.swifttech.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "course")
@JsonIgnoreProperties(value = {"users"}, allowSetters = true)
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String courseName;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String slug;

    private Integer enrolledCount = 0;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false, unique = true)
    private UUID courseCode;

    @Column(nullable = false, unique = false)
    private String status;

    @ManyToOne
    private FileStorage file;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<User> users;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CourseDetails> courseDetails;
}
