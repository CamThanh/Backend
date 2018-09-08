package com.vn.camthanh.account.services;

import com.vn.camthanh.CamthanhAccount.Authority;
import com.vn.camthanh.CamthanhAccount.User;
import com.vn.camthanh.account.repository.AccountRepository;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImpl implements UserDetailsService {

    @Autowired
    private AccountRepository userRepository;

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

	public AccountRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(AccountRepository userRepository) {
		this.userRepository = userRepository;
	}
    
    
}
