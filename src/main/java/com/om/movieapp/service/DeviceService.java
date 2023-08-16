package com.om.movieapp.service;
import com.om.movieapp.model.DeviceInfo;
import com.om.movieapp.rapo.DeviceInfoRapo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeviceService {
    @Autowired
    private DeviceInfoRapo deviceRapo;

    public boolean add_device(DeviceInfo info){
        try{

           deviceRapo.save(info);
            return true;

        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public DeviceInfo get_device_by_token(String token) {
        Optional<DeviceInfo> device = deviceRapo.findByDeviceToken(token);
        return device.orElse(null);
    }
}
