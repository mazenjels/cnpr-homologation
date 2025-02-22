package com.cnpr.homologation.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cnpr.homologation.models.CnprVehiculeType;
import com.cnpr.homologation.models.Permission;
import com.cnpr.homologation.models.Province;
import com.cnpr.homologation.service.PermissionServiceImpl;
import com.cnpr.homologation.service.DocumentTypeServiceImpl;
import com.cnpr.homologation.service.ModuleServiceImpl;




@Controller
@RequestMapping(value="/permission")
public class PermissionController {

	@Autowired
	PermissionServiceImpl permissionServiceImpl;
	
	@Autowired
	ModuleServiceImpl mooduleServiceImpl;

	
	@GetMapping({"/list", "/permission/list"})
	public String  viewPersonnelList(Model model, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int size, HttpServletRequest request,
			@ModelAttribute("message") String message) {
		HttpSession session = request.getSession();
		
		Pageable pageable = PageRequest.of(page - 1, size);
		Page<Permission> pagePermission = permissionServiceImpl.getAllPageable(pageable);

		List<Permission> permissionList = pagePermission.getContent();
		  //List<Permission> permissionList = permissionServiceImpl.getAllPermission();
		  
		  
		  List<com.cnpr.homologation.models.Module> moduleList = mooduleServiceImpl.getAllActiveModule();
		  model.addAttribute("permissionList", permissionList);
		  model.addAttribute("currentPage", pagePermission.getNumber() + 1);
			model.addAttribute("totalItems", pagePermission.getTotalElements());
			model.addAttribute("totalPages", pagePermission.getTotalPages());
			model.addAttribute("pageSize", size);
		  model.addAttribute("message", message);
		  session.setAttribute("moduleList", moduleList);
		
		return "permission/list";
	}
	
	@GetMapping("/new")
	public String viewDocumentNew(@ModelAttribute("message") String message, Model model) {
		Permission permission = new Permission();
		
		model.addAttribute("permission", permission);
		model.addAttribute("message", message);
		
		return "permission/new";
	}
	
	@PostMapping("/save")
	public String save(RedirectAttributes redirectAttributes,HttpServletRequest request) {
		Permission permission = null;
		//com.cnpr.homologation.models.Module module = new com.cnpr.homologation.models.Module();;
		//module.setId(Long.parseLong(request.getParameter("module")));
		String action[] = request.getParameterValues("action");
		//System.out.println("Actions : "+action.length);
		String permissionshortCode = "";
		String moduleShortCode=request.getParameter("module");
		List<Permission> peermissions = new ArrayList<Permission>();
		
		for(int i=0;i<action.length;i++) {
			permission = new Permission();
			permissionshortCode= action[i]+"_"+moduleShortCode;
			permission.setShortCode(permissionshortCode);
			permission.setActiveStatus(false);
			//System.out.println(permissionshortCode);
			//this.permissionServiceImpl.saveOrUpdatePermission(permission);
			peermissions.add(permission);
		}
		
		
		permissionServiceImpl.bulkInsertPermissions(peermissions);
		
		//permission.setModule(module);
	//	permission.setShortCode(null);
		//permission.setDesignation(request.get)
		//if(this.permissionServiceImpl.saveOrUpdatePermission(permission)) {
			redirectAttributes.addFlashAttribute("message", "Save success");
			return "redirect:/permission/list";
		//};
		//redirectAttributes.addFlashAttribute("message", "Save failure");
		//return "redirect:/permission/new";
	}
	
	@PostMapping("/edit")
	public String editBanque(Permission permission, RedirectAttributes redirectAttributes,HttpServletRequest request) {

		if (this.permissionServiceImpl.saveOrUpdatePermission(permission)) {
			redirectAttributes.addFlashAttribute("message", "Edit success");
			return "redirect:/admin/permission/list";
		}
		;
		redirectAttributes.addFlashAttribute("message", "Edit failure");
		return "redirect:/admin/permission/edit/" + permission.getId();
	}
	
	
//	@GetMapping("/delete/{id}")
//	public String deleteDocument(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
//
//		if (this.permissionServiceImpl.deleteDocumentById(id)) {
//			redirectAttributes.addFlashAttribute("message", "Delete success");
//			return "redirect:/document/list";
//		}
//		;
//		redirectAttributes.addFlashAttribute("message", "Delete failure");
//		return "redirect:/document/list";
//	}
	
	@GetMapping("/changeStatus/{id}")
	public String enableDisableDocument(@PathVariable("id") long id, Model model) {

		Permission document = this.permissionServiceImpl.getPermissionById(id);

		if (document.isActiveStatus() == false) {
			document.setActiveStatus(true);
		} else if (document.isActiveStatus() == true) {
			document.setActiveStatus(false);
		}
		this.permissionServiceImpl.saveOrUpdatePermission(document);

		return "redirect:/permission/list";
	}	

	@GetMapping("/edit/{id}")
	public String showFormEditDocument(@PathVariable("id") long id, Model model) {
		Permission permission = this.permissionServiceImpl.getPermissionById(id);
		model.addAttribute("permission", permission);
		return "/permission/edit";
	}
}
