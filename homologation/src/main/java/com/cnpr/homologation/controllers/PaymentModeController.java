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

import com.cnpr.homologation.models.PaymentMode;
import com.cnpr.homologation.service.PaymentModeServiceImpl;




@Controller
@RequestMapping(value="/paymentMode")
public class PaymentModeController {

	@Autowired
	PaymentModeServiceImpl paymentModeService;

	
	@GetMapping({"/list", "/paymentMode/list"})
	public String  viewPersonnelList(@ModelAttribute("message") String message, Model model) {
		  List<PaymentMode> paymentModeList = paymentModeService.getAllPaymentMode();
		  model.addAttribute("paymentModeList", paymentModeList);
		  model.addAttribute("message", message);
		
		return "paymentMode/list";
	}
	
	@GetMapping("/new")
	public String viewDocumentNew(@ModelAttribute("message") String message, Model model) {
		PaymentMode paymentMode = new PaymentMode();
		
		model.addAttribute("paymentMode", paymentMode);
		model.addAttribute("message", message);
		
		return "paymentMode/new";
	}
	
	@PostMapping("/save")
	public String saveDocument(PaymentMode paymentMode, RedirectAttributes redirectAttributes) {
		
		if(this.paymentModeService.saveOrUpdatePaymentMode(paymentMode)) {
			redirectAttributes.addFlashAttribute("message", "Save success");
			return "redirect:/paymentMode/list";
		};
		redirectAttributes.addFlashAttribute("message", "Save failure");
		return "redirect:/paymentMode/new";
	}
	
	@PostMapping("/edit")
	public String editBanque(PaymentMode paymentMode, RedirectAttributes redirectAttributes,HttpServletRequest request) {

		if (this.paymentModeService.saveOrUpdatePaymentMode(paymentMode)) {
			redirectAttributes.addFlashAttribute("message", "Edit success");
			return "redirect:/admin/paymentMode/list";
		}
		;
		redirectAttributes.addFlashAttribute("message", "Edit failure");
		return "redirect:/admin/paymentMode/edit/" + paymentMode.getId();
	}
	
	
//	@GetMapping("/delete/{id}")
//	public String deleteDocument(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
//
//		if (this.paymentModeService.deleteDocumentById(id)) {
//			redirectAttributes.addFlashAttribute("message", "Delete success");
//			return "redirect:/paymentMode/list";
//		}
//		;
//		redirectAttributes.addFlashAttribute("message", "Delete failure");
//		return "redirect:/paymentMode/list";
//	}
	
	@GetMapping("/changeStatus/{id}")
	public String enableDisableDocument(@PathVariable("id") long id, Model model) {

		PaymentMode paymentMode = this.paymentModeService.getPaymentModeById(id);

		if (paymentMode.isActiveStatus() == false) {
			paymentMode.setActiveStatus(true);
		} else if (paymentMode.isActiveStatus() == true) {
			paymentMode.setActiveStatus(false);
		}
		this.paymentModeService.saveOrUpdatePaymentMode(paymentMode);

		return "redirect:/paymentMode/list";
	}	

	@GetMapping("/edit/{id}")
	public String showFormEditDocument(@PathVariable("id") long id, Model model) {
		PaymentMode paymentMode = this.paymentModeService.getPaymentModeById(id);
		model.addAttribute("paymentMode", paymentMode);
		return "/paymentMode/edit";
	}
}
