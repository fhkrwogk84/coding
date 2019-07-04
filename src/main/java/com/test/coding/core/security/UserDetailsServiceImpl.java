package com.test.coding.core.security;

import com.test.coding.business.user.domain.model.User;
import com.test.coding.business.user.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    /**
     * 사용자 조회 오버라이드
     */
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

        User user = userService.findByLoginId(id);

        if (user == null) {
            throw new UsernameNotFoundException(id);
        }

        return new UserDetailsImpl(user);
    }
}
