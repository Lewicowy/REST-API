package com.lewicowy.Mytest.Controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lewicowy.Mytest.DTO.UserRequest;
import com.lewicowy.Mytest.DTO.UserResponse;
import com.lewicowy.Mytest.Service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/createNewUser")
    public UserResponse createUser(@Valid @RequestBody UserRequest request) {
        return userService.createUser(request);
    }

    @GetMapping("/users")
    public UserResponse getUser(@RequestParam UUID userID) {
        return userService.getUser(userID);
    }

    @PutMapping("/userDetailsUpdate")
    public UserResponse updateUser(@RequestParam UUID userID, 
                                 @Valid @RequestBody UserRequest request) {
        return userService.updateUser(userID, request);
    }

    @DeleteMapping("/users")
    public void deleteUser(@RequestParam UUID userID) {
        userService.deleteUser(userID);
    }
}