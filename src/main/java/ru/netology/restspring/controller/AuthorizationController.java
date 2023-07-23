package ru.netology.restspring.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.netology.restspring.DTO.Person;
import ru.netology.restspring.authorities.Authorities;
import ru.netology.restspring.service.AuthorizationService;

import java.util.List;

@RestController
@RequestMapping("/")
public class AuthorizationController {
    private final AuthorizationService service;

    public AuthorizationController(AuthorizationService service) {
        this.service = service;
    }

    @GetMapping("/authorize")
    public List<Authorities> getAuthorities(@RequestBody @Validated Person user) {
        return service.getAuthorities(user);
    }

    @PostMapping("/signup")
    public void signUpUser(@RequestBody @Validated Person user) {
        service.signUpUser(user);
    }
}
