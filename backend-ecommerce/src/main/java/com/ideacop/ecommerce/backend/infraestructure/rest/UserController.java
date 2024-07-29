package com.ideacop.ecommerce.backend.infraestructure.rest;

import com.ideacop.ecommerce.backend.application.UserService;
import com.ideacop.ecommerce.backend.domain.model.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Integer id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        User user = userService.findById(id);

        if (userDetails.getUsername().equals(user.getEmail())) {
            return ResponseEntity.ok(userService.findById(id));
        } else {
            throw new SecurityException("Access denied");
        }

       // return userService.findById(id);
    }

    @GetMapping
    public ResponseEntity<Iterable<User>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
