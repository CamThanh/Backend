package com.vn.camthanh.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vn.camthanh.services.BaseService;


@RestController
abstract public class BaseController<T> { // S is service instance, M is model
	private Logger logger = LoggerFactory.getLogger(BaseController.class);
	
    protected BaseService<T> service;

	public BaseController(BaseService<T> service) {
        this.service = service;
	}
	
	@GetMapping("/model")
	public ResponseEntity<T> getModel() {
		T entity = service.getModel();
		logger.info("Generated Object Model");
		ResponseEntity<T> responseEntity = new ResponseEntity<>(entity, HttpStatus.OK);
		return responseEntity;
	}
	
	@RequestMapping(value="/about", method=RequestMethod.GET)
    public String getInfo() {
		logger.debug("Get info API");
		
        return "Info API";
    }
	
	@RequestMapping(method=RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
    public Resources<Resource<T>> listAll() {
		logger.debug("Get all instances API");
		List<T> all = this.service.findAll();
        List<String> params = this.service.getUUIDParams(all);
		
        Resources<Resource<T>> result = this.service.addLinkToResources(getClass(), all, params);
        
        return result;
    }

    @RequestMapping(method=RequestMethod.POST, consumes={MediaType.APPLICATION_JSON_VALUE}, produces={MediaType.APPLICATION_JSON_VALUE})
    public Resource<T> create(@RequestBody T json) {
        logger.debug("create() with body {} of type {}", json, json.getClass());

        T created = this.service.save(json);
        
        Resource<T> result = this.service.addLinkToResource(getClass(), created, this.service.getUUIDParam(created));
        
        return result;
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Resource<T> get(@PathVariable UUID id) {
        T entity = this.service.findById(id);
        
        Resource<T> result = this.service.addLinkToResource(getClass(), entity, this.service.getUUIDParam(entity));
        return result;
    }

    @RequestMapping(value="/{id}", method=RequestMethod.POST, consumes={MediaType.APPLICATION_JSON_VALUE}, produces={MediaType.APPLICATION_JSON_VALUE})
    public Resource<T> update(@PathVariable UUID id, @RequestBody T json) throws IOException {
        logger.debug("update() of id#{} with body {}", id, json);
        logger.debug("T json is of type {}", json.getClass());

        T entity = this.service.findById(id);
        try {
            BeanUtils.copyProperties(entity, json);
        }
        catch (Exception e) {
            logger.warn("while copying properties", e);
            throw new IOException("Set value failed");
        }

        logger.debug("merged entity: {}", entity);

        T updated = this.service.save(entity);
        logger.debug("updated enitity: {}", updated);

        Resource<T> result = this.service.addLinkToResource(getClass(), updated, this.service.getUUIDParam(updated));
        return result;
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Map<String,String>> delete(@PathVariable UUID id) {
    	logger.debug("delete instance id {}", id);
        this.service.delete(id);
        Map<String, String> m = new HashMap<String, String>();
        m.put("status", "deleted");
        return ResponseEntity.ok(m);
    }
}
