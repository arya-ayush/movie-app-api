package com.om.movieapp.service;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "users")  // Change table name as needed
public class User {

    @Id
    @Column(name = "user_id", nullable = false, unique = true)
    private String user_id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "photo_url")
    private String photo_url;

//    @Column(name = "created_date", updatable = false)
//    private LocalDateTime createdDate = LocalDateTime.now();
//
//    @Column(name = "modified_date")
//    private LocalDateTime modifiedDate = LocalDateTime.now();

//    @PreUpdate
//    public void updateTimestamp() {
//        this.modifiedDate = LocalDateTime.now();
//    }
}