package com.itdragon.service;

import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.itdragon.common.ItdragonResult;
import com.itdragon.common.SortType;
import com.itdragon.pojo.User;
import com.itdragon.repository.UserRepository;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * 
	 * @param searchParams 	查询条件
	 * @param pageNumber	当前页数
	 * @param pageSize		每页数量
	 * @param sortType		排序类型
	 * @return
	 */
	public Page<User> findPageUsers(Map<String, Object> searchParams, int pageNumber, int pageSize,  
            String sortType) {  
        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);  
        Specification<User> spec = buildSpecification(searchParams);  
  
        return userRepository.findAll(spec, pageRequest);  
    }  
  
    /** 
     * 创建分页请求. 
     */  
    private PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {  
        Sort sort = new Sort(Direction.ASC, SortType.valueOf(sortType).getValue());
        return new PageRequest(pageNumber - 1, pagzSize, sort);
    }  
      
    /** 
     * 创建动态查询条件组合. 
     */  
    private Specification<User> buildSpecification(Map<String, Object> searchParams) {  
//        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);  
//        Specification<User> spec = DynamicSpecifications.bySearchFilter(filters.values(), User.class);  
        Specification<User> spec = null;
        return spec;  
    } 
    
    
    public ItdragonResult registerUser(User user) {
    	// 检查用户名是否注册，一般在前端验证的时候处理，因为注册不存在高并发的情况，这里再加一层查询是不影响性能的
    	if (null != userRepository.findByAccount(user.getAccount())) {
    		// 提示
    		return ItdragonResult.build(400, "");
    	}
    	userRepository.save(user);
    	// 注册成功后选择发送邮件激活。现在一般都是短信验证码
    	return ItdragonResult.build(200, "");
    }

}
