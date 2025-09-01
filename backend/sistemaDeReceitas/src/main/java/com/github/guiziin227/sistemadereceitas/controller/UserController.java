package com.github.guiziin227.sistemadereceitas.controller;

import com.github.guiziin227.sistemadereceitas.dtos.request.UserRequestDTO;
import com.github.guiziin227.sistemadereceitas.entities.User;
import com.github.guiziin227.sistemadereceitas.service.UserService;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    Logger logger = org.slf4j.LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody UserRequestDTO userRequestDTO) {
        logger.info("Creating user: {}", userRequestDTO);
        userService.createUser(userRequestDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<User>> getAllUsers() {
        var userResp = userService.getAllUsers();
        return ResponseEntity.ok(userResp);
    }
}
