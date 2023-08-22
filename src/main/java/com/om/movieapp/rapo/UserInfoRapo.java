package com.om.movieapp.rapo;
import com.om.movieapp.model.OtpUser;
import org.springframework.data.repository.CrudRepository;

public interface UserInfoRapo extends CrudRepository<OtpUser,Integer> {
    OtpUser findByPhoneNumber(String phone_number);
}
