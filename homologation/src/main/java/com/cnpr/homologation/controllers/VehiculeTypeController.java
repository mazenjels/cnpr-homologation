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



import com.cnpr.homologation.models.CnprVehiculeType;
import com.cnpr.homologation.service.VehiculeTypeServiceImpl;




@Controller
@RequestMapping(value="/vehicleType")
public class VehiculeTypeController {

	@Autowired
	VehiculeTypeServiceImpl vehicleTypeService;

	
	@GetMapping({"/list", "/vehicleType/list"})
	public String  viewPersonnelList(@ModelAttribute("message") String message, Model model) {
		  List<CnprVehiculeType> vehicleList = vehicleTypeService.getAllCnprVehiculeType();
		  model.addAttribute("vehicleList", vehicleList);
		  model.addAttribute("message", message);
		
		return "vehicleType/list";
	}
	
	@GetMapping("/new")
	public String viewDocumentNew(@ModelAttribute("message") String message, Model model) {
		CnprVehiculeType vehicle = new CnprVehiculeType();
		
		model.addAttribute("vehicle", vehicle);
		model.addAttribute("message", message);
		
		return "vehicleType/new";
	}
	
	@PostMapping("/save")
	public String saveDocument(CnprVehiculeType vehicle, RedirectAttributes redirectAttributes) {
		
		if(this.vehicleTypeService.saveOrUpdateCnprVehiculeType(vehicle)) {
			redirectAttributes.addFlashAttribute("message", "Save success");
			return "redirect:/vehicleType/list";
		};
		redirectAttributes.addFlashAttribute("message", "Save failure");
		return "redirect:/vehicleType/new";
	}
	
	@PostMapping("/edit")
	public String editVehicle(CnprVehiculeType vehicle, RedirectAttributes redirectAttributes,HttpServletRequest request) {

		if (this.vehicleTypeService.saveOrUpdateCnprVehiculeType(vehicle)) {
			redirectAttributes.addFlashAttribute("message", "Edit success");
			return "redirect:/admin/vehicleType/list";
		}
		;
		redirectAttributes.addFlashAttribute("message", "Edit failure");
		return "redirect:/admin/vehicleType/edit/" + vehicle.getId();
	}
	
//	@GetMapping("/delete/{id}")
//	public String deleteDocument(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
//
//		if (this.vehicleTypeService.deleteDocumentById(id)) {
//			redirectAttributes.addFlashAttribute("message", "Delete success");
//			return "redirect:/vehicle/list";
//		}
//		;
//		redirectAttributes.addFlashAttribute("message", "Delete failure");
//		return "redirect:/vehicle/list";
//	}
	
	@GetMapping("/changeStatus/{id}")
	public String enableDisableDocument(@PathVariable("id") long id, Model model) {

		CnprVehiculeType vehicle = this.vehicleTypeService.getCnprVehiculeTypeById(id);

		if (vehicle.isActiveStatus() == false) {
			vehicle.setActiveStatus(true);
		} else if (vehicle.isActiveStatus() == true) {
			vehicle.setActiveStatus(false);
		}
		this.vehicleTypeService.saveOrUpdateCnprVehiculeType(vehicle);

		return "redirect:/vehicleType/list";
	}	

	@GetMapping("/edit/{id}")
	public String showFormEditDocument(@PathVariable("id") long id, Model model) {
		CnprVehiculeType vehicle = this.vehicleTypeService.getCnprVehiculeTypeById(id);
		model.addAttribute("vehicle", vehicle);
		return "/vehicleType/edit";
	}
}
