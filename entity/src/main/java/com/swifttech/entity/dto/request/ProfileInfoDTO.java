package com.swifttech.entity.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileInfoDTO {
    private String id;
    private String address;
    private String city;
    private String country;
    private String district;
    private String toll;
    private Integer phoneNumber;
    private Integer panNo;
    private Integer citizenshipNo;
    private Integer NationalIdNo;
    private String UserImage;
}
