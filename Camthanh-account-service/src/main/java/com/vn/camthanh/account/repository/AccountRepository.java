package com.vn.camthanh.account.repository;

import java.util.UUID;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vn.camthanh.CamthanhAccount.User;
import com.vn.camthanh.repository.BaseRepository;

//@Repository
public interface AccountRepository extends BaseRepository<User> {

	@Query("SELECT user FROM User user " +
            "LEFT OUTER JOIN FETCH user.authorities AS authorities " +
            "LEFT OUTER JOIN FETCH user.userDetail AS userDetail " +
			"WHERE user.username = :username")
    User findByUsername(@Param("username") String username);
}
