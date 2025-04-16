package com.pragma.foodcourt.core.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class UserResponseDto {

    private String userIdentityType;
    private String userIdentityNumber;
    private String userFirstName;
    private String userSecondName;
    private String userFirstLastName;
    private String userSecondLastName;
    private String userPhone;
    private LocalDate userBirthdate;
    private String userEmail;

}
