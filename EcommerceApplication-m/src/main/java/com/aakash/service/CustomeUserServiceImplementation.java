package com.aakash.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aakash.model.User;
import com.aakash.repository.UserRepository;

@Service
public class CustomeUserServiceImplementation implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEcoder;
	
	public CustomeUserServiceImplementation(UserRepository userRepository) {
		// TODO Auto-generated constructor stub
		this.userRepository=userRepository;
	}
//	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		// TODO Auto-generated method stub
//		User user=userRepository.findByEmail(username);
//		
//		if(user==null) {
//			throw new UsernameNotFoundException("user not found - "+username);
//			
//		}
//		List<GrantedAuthority> authorities=new ArrayList<>();
//		
//		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
//	}
	  public User registerUser(User user) {
	        // Check if email already exists
	        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
	            throw new RuntimeException("Email already exists. Please use a different email.");
	        }

	        // Encrypt password before saving
	        user.setPassword(passwordEcoder.encode(user.getPassword()));
	        
	        return userRepository.save(user);
	    }
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
