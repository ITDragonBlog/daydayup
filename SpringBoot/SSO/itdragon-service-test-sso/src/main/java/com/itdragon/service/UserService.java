package com.itdragon.service;

import com.itdragon.pojo.User;

public interface UserService {
	
	User getUserByToken(String token);
	
}
