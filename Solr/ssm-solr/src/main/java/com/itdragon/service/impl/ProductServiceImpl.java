package com.itdragon.service.impl;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itdragon.mapper.pojo.FilterQueryKey;
import com.itdragon.mapper.pojo.SearchProductResult;
import com.itdragon.service.ProductService;
import com.itdragon.solr.service.SolrSearchDao;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private SolrSearchDao solrSearchDao;

	@Override
	public SearchProductResult search(String queryString, Map<String, String> filterQueryMap, Integer page, Integer rows) throws Exception {
		// 创建查询对象
		SolrQuery query = new SolrQuery();
		// 设置查询条件
		queryString = StringUtils.isEmpty(queryString)? "*:*" : queryString;
		query.setQuery(queryString);
		// 设置复杂查询条件
		if (null != filterQueryMap.get(FilterQueryKey.CATALOG_NAME.getValue())) {
			query.addFilterQuery("product_catalog_name:" + filterQueryMap.get(FilterQueryKey.CATALOG_NAME.getValue()));
		}
		if (null != filterQueryMap.get(FilterQueryKey.PRICE.getValue())) {
			String price = filterQueryMap.get(FilterQueryKey.PRICE.getValue());
			query.addFilterQuery("product_price:[" + price.split("-")[0] + " TO " + price.split("-")[1] + "]");
		}
		if (null != filterQueryMap.get(FilterQueryKey.SORT.getValue())) {
			String priceSort = filterQueryMap.get(FilterQueryKey.SORT.getValue());
			query.setSort("product_price", "1".equals(priceSort) ? ORDER.desc : ORDER.asc);
		}
		// 设置分页
		query.setStart((page - 1) * rows); // 当前页面开始下标
		query.setRows(rows);
		// 设置默认搜素域
		query.set("df", "product_keywords");
		// 设置高亮显示
		query.setHighlight(true);
		query.addHighlightField("product_name");
		query.setHighlightSimplePre("<em style=\"color:red\">");
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
