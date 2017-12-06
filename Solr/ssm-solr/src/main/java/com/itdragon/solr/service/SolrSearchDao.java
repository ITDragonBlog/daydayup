package com.itdragon.solr.service;

import org.apache.solr.client.solrj.SolrQuery;

import com.itdragon.mapper.pojo.SearchProductResult;

public interface SolrSearchDao {
	
	SearchProductResult search(SolrQuery query) throws Exception;

}
