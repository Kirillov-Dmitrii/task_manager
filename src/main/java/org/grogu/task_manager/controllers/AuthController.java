package org.grogu.task_manager.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.grogu.task_manager.dto.JwtRequest;
import org.grogu.task_manager.dto.RegistrationDto;
import org.grogu.task_manager.dto.UserDto;
import org.grogu.task_manager.entity.User;
import org.grogu.task_manager.exeption.AppError;
import org.grogu.task_manager.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;


    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        log.info("AuthController ---> /auth");
        return authService.createAuthToken(authRequest);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody RegistrationDto registrationDto) {
        log.info("AuthController ---> /registration");
        return authService.registrationNewUser(registrationDto);
    }

}
