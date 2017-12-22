package com.itdragon.service;

import com.itdragon.common.pojo.User;

public interface UserService {
	
	User getUserByToken(String token);
	
}
