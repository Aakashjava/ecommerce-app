package com.aakash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aakash.Exception.UserException;
import com.aakash.authResponse.AuthResponse;
import com.aakash.config.JwtProvider;
import com.aakash.model.User;
import com.aakash.repository.UserRepository;
import com.aakash.request.LoginRequest;
import com.aakash.service.CustomeUserServiceImplementation;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private JwtProvider jwtProvider;
	@Autowired
	private PasswordEncoder passwordEcoder;
	@Autowired
	private CustomeUserServiceImplementation customUserService;

public AuthController(UserRepository userRepository,CustomeUserServiceImplementation customUserService , PasswordEncoder passwordEcoder,JwtProvider jwtProvider) {
	// TODO Auto-generated constructor stub
	this.userRepository=userRepository;
	this.customUserService=customUserService;
	this .passwordEcoder=passwordEcoder;
	this.jwtProvider=jwtProvider;
}

//
//	@PostMapping("/signup")
//	public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws UserException{
//	System.out.println("Signup API hit!");  // Check if API is being called
//	String email=user.getEmail();
//		String password=user.getPassword();
//		String firstName=user.getFirstName();
//		String lastName=user.getLastName();
//		
//		 System.out.println("Email: " + email);
//		    System.out.println("Password: " + password);
//		    
//		System.out.println(email);
//		User isEmailExist=userRepository.findByEmail(email);
//		if(isEmailExist !=null) {
//			throw new UserException("Email is already used with another account");
//		}
//		User createdUser=new User();
//		createdUser.setEmail(email);
//		createdUser.setPassword(passwordEcoder.encode(password));
//		createdUser.setFirstName(firstName);
//		createdUser.setLastName(lastName);
//		
//		System.out.println("Saving user to DB...");
//		
//		User savedUser=userRepository.save(createdUser);
//		System.out.println("Saved User: " + savedUser+ "  " +savedUser.getId());
//		
//		Authentication authentication =new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//		String token=jwtProvider.generateToken(authentication);
//		
//		
//		AuthResponse authResponse=new AuthResponse();
//		authResponse.setJwt(token);
//		authResponse.setMessage("Signup success..");
//		System.out.println(authResponse);
//		return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);
//		
//	}

//chatGpt working
//@PostMapping("/signup")
//public ResponseEntity<String> createUserHandler(@RequestBody User user) {
//    System.out.println("🔥 Signup API Hit! Received User: " + user);
//
//	String email=user.getEmail();
//	String password=user.getPassword();
//	String firstName=user.getFirstName();
//	String lastName=user.getLastName();
//    // Debug: Ensure email is not null
//    if (user.getEmail() == null) {
//        System.out.println("❌ Error: Email is null");
//        return ResponseEntity.badRequest().body("Email is required");
//    }
//
//    // Debug: Check password encoding
//    String encodedPassword = passwordEcoder.encode(user.getPassword());
//    System.out.println("🔑 Encoded Password: " + encodedPassword);
//	User createdUser=new User();
//	createdUser.setEmail(email);
//	createdUser.setPassword(passwordEcoder.encode(password));
//	createdUser.setFirstName(firstName);
//	createdUser.setLastName(lastName);
//    
//    user.setPassword(encodedPassword);
//    userRepository.save(user);
//
//    return ResponseEntity.ok("Signup successful!");
//}

@PostMapping("/signup")
public ResponseEntity<?> registerUser(@RequestBody User user) {
    try {
        User newUser = customUserService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    } catch (RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest loginRequest){
		 
		
		String username=loginRequest.getEmail();
		String password=loginRequest.getPassword();
		System.out.println("🔥 Signin API Hit! user succesfully login: " + username); 
		
		Authentication authentication=authenticate( username, password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token=jwtProvider.generateToken(authentication);
		
		
		AuthResponse authResponse=new AuthResponse();
		authResponse.setJwt(token);
		authResponse.setMessage("Signin success..");
		
		
		return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);
		
	}


	private Authentication authenticate(String username,String password) {
		// TODO Auto-generated method stub
		
		UserDetails userDetails =customUserService.loadUserByUsername(username);
		
		
		if(userDetails==null) {
			throw new BadCredentialsException("invalid Username...");
			
		}
		if(!passwordEcoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid Password...");
			
		}
				
				
		return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
	}
}
