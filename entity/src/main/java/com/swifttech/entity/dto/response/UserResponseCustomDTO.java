package com.swifttech.entity.dto.response;

import com.swifttech.entity.enumuration.Role;
import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Builder
public class UserResponseCustomDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private Role role;
}
