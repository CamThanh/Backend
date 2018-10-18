package com.vn.camthanh.CamthanhBook;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Data
public class Book {

	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "ID", columnDefinition = "BINARY(16)")
	@Id
	private UUID uuid;
	
	@Column
    private String title;
	
	@Column
    private String cover;
	
	@Column
    private boolean enabled;
	
	@Column
    private boolean completed;
	
	@Column
    private String description;
	
	@Column
    private String authorId;
	
	@ElementCollection
	@Column
    private List<String> authors;
	
	@ElementCollection
	@Column
    private List<String> writers;
	
	@ElementCollection
	@Column
    private List<String> editors;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinTable(name = "BOOKS_AUTHORITIES", joinColumns = @JoinColumn(name = "BOOK_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID"))
	private Collection<Authority> authorities;
	
	//@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinTable(name = "BOOKS_TAGS", joinColumns = @JoinColumn(name = "BOOK_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "TAG_ID", referencedColumnName = "ID"))
	@ElementCollection
	@Column
	private List<String> tags;
	
	//@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinTable(name = "BOOKS_CATEGORIES", joinColumns = @JoinColumn(name = "BOOK_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID"))
	@ElementCollection
	@Column
	private List<String> categories;
	
	@Column
    private String summary;
	
	@Column
    private int view;
	
	@Column
    private int totalChapters;
	
	@Column
    private float rate;
	
	@Column
    private String type;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinTable(name = "BOOKS_CHAPTERS", joinColumns = @JoinColumn(name = "BOOK_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "CHAPTER_ID", referencedColumnName = "ID"))
	//@ElementCollection
	private List<BookChapter> chapters;
	
	@Column
	@Embedded
	private DateModel dateModel;
}
