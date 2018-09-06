package com.vn.camthanh.CamthanhAccount;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import org.hibernate.annotations.GenericGenerator;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.Getter;

@Entity
@Table(name = "USER_DETAIL")
@Data
//@EqualsAndHashCode(of = "id")
public class UserDetail {

    /**
	 * 
	 */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Transient
	private static final long serialVersionUID = 1L;
//
//	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "ID")
	@GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
	@Id
    private String id;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column(length=1)
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

    @Getter
    enum GENTLE_ENUM {
    	MALE("M"),
    	FEMALE("F"),
    	NA("N");
    	
    	private String gentle;
    	
    	GENTLE_ENUM(String gentle) {
    		this.gentle = gentle;
    	}
    }
}
