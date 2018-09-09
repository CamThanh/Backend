package com.vn.camthanh.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

public interface BaseRepository<M> extends CrudRepository<M, UUID> {

}
