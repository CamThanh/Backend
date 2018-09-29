package com.vn.camthanh.CamthanhAccount;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Embeddable
@Data
public class DateModel {

//	@GeneratedValue(generator = "uuid2")
//	@GenericGenerator(name = "uuid2", strategy = "uuid2")
//	@Column(name = "ID", columnDefinition = "BINARY(16)")
//	@Id
//	private UUID uuid;
	
	//private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
	
	@Column
	private Date createdDate;
	
	@Column
	private Date modifiedDate;
	
	@Column
	private Date effectiveDate;
	
	@Column
	private Date expiredDate;
	
	@JsonIgnore
	public boolean isValidExpDate() {
		return effectiveDate.before(expiredDate) && !createdDate.after(effectiveDate);
	}
	
	@JsonIgnore
	public boolean isValidEffDate() {
		return !effectiveDate.before(createdDate);
	}
	
	@JsonIgnore
	public boolean isValidCreateDate() {
		return createdDate.getYear() > 1900;
	}
	
}
