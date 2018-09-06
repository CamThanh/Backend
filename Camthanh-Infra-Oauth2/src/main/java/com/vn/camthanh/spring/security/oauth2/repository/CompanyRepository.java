package com.vn.camthanh.spring.security.oauth2.repository;

import com.vn.camthanh.spring.security.oauth2.model.Company;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface CompanyRepository extends CrudRepository<Company, Long> {

    Company findByName(String name);

    List<Company> findAll();

}
