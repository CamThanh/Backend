package com.vn.camthanh.book.services;

import com.vn.camthanh.CamthanhBook.Authority;
import com.vn.camthanh.CamthanhBook.Book;
import com.vn.camthanh.CamthanhBook.BookChapter;
import com.vn.camthanh.CamthanhBook.Category;
import com.vn.camthanh.CamthanhBook.DateModel;
import com.vn.camthanh.CamthanhBook.Tag;
import com.vn.camthanh.book.repository.BookRepository;
import com.vn.camthanh.services.BaseServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookServiceImpl extends BaseServiceImpl<Book> {

//    @Autowired
//    private AuthorityRepository authRepository;

    @Autowired
    public BookServiceImpl(BookRepository repository) {
    	super(repository);
    	this.repository = repository;
    }

    @Override
    public List<String> getUUIDParams(List<Book> list) {
    	List<String> params = new ArrayList<>();
    	list.forEach(t -> {
    		params.add(t.getUuid().toString());
    	});
		return params;
	}
    
    @Override
	public String getUUIDParam(Book entity) {
		return entity.getUuid().toString();
	}
	
    public Book addAuthority(UUID id, List<Authority> auths) {
    	Optional<Book> opt = repository.findById(id);
    	Book book = null;
    	if(opt.isPresent()) {
    		book = setupAuthority(opt.get(), auths);
    	}
    	return repository.save(book);
    }
    
    @Override
    public Book cloneEntity(Book book) {
    	Book entity = new Book();
    	BeanUtils.copyProperties(book, entity);
    	
    	return entity;
    }
	
	private Book setupBook(Book book) {
		Book entity = new Book();
//		entity.setAuthorId(book.getAuthorId());
//		entity.setCover(book.getCover());
//		entity.se
    	
		BeanUtils.copyProperties(book, entity);
		
    	List<Authority> auths = new ArrayList<>();
    	auths.add(new Authority("COMPANY_CREATE"));
    	auths.add(new Authority("COMPANY_READ"));
    	auths.add(new Authority("COMPANY_UPDATE"));
    	auths.add(new Authority("COMPANY_DELETE"));
    	entity.setAuthorities(auths);
    	
    	return null;
	}
	
	protected Book setupModel() {
    	Book model = new Book();
    	model.setAuthorId("");
    	
    	model.setCover("");
    	model.setDateModel(new DateModel());
    	model.setDescription("");
    	model.setEnabled(false);
    	model.setSummary("");
    	model.setTitle("");
    	model.setView(0);
    	
    	List<Authority> auths = new ArrayList<>();
    	auths.add(new Authority("COMPANY_CREATE"));
    	auths.add(new Authority("COMPANY_READ"));
    	auths.add(new Authority("COMPANY_UPDATE"));
    	auths.add(new Authority("COMPANY_DELETE"));
    	model.setAuthorities(auths);
    	
    	List<Category> categories = new ArrayList<>();
    	Category category = new Category();
    	category.setDescription("");
    	category.setName("");
    	categories.add(category);
    	model.setCategories(categories);
    	
    	List<BookChapter> chapters = new ArrayList<>();
    	BookChapter chapter = new BookChapter();
    	chapter.setChapterNum(0);
    	chapter.setDateModel(new DateModel());
    	chapter.setFolderContent("");
    	chapter.setContent("");
    	chapter.setShortPath("");
    	chapters.add(chapter);
    	model.setChapters(chapters);
    	
    	List<Tag> tags = new ArrayList<>();
    	Tag tag = new Tag();
    	tag.setValue("");
    	tags.add(tag);
    	model.setTags(tags);
    	
    	return model;
	}
	
	private Book setupAuthority(Book book, List<Authority> auths) {
		book.getAuthorities().addAll(auths);
		return book;
	}
}
