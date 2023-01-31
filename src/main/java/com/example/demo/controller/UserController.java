package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public final class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDto create(@RequestBody @Validated final UserDto userDto) {
        return userService.create(userDto);
    }

    @PutMapping("/{userId}")
    public UserDto update(@PathVariable final UUID userId, @RequestBody @Validated final UserDto userDto) {
        return userService.update(userId, userDto);
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable final UUID userId) {
        userService.delete(userId);
    }

    @GetMapping("/{userId}/revisions")
    public List<UserDto> getUserRevisionsById(@PathVariable final UUID userId) {
        return userService.getUserRevisionsById(userId);
    }

    @GetMapping("/{userId}")
    public UserDto getById(@PathVariable final UUID userId) {
        return userService.getById(userId);
    }
}
