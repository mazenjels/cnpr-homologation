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

import com.cnpr.homologation.models.District;
import com.cnpr.homologation.service.DistrictServiceImpl;




@Controller
@RequestMapping(value="/district")
public class DistrictController {

	@Autowired
	DistrictServiceImpl districtServiceImpl;

	
	@GetMapping({"/list", "/district/list"})
	public String  viewPersonnelList(@ModelAttribute("message") String message, Model model) {
		  List<District> districtList = districtServiceImpl.getAllDistrict();
		  model.addAttribute("districtList", districtList);
		  model.addAttribute("message", message);
		
		return "district/list";
	}
	
	@GetMapping("/new")
	public String viewDocumentNew(@ModelAttribute("message") String message, Model model) {
		District district = new District();
		
		model.addAttribute("district", district);
		model.addAttribute("message", message);
		
		return "district/new";
	}
	
	@PostMapping("/save")
	public String save(District district, RedirectAttributes redirectAttributes) {
		
		if(this.districtServiceImpl.saveOrUpdateDistrict(district)) {
			redirectAttributes.addFlashAttribute("message", "Save success");
			return "redirect:/province/"+district.getProvince().getId()+"/districts";
		};
		redirectAttributes.addFlashAttribute("message", "Save failure");
		
		return "redirect:/district/new";
	}
	
	@PostMapping("/edit")
	public String editBanque(District district, RedirectAttributes redirectAttributes,HttpServletRequest request) {

		if (this.districtServiceImpl.saveOrUpdateDistrict(district)) {
			redirectAttributes.addFlashAttribute("message", "Edit success");
			return "redirect:/admin/district/list";
		}
		;
		redirectAttributes.addFlashAttribute("message", "Edit failure");
		return "redirect:/admin/district/edit/" + district.getId();
	}
	
	
//	@GetMapping("/delete/{id}")
//	public String deleteDocument(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
//
//		if (this.districtServiceImpl.deleteDocumentById(id)) {
//			redirectAttributes.addFlashAttribute("message", "Delete success");
//			return "redirect:/document/list";
//		}
//		;
//		redirectAttributes.addFlashAttribute("message", "Delete failure");
//		return "redirect:/document/list";
//	}
	
	@GetMapping("/changeStatus/{id}")
	public String enableDisableDocument(@PathVariable("id") long id, Model model) {

		District document = this.districtServiceImpl.getDistrictById(id);

		if (document.isActiveStatus() == false) {
			document.setActiveStatus(true);
		} else if (document.isActiveStatus() == true) {
			document.setActiveStatus(false);
		}
		this.districtServiceImpl.saveOrUpdateDistrict(document);

		return "redirect:/district/list";
	}	

	@GetMapping("/edit/{id}")
	public String showFormEditDocument(@PathVariable("id") long id, Model model) {
		District district = this.districtServiceImpl.getDistrictById(id);
		model.addAttribute("district", district);
		return "/district/edit";
	}
}
