package com.vn.camthanh.account.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vn.camthanh.CamthanhAccount.Authority;
import com.vn.camthanh.CamthanhAccount.User;

public interface AuthorityRepository extends CrudRepository<Authority, UUID> {

	@Override
    List<Authority> findAll();
	
	//List<Authority> saveAll();
}
