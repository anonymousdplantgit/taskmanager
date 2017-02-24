package com.soucreation.service;

import com.soucreation.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}
