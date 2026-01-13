package com.example.springsecurity.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="customer")
@Data
@Getter @Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;

    @Column(nullable = false, length = 255)
    private String pwd;
    @Column(name="role")
    private  String role;

}
