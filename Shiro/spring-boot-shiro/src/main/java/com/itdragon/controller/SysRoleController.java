package com.itdragon.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itdragon.pojo.SysRole;
import com.itdragon.service.SysRoleService;

/**
 * 角色页面的增删改查，分页，搜索
 * @author itdragon
 *
 */
@Controller
@RequestMapping("/roles")
public class SysRoleController {
	
	private static final String PAGE_SIZE = "10";

	@Autowired
	private SysRoleService sysRoleService;

	@RequestMapping(method = RequestMethod.GET)
	public String getSysRoles(@RequestParam(value = "page", defaultValue = "1") Integer pageNumber,
			@RequestParam(value = "page.size", defaultValue = PAGE_SIZE) Integer pageSize,
			@RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
			ServletRequest request) {
		Map<String, Object> searchParams = new HashMap<>();
		Page<SysRole> sysRoles = sysRoleService.getSysRoles(searchParams, pageNumber, pageSize, sortType);
		model.addAttribute("sysRoles", sysRoles);
		model.addAttribute("sortType", sortType);
		
		return "sysRole/manageSysRole";
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("sysRole", new SysRole());
		model.addAttribute("action", "create");
		return "sysRole/editSysRole";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid SysRole sysRole, RedirectAttributes redirectAttributes) {
		sysRoleService.saveSysRole(sysRole);
		redirectAttributes.addFlashAttribute("message", "创建任务成功");
		return "redirect:/sysRole/";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("sysRole", sysRoleService.getSysRole(id));
		model.addAttribute("action", "update");
		return "sysRole/editSysRole";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("SysRole") SysRole SysRole, RedirectAttributes redirectAttributes) {
		sysRoleService.saveSysRole(SysRole);
		redirectAttributes.addFlashAttribute("message", "更新任务成功");
		return "redirect:/sysRole/";
	}

	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		sysRoleService.deleteSysRole(id);
		redirectAttributes.addFlashAttribute("message", "删除任务成功");
		return "redirect:/sysRole/";
	}

	@ModelAttribute
	public void getSysRole(@RequestParam(value = "id", defaultValue = "-1") Integer id, Model model) {
		if (id != -1) {
			model.addAttribute("sysRole", sysRoleService.getSysRole(id));
		}
	}

}
