package com.example.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountsController {
    @GetMapping("/myAccounts")
    public String getAccountDetails(){
        return "here are the account details from the DB";

    }
}

