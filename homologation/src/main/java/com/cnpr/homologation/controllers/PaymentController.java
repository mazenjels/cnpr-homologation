package com.cnpr.homologation.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cnpr.homologation.models.BankPaiement;
import com.cnpr.homologation.models.CnprPayment;
import com.cnpr.homologation.models.CnprUser;
import com.cnpr.homologation.models.PaymentMode;
import com.cnpr.homologation.pojo.Summary;
import com.cnpr.homologation.service.BankPaiementServiceImpl;
import com.cnpr.homologation.service.PaymentModeServiceImpl;
import com.cnpr.homologation.service.PaymentServiceImpl;


@Controller
@RequestMapping(value = "/payment")
public class PaymentController {

	@Autowired
	PaymentServiceImpl paymentService;
	
	@Autowired
	PaymentModeServiceImpl paymentModeServiceImpl;

	@Autowired
	BankPaiementServiceImpl bankPaiementServiceImpl;

	@GetMapping({ "/list", "/payment/list" })
	public String viewPaymentList(@ModelAttribute("message") String message, Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		List<CnprPayment> paymentList = paymentService.getAllCnprPayment();
		
		Summary summaryUSD = paymentService.getAmountUSD();
		Summary summaryCDF = paymentService.getAmountCDF();
		
		session.setAttribute("summaryUSD", summaryUSD.getTotal());
		session.setAttribute("summaryCDF", summaryCDF.getTotal());
		
		model.addAttribute("paymentList", paymentList);
		model.addAttribute("message", message);

		return "paiement/list";
	}

	@GetMapping("banque")
	public String banque(@ModelAttribute("message") String message, Model model) {
		List<BankPaiement> bankPaymentList = bankPaiementServiceImpl.getAllBankPaiement();
		model.addAttribute("bankPaymentList", bankPaymentList);
		model.addAttribute("message", message);

		return "paiement/banque";
	}

	@GetMapping("banque/upload")
	public String banqueUpload(@ModelAttribute("message") String message, Model model) {
		
		
		model.addAttribute("message", message);

		return "paiement/upload";
	}
	//banque/import-timesheet
	@PostMapping(path = "banque/import-timesheet", params = "action=importerXls")
	public String importTimesheet(@RequestPart(required = true) List<MultipartFile> files, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("uploadedBanquePayementList");

		List<BankPaiement> uploadedBanquePayementList = new BankPaiementServiceImpl().importBankPayment(files);

		

		session.setAttribute("uploadedBanquePayementList", uploadedBanquePayementList);

		return "redirect:/payment/banque/upload";
	}
	
	@GetMapping("/banque/validateUpload")
	public String valider(HttpServletRequest request) {
		HttpSession session = request.getSession();
		CnprUser user = (CnprUser) session.getAttribute("loggedUser");
		@SuppressWarnings("unchecked")
		List<BankPaiement> uploadedBanquePayementList = (List<BankPaiement>) session.getAttribute("uploadedBanquePayementList");

		Map<String, Object> keyMap = new HashMap<>();
		// uploadedTimeSheetList.forEach(ts->timeSheetServiceImpl.saveOrUpdateTimeSheet(ts));
		new BankPaiementServiceImpl().bulkInsertBanquePayment(uploadedBanquePayementList,user);

		session.setAttribute("keyMap", keyMap);

		return "redirect:/payment/banque";
	}

	@PostMapping("/save")
	public String saveDocument(RedirectAttributes redirectAttributes, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		PaymentMode paymentMode = paymentModeServiceImpl.getPaymentModeById(Long.parseLong(request.getParameter("paymentMode")));

		CnprPayment payment = (CnprPayment) session.getAttribute("payment");
		payment.setReference(request.getParameter("reference"));
		payment.setAmount(Long.parseLong(request.getParameter("amount")));
		payment.setCurrencyCode(request.getParameter("currencyCode"));
		payment.setBankBranch(request.getParameter("bankBranch"));
		payment.setTransactionId(request.getParameter("transactionId"));
		payment.setMotif(request.getParameter("motif"));
		payment.setBank(paymentMode.getDesignation());
		//PaymentMode paymentMode = new PaymentMode();
		//paymentMode.setId(Long.parseLong(request.getParameter("paymentMode")));
		payment.setPaymentMode(paymentMode);
		payment.setCreatedBy((CnprUser) session.getAttribute("loggedUser"));
		if (this.paymentService.saveOrUpdateCnprPayment(payment)) {
			redirectAttributes.addFlashAttribute("message", "Save success");
			return "redirect:/autoEcole/view/" + payment.getAutoEcole().getId();
		}
		;
		redirectAttributes.addFlashAttribute("message", "Save failure");
		return "redirect:/autoEcole/view/" + payment.getAutoEcole().getId();
	}

}
