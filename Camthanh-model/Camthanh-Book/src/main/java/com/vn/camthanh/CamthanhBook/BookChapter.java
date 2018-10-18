package com.vn.camthanh.CamthanhBook;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Data
public class BookChapter {
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "ID", columnDefinition = "BINARY(16)")
	@Id
	private UUID uuid;
	
	@Column
    private int chapterNum;
	
	@Column
    private String shortPath; // book name + chapter number
	
	@Lob
    @Column(columnDefinition="BLOB")
    private byte[] content;
	
	@Column
    private String folderContent;
	
	@Column
	@Embedded
	private DateModel dateModel;
}
