package com.vn.camthanh.CamthanhAccount;

import com.fasterxml.jackson.annotation.JsonValue;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Data;
//@Entity
//@Table(name = "USER_DETAIL", uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }) })
@Data
@Embeddable
public class UserDetail implements Serializable {

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 1L;

	/*@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "ID", columnDefinition = "BINARY(16)")
	@Id
	private UUID uuid;*/
	
	@NotNull
    @Email
    @Column(unique = true)
    private String email;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column(length = 32, columnDefinition = "varchar(32) default 'NA'")
    @Enumerated(EnumType.STRING)
    private GENTLE_ENUM gentle;

    @Column
    private int age;

    @Column
    private String phone;

    @Column
    private String avatarUri;
    
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "USER_DETAIL_PAYMENT", joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "PAYMENT_ID", referencedColumnName = "ID"))
//    //@OrderBy
//    @JsonIgnore
//    private List<Payment> payments;
//
//    @Column
//    @ManyToMany(cascade=CascadeType.ALL)
//    @JoinTable(name = "USER_DETAIL_BOOK", joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "BOOK_ID", referencedColumnName = "ID"))
//    //@OrderBy
//    @JsonIgnore
//    private List<Book> books;
//    
//    @Column
//    @OneToMany(cascade=CascadeType.ALL)
//    @JoinTable(name = "USER_DETAIL_TRANSACTION", joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "TRANSACTION_ID", referencedColumnName = "ID"))
//    //@OrderBy
//    @JsonIgnore
//    private List<Transaction> transactions;
//    
//    @Column
//    @OneToMany(cascade=CascadeType.ALL)
//    @JoinTable(name = "USER_DETAIL_SESSION", joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "SESSION_ID", referencedColumnName = "ID"))
//    //@OrderBy
//    @JsonIgnore
//    private List<Session> sessions;
    
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "USERS_AUTHORITIES", joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID"))
//    //@OrderBy
//    @JsonIgnore
//    private Collection<Authority> authorities;

    //@JsonFormat(shape = JsonFormat.Shape.OBJECT)
    enum GENTLE_ENUM {
    	MALE("M"),
    	FEMALE("F"),
    	NA("N");
    	
    	private String gentle;
    	
    	GENTLE_ENUM(String gentle) {
    		this.gentle = gentle;
    	}
    	
    	@JsonValue
    	public String getGentle( ) {
    		return gentle;
    	}
    }
}
