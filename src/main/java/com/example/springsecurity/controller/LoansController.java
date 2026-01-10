package com.example.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class LoansController {
    @GetMapping("/myLoans")
    public String getLoansDetails(){
        return "here are the loans details from the DB";

    }
}
