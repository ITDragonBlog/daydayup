package com.itdragon.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itdragon.common.pojo.EUTreeNode;
import com.itdragon.mapper.CategoryMapper;
import com.itdragon.pojo.Category;
import com.itdragon.pojo.CategoryExample;
import com.itdragon.pojo.CategoryExample.Criteria;
import com.itdragon.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryMapper categoryMapper;
	
	@Override
	public List<EUTreeNode> getCategoryList(int parentId) {
		
		// 1. 创建查询条件
		CategoryExample example = new CategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId); // 查询父节点下的所有子节点
		criteria.andStatusEqualTo(0); // 查询未删除状态的菜单
		// TODO 权限拦截
		// 2. 根据条件查询
		List<Category> list = categoryMapper.selectByExample(example);
		List<EUTreeNode> resultList = new ArrayList<>();
		// 3. 把列表转换成 EasyUI Tree 需要的json格式
		for (Category category : list) {
			EUTreeNode node = new EUTreeNode();
			node.setId(category.getId());
			node.setText(category.getName());
			node.setState(category.getIsLeaf() == 1?"open":"closed");
			resultList.add(node);
		}
		// 4. 返回结果
		return resultList;
	}
	
	@Override
	public List<EUTreeNode> getCategoryList() {
		
		// 1. 创建查询条件
		CategoryExample example = new CategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(0); // 查询未删除状态的菜单
		// TODO 权限拦截
		// 2. 根据条件查询
		List<Category> list = categoryMapper.selectByExample(example);
		List<EUTreeNode> resultList = new ArrayList<>();
		// 3. 把列表转换成 EasyUI Tree 需要的json格式
		for (Category category : list) {
			EUTreeNode node = new EUTreeNode();
			node.setId(category.getId());
			node.setText(category.getName());
			node.setState(category.getIsLeaf() == 1?"open":"closed");
			node.setParentId(category.getParentId());
			resultList.add(node);
		}
		// 4. 返回结果
		return resultList;
	}

}
