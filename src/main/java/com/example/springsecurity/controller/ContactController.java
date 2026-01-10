package com.example.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ContactController {
    @GetMapping("/contact")
    public String saveContactInquiryDetails(){
        return "here are the contact details from the DB";

    }
}
