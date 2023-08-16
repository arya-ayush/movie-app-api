package com.om.movieapp.rapo;



import com.om.movieapp.model.DeviceInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DeviceInfoRapo extends CrudRepository<DeviceInfo,Integer> {
    Optional<DeviceInfo> findByDeviceToken(String device_token);
}
