package com.test.coding.business.user.domain.service;

import com.test.coding.business.user.domain.model.User;
import com.test.coding.business.user.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User findByLoginId(String loginId) {
        return userRepository.findByLoginId(loginId);
    }

    @Override
    public User findByLoginIdAndAndPassword(String loginId, String password) {
        return userRepository.findByLoginIdAndAndPassword(loginId, password);
    }


}
