package com.vn.camthanh.account.controller;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vn.camthanh.CamthanhAccount.User;
import com.vn.camthanh.account.repository.AccountRepository;

@RestController
@RequestMapping("/account")
public class AccountController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);
	
//	@Autowired
//	UserRepository repository;
	
	@Autowired
	private AccountRepository repository;
	
	//@PostMapping("/")
	@RequestMapping(value="/", method=RequestMethod.POST)
	public User add(@RequestBody User account) {
		LOGGER.info("User add: {}", account);
		return repository.save(account);
	}
	
	@GetMapping("/{id}")
	public User findById(@PathVariable("id") String id) {
		LOGGER.info("User find: id={}", id);
		return repository.findById(id).get();
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public List<User> findAll() {
		LOGGER.info("User find");
		List<User> users = new ArrayList<>();
		repository.findAll().forEach(users::add);
		return users;
	}
	
	/*@GetMapping("/department/{departmentId}")
	public List<User> findByDepartment(@PathVariable("departmentId") Long departmentId) {
		LOGGER.info("User find: departmentId={}", departmentId);
		List<User> users = new ArrayList<>();
		repository.findAll().forEach(users::add);
		return users;
		return ;
	}*/
	
	/*@GetMapping("/organization/{organizationId}")
	public List<User> findByOrganization(@PathVariable("organizationId") Long organizationId) {
		LOGGER.info("User find: organizationId={}", organizationId);
		return repository.findByOrganization(organizationId);
	}*/
	
}
