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

import com.cnpr.homologation.models.Commune;
import com.cnpr.homologation.service.CommuneServiceImpl;




@Controller
@RequestMapping(value="/commune")
public class CommuneController {

	@Autowired
	CommuneServiceImpl communeService;

	
	@GetMapping({"/list", "/commune/list"})
	public String  viewPersonnelList(@ModelAttribute("message") String message, Model model) {
		  List<Commune> communeList = communeService.getAllCommune();
		  model.addAttribute("communeList", communeList);
		  model.addAttribute("message", message);
		
		return "commune/list";
	}
	
	@GetMapping("/new")
	public String viewDocumentNew(@ModelAttribute("message") String message, Model model) {
		Commune commune = new Commune();
		
		model.addAttribute("commune", commune);
		model.addAttribute("message", message);
		
		return "commune/new";
	}
	
	@PostMapping("/save")
	public String saveDocument(Commune commune, RedirectAttributes redirectAttributes) {
		
		if(this.communeService.saveOrUpdateCommune(commune)) {
			redirectAttributes.addFlashAttribute("message", "Save success");
			return "redirect:/province/"+commune.getDistrict().getProvince().getId()+"/district/"+commune.getDistrict().getId()+"/communes";
			
			//return "redirect:/commune/list";
		};
		redirectAttributes.addFlashAttribute("message", "Save failure");
		return "redirect:/commune/new";
	}
	
	@PostMapping("/edit")
	public String editBanque(Commune commune, RedirectAttributes redirectAttributes,HttpServletRequest request) {

		if (this.communeService.saveOrUpdateCommune(commune)) {
			redirectAttributes.addFlashAttribute("message", "Edit success");
			return "redirect:/commune/list";
		}
		;
		redirectAttributes.addFlashAttribute("message", "Edit failure");
		return "redirect:/commune/edit/" + commune.getId();
	}
	
	
//	@GetMapping("/delete/{id}")
//	public String deleteDocument(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
//
//		if (this.communeService.deleteDocumentById(id)) {
//			redirectAttributes.addFlashAttribute("message", "Delete success");
//			return "redirect:/commune/list";
//		}
//		;
//		redirectAttributes.addFlashAttribute("message", "Delete failure");
//		return "redirect:/commune/list";
//	}
	
	@GetMapping("/changeStatus/{id}")
	public String enableDisableDocument(@PathVariable("id") long id, Model model) {

		Commune commune = this.communeService.getCommuneById(id);

		if (commune.isActiveStatus() == false) {
			commune.setActiveStatus(true);
		} else if (commune.isActiveStatus() == true) {
			commune.setActiveStatus(false);
		}
		this.communeService.saveOrUpdateCommune(commune);

		return "redirect:/province/"+commune.getDistrict().getProvince().getId()+"/district/"+commune.getDistrict().getId()+"/communes";
	}	

	@GetMapping("/edit/{id}")
	public String showFormEditDocument(@PathVariable("id") long id, Model model) {
		Commune commune = this.communeService.getCommuneById(id);
		model.addAttribute("commune", commune);
		return "/commune/edit";
	}
}
