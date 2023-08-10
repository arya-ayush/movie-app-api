package com.om.movieapp.model;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@Entity
@Table(name = "mv_device_info")
public class DeviceInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String deviceToken;

    private String info;

    @Column(nullable = false)
    private String lastSessionTime;

    @Column(nullable = false)
    private String lastIp;


}
