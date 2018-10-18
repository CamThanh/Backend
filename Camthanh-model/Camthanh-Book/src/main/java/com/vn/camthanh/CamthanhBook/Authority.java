package com.vn.camthanh.CamthanhBook;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "AUTHORITY")//, uniqueConstraints = {@UniqueConstraint(columnNames = {"NAME"})})
@Getter
@Setter
//@EqualsAndHashCode(of = "id")
public class Authority implements GrantedAuthority {

    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "ID", columnDefinition = "BINARY(16)")
    @Id
    private UUID uuid;

    @Column(name = "NAME")
    private String name;

    @Override
    public String getAuthority() {
        return getName();
    }
    
    public Authority(String name) {
    	this.name = name;
    }
    
    public Authority() {
    }
}
