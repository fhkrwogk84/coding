package com.test.coding.business.user.domain.service;

import com.test.coding.business.user.domain.model.User;

public interface UserService {

    User findByLoginId(String loginId);

    User findByLoginIdAndAndPassword(String loginId, String password);

}
