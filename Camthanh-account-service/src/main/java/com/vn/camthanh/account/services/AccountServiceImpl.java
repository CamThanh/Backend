package com.vn.camthanh.account.services;

import com.vn.camthanh.CamthanhAccount.Authority;
import com.vn.camthanh.CamthanhAccount.User;
import com.vn.camthanh.CamthanhAccount.UserDetail;
import com.vn.camthanh.account.config.Encoders;
import com.vn.camthanh.account.repository.AccountRepository;
import com.vn.camthanh.account.repository.AuthorityRepository;
import com.vn.camthanh.services.BaseServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Import(Encoders.class)
public class AccountServiceImpl extends BaseServiceImpl<User> implements UserDetailsService {

    @Autowired
    private AuthorityRepository authRepository;
//    
    @Autowired
    private PasswordEncoder userPasswordEncoder;
    
    @Autowired
    private EntityManager em;
	
//    @Autowired
//    private AuthorityRepository authRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository repository) {
    	super(repository);
    	this.repository = repository;
    }
    
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
        User user = ((AccountRepository)repository).findByUsername(username);

        //System.out.println(user.isAccountExpired() + " " +user.isAccountLocked() + " " + user.is);
        if (user != null) {
            return user;
        }

        throw new UsernameNotFoundException(username);
    }

    @Override
    public List<String> getUUIDParams(List<User> list) {
    	List<String> params = new ArrayList<>();
    	list.forEach(t -> {
    		params.add(t.getUuid().toString());
    	});
		return params;
	}
    
    @Override
	public String getUUIDParam(User entity) {
		return entity.getUuid().toString();
	}
    
    public User getModel() {
    	User user = setupModel();
    	return user;
    }
    
    @Override
	public User save(User entity) {
    	List<Authority> authRecords = new ArrayList<>();
		authRecords.addAll(authRepository.findAll());
		
		List<Authority> auths = new ArrayList<>();
		auths.addAll(entity.getAuthorities());
		
    	User cloned = cloneEntity(entity);
		User result = repository.save(cloned);
		
		return result;
	}
	
    public List<Authority> addAuthority(List<Authority> auths, List<Authority> authRecords) {
    	auths.forEach(auth -> {
			for(Authority a : authRecords) {
				if(a.getName().equalsIgnoreCase(auth.getName())) {
					auth.setUuid(a.getUuid());
					break;
				} else {
					//auth = authRepository.save(auth);
				}
			}
			
		});
    	
    	//auths = authRepository.saveAll();
    	
    	return auths;
    }
    
    @Override
	public User cloneEntity(User account) {
    	User user = new User();
		//user.setUuid(account.getUuid());
		user.setAccountExpired(account.isAccountExpired());
		user.setAccountLocked(account.isAccountLocked());
		user.setCredentialsExpired(account.isCredentialsExpired());
		user.setEnabled(account.isEnabled());
		user.setPassword(userPasswordEncoder.encode(account.getPassword())); // admin1234
		user.setUsername(account.getUsername());
		
		UserDetail userDetail = new UserDetail();
		//userDetail.setUuid(account.getUserDetail().getUuid());
		userDetail.setEmail(account.getUserDetail().getEmail());
		userDetail.setAge(account.getUserDetail().getAge());
		userDetail.setFirstname(account.getUserDetail().getFirstname());
		userDetail.setLastname(account.getUserDetail().getLastname());
		userDetail.setGentle(account.getUserDetail().getGentle());
		userDetail.setPhone(account.getUserDetail().getPhone());
		userDetail.setAvatarUri(account.getUserDetail().getAvatarUri());
		user.setUserDetail(userDetail);
    	
		/*List<Authority> authRecords = new ArrayList<>();
		authRecords.addAll(authRepository.findAll());
		
		List<Authority> auths = new ArrayList<>();
		auths.addAll((List<Authority>) account.getAuthorities());
		
		auths.forEach(auth -> {
			for(Authority a : authRecords) {
				if(a.getName().equalsIgnoreCase(auth.getName())) {
					auth.setUuid(a.getUuid());
					break;
				}
			}
			authRepository.save(auth);
		});*/
		
    	List<Authority> auths = new ArrayList<>();
    	auths.add(new Authority("COMPANY_CREATE"));
    	auths.add(new Authority("COMPANY_READ"));
    	auths.add(new Authority("COMPANY_UPDATE"));
    	auths.add(new Authority("COMPANY_DELETE"));
    	
		//authRepository.saveAll(auths);
    	user.setAuthorities(auths);
    	
    	return user;
	}
	
	public User setupUser(User account) {
		User user = new User();
		user.setUuid(account.getUuid());
		user.setAccountExpired(account.isAccountExpired());
		user.setAccountLocked(account.isAccountLocked());
		user.setCredentialsExpired(account.isCredentialsExpired());
		user.setEnabled(account.isEnabled());
		user.setPassword(userPasswordEncoder.encode(account.getPassword())); // admin1234
		user.setUsername(account.getUsername());
		
		UserDetail userDetail = new UserDetail();
		//userDetail.setUuid(account.getUserDetail().getUuid());
		userDetail.setEmail(account.getUserDetail().getEmail());
		userDetail.setAge(account.getUserDetail().getAge());
		userDetail.setFirstname(account.getUserDetail().getFirstname());
		userDetail.setLastname(account.getUserDetail().getLastname());
		userDetail.setGentle(account.getUserDetail().getGentle());
		userDetail.setPhone(account.getUserDetail().getPhone());
		userDetail.setAvatarUri(account.getUserDetail().getAvatarUri());
		user.setUserDetail(userDetail);
    	
    	List<Authority> auths = new ArrayList<>();
    	auths.add(new Authority("COMPANY_CREATE"));
    	auths.add(new Authority("COMPANY_READ"));
    	auths.add(new Authority("COMPANY_UPDATE"));
    	auths.add(new Authority("COMPANY_DELETE"));
    	
    	user.setAuthorities(auths);
    	
    	return user;
	}
	
	protected User setupModel() {
		User user = new User();
		user.setAccountExpired(false);
    	user.setAccountLocked(false);
    	user.setCredentialsExpired(false);
    	user.setEnabled(true);
    	user.setPassword(""); // admin1234
    	user.setUsername("");
    	
    	UserDetail userDetail = new UserDetail();
    	user.setUserDetail(userDetail);
    	
    	List<Authority> auths = new ArrayList<>();
    	auths.add(new Authority("COMPANY_CREATE"));
    	auths.add(new Authority("COMPANY_READ"));
    	auths.add(new Authority("COMPANY_UPDATE"));
    	auths.add(new Authority("COMPANY_DELETE"));
    	
    	user.setAuthorities(auths);
    	return user;
	}
	
}
