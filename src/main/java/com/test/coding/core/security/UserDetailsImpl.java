package com.test.coding.core.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class UserDetailsImpl extends User {

    private static final long serialVersionUID = 2531090799965362852L;

    private com.test.coding.business.user.domain.model.User loginUser;

    public UserDetailsImpl(com.test.coding.business.user.domain.model.User user) {
        super(user.getLoginId(), user.getPassword(), AuthorityUtils.createAuthorityList("USER"));
        loginUser = user;
    }

    public com.test.coding.business.user.domain.model.User getLoginUser(){
        return this.loginUser;
    }

}
