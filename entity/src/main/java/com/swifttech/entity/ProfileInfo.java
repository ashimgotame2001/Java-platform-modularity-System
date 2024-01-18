package com.swifttech.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "profileInfo")
@JsonIgnoreProperties(value = {"user"}, allowSetters = true)
public class ProfileInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, unique = false)
    private String address;

    @Column(nullable = true, unique = false)
    private String city;

    @Column(nullable = true, unique = false)
    private String country;

    @Column(nullable = true, unique = false)
    private String district;

    @Column(nullable = true, unique = false)
    private String toll;

    @Column(nullable = true, unique = true)
    private Integer phoneNumber;

    @Column(nullable = true, unique = true)
    private Integer panNo;

    @Column(nullable = true, unique = true)
    private Integer citizenshipNo;

    @Column(nullable = true, unique = true)
    private Integer nationalIdNo;

    @OneToOne(fetch = FetchType.EAGER)
    private FileStorage image;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

}
