package com.vn.camthanh.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.vn.camthanh.services.BaseService;

abstract public class BaseController<S, M> { // S is service instance, M is model
	private Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	@Autowired
    private BaseService<S> service;

	public BaseController(BaseService service) {
        this.service = service;
	}
}
