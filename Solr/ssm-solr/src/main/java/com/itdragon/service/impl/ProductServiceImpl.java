package com.itdragon.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itdragon.mapper.pojo.SearchProductResult;
import com.itdragon.service.ProductService;
import com.itdragon.solr.service.SolrSearchDao;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private SolrSearchDao solrSearchDao;

	@Override
	public SearchProductResult search(String queryString, Integer page, Integer rows) throws Exception {
		// 创建查询对象
		SolrQuery query = new SolrQuery();
		// 设置查询条件
		query.setQuery(queryString);
		// 设置分页
		query.setStart((page - 1) * rows); // 当前页面开始下标
		query.setRows(rows);
		// 设置默认搜素域
		query.set("df", "product_keywords");
		// 设置高亮显示
		query.setHighlight(true);
		query.addHighlightField("product_name");
		query.setHighlightSimplePre("<em style=\"color:yellow\">");
		query.setHighlightSimplePost("</em>");
		// 执行查询
		SearchProductResult searchResult = solrSearchDao.search(query);
		// 计算查询结果总页数
		long recordCount = searchResult.getRecordCount();
		int pageCount = (int) (recordCount % rows > 0? (recordCount / rows) + 1 : (recordCount / rows));
		searchResult.setPageCount(pageCount);
		searchResult.setCurPage(page);
		
		return searchResult;
	}

}
