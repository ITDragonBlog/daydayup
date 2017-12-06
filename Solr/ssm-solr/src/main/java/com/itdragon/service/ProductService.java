package com.itdragon.service;

import com.itdragon.mapper.pojo.SearchProductResult;

public interface ProductService {
	
	/**
	 * 
	 * @param query 查询条件
	 * @param page	页数
	 * @param rows	每页数量
	 * @return
	 * @throws Exception
	 */
	SearchProductResult search(String query, Integer page, Integer rows) throws Exception;

}
