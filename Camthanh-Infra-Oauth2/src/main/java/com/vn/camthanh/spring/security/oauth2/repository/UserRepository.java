package com.vn.camthanh.spring.security.oauth2.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vn.camthanh.CamthanhAccount.User;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {

	@Query("SELECT user FROM User user " +
			//"LEFT JOIN FETCH user.userDetail AS userDetail " +
            "LEFT JOIN FETCH user.authorities AS authorities " +
			"WHERE user.username = :username")
    User findByUsername(@Param("username") String username);
	
//	@Query("SELECT user FROM User user " +
//			"LEFT JOIN FETCH user.userDetail AS userDetail " +
//            "LEFT JOIN FETCH user.authorities AS authorities " +
//			"WHERE user.username = :username")
//    UUID findByUsername(@Param("username") String username);
}
