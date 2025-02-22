package com.cnpr.homologation.controllers;

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

import com.cnpr.homologation.models.CnprAutoEcole;
import com.cnpr.homologation.models.CnprUser;
import com.cnpr.homologation.models.Planification;
import com.cnpr.homologation.models.Province;
import com.cnpr.homologation.service.AutoEcoleServiceImpl;
import com.cnpr.homologation.service.CommuneServiceImpl;
import com.cnpr.homologation.service.PlanificationServiceImpl;
import com.cnpr.homologation.service.ProvinceServiceImpl;
import com.cnpr.homologation.service.DistrictServiceImpl;




@Controller
@RequestMapping(value="/planification")
public class PlanificationController {

	@Autowired
	PlanificationServiceImpl planificationService;
	
	@Autowired
	AutoEcoleServiceImpl autoEcoleServiceImpl;

	@Autowired
	CommuneServiceImpl communeServiceImpl;
	
	@Autowired
	DistrictServiceImpl districtServiceImpl;
	
	@Autowired
	ProvinceServiceImpl provinceServiceImpl;
	
	@GetMapping({"/list", "/planification/list"})
	public String  viewPersonnelList(Model model, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int size, HttpServletRequest request,
			@ModelAttribute("message") String message) {
		HttpSession session = request.getSession();
		
		 List<Province> provinceList = provinceServiceImpl.getAllActiveProvince();
		 //Long selectedProvinceId = provinceList.get(0).getId();
		 Pageable pageable = PageRequest.of(page - 1, size);
		Page<Planification> pagePlanification = planificationService.getAllPageable(pageable);

			
		  List<Planification> planificationList = pagePlanification.getContent();
		 // List<District> districtList = districtServiceImpl.getAllActiveDistrict();
		 // Long selectedDistrictId = districtList.get(0).getId();
		  //List<Commune> communeList = communeServiceImpl.getAllActiveCommune();
		 
		  session.setAttribute("provinceList", provinceList);
		  
		  //session.setAttribute("communeList", communeList);
		 // session.setAttribute("districtList", districtList);
		  model.addAttribute("planificationList", planificationList);
		  model.addAttribute("currentPage", pagePlanification.getNumber() + 1);
			model.addAttribute("totalItems", pagePlanification.getTotalElements());
			model.addAttribute("totalPages", pagePlanification.getTotalPages());
			model.addAttribute("pageSize", size);
		  model.addAttribute("message", message);
		
		return "planification/list";
	}
	
	@GetMapping("/new")
	public String viewPlanification(@ModelAttribute("message") String message, Model model) {
		Planification planification = new Planification();
		
		model.addAttribute("planification", planification);
		model.addAttribute("message", message);
		
		return "planification/new";
	}
	
	@PostMapping("/save")
	public String saveDocument(Planification planification, RedirectAttributes redirectAttributes,HttpServletRequest request) {
		HttpSession session = request.getSession();
		planification.setCurrentStatus("draft");
		planification.setCreatedBy((CnprUser)session.getAttribute("loggedUser"));
		if(this.planificationService.saveOrUpdatePlanification(planification)) {
			redirectAttributes.addFlashAttribute("message", "Save success");
			return "redirect:/planification/list";
		};
		redirectAttributes.addFlashAttribute("message", "Save failure");
		return "redirect:/planification/new";
	}
	
	@PostMapping("/edit")
	public String editBanque(Planification planification, RedirectAttributes redirectAttributes,HttpServletRequest request) {

		if (this.planificationService.saveOrUpdatePlanification(planification)) {
			redirectAttributes.addFlashAttribute("message", "Edit success");
			return "redirect:/planification/list";
		}
		;
		redirectAttributes.addFlashAttribute("message", "Edit failure");
		return "redirect:/planification/edit/" + planification.getId();
	}
	
	
//	@GetMapping("/delete/{id}")
//	public String deleteDocument(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
//
//		if (this.planificationService.deleteDocumentById(id)) {
//			redirectAttributes.addFlashAttribute("message", "Delete success");
//			return "redirect:/planification/list";
//		}
//		;
//		redirectAttributes.addFlashAttribute("message", "Delete failure");
//		return "redirect:/planification/list";
//	}
	
	@GetMapping("/changeStatus/{id}")
	public String enableDisableDocument(@PathVariable("id") long id, Model model) {

		Planification planification = this.planificationService.getPlanificationById(id);

		if (planification.isActiveStatus() == false) {
			planification.setActiveStatus(true);
		} else if (planification.isActiveStatus() == true) {
			planification.setActiveStatus(false);
		}
		this.planificationService.saveOrUpdatePlanification(planification);

		return "redirect:/planification/list";
	}	

	@GetMapping("/edit/{id}")
	public String showFormEditDocument(@PathVariable("id") long id, Model model) {
		Planification planification = this.planificationService.getPlanificationById(id);
		model.addAttribute("planification", planification);
		return "/planification/edit";
	}
	
	@GetMapping("/open/{id}")
	public String view(@PathVariable("id") long id, Model model,HttpServletRequest request) {
		HttpSession session= request.getSession();
		
		Planification planification = this.planificationService.getPlanificationById(id);
		 List<CnprAutoEcole> autoEcoleList = autoEcoleServiceImpl.getAllCnprAutoEcoleByPlanification(planification.getId());
		

		session.setAttribute("planification", planification);
		model.addAttribute("autoEcoleList", autoEcoleList);
		
		return "/planification/view";
	}
	
	@GetMapping("/lancer/{id}")
	public String lancer(@PathVariable("id") long id, Model model) {

		Planification planification = this.planificationService.getPlanificationById(id);
		planification.setCurrentStatus("launched");;
		this.planificationService.saveOrUpdatePlanification(planification);

		return "redirect:/planification/list";
	}	
	@GetMapping("/fermer/{id}")
	public String fermer(@PathVariable("id") long id, Model model) {

		Planification planification = this.planificationService.getPlanificationById(id);
		planification.setCurrentStatus("closed");;
		this.planificationService.saveOrUpdatePlanification(planification);

		return "redirect:/planification/list";
	}	
}
