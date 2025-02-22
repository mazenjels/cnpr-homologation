package com.cnpr.homologation.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cnpr.homologation.models.Module;
import com.cnpr.homologation.service.ModuleServiceImpl;




@Controller
@RequestMapping(value="/module")
public class ModuleController {

	@Autowired
	ModuleServiceImpl moduleServiceImpl;

	
	@GetMapping({"/list", "/module/list"})
	public String  viewPersonnelList(@ModelAttribute("message") String message, Model model) {
		  List<Module> moduleList = moduleServiceImpl.getAllModule();
		  model.addAttribute("moduleList", moduleList);
		  model.addAttribute("message", message);
		
		return "module/list";
	}
	
	@GetMapping("/new")
	public String viewDocumentNew(@ModelAttribute("message") String message, Model model) {
		Module module = new Module();
		
		model.addAttribute("module", module);
		model.addAttribute("message", message);
		
		return "module/new";
	}
	
	@PostMapping("/save")
	public String save(Module module, RedirectAttributes redirectAttributes) {
		
		if(this.moduleServiceImpl.saveOrUpdateModule(module)) {
			redirectAttributes.addFlashAttribute("message", "Save success");
			return "redirect:/module/list";
		};
		redirectAttributes.addFlashAttribute("message", "Save failure");
		return "redirect:/module/new";
	}
	
	@PostMapping("/edit")
	public String editBanque(Module module, RedirectAttributes redirectAttributes,HttpServletRequest request) {

		if (this.moduleServiceImpl.saveOrUpdateModule(module)) {
			redirectAttributes.addFlashAttribute("message", "Edit success");
			return "redirect:/admin/module/list";
		}
		;
		redirectAttributes.addFlashAttribute("message", "Edit failure");
		return "redirect:/admin/module/edit/" + module.getId();
	}
	
	
//	@GetMapping("/delete/{id}")
//	public String deleteDocument(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
//
//		if (this.moduleServiceImpl.deleteDocumentById(id)) {
//			redirectAttributes.addFlashAttribute("message", "Delete success");
//			return "redirect:/document/list";
//		}
//		;
//		redirectAttributes.addFlashAttribute("message", "Delete failure");
//		return "redirect:/document/list";
//	}
	
	@GetMapping("/changeStatus/{id}")
	public String enableDisableDocument(@PathVariable("id") long id, Model model) {

		Module document = this.moduleServiceImpl.getModuleById(id);

		if (document.isActiveStatus() == false) {
			document.setActiveStatus(true);
		} else if (document.isActiveStatus() == true) {
			document.setActiveStatus(false);
		}
		this.moduleServiceImpl.saveOrUpdateModule(document);

		return "redirect:/module/list";
	}	

	@GetMapping("/edit/{id}")
	public String showFormEditDocument(@PathVariable("id") long id, Model model) {
		Module module = this.moduleServiceImpl.getModuleById(id);
		model.addAttribute("module", module);
		return "/module/edit";
	}
}
