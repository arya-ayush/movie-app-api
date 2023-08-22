package com.om.movieapp.service;

import com.om.movieapp.dto.User;
import com.om.movieapp.model.OtpUser;
import com.om.movieapp.rapo.UserInfoRapo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class OtpUserService {
    @Autowired
    private UserInfoRapo userRapo;

    @Autowired
    @Lazy
    private PasswordEncoder encoder;

    public Boolean save_user(User user){
       OtpUser userModel=new OtpUser();
       userModel.setDob(user.getDob());
       userModel.setName(user.getName());
       userModel.setPhoneNumber(user.getPhone_number()+user.getCountry_code());
        String encodedPassword =encoder.encode(user.getPassword());
        userModel.setPassword(encodedPassword);
        try{
            userRapo.save(userModel);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    public OtpUser find_user_by_phone_number(String number){
        return userRapo.findByPhoneNumber(number);
    }
}
