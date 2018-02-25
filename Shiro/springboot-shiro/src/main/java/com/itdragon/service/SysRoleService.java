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

import com.itdragon.pojo.SysRole;
import com.itdragon.repository.SysRoleRepository;

/**
 * 用户权限管理
 * @author itdragon
 *
 */
@Service
@Transactional
public class SysRoleService {
	
	@Autowired
	private SysRoleRepository sysRoleRepository;
	
	public SysRole getSysRole(Integer id) {
		return sysRoleRepository.findOne(id);
	}
	
	public List<SysRole> findAllSysRoles(){
		return (List<SysRole>) sysRoleRepository.findAll();
	}
	
	public void saveSysRoles(SysRole SysRole) {
		sysRoleRepository.save(SysRole);
	}
	
	public void deleteSysRoles(Integer id) {
		sysRoleRepository.delete(id);
	}
	
	public Page<SysRole> getSysRole(Map<String, Object> searchParams, int pageNumber, int pageSize,  
            String sortType) {  
        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);  
        Specification<SysRole> spec = buildSpecification(searchParams);  
        return sysRoleRepository.findAll(spec, pageRequest);  
    }  
  
    private PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {  
        Sort sort = new Sort(Direction.DESC, "id");
        return new PageRequest(pageNumber - 1, pagzSize, sort);  
    }  
    
	private Specification<SysRole> buildSpecification(Map<String, Object> searchParams) {
		return new Specification<SysRole>() {
			@Override
			public Predicate toPredicate(Root<SysRole> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				if (searchParams.get("name") != null && !searchParams.get("name").equals("")) {
				}
				Path<String> exp = root.get("name");
				return cb.like(exp, "%name%");
			}
		};
	}  

}
