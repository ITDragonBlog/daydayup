package com.itdragon.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.itdragon.pojo.SysPermission;

public interface SysPermissionRepository extends PagingAndSortingRepository<SysPermission, Integer>, 
	JpaSpecificationExecutor<SysPermission>{
	
	SysPermission findByName(String name);
	
}
