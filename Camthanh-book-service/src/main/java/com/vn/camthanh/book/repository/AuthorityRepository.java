package com.vn.camthanh.book.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vn.camthanh.CamthanhBook.Authority;
import com.vn.camthanh.repository.BaseRepository;

//@Repository
public interface AuthorityRepository extends BaseRepository<Authority> {

	
	Authority save(Authority entity);
}
