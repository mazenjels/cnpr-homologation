package com.cnpr.homologation.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.cnpr.homologation.models.CnprRole;
import com.cnpr.homologation.models.Permission;
import com.cnpr.homologation.models.RolePermission;
import com.cnpr.homologation.service.ModuleServiceImpl;
import com.cnpr.homologation.service.PermissionServiceImpl;
import com.cnpr.homologation.service.RolePermissionServiceImpl;
import com.cnpr.homologation.service.RoleServiceImpl;




@Controller
@RequestMapping(value="/role")
public class RoleController {

	@Autowired
	RoleServiceImpl roleService;
	
	@Autowired
	PermissionServiceImpl permissionService;
	
	@Autowired
	ModuleServiceImpl moduleService;
	
	@Autowired
	RolePermissionServiceImpl rolePermissionService;

	
	@GetMapping({"/list", "/role/list"})
	public String  viewPersonnelList(@ModelAttribute("message") String message, Model model) {
		  List<CnprRole> roleList = roleService.getAllCnprRole();
		  model.addAttribute("roleList", roleList);
		  model.addAttribute("message", message);
		
		return "role/list";
	}
	
	@GetMapping("/new")
	public String viewDocumentNew(@ModelAttribute("message") String message, Model model) {
		CnprRole role = new CnprRole();
		
		model.addAttribute("role", role);
		model.addAttribute("message", message);
		
		return "role/new";
	}
	
	@PostMapping("/save")
	public String saveDocument(CnprRole role, RedirectAttributes redirectAttributes) {
		
		if(this.roleService.saveOrUpdateCnprRole(role)) {
			redirectAttributes.addFlashAttribute("message", "Save success");
			return "redirect:/role/list";
		};
		redirectAttributes.addFlashAttribute("message", "Save failure");
		return "redirect:/role/new";
	}
	
	@PostMapping("/edit")
	public String editBanque(CnprRole role, RedirectAttributes redirectAttributes,HttpServletRequest request) {

		if (this.roleService.saveOrUpdateCnprRole(role)) {
			redirectAttributes.addFlashAttribute("message", "Edit success");
			return "redirect:/role/list";
		}
		;
		redirectAttributes.addFlashAttribute("message", "Edit failure");
		return "redirect:/role/edit/" + role.getId();
	}
	
	
//	@GetMapping("/delete/{id}")
//	public String deleteDocument(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
//
//		if (this.roleService.deleteDocumentById(id)) {
//			redirectAttributes.addFlashAttribute("message", "Delete success");
//			return "redirect:/role/list";
//		}
//		;
//		redirectAttributes.addFlashAttribute("message", "Delete failure");
//		return "redirect:/role/list";
//	}
	
	@GetMapping("/changeStatus/{id}")
	public String enableDisableDocument(@PathVariable("id") long id, Model model) {

		CnprRole role = this.roleService.getCnprRoleById(id);

		if (role.isActiveStatus() == false) {
			role.setActiveStatus(true);
		} else if (role.isActiveStatus() == true) {
			role.setActiveStatus(false);
		}
		this.roleService.saveOrUpdateCnprRole(role);

		return "redirect:/role/list";
	}	

	@GetMapping("/edit/{id}")
	public String showFormEditDocument(@PathVariable("id") long id, Model model) {
		CnprRole role = this.roleService.getCnprRoleById(id);
		model.addAttribute("role", role);
		return "/role/edit";
	}
	
	@GetMapping("/{id}/permissions")
	public String showPermissions(@PathVariable("id") long id, Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		CnprRole role= this.roleService.getCnprRoleById(id);
		RolePermission rolePermission = new RolePermission();
		rolePermission.setRole(role);
		session.setAttribute("rolePermission", rolePermission);

		Permission permission = (Permission)request.getSession().getAttribute("permission");

		List<Permission> permissionsList = permissionService.getAllPermission();
		List<com.cnpr.homologation.models.Module> moduleList = moduleService.getAllActiveModule();
		model.addAttribute("permissionsList", permissionsList);

		List<Long> roleIds = List.of(Long.valueOf(id)) ;
		List<RolePermission> rolePermissions = this.rolePermissionService.getAllRolePermissionByRoleID(roleIds);
		model.addAttribute("rolePermissions", rolePermissions);
		session.setAttribute("rolePermission", rolePermission);
		session.setAttribute("moduleList", moduleList);

		
		model.addAttribute("rolePermission", rolePermission);
		return "/role/permissions";
	}
	
	@GetMapping("/{roleId}/permissions/changeStatus/{rolePermissionId}")
	public String enableDisableRolePermission(@PathVariable("roleId") long roleId,@PathVariable("rolePermissionId") long rolePermissionId,Model model) {
		//long idRole= roleId;
		RolePermission rolePermission = this.rolePermissionService.getRolePermissionById(rolePermissionId);
		
		if(rolePermission.isActiveStatus() == false) {
			rolePermission.setActiveStatus(true);
		}
		else if(rolePermission.isActiveStatus() == true){
			rolePermission.setActiveStatus(false);
		}
		this.rolePermissionService.saveOrUpdateRolePermission(rolePermission);
				
		return "redirect:/role/"+roleId+"/permissions";
	}
	
	@GetMapping("/{roleId}/permissions/delete/{rolePermissionId}")
	public String deleteRolePermission(@PathVariable("roleId") long roleId,@PathVariable("rolePermissionId") long rolePermissionId,RedirectAttributes redirectAttributes) {
		
		if (this.rolePermissionService.deleteRolePermissionById(rolePermissionId)) {
			redirectAttributes.addFlashAttribute("message", "Delete success");
			return "redirect:/role/"+roleId+"/permissions";
		}	
				
		return "redirect:/role/"+roleId+"/permissions";
	}
	
	@PostMapping("/addPermission")
	public String saveRolePermission( RedirectAttributes redirectAttributes,HttpServletRequest request) {
		//System.out.println("Role ID : "+rolePermission.getRole().getId());
		CnprRole role =  new CnprRole();
		role.setId(Long.parseLong(request.getParameter("role")));
		RolePermission rolePerm = (RolePermission) request.getSession().getAttribute("rolePermission");
		
		String action [] = request.getParameterValues("action");
		List<Permission> allPerm = new ArrayList<Permission>();
		for(int i=0;i<action.length;i++) {
			//RolePermission rolePermission = new RolePermission();
			Permission permission = new Permission();
			permission.setId(Long.parseLong(action[i]));
			//rolePermission.setPermission(permission);
			//rolePermission.setRole(role);
			allPerm.add(permission);
		}
		rolePermissionService.bulkInsertPermissionsToRole(allPerm,role);
//		if(action != null) {
//			List<Permission> allPermissions = permissionService.getAllActivePermission();
//			allPermissions.forEach(p->{
//				
//				
//				Permission permission = new Permission();
//				permission.setId(p.getId());
//				rolePermission.setPermission(permission);
//				rolePermission.setRole(role);
//			});
//			rolePermissionService.bulkInsertPermissionsToRole(allPermissions,role);
//		}else {
//			Permission permission = new Permission();
//			permission.setId(Long.parseLong(request.getParameter("permission")));
//			rolePerm.setPermission(permission);
//			rolePermissionService.saveOrUpdateRolePermission(rolePerm);
//		}
		
		//
		
		redirectAttributes.addFlashAttribute("message", "Permssions ajoutee au profil "+rolePerm.getRole().getDesignation());
		return "redirect:/role/"+role.getId()+"/permissions";
	

	}

}
