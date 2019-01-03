package com.cardealership.service;

public interface RecaptchaService {
    String verifyRecaptcha(String userIpAddress, String gRecaptchaResponse);
}
