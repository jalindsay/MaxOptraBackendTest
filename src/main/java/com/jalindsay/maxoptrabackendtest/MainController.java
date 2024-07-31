package com.jalindsay.maxoptrabackendtest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String home() {
        return "MAXOPTRA TEST HOME CONTROLLER RETURN STRING!";
    }
}