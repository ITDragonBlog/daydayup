package com.itdragon.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.MapSolrParams;
import org.junit.Test;

public class SolrJTest {
	
	/**
	 * 不同Solr 版本之间创建连接方式不同
	 * Solr4 ： SolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr");  
	 * Solr5 ：HttpSolrClient solrClient = new HttpSolrClient("http://localhost:8080/solr/new_core");
	 * Solr7 ：HttpSolrClient solrClient = new HttpSolrClient.Builder("http://localhost:8080/solr/new_core").build();
	 */
	
	private final static String BASE_SOLR_URL = "http://localhost:8080/solr/new_core";
	
	// solrJ 基础用法
	@Test
	public void queryDocumentBasic() throws Exception {
		// 创建连接
		HttpSolrClient solrClient = new HttpSolrClient.Builder(BASE_SOLR_URL).build();
		/* 不推荐
		Map<String, String> queryParamMap = new HashMap<String, String>();
		// 封装查询参数
		queryParamMap.put("q", "*:*");
        // 添加到SolrParams对象
        MapSolrParams query = new MapSolrParams(queryParamMap);
        */
		// 设置查询条件
        SolrQuery query = new SolrQuery();
		query.setQuery("*:*");
        // 执行查询
        QueryResponse response = solrClient.query(query);
        // 获取doc文档
        SolrDocumentList documents = response.getResults();
		System.out.println("共查询到记录：" + documents.getNumFound());
		for (SolrDocument solrDocument : documents) {
			System.out.println("ID : " + solrDocument.get("id") + " \t Name : " + solrDocument.get("product_name"));
		}
	}
	
	// solrJ 的复杂查询
	@Test
	public void queryDocument() throws Exception {
		// 创建连接
		HttpSolrClient solrClient = new HttpSolrClient.Builder(BASE_SOLR_URL).build();
		SolrQuery query = new SolrQuery();
		// 设置查询条件
		query.setQuery("product_name:街头原木电话亭");
		// 设置分页信息
		query.setStart(0);
		query.setRows(10);
		// 设置排序
		query.setSort("id", ORDER.desc);
		// 设置显示的Field的域集合,即设置那些值有返回值
		query.setFields("id,product_name");
		// 设置默认域
		query.set("df", "product_keywords");
		// 设置高亮信息
		query.setHighlight(true);
		query.addHighlightField("product_name");
		query.setHighlightSimplePre("<em>");
		query.setHighlightSimplePost("</em>");
		// 执行查询
		QueryResponse response = solrClient.query(query);
		// 获取doc文档
		SolrDocumentList documents = response.getResults();
		System.out.println("共查询到记录：" + documents.getNumFound());
		// 获取高亮显示信息
		Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
		for (SolrDocument doc : documents) {
			System.out.println(doc.get("id"));
			List<String> hightDocs = highlighting.get(doc.get("id")).get("product_name");
			if (hightDocs != null)
				System.out.println("高亮显示的商品名称：" + hightDocs.get(0));
			else {
				System.out.println(doc.get("product_name"));
			}
		}
	}
	
	// 添加索引
	@Test
	public void addDocuments() throws SolrServerException, IOException {
		HttpSolrClient solrClient = new HttpSolrClient.Builder(BASE_SOLR_URL).withConnectionTimeout(10000)
                .withSocketTimeout(60000).build();
		// 创建一个文档对象
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", "add-001");
		document.addField("product_name", "ITDragon-Solr7系列博客");
		// 将文档对象写入到索引库中
		solrClient.add(document);
		// 提交
		solrClient.commit();
		
		SolrQuery query = new SolrQuery();
		query.setQuery("id:add-001");
        QueryResponse response = solrClient.query(query);
        SolrDocumentList documents = response.getResults();
		System.out.println("共查询到记录：" + documents.getNumFound());
		for (SolrDocument solrDocument : documents) {
			System.out.println("ID : " + solrDocument.get("id") + " \t Name : " + solrDocument.get("product_name"));
		}
		
	}
	
	// 更新的逻辑：先通过id将直接的数据删掉，然后再创建，所以update 和 create 是同一代码
	
	// 删除/批量删除索引
	@Test
	public void deleteDocument() throws SolrServerException, IOException {
		HttpSolrClient solrClient = new HttpSolrClient.Builder(BASE_SOLR_URL).build();
		//solrClient.deleteById("add-001");
		/*
		ArrayList<String> ids = new ArrayList<String>();
        ids.add("1");
        ids.add("2");
        solrClient.deleteById(ids);
        */
		solrClient.deleteByQuery("id:add-001");
		solrClient.commit();
	}
	
	

}
