package ru.mahalov.CodeReviewRESTApp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {
    @GetMapping
    public ResponseEntity<String> RootPage(){
        return ResponseEntity.ok("Welcome to our service! \n" +
                "For generate short link please go to '/generate' - path..");
    }
}
