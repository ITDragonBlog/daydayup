package com.itdragon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itdragon.common.pojo.EUTreeNode;
import com.itdragon.service.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	/**
	 * http://www.jeasyui.net/plugins/185.html
	 * 当展开一个关闭的节点时，如果该节点没有子节点加载，它将通过上面定义的 URL 向服务器发送节点的 id 值作为名为 'id' 的 http 请求参数，以便检索子节点。
	 * 所以这里的参数value必须是id，若是其他值则接收不到。缺省值是0，表示初始化一级菜单。
	 * 
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/async")
	@ResponseBody
	private List<EUTreeNode> getAsyncCatList(@RequestParam(value="id",defaultValue="0") int parentId) {
		List<EUTreeNode> results = categoryService.getCategoryList(parentId);
		return results;
	}
	
	@RequestMapping("/sync")
	@ResponseBody
	private List<EUTreeNode> getSyncCatList() {
		List<EUTreeNode> results = categoryService.getCategoryList();
		return results;
	}
}
