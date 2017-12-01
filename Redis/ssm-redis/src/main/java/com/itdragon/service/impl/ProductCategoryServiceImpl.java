package com.itdragon.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.itdragon.common.pojo.EUTreeNode;
import com.itdragon.common.pojo.ResponseResult;
import com.itdragon.common.utils.JsonUtils;
import com.itdragon.mapper.ProductCategoryMapper;
import com.itdragon.pojo.ProductCategory;
import com.itdragon.pojo.ProductCategoryExample;
import com.itdragon.pojo.ProductCategoryExample.Criteria;
import com.itdragon.service.JedisClient;
import com.itdragon.service.ProductCategoryService;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
	
	@Autowired
	private ProductCategoryMapper categoryMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${CATEGROY_ID_CACHE_REDIS_KEY}")
	private String CATEGROY_ID_CACHE_REDIS_KEY;

	@Override
	public List<EUTreeNode> getCategoryList(Long parentId) {
		long startTime = System.currentTimeMillis();
		List<EUTreeNode> resultList = new ArrayList<>();
		// 从redis缓存中取内容
		try {
			String cacheDatas = jedisClient.hget(CATEGROY_ID_CACHE_REDIS_KEY, parentId.toString());
			if (StringUtils.isNotBlank(cacheDatas)) {
				List<ProductCategory> categories = JsonUtils.jsonToList(cacheDatas, ProductCategory.class);
				for (ProductCategory category : categories) {
					EUTreeNode node = new EUTreeNode();
					node.setId(category.getId());
					node.setText(category.getName());
					node.setState(category.getIsParent()?"closed":"open");
					resultList.add(node);
				}
				System.out.println("redis cache Time : " + (System.currentTimeMillis() - startTime));
				return resultList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ProductCategoryExample example = new ProductCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		criteria.andParentIdEqualTo(parentId); // 查询父节点下的所有子节点
		List<ProductCategory> productCategories = categoryMapper.selectByExample(example);
		
		// 把对象集合转换成 EasyUI Tree 需要的json格式
		for (ProductCategory category : productCategories) {
			EUTreeNode node = new EUTreeNode();
			node.setId(category.getId());
			node.setText(category.getName());
			node.setState(category.getIsParent()?"closed":"open");
			resultList.add(node);
		}
		System.out.println("No redis cache Time : " + (System.currentTimeMillis() - startTime));
		// 向redis缓存中添加内容
		try {
			jedisClient.hset(CATEGROY_ID_CACHE_REDIS_KEY, parentId.toString(), JsonUtils.objectToJson(productCategories));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}

	@Override
	public ResponseResult createCategoryList(Long parentId, String name) {
		ProductCategory productCategory = new ProductCategory();
		productCategory.setName(name);
		productCategory.setIsParent(false);
		productCategory.setStatus(1);
		productCategory.setParentId(parentId);
		productCategory.setSortOrder(1);
		productCategory.setCreated(new Date());
		productCategory.setUpdated(new Date());
		if (1 != categoryMapper.insert(productCategory)) {
			return null;
		}
		ProductCategory productCategoryParent = categoryMapper.selectByPrimaryKey(parentId);
		productCategoryParent.setIsParent(true);
		categoryMapper.updateByPrimaryKey(productCategoryParent);
		// 清理redis缓存
		try {
			System.out.println("clean cache");
			jedisClient.hdel(CATEGROY_ID_CACHE_REDIS_KEY, parentId.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ProductCategoryExample example = new ProductCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		criteria.andNameEqualTo(name);
		List<ProductCategory> productCategories = categoryMapper.selectByExample(example);
		return CollectionUtils.isEmpty(productCategories)? null : ResponseResult.success(productCategories.get(0));
	}

	@Override
	public ResponseResult updateCategoryList(Long categoryId, String name) {
		ProductCategory productCategory = categoryMapper.selectByPrimaryKey(categoryId);
		productCategory.setName(name);
		if (1 != categoryMapper.updateByPrimaryKey(productCategory)) {
			return null;
		}
		// TODO 清理缓存
		return ResponseResult.success();
	}

	@Transactional
	@Override
	public ResponseResult deleteCategoryList(Long parentId, Long categoryId) {
		ProductCategory productCategory = categoryMapper.selectByPrimaryKey(categoryId);
		productCategory.setStatus(0);
		if (1 != categoryMapper.updateByPrimaryKey(productCategory)) {
			return null;
		}
		ProductCategoryExample example = new ProductCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		criteria.andParentIdEqualTo(parentId); // 查询父节点下的所有子节点
		List<ProductCategory> productCategorieLeafs = categoryMapper.selectByExample(example);
		for (ProductCategory productCategoryLeaf : productCategorieLeafs) {
			productCategoryLeaf.setStatus(0);
			categoryMapper.updateByPrimaryKey(productCategoryLeaf);
		}
		// TODO 清理缓存
		return ResponseResult.success();
	}

}
