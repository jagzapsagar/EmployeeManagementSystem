package com.example.demo.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.entity.User;
import com.example.demo.repo.UserRepo;

public class CustomUserDetailsService implements UserDetailsService {
	
	 @Autowired
	 private UserRepo repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> credential = repository.findByName(username);
		System.out.println("-------------------------");
		System.out.println(credential.get());
        return credential.map(CustomUserDetails::new).orElseThrow(() -> 
        new UsernameNotFoundException("user not found with name :" + username));
		//return null;
	}

}
