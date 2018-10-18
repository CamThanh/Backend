package com.vn.camthanh.book.services;

import com.vn.camthanh.CamthanhBook.Authority;
import com.vn.camthanh.CamthanhBook.Book;
import com.vn.camthanh.CamthanhBook.BookChapter;
import com.vn.camthanh.CamthanhBook.Category;
import com.vn.camthanh.CamthanhBook.DateModel;
import com.vn.camthanh.CamthanhBook.Tag;
import com.vn.camthanh.book.repository.BookRepository;
import com.vn.camthanh.services.BaseServiceImpl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookServiceImpl extends BaseServiceImpl<Book> implements BookService {

//    @Autowired
//    private AuthorityRepository authRepository;

    @Autowired
    public BookServiceImpl(BookRepository repository) {
    	super(repository);
    	this.repository = repository;
    }
    
    public Object readZipEntry(ZipInputStream zi) throws IOException {
    	ZipEntry ze = zi.getNextEntry();
    	Book book = new Book();
    	List<BookChapter> chapters = new ArrayList<>();
    	book.setChapters(chapters);
    	
    	out: while(ze != null) {
    		if(ze.isDirectory()) {
    			ze = zi.getNextEntry();
    		} else {
    			// ze is the text file
    			String name = new File(ze.getName()).getName();
    			switch (name) {
				case "info.properties":
					System.out.println("Saving book info");
					readBookInfo(zi, book);
					break;

				default: // read chapter content
					System.out.println(ze.getName());
					System.out.println("Saving book content " + ze.getName());
					readBookChapter(ze, zi, book);
					break;
				}
    			
    			ze = zi.getNextEntry();
    		}
    	}
    	zi.closeEntry();
    	zi.close();
    	this.repository.save(book);
    	return true;
    }
    
    private void readBookChapter(ZipEntry ze, InputStream is, Book book) {
    	BookChapter chapter = new BookChapter();

		chapter.setChapterNum(getChapterNum(ze.getName()));

    	ByteArrayOutputStream out = new ByteArrayOutputStream();
    	byte[] buffer = new byte[2048];
    	int len;
        try {
			while ((len = is.read(buffer)) > 0) {
			    out.write(buffer, 0, len);
			}
		} catch (IOException e) {
			throw new RuntimeException("Read chapter " + ze.getName() + " error");
		}
        
        chapter.setContent(out.toByteArray());
        book.getChapters().add(chapter);
    	
    }
    
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    private final SimpleDateFormat targetdf = new SimpleDateFormat("YYYY-MM-dd");
    
    private int getChapterNum(String fileName) {
    	String[] path = fileName.split("/");
    	String canonicalName = path[path.length-1];
    	String name = canonicalName.split("\\.")[0];
    	String chapNum = name.split("_")[1];
    	return Integer.valueOf(chapNum);
    }
    
    private void readBookInfo(InputStream is, Book book) {
    	Properties prop = new Properties();
    	
    	try {
			//prop.load(is);
			prop.load(new InputStreamReader(is, Charset.forName("UTF-8")));
			String authors = prop.getProperty("authors");
			String tags = prop.getProperty("tags");
			String editors = prop.getProperty("editors");
			String writters = prop.getProperty("writters");
			String authorities = prop.getProperty("authorities");
			String categories = prop.getProperty("categories");
			String title = prop.getProperty("title");
			String cover = prop.getProperty("cover");
			String description = prop.getProperty("description");
			String type = prop.getProperty("type");
			String summary = prop.getProperty("summary");
			int view = Integer.valueOf(prop.getProperty("view"));
			float rate = Float.valueOf(prop.getProperty("rate"));
			int totalChapters = Integer.valueOf(prop.getProperty("totalChapters"));
			boolean completed = Boolean.valueOf(prop.getProperty("completed"));
			Date createdDate = sdf.parse(prop.getProperty("createdDate"));
			Date modifiedDate = sdf.parse(prop.getProperty("modifiedDate"));
			
			book.setAuthors(readStringToken(authors));
			book.setTags(readStringToken(tags));
			book.setCategories(readStringToken(categories));
			book.setEditors(readStringToken(editors));
			book.setWriters(readStringToken(writters));
			
			List<Authority> auths = new ArrayList<>();
			for(String auth : readStringToken(authorities)) {
				auths.add(new Authority(auth));
			}
			book.setAuthorities(auths);
			book.setTitle(title);
			book.setCover(cover);
			book.setDescription(description);
			book.setType(type);
			book.setSummary(summary);
			book.setView(view);
			book.setRate(rate);
			book.setTotalChapters(totalChapters);
			book.setCompleted(completed);
			
			Date createdDateF = targetdf.parse(targetdf.format(createdDate));
			Date modifiedDateF = targetdf.parse(targetdf.format(modifiedDate));
			DateModel dateModel = new DateModel();
			dateModel.setCreatedDate(createdDateF);
			dateModel.setModifiedDate(modifiedDateF);
			book.setDateModel(dateModel);
			
		} catch (IOException e) {
			throw new RuntimeException("Book info is incorrect format");
		} catch (ParseException e) {
			throw new RuntimeException("Date is incorrect format");
		} catch (Exception e) {
			throw new RuntimeException("Read book info error");
		}
    	
    	/*Scanner sc = new Scanner(is);
		while (sc.hasNextLine()) {
		     System.out.println(sc.nextLine());
		 }*/
		
    }
    
    private List<String> readStringToken(String tokens) {
    	List<String> result = new ArrayList<>();
    	
    	if(StringUtils.isBlank(tokens)) {
    		return result;
    	} else {
	    	
	    	StringTokenizer tok = new StringTokenizer(tokens, "#");
	    	while (tok.hasMoreElements()) {
				result.add((String) tok.nextElement());
			}
    	}
    	return result;
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
    	
//    	List<Category> categories = new ArrayList<>();
//    	Category category = new Category();
//    	category.setDescription("");
//    	category.setName("");
//    	categories.add(category);
//    	model.setCategories(categories);

    	model.setCategories(new ArrayList<String>());
    	
    	List<BookChapter> chapters = new ArrayList<>();
    	BookChapter chapter = new BookChapter();
    	chapter.setChapterNum(0);
    	chapter.setDateModel(new DateModel());
    	chapter.setFolderContent("");
    	chapter.setContent(null);
    	chapter.setShortPath("");
    	chapters.add(chapter);
    	model.setChapters(chapters);
    	
//    	List<Tag> tags = new ArrayList<>();
//    	Tag tag = new Tag();
//    	tag.setValue("");
//    	tags.add(tag);
//    	model.setTags(tags);
    	
    	model.setTags(new ArrayList<String>());
    	
    	DateModel dateModel = new DateModel();
    	model.setDateModel(dateModel);
    	
    	return model;
	}
	
	private Book setupAuthority(Book book, List<Authority> auths) {
		book.getAuthorities().addAll(auths);
		return book;
	}
}
