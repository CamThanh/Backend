package com.vn.camthanh.account.services;

import com.vn.camthanh.CamthanhAccount.Authority;
import com.vn.camthanh.CamthanhAccount.User;
import com.vn.camthanh.CamthanhAccount.UserDetail;
import com.vn.camthanh.account.repository.AccountRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
public class AccountServiceImpl implements UserDetailsService {

    @Autowired
    private AccountRepository userRepository;
    
    @Autowired
    private PasswordEncoder userPasswordEncoder;
    
//    @Autowired
//    private AuthorityRepository authRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
        User user = userRepository.findByUsername(username);

        //System.out.println(user.isAccountExpired() + " " +user.isAccountLocked() + " " + user.is);
        if (user != null) {
            return user;
        }

        throw new UsernameNotFoundException(username);
    }

    public User save(User account) {
    	User user = setupUser(account);
    	return userRepository.save(user);
    }
    
    public User getModel() {
    	User user = setupModel();
    	return user;
    }
	
    public User addAuthority(UUID id, List<Authority> auths) {
    	Optional<User> opt = userRepository.findById(id);
    	User user = null;
    	if(opt.isPresent()) {
    		user = setupAuthority(opt.get(), auths);
    	}
    	return userRepository.save(user);
    }
	
	private User setupUser(User account) {
		User user = new User();
		user.setAccountExpired(account.isAccountExpired());
		user.setAccountLocked(account.isAccountLocked());
		user.setCredentialsExpired(account.isCredentialsExpired());
		user.setEnabled(account.isEnabled());
		user.setPassword(userPasswordEncoder.encode(account.getPassword())); // admin1234
		user.setUsername(account.getUsername());
    	
    	List<Authority> auths = new ArrayList<>();
    	auths.add(new Authority("COMPANY_CREATE"));
    	auths.add(new Authority("COMPANY_READ"));
    	auths.add(new Authority("COMPANY_UPDATE"));
    	auths.add(new Authority("COMPANY_DELETE"));
    	
    	user.setAuthorities(auths);
    	
    	return user;
	}
	
	private User setupModel() {
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
	
	private User setupAuthority(User user, List<Authority> auths) {
		user.getAuthorities().addAll(auths);
		return user;
	}
}
