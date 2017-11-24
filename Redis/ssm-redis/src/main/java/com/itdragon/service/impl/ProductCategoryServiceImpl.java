package com.itdragon.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.itdragon.common.pojo.EUTreeNode;
import com.itdragon.mapper.ProductCategoryMapper;
import com.itdragon.pojo.ProductCategory;
import com.itdragon.pojo.ProductCategoryExample;
import com.itdragon.pojo.ProductCategoryExample.Criteria;
import com.itdragon.service.ProductCategoryService;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
	
	@Autowired
	private ProductCategoryMapper categoryMapper;

	@Override
	public List<EUTreeNode> getCategoryList(long parentId) {
		ProductCategoryExample example = new ProductCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId); // 查询父节点下的所有子节点
		List<ProductCategory> list = categoryMapper.selectByExample(example);
		List<EUTreeNode> resultList = new ArrayList<>();
		// 把对象集合转换成 EasyUI Tree 需要的json格式
		for (ProductCategory category : list) {
			EUTreeNode node = new EUTreeNode();
			node.setId(category.getId());
			node.setText(category.getName());
			node.setState(category.getIsParent()?"closed":"open");
			resultList.add(node);
		}
		return resultList;
	}

}
