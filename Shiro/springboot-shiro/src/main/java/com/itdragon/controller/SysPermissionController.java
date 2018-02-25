package com.itdragon.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
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

import com.itdragon.pojo.SysPermission;
import com.itdragon.service.SysPermissionService;

@Controller
@RequestMapping("/permission")
public class SysPermissionController {
	
	private static final String PAGE_SIZE = "10";
	private static Map<String, String> sortTypes = new LinkedHashMap<>();
	static {
		sortTypes.put("auto", "自动");
		sortTypes.put("title", "标题");
		sortTypes.put("createdDate", "创建时间");
	}

	@Autowired
	private SysPermissionService sysPermissionService;

	@RequestMapping(value="list", method = RequestMethod.GET)
	public String getSysPermissions(@RequestParam(value = "page", defaultValue = "1") Integer pageNumber,
			@RequestParam(value = "page.size", defaultValue = PAGE_SIZE) Integer pageSize,
			@RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
			ServletRequest request) {
		Map<String, Object> searchParams = new HashMap<>();
		Page<SysPermission> sysPermissions = sysPermissionService.getSysPermission(searchParams, pageNumber, pageSize, sortType);
		model.addAttribute("sysPermissions", sysPermissions);
		model.addAttribute("sortType", sortType);
		model.addAttribute("sortTypes", sortTypes);
		
		return "sysPermission/manageSysPermission";
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("sysPermission", new SysPermission());
		model.addAttribute("action", "create");
		return "sysPermission/editSysPermission";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid SysPermission sysPermission, RedirectAttributes redirectAttributes) {
		sysPermissionService.saveSysPermissions(sysPermission);
		redirectAttributes.addFlashAttribute("message", "创建任务成功");
		return "redirect:/sysPermission/";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("sysPermission", sysPermissionService.getSysPermission(id));
		model.addAttribute("action", "update");
		return "sysPermission/editSysPermission";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("sysPermission") SysPermission sysPermission, RedirectAttributes redirectAttributes) {
		sysPermissionService.saveSysPermissions(sysPermission);
		redirectAttributes.addFlashAttribute("message", "更新任务成功");
		return "redirect:/sysPermission/";
	}

	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		sysPermissionService.deleteSysPermissions(id);
		redirectAttributes.addFlashAttribute("message", "删除任务成功");
		return "redirect:/sysPermission/";
	}

	@ModelAttribute
	public void getSysPermission(@RequestParam(value = "id", defaultValue = "-1") Integer id, Model model) {
		if (id != -1) {
			model.addAttribute("sysPermission", sysPermissionService.getSysPermission(id));
		}
	}

}
