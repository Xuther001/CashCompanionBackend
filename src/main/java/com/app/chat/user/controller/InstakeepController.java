package com.app.chat.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class InstakeepController {

    @GetMapping("/usernamehere")
    public ResponseEntity<String> sayHello() {
        String message = "this will retrieve all pictures posted by user";
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}
