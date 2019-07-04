package com.test.coding.business.user.domain.repository;

import com.test.coding.business.user.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByLoginId(String loginId);

    User findByLoginIdAndAndPassword(String loginId, String password);

}
