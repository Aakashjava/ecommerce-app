package com.aakash.service;



import com.aakash.Exception.UserException;
import com.aakash.model.User;

public interface UserService {

	public User findUserById(Long userId)throws UserException;
	public User findUserProfileByJwt(String jwt)throws UserException;
}
