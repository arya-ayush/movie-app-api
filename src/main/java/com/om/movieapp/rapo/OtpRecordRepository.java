package com.om.movieapp.rapo;

import com.om.movieapp.model.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface OtpRecordRepository extends JpaRepository<Otp, Integer> {

    Otp findByPhoneAndOtpAndCreatedAtAfter(String phone, String otpCode, LocalDateTime validTime);
}

