package com.om.movieapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="otp")
public class Otp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false,unique = true)
    private String phone;
    @Column(nullable = false)
    private String otp;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
