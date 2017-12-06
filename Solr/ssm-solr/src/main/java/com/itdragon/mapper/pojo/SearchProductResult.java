package com.itdragon.mapper.pojo;

import java.util.List;

public class SearchProductResult {

	private List<Product> productList;	// 商品列表
	private Long recordCount;	// 商品总数
	private Integer pageCount;	// 总页数
	private Integer curPage;	// 当前页

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public Long getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(Long recordCount) {
		this.recordCount = recordCount;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getCurPage() {
		return curPage;
	}

	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}

}
