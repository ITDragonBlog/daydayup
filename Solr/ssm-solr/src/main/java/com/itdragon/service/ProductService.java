package com.itdragon.service;

import java.util.Map;

import com.itdragon.mapper.pojo.SearchProductResult;

public interface ProductService {
	
	/**
	 * @param query 简单的q查询条件
	 * @param filterQueryMap 负责的查询条件
	 * @param page	页数
	 * @param rows	每页数量
	 * @return
	 * @throws Exception
	 */
	SearchProductResult search(String query, Map<String, String> filterQueryMap, Integer page, Integer rows) throws Exception;

}
