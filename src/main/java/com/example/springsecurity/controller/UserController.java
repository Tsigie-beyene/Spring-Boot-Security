package com.example.springsecurity.controller;

import com.example.springsecurity.model.Customer;
import com.example.springsecurity.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer) {
        try {
          String hashPwd= passwordEncoder.encode(customer.getPwd());
          customer.setPwd(hashPwd);
          customer.setCreateDt(new Date(System.currentTimeMillis()));
          Customer savedCustomer= customerRepository.save(customer);

          if(savedCustomer.getId()>0){
              return ResponseEntity.status(HttpStatus.CREATED).
                      body("The given user has successfully created");
          }
          else{
              return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                      body("User registration failed");
          }

        }
        catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("An exception occurred"+ ex.getMessage());

        }
    }

    @RequestMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication){
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(authentication.getName());
        return optionalCustomer.orElse(null);
    }
}