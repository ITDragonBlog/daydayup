package com.itdragon.mapper.pojo;

import org.apache.solr.client.solrj.beans.Field;

public class Product {
    
    @Field
    private String id;				// 商品编号
    @Field
    private String name; 			// 商品名称
    @Field
    private String catalog_name; 	// 商品分类名称
    @Field
    private Float price;			// 价格
    @Field
    private Long number;			// 数量
    @Field
    private String picture;			//图片名称
    @Field
    private String description;		// 商品描述

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCatalog_name() {
		return catalog_name;
	}

	public void setCatalog_name(String catalog_name) {
		this.catalog_name = catalog_name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
