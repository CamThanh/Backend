package com.vn.camthanh.services;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;

import com.vn.camthanh.repository.BaseRepository;

@SuppressWarnings("rawtypes")
public abstract class BaseServiceImpl<T> implements BaseService<T> {
	
	protected BaseRepository<T> repository;
	
	public BaseServiceImpl(BaseRepository<T> repository) {
		this.repository = repository;
	}
	
	@Override
	public T getModel() {
    	T entity = setupModel();
    	return entity;
    }
	
	protected T setupModel() {
    	return null;
	}
	
	@Override
	public Resources<Resource<T>> addLinkToResources(Class clazz, List<T> entities, List<String> params) {
		List<Resource<T>> list = new ArrayList<>();
		Link link = getLink();
		
		for(int i = 0; i < entities.size(); i++) {
			list.add(addLinkToResource(clazz, entities.get(i), "/" + params.get(i)));
		}
		
		Resources<Resource<T>> resources = new Resources<Resource<T>>(list, link);
		
		return resources;
	}
	
	@Override
	public Resource<T> addLinkToResource(Class clazz, T entity, String param) {
		Link link = linkTo(clazz).slash(param).withSelfRel();
		
		Resource<T> resource = new Resource<T>(entity, link);
		
		return resource;
	}
	
	@Override
	public List<String> getUUIDParams(List<T> list) {
		return null;
	}
	
	@Override
	public String getUUIDParam(T entity) {
		return null;
	}
	
	@Override
	public Link getLink(List<String> params) {
		StringBuilder paths = new StringBuilder();
		params.forEach(param -> {
			paths.append("/" + param);
		});
		Link link = linkTo(Object.class).slash(paths.toString()).withSelfRel();
		
		return link;
	}
	
	@Override
	public Link getLink(String param) {
		StringBuilder paths = new StringBuilder();
		paths.append("/" + param);
		Link link = linkTo(Object.class).slash(paths.toString()).withSelfRel();
		
		return link;
	}
	
	@Override
	public Link getLink() {
		Link link = linkTo(Object.class).withSelfRel();
		
		return link;
	}
	
	@Override
	public T findById(UUID uuid) {
		Optional<T> entity = repository.findById(uuid);
		if(entity.isPresent()) {
			return entity.get();
		} else {
			return null;
		}
	}

	@Override
	public List<T> findAll() {
		Iterable<T> result = repository.findAll();
		List<T> list = new ArrayList<>();
		result.forEach(list::add);
		
		return list;
	}

	@Override
	public boolean delete(T entity) {
		repository.delete(entity);
		return true;
	}
	
	@Override
	public boolean delete(UUID uuid) {
		if(repository.existsById(uuid)) {
			repository.deleteById(uuid);
			if(repository.existsById(uuid)) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public T cloneEntity(T entity) {
		return null;
	}

	@Override
	public T save(T entity) {
		T cloned = cloneEntity(entity);
		T result = repository.save(cloned);
		return result;
	}

	@Override
	public T update(UUID uuid, T entity) {
		if(repository.existsById(uuid)) {
			T result = repository.save(entity);
			return result;
		}
		return null;
	}

}
