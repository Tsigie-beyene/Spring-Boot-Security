package com.example.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class NoticesController {
    @GetMapping("/notices")
    public String getNotices(){
        return "here are the notice details from the DB";

    }
}
