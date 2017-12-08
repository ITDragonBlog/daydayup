package com.itdragon.solr.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;

import com.itdragon.mapper.pojo.Product;
import com.itdragon.mapper.pojo.SearchProductResult;
import com.itdragon.solr.service.SolrSearchDao;

public class SolrSearchDaoImpl implements SolrSearchDao {
	
	@Autowired
	private HttpSolrClient httpSolrClient;

	@Override
	public SearchProductResult search(SolrQuery query) throws Exception {
		// 返回值对象
		SearchProductResult result = new SearchProductResult();
		// 根据查询条件查询索引库
		System.out.println("solr Query : " + query);
		QueryResponse queryResponse = httpSolrClient.query(query);
		// 获取查询结果
		SolrDocumentList solrDocumentList = queryResponse.getResults();
		// 获取查询结果总数量
		result.setRecordCount(solrDocumentList.getNumFound());
		// 初始化返回结果商品列表
		List<Product> products = new ArrayList<>();
		// 获取高亮显示数据
		Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
		// 遍历查询结果
		for (SolrDocument solrDocument : solrDocumentList) {
			Product product = new Product();
			product.setId((String) solrDocument.get("id"));
			// 获取高亮显示的集合
			List<String> hightNames = highlighting.get(solrDocument.get("id")).get("product_name");
			String pName = CollectionUtils.isNotEmpty(hightNames)? hightNames.get(0) : (String) solrDocument.get("product_name");
			product.setName(pName);
			product.setPicture((String) solrDocument.get("product_picture"));
			product.setPrice((float) solrDocument.get("product_price"));
			product.setCatalog_name((String) solrDocument.get("product_category_name"));
			products.add(product);
		}
		result.setProductList(products);
		return result;
	}

}
