package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.dtos.requests.AdminLoginRequest;
import org.example.dtos.requests.AdminSignUpRequest;
import org.example.dtos.requests.UserLoginRequest;
import org.example.dtos.requests.UserSignUpRequest;
import org.example.dtos.responses.APIResponse;
import org.example.services.AdminServices;
import org.example.services.UserServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserServices userServices;
    private final AdminServices adminServices;

    @PostMapping("/user/signup")
    public ResponseEntity<?> signUp(@RequestBody UserSignUpRequest request) {
        return new ResponseEntity<>(new APIResponse(true, userServices.signUp(request)), HttpStatus.CREATED);
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> userLogin(@RequestBody UserLoginRequest request) {
        return new ResponseEntity<>(new APIResponse(true, userServices.login(request)), HttpStatus.OK);
    }

    @PostMapping("/admin/signup")
    public ResponseEntity<?> adminSignup(@RequestBody AdminSignUpRequest request) {
        return new ResponseEntity<>(new APIResponse(true,adminServices.adminSignUp(request)), HttpStatus.CREATED);
    }

    @PostMapping("/admin/login")
    public ResponseEntity<?> adminLogin(@RequestBody AdminLoginRequest request) {
        return new ResponseEntity<>(new APIResponse(true,adminServices.adminLogin(request)), HttpStatus.OK);
    }
}
