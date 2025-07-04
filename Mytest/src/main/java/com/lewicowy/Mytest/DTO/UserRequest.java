package com.lewicowy.Mytest.DTO;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserRequest {
    @NotBlank
    private String fio;

    @Pattern(regexp = "^\\+?[0-9]{10,15}$")
    private String phoneNumber;

    @URL
    private String avatar;

    @NotBlank
    private String roleName;
}