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

import com.itdragon.pojo.ItdragonResult;
import com.itdragon.pojo.User;
import com.itdragon.repository.UserRepository;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User getUser(Long id) {
		return userRepository.findOne(id);
	}
	
	public List<User> findAllUsers(){
		return (List<User>) userRepository.findAll();
	}
	
	public void saveUsers(User User) {
		userRepository.save(User);
	}
	
	public void deleteUsers(Long id) {
		userRepository.delete(id);
	}
	
	public Page<User> getUsers(Map<String, Object> searchParams, int pageNumber, int pageSize,  
            String sortType) {  
        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);  
        Specification<User> spec = buildSpecification(searchParams);  
        return userRepository.findAll(spec, pageRequest);  
    }  
	
	private Specification<User> buildSpecification(Map<String, Object> searchParams) {
		return new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				if (searchParams.get("account") != null && !searchParams.get("account").equals("")) {
					Path<String> exp = root.get("account");
					return cb.like(exp, "%account%");
				}
				return null;
			}
		};
	} 
  
    private PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {  
        Sort sort = new Sort(Direction.DESC, "id");
        return new PageRequest(pageNumber - 1, pagzSize, sort);  
    }  
	
	public User findByAccount(String account) {
		return userRepository.findByAccount(account);
	}
	
    public ItdragonResult registerUser(User user) {
    	// 检查用户名是否注册，一般在前端验证的时候处理，因为注册不存在高并发的情况，这里再加一层查询是不影响性能的
    	if (null != userRepository.findByAccount(user.getAccount())) {
    		return ItdragonResult.build(400, "");
    	}
    	userRepository.save(user);
    	// 注册成功后选择发送邮件激活。现在一般都是短信验证码
    	return ItdragonResult.build(200, "");
    }
    
}
