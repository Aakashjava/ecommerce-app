package com.aakash.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aakash.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	//public User findByEmail(String email);
	Optional<User> findByEmail(String email);

}
