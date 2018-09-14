package com.vn.camthanh.CamthanhAccount;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Embeddable
public class DateModel {

//	@GeneratedValue(generator = "uuid2")
//	@GenericGenerator(name = "uuid2", strategy = "uuid2")
//	@Column(name = "ID", columnDefinition = "BINARY(16)")
//	@Id
//	private UUID uuid;
	
	@Column
	private Date createdDate;
	
	@Column
	private Date modifiedDate;
	
	@Column
	private Date effectiveDate;
	
	@Column
	private Date expiredDate;
	
}
