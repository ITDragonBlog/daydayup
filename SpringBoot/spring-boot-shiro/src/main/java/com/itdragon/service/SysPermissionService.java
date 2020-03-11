package com.itdragon.service;

import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.itdragon.pojo.SysPermission;
import com.itdragon.repository.SysPermissionRepository;

/**
 * 用户权限管理
 * @author itdragon
 *
 */
@Service
@Transactional
public class SysPermissionService {
	
	@Autowired
	private SysPermissionRepository sysPermissionRepository;
	
	public SysPermission getSysPermission(Integer id) {
		return sysPermissionRepository.findOne(id);
	}
	
	public List<SysPermission> findAllSysPermissions(){
		return (List<SysPermission>) sysPermissionRepository.findAll();
	}
	
	public void saveSysPermission(SysPermission sysPermission) {
		sysPermissionRepository.save(sysPermission);
	}
	
	public void deleteSysPermission(Integer id) {
		sysPermissionRepository.delete(id);
	}
	
	public Page<SysPermission> getSysPermissions(Map<String, Object> searchParams, int pageNumber, int pageSize,  
            String sortType) {  
        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);  
        Specification<SysPermission> spec = buildSpecification(searchParams);  
        return sysPermissionRepository.findAll(spec, pageRequest);  
    }  
  
    private PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {  
        Sort sort = new Sort(Direction.ASC, "id");
        return new PageRequest(pageNumber - 1, pagzSize, sort);  
    }  
    
	private Specification<SysPermission> buildSpecification(Map<String, Object> searchParams) {
		return new Specification<SysPermission>() {
			@Override
			public Predicate toPredicate(Root<SysPermission> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				if (searchParams.get("name") != null && !searchParams.get("name").equals("")) {
					Path<String> exp = root.get("name");
					return cb.like(exp, "%name%");
				}
				return null;
			}
		};
	}  

}
