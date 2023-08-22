package com.om.movieapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "mv_user")
public class MvUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String dob;

    @Column(nullable = false)
    private String nationality;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Integer countryCode;

    @Column(nullable = false)
    private Integer mobileNo;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)

    private DeviceInfo device;

}
