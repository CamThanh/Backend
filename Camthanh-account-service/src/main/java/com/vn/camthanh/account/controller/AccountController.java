package com.vn.camthanh.account.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vn.camthanh.CamthanhAccount.Authority;
import com.vn.camthanh.CamthanhAccount.User;
import com.vn.camthanh.account.repository.AccountRepository;
import com.vn.camthanh.account.services.AccountServiceImpl;
import com.vn.camthanh.controller.BaseController;
import com.vn.camthanh.services.BaseService;

@RestController
@RequestMapping("/account")
public class AccountController extends BaseController<User> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

//	@Autowired
//	private AccountRepository repository;
	
	public AccountController(AccountServiceImpl service) {
		super(service);
		this.service = service;
	}
	
	@GetMapping("/model")
	public ResponseEntity<User> getModel() {
		User user = ((AccountServiceImpl)service).getModel();
		LOGGER.info("Generated User Model");
		ResponseEntity<User> responseEntity = new ResponseEntity<>(user, HttpStatus.OK);
		return responseEntity;
	}
	
	
	/*
	@PostMapping
	public ResponseEntity<User> add(@RequestBody User account) throws Exception {
		LOGGER.info("User add: {}", account);
		
		User user = service.save(account);
		ResponseEntity<User> responseEntity = new ResponseEntity<>(user, HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping("/{id}")
	public User findById(@PathVariable("id") UUID id) {
		LOGGER.info("User find: id={}", id);
		return repository.findById(id).get();
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<User> updateById(@PathVariable("id") UUID id, @RequestBody User account) {
		LOGGER.info("User find: id={}", id);
		User user = repository.findById(id).get();
		user = service.setupUser(account);
		ResponseEntity<User> responseEntity = new ResponseEntity<>(user, HttpStatus.OK);
		return responseEntity;
	}
	
	

	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		LOGGER.info("User find");
		List<User> users = new ArrayList<>();
		repository.findAll().forEach(users::add);
		
		ResponseEntity<List<User>> responseEntity = new ResponseEntity<>(users, HttpStatus.OK);
		return responseEntity;
	}

*/
}
