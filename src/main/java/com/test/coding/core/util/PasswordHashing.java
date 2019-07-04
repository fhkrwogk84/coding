package com.test.coding.core.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordHashing implements PasswordEncoder {

    private PasswordEncoder passwordEncoder;

    /**
     * 생성자
     */
    public PasswordHashing() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }
    
    /**
     * 생성자
     */
    public PasswordHashing(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 인코딩
     */
    @Override
    public String encode(CharSequence plainText) {
        return passwordEncoder.encode(plainText);
    }

    /**
     * 비밀번호 일치 체크
     */
    @Override
    public boolean matches(CharSequence plainText, String chipherText) {
        return passwordEncoder.matches(plainText, chipherText);
    }

}
