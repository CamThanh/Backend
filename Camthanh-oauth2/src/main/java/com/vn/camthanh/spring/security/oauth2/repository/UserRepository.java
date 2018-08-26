package com.vn.camthanh.spring.security.oauth2.repository;

import com.vn.camthanh.spring.security.oauth2.model.security.User;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface UserRepository extends CrudRepository<User, Long> {

	@Query("SELECT user FROM User user " +
            "RIGHT OUTER JOIN FETCH user.authorities AS authorities " +
			"WHERE user.username = :username")
    User findByUsername(@Param("username") String username);
}
