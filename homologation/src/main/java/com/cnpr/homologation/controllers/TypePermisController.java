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

import com.cnpr.homologation.models.TypePermis;
import com.cnpr.homologation.service.PermisTypeServiceImpl;




@Controller
@RequestMapping(value="/typePermis")
public class TypePermisController {

	@Autowired
	PermisTypeServiceImpl typePermisService;

	
	@GetMapping({"/list", "/typePermis/list"})
	public String  viewPersonnelList(@ModelAttribute("message") String message, Model model) {
		  List<TypePermis> typePermisList = typePermisService.getAllTypePermis();
		  model.addAttribute("typePermisList", typePermisList);
		  model.addAttribute("message", message);
		
		return "typePermis/list";
	}
	
	@GetMapping("/new")
	public String viewDocumentNew(@ModelAttribute("message") String message, Model model) {
		TypePermis typePermis = new TypePermis();
		
		model.addAttribute("typePermis", typePermis);
		model.addAttribute("message", message);
		
		return "typePermis/new";
	}
	
	@PostMapping("/save")
	public String saveDocument(TypePermis typePermis, RedirectAttributes redirectAttributes) {
		
		if(this.typePermisService.saveOrUpdateTypePermis(typePermis)) {
			redirectAttributes.addFlashAttribute("message", "Save success");
			return "redirect:/typePermis/list";
		};
		redirectAttributes.addFlashAttribute("message", "Save failure");
		return "redirect:/typePermis/new";
	}
	
	@PostMapping("/edit")
	public String editVehicle(TypePermis typePermis, RedirectAttributes redirectAttributes,HttpServletRequest request) {

		if (this.typePermisService.saveOrUpdateTypePermis(typePermis)) {
			redirectAttributes.addFlashAttribute("message", "Edit success");
			return "redirect:/admin/typePermis/list";
		}
		;
		redirectAttributes.addFlashAttribute("message", "Edit failure");
		return "redirect:/admin/typePermis/edit/" + typePermis.getId();
	}
	
//	@GetMapping("/delete/{id}")
//	public String deleteDocument(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
//
//		if (this.typePermisService.deleteDocumentById(id)) {
//			redirectAttributes.addFlashAttribute("message", "Delete success");
//			return "redirect:/typePermis/list";
//		}
//		;
//		redirectAttributes.addFlashAttribute("message", "Delete failure");
//		return "redirect:/typePermis/list";
//	}
	
	@GetMapping("/changeStatus/{id}")
	public String enableDisableDocument(@PathVariable("id") long id, Model model) {

		TypePermis typePermis = this.typePermisService.getTypePermisById(id);

		if (typePermis.isActiveStatus() == false) {
			typePermis.setActiveStatus(true);
		} else if (typePermis.isActiveStatus() == true) {
			typePermis.setActiveStatus(false);
		}
		this.typePermisService.saveOrUpdateTypePermis(typePermis);

		return "redirect:/typePermis/list";
	}	

	@GetMapping("/edit/{id}")
	public String showFormEditDocument(@PathVariable("id") long id, Model model) {
		TypePermis typePermis = this.typePermisService.getTypePermisById(id);
		model.addAttribute("typePermis", typePermis);
		return "/typePermis/edit";
	}
}
