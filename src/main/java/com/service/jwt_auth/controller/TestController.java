package com.service.jwt_auth.controller;

import com.service.jwt_auth.pojos.request.Employee;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {


    @GetMapping
    public String test() {
        return "Test successful";
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee create(@RequestBody Employee employee) {
        return employee;
    }

}
