package com.itdragon.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.itdragon.pojo.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long>{

}
