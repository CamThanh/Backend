package com.vn.camthanh.services;

import java.util.List;
import java.util.UUID;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;

@SuppressWarnings("rawtypes")
public interface BaseService<T> { // repo + model

	T getModel();
	
	T findById(UUID uuid);
	
	List<T> findAll();
	
	boolean delete(T entity);
	
	boolean delete(UUID uuid);
	
	T save(T entity);
	
	T update(UUID uuid, T entity);
	
	Link getLink(List<String> params);
	
	Link getLink(String param);
	
	Link getLink();
	
	List<String> getUUIDParams(List<T> list);
	
	String getUUIDParam(T entity);
	
	T cloneEntity(T entity);
	
	Resources<Resource<T>> addLinkToResources(Class clazz, List<T> entities, List<String> params);
	
	Resource<T> addLinkToResource(Class clazz, T entity, String param);
}
