package com.om.movieapp.service;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "video")  // Change table name as needed
public class Videos {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    @Column(name = "description")
    private String description;

    @Column(name = "name")
    private String name;

    @Column(name = "video")
    private String video;




}