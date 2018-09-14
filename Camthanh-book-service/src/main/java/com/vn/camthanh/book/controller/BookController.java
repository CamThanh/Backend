package com.vn.camthanh.book.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vn.camthanh.CamthanhBook.Book;
import com.vn.camthanh.book.services.BookServiceImpl;
import com.vn.camthanh.controller.BaseController;

@RestController
@RequestMapping("/book")
public class BookController extends BaseController<Book> {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

//	@Autowired
//	private AccountRepository repository;
	
	public BookController(BookServiceImpl service) {
		super(service);
		this.service = service;
	}
}
