package com.itdragon.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.itdragon.pojo.SysRole;

public interface SysRoleRepository extends PagingAndSortingRepository<SysRole, Integer>, 
	JpaSpecificationExecutor<SysRole>{
	
	SysRole findByRole(String role);
	
}
