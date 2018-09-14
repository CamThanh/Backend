package com.vn.camthanh.book.repository;

import java.util.UUID;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vn.camthanh.CamthanhBook.Book;
import com.vn.camthanh.repository.BaseRepository;

//@Repository
public interface BookRepository extends BaseRepository<Book> {

}
