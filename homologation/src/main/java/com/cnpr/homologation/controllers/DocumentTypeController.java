package com.cnpr.homologation.controllers;

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

import com.cnpr.homologation.models.CnprDocumentType;
import com.cnpr.homologation.models.CnprVehiculeType;
import com.cnpr.homologation.service.DocumentTypeServiceImpl;




@Controller
@RequestMapping(value="/documentType")
public class DocumentTypeController {

	@Autowired
	DocumentTypeServiceImpl documentTypeService;

	
	@GetMapping({"/list", "/documentType/list"})
	public String  viewPersonnelList(@ModelAttribute("message") String message, Model model) {
		  List<CnprDocumentType> documentList = documentTypeService.getAllCnprDocumentType();
		  model.addAttribute("documentList", documentList);
		  model.addAttribute("message", message);
		
		return "documentType/list";
	}
	
	@GetMapping("/new")
	public String viewDocumentNew(@ModelAttribute("message") String message, Model model) {
		CnprDocumentType document = new CnprDocumentType();
		
		model.addAttribute("document", document);
		model.addAttribute("message", message);
		
		return "documentType/new";
	}
	
	@PostMapping("/save")
	public String saveDocument(CnprDocumentType document, RedirectAttributes redirectAttributes) {
		
		if(this.documentTypeService.saveOrUpdateCnprDocumentType(document)) {
			redirectAttributes.addFlashAttribute("message", "Save success");
			return "redirect:/documentType/list";
		};
		redirectAttributes.addFlashAttribute("message", "Save failure");
		return "redirect:/documentType/new";
	}
	
	@PostMapping("/edit")
	public String editBanque(CnprDocumentType document, RedirectAttributes redirectAttributes,HttpServletRequest request) {

		if (this.documentTypeService.saveOrUpdateCnprDocumentType(document)) {
			redirectAttributes.addFlashAttribute("message", "Edit success");
			return "redirect:/admin/documentType/list";
		}
		;
		redirectAttributes.addFlashAttribute("message", "Edit failure");
		return "redirect:/admin/documentType/edit/" + document.getId();
	}
	
	
//	@GetMapping("/delete/{id}")
//	public String deleteDocument(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
//
//		if (this.documentTypeService.deleteDocumentById(id)) {
//			redirectAttributes.addFlashAttribute("message", "Delete success");
//			return "redirect:/document/list";
//		}
//		;
//		redirectAttributes.addFlashAttribute("message", "Delete failure");
//		return "redirect:/document/list";
//	}
	
	@GetMapping("/changeStatus/{id}")
	public String enableDisableDocument(@PathVariable("id") long id, Model model) {

		CnprDocumentType document = this.documentTypeService.getCnprDocumentTypeById(id);

		if (document.isActiveStatus() == false) {
			document.setActiveStatus(true);
		} else if (document.isActiveStatus() == true) {
			document.setActiveStatus(false);
		}
		this.documentTypeService.saveOrUpdateCnprDocumentType(document);

		return "redirect:/documentType/list";
	}	

	@GetMapping("/edit/{id}")
	public String showFormEditDocument(@PathVariable("id") long id, Model model) {
		CnprDocumentType document = this.documentTypeService.getCnprDocumentTypeById(id);
		model.addAttribute("document", document);
		return "/documentType/edit";
	}
}
