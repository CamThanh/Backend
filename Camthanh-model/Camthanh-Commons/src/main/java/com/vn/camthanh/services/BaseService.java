package com.vn.camthanh.services;

import java.util.List;
import java.util.UUID;

public interface BaseService<R, M> {

	M findById(UUID uuid);
	List<M> findAll();
	
}
