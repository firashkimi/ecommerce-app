package com.ideacop.ecommerce.backend.infraestructure.rest;

import com.ideacop.ecommerce.backend.application.RegistrationService;
import com.ideacop.ecommerce.backend.domain.model.User;
import com.ideacop.ecommerce.backend.domain.model.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/security")
public class RegistrationController {
    private final RegistrationService registrationService;
    private  final BCryptPasswordEncoder passwordEncoder;

    public RegistrationController(RegistrationService registrationService, BCryptPasswordEncoder passwordEncoder) {
        this.registrationService = registrationService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        log.info("Clave encriptada: {}", passwordEncoder.encode(user.getPassword()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("Usuario registrandose {}", user);
        return new ResponseEntity<User>(registrationService.register(user), HttpStatus.CREATED);
    }
    @PostMapping("/updateProfile")
    public ResponseEntity<User> updateProfile(@RequestBody UserDto user) {
        log.info("Usuario registrandose {}", user);
        return new ResponseEntity<User>(registrationService.updateProfile(user), HttpStatus.CREATED);
    }
}
