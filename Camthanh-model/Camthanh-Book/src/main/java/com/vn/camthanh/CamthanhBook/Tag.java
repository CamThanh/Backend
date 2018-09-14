package com.vn.camthanh.CamthanhBook;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Table(name = "tag", uniqueConstraints = { @UniqueConstraint(columnNames = { "value" }) })
@Entity
@Data
public class Tag {
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "ID", columnDefinition = "BINARY(16)")
	@Id
	private UUID uuid;
	
	@Column
    private String value;
	
}
