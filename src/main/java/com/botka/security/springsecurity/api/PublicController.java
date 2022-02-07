package com.botka.security.springsecurity.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/")
public class PublicController {

    @GetMapping("test")
    public String getStudents() {
        return "API is working";
    }

}
