package com.lewicowy.Mytest.DTO;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {
    private UUID uuid;
    private String fio;
    private String phoneNumber;
    private String avatar;
    private String roleName;
}