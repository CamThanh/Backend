package com.vn.camthanh.book.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.vn.camthanh.CamthanhBook.Book;
import com.vn.camthanh.book.services.BookServiceImpl;
import com.vn.camthanh.controller.BaseController;

@RestController
@RequestMapping("/book")
public class BookController extends BaseController<Book> {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);
	
	private static final String UPLOADED_FOLDER="./temp/";

	public BookController(BookServiceImpl service) {
		super(service);
		this.service = service;
	}
	
	@RequestMapping(value="/attachment", method=RequestMethod.POST)
    public String uploadFiles(@RequestParam("file") MultipartFile file) {
		LOGGER.info("Upload files to books");
		if(file.isEmpty()) {
			// return error
		} else {
			try {

	            // Get the file and save it somewhere
//	            byte[] bytes = file.getBytes();
//	            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
	            //Files.write(path, bytes);
	            
	            ZipInputStream zipIn = new ZipInputStream(file.getInputStream());

	            ((BookServiceImpl)this.service).readZipEntry(zipIn);
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	            // return error
	        }
		}
        return "Info API";
    }
}
