package com.vn.camthanh.spring.security.oauth2.config.server;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.vn.camthanh.CamthanhAccount.Authority;
import com.vn.camthanh.CamthanhAccount.User;
import com.vn.camthanh.spring.security.oauth2.config.encryption.Encoders;
import com.vn.camthanh.spring.security.oauth2.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@Import(Encoders.class)
public class ServerSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder userPasswordEncoder;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	User user1 = new User();
    	user1.setAccountExpired(false);
    	user1.setAccountLocked(false);
    	user1.setCredentialsExpired(false);
    	user1.setEnabled(true);
    	user1.setPassword("$2a$08$qvrzQZ7jJ7oy2p/msL4M0.l83Cd0jNsX6AJUitbgRXGzge4j035ha"); // admin1234
    	user1.setUsername("admin");
    	
    	List<Authority> auths = new ArrayList<>();
    	auths.add(new Authority("COMPANY_CREATE"));
    	auths.add(new Authority("COMPANY_READ"));
    	auths.add(new Authority("COMPANY_UPDATE"));
    	auths.add(new Authority("COMPANY_DELETE"));
    	
    	user1.setAuthorities(auths);
  	
    	((UserDetailsServiceImpl)userDetailsService).getUserRepository().save(user1);
        auth.userDetailsService(userDetailsService).passwordEncoder(userPasswordEncoder);
    }
}
