package org.grogu.task_manager.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.grogu.task_manager.dto.JwtRequest;
import org.grogu.task_manager.dto.JwtResponse;
import org.grogu.task_manager.dto.RegistrationDto;
import org.grogu.task_manager.dto.UserDto;
import org.grogu.task_manager.entity.User;
import org.grogu.task_manager.exeption.AppError;
import org.grogu.task_manager.utils.JwtTokenUtils;
import org.slf4j.Marker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;

    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        log.info("AuthService ---> createAuthToken method");

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
                    authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(),
                    "неправильный логий или пароль"), HttpStatus.UNAUTHORIZED);
        }

        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    public ResponseEntity<?> registrationNewUser(@RequestBody RegistrationDto registrationDto) {
        log.info("AuthService ---> createAuthToken method");

        if (!registrationDto.getPassword().equals(registrationDto.getConfirmPassword())) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пароли не совпадают"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userService.findUserByEmail(registrationDto.getEmail()).isPresent()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пользователь с таким email уже " +
                    "существует"), HttpStatus.BAD_REQUEST);
        }
        User user = userService.createNewUser(registrationDto);
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());

        return ResponseEntity.ok(userDto);
    }

}
