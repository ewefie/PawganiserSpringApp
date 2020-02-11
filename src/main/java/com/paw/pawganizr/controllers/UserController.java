package com.paw.pawganizr.controllers;

import com.paw.pawganizr.exceptions.ResourceNotFoundException;
import com.paw.pawganizr.models.AppUser;
import com.paw.pawganizr.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = {"http://localhost:4200", "http://pawganiser.sdacademy.xyz"})
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public AppUser getUserByPrincipalData(final Principal principal) throws ResourceNotFoundException {
        return userService.createOrUpdateUser(principal);
    }

    @DeleteMapping("/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(final Principal principal) throws ResourceNotFoundException {
        userService.deleteByPrincipal(principal);
    }
}
