package com.swifttech.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileInfoResponseDTO {
    private Long id;
    private String address;
    private String city;
    private String country;
    private String district;
    private String toll;
    private Integer phoneNumber;
    private Integer panNo;
    private Integer citizenShipNo;
    private Integer nationalIdNo;
    private FileResponseDTO image;

}
