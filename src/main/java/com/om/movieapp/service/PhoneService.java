package com.om.movieapp.service;

import com.om.movieapp.model.Otp;
import com.om.movieapp.rapo.OtpRecordRepository;
import com.om.movieapp.properties.TwilioProperties;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class PhoneService {
    @Autowired
    private TwilioProperties properties;

    @Autowired
    private OtpRecordRepository otpRecordRepository;

    public String sendOtp(String phoneNumber) {
        PhoneNumber to = new PhoneNumber(phoneNumber);
        PhoneNumber from = new PhoneNumber(properties.getTrailNumber());
        String otp = generateOTP();
        String otpMessage = "Otp is" + otp + ".It will be expired after 5 minutes";
        Message message = Message.creator(to, from, otpMessage).create();

        String hashedPhoneNumber = hashPhoneNumberDeterministic(phoneNumber);


        Otp otpEntity = new Otp();
        otpEntity.setPhone(hashedPhoneNumber);
        otpEntity.setOtp(otp);
        otpEntity.setCreatedAt(LocalDateTime.now());

        otpRecordRepository.save(otpEntity);

        return otp;
    }

    public Boolean verifyOtp(String phoneNumber, String enteredOtp) {
        LocalDateTime validTime = LocalDateTime.now().minusMinutes(5);
        String hashedPhoneNumber =hashPhoneNumberDeterministic(phoneNumber);
        System.out.println(hashedPhoneNumber);

        Otp otpRecord = otpRecordRepository.findByPhoneAndOtpAndCreatedAtAfter(
                hashedPhoneNumber, enteredOtp, validTime
        );

        if (otpRecord != null) {
            otpRecordRepository.delete(otpRecord);
            return true;
        }

        return false;
    }

    private String generateOTP() {
        return new DecimalFormat("000000").format(new Random().nextInt(999999));
    }

    private static String hashPhoneNumberDeterministic(String phoneNumber) {
        return String.valueOf(phoneNumber.hashCode());
    }
}
