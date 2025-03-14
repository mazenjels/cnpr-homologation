package com.cnpr.homologation.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itextpdf.text.DocumentException;

import com.cnpr.homologation.models.AutoEcoleTypePermis;
import com.cnpr.homologation.models.AutoEcoleUser;
import com.cnpr.homologation.models.Candidat;
import com.cnpr.homologation.models.CnprAutoEcole;
import com.cnpr.homologation.models.CnprAutoEcoleDocumentType;
import com.cnpr.homologation.models.CnprAutoEcoleVehiculeType;
import com.cnpr.homologation.models.CnprPayment;
import com.cnpr.homologation.models.CnprRole;
import com.cnpr.homologation.models.CnprUser;
import com.cnpr.homologation.models.CnprVehiculeType;
import com.cnpr.homologation.models.Commune;
import com.cnpr.homologation.models.PaymentMode;
import com.cnpr.homologation.models.Planification;
import com.cnpr.homologation.models.PrixTypePermisAutoEcole;
import com.cnpr.homologation.models.Province;
import com.cnpr.homologation.models.TypePermis;
import com.cnpr.homologation.pojo.Summary;
import com.cnpr.homologation.models.District;
import com.cnpr.homologation.models.Formateur;
import com.cnpr.homologation.service.AutoEcoleDocumentTypeServiceImpl;
import com.cnpr.homologation.service.AutoEcoleServiceImpl;
import com.cnpr.homologation.service.AutoEcoleTypePermisServiceImpl;
import com.cnpr.homologation.service.AutoEcoleUserServiceImpl;
import com.cnpr.homologation.service.AutoEcoleVehiculeTypeServiceImpl;
import com.cnpr.homologation.service.CandidatServiceImpl;
import com.cnpr.homologation.service.CommuneServiceImpl;
import com.cnpr.homologation.service.DocumentTypeServiceImpl;
import com.cnpr.homologation.service.FormateurServiceImpl;
import com.cnpr.homologation.service.PaymentModeServiceImpl;
import com.cnpr.homologation.service.PaymentServiceImpl;
import com.cnpr.homologation.service.PermisTypeServiceImpl;
import com.cnpr.homologation.service.PrixPermisTypeAutoEcoleServiceImpl;
import com.cnpr.homologation.service.ProvinceServiceImpl;
import com.cnpr.homologation.service.RoleServiceImpl;
import com.cnpr.homologation.service.UserServiceImpl;
import com.cnpr.homologation.service.VehiculeTypeServiceImpl;
import com.cnpr.homologation.utils.AutoEcoleExcelGenerator;
import com.cnpr.homologation.utils.PdfUtilsAutoEcole;
import com.cnpr.homologation.service.DistrictServiceImpl;

@Controller
@RequestMapping(value = "/autoEcole")
public class AutoEcoleController {

	@Autowired
	AutoEcoleServiceImpl autoEcoleServiceImpl;

	@Autowired
	PrixPermisTypeAutoEcoleServiceImpl prixPermisTypeAutoServiceImpl;

	@Autowired
	VehiculeTypeServiceImpl vehiculeTypeServiceImpl;

	@Autowired
	PermisTypeServiceImpl typePermisServiceImpl;

	@Autowired
	AutoEcoleVehiculeTypeServiceImpl autoEcoleVehiculeTypeServiceImpl;

	@Autowired
	AutoEcoleDocumentTypeServiceImpl autoEcoleDocumentTypeServiceImpl;

	@Autowired
	AutoEcoleTypePermisServiceImpl autoEcoleTypePermisServiceImpl;

	@Autowired
	CommuneServiceImpl communeServiceImpl;

	@Autowired
	DistrictServiceImpl districtServiceImpl;

	@Autowired
	ProvinceServiceImpl provinceServiceImpl;

	@Autowired
	PaymentModeServiceImpl paymentModeServiceImpl;

	@Autowired
	PaymentServiceImpl paymentService;

	@Autowired
	FormateurServiceImpl formateurService;

	@Autowired
	CandidatServiceImpl candidatService;

	@Autowired
	UserServiceImpl userServiceImpl;

	@Autowired
	AutoEcoleUserServiceImpl autoEcoleUserServiceImpl;

	@Autowired
	RoleServiceImpl roleServiceImpl;

	@GetMapping({ "/list", "/autoEcole/list" })
	public String viewPersonnelList(Model model, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int size, HttpServletRequest request,
			@ModelAttribute("message") String message) {
		HttpSession session = request.getSession();
		Pageable pageable = PageRequest.of(page - 1, size);
		Page<CnprAutoEcole> pageAutoecole = autoEcoleServiceImpl.getAllPageable(pageable);

		List<CnprAutoEcole> autoEcoleList = pageAutoecole.getContent();

		// List<District> districtList = districtServiceImpl.getAllDistrict();
		// List<Commune> communeList = communeServiceImpl.getAllActiveCommune();
		// List<Province> provinceList = provinceServiceImpl.getAllActiveProvince();
		// session.setAttribute("provinceList", provinceList);

		// session.setAttribute("communeList", communeList);
		// session.setAttribute("districtList", districtList);

		model.addAttribute("autoEcoleList", autoEcoleList);
		model.addAttribute("currentPage", pageAutoecole.getNumber() + 1);
		model.addAttribute("totalItems", pageAutoecole.getTotalElements());
		model.addAttribute("totalPages", pageAutoecole.getTotalPages());
		model.addAttribute("pageSize", size);
		model.addAttribute("message", message);

		return "autoEcole/list";
	}

	@GetMapping("/new")
	public String viewDocumentNew(@ModelAttribute("message") String message, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		CnprAutoEcole autoEcole = new CnprAutoEcole();
		Planification planification = (Planification) request.getSession().getAttribute("planification");
		autoEcole.setPlanification(planification);
		session.setAttribute("planification", planification);
		autoEcole.setCreatedBy((CnprUser) session.getAttribute("loggedUser"));

		model.addAttribute("autoEcole", autoEcole);
		model.addAttribute("message", message);

		return "autoEcole/new";
	}

	@PostMapping("/save")
	public String save(CnprAutoEcole autoEcole, RedirectAttributes redirectAttributes) {

		if (this.autoEcoleServiceImpl.saveOrUpdateCnprAutoEcole(autoEcole)) {
			redirectAttributes.addFlashAttribute("message", "Save success");
			return "redirect:/planification/open/" + autoEcole.getPlanification().getId();
		}
		;
		redirectAttributes.addFlashAttribute("message", "Save failure");
		return "redirect:/autoEcole/new";
	}

	@PostMapping("/edit")
	public String editBanque(CnprAutoEcole autoEcole, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {

		if (this.autoEcoleServiceImpl.saveOrUpdateCnprAutoEcole(autoEcole)) {
			redirectAttributes.addFlashAttribute("message", "Edit success");
			return "redirect:/planification/open/" + autoEcole.getPlanification().getId();
		}
		;
		redirectAttributes.addFlashAttribute("message", "Edit failure");
		return "redirect:/admin/autoEcole/edit/" + autoEcole.getId();
	}

//	@GetMapping("/vehicleType/{id}")
//	public String deleteDocument(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
//
//		if (this.autoEcoleServiceImpl.deleteDocumentById(id)) {
//			redirectAttributes.addFlashAttribute("message", "Delete success");
//			return "redirect:/document/list";
//		}
//		;
//		redirectAttributes.addFlashAttribute("message", "Delete failure");
//		return "redirect:/document/list";
//	}

	@GetMapping("/changeStatus/{id}")
	public String changeStatus(@PathVariable("id") long id, Model model) {

		CnprAutoEcole autoEcole = this.autoEcoleServiceImpl.getCnprAutoEcoleById(id);
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		LocalDate currentdate = LocalDate.now();
		Month currentMonth = currentdate.getMonth();

		Calendar calendar = Calendar.getInstance();
//		int year = calendar.get(Calendar.YEAR);
//		int day = calendar.get(Calendar.DAY_OF_MONTH);
//		int hour = calendar.get(Calendar.HOUR_OF_DAY);
//		int min = calendar.get(Calendar.MINUTE);
//		int sec = calendar.get(Calendar.SECOND);

		Map<String, String> mapMonths = new HashMap<>();
		mapMonths.put("JANUARY", "01");
		mapMonths.put("FEBRUARY", "02");
		mapMonths.put("MARCH", "03");
		mapMonths.put("APRIL", "04");
		mapMonths.put("MAY", "05");
		mapMonths.put("JUNE", "06");
		mapMonths.put("JULY", "07");
		mapMonths.put("AUGUST", "08");
		mapMonths.put("SEPTEMBER", "09");
		mapMonths.put("OCTOBER", "10");
		mapMonths.put("NOVEMBER", "11");
		mapMonths.put("DECEMBER", "12");

		String prefix = "";
		if (autoEcole.getId() < 10) {
			prefix = "000" + autoEcole.getId();
		} else if (autoEcole.getId() >= 10 && autoEcole.getId() < 100) {
			prefix = "00" + autoEcole.getId();
		} else if (autoEcole.getId() >= 100 && autoEcole.getId() < 1000) {
			prefix = "0" + autoEcole.getId();
		} else if (autoEcole.getId() >= 1000 && autoEcole.getId() < 10000) {
			prefix = "" + autoEcole.getId();
		}
		String codeUnique = autoEcole.getDistrict().getProvince().getCode() + prefix;
		autoEcole.setCodeUnique(codeUnique);

		if (autoEcole.isActiveStatus() == false) {
			autoEcole.setActiveStatus(true);
		} else if (autoEcole.isActiveStatus() == true) {
			autoEcole.setActiveStatus(false);
		}
		this.autoEcoleServiceImpl.saveOrUpdateCnprAutoEcole(autoEcole);

		return "redirect:/autoEcole/changeStatus/" + autoEcole.getId();
	}

	@GetMapping("/edit/{id}")
	public String showFormEditDocument(@PathVariable("id") long id, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		CnprAutoEcole autoEcole = this.autoEcoleServiceImpl.getCnprAutoEcoleById(id);
		// List<CnprAutoEcole> autoEcoleList =
		// autoEcoleServiceImpl.getAllCnprAutoEcole();
		List<District> districtList = districtServiceImpl.getAllDistrict();
		List<Commune> communeList = communeServiceImpl.getAllActiveCommune();
		List<Province> provinceList = provinceServiceImpl.getAllActiveProvince();

		session.setAttribute("communeList", communeList);
		session.setAttribute("districtList", districtList);
		session.setAttribute("provinceList", provinceList);
		model.addAttribute("autoEcole", autoEcole);
		return "/autoEcole/edit";
	}

	@GetMapping("/view/{id}")
	public String view(@PathVariable("id") long id, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		CnprPayment payment = new CnprPayment();

		List<CnprPayment> paymentList = paymentService.getAllPaymentByAutoEcole(id);
		List<PaymentMode> paymentModeList = paymentModeServiceImpl.getAllActivePaymentMode();
		List<CnprAutoEcoleVehiculeType> autoEcoleVehicleList = autoEcoleVehiculeTypeServiceImpl
				.getAllAutoEcoleVehiculeTypeByAutoEcoleId(id);
		List<CnprAutoEcoleDocumentType> autoEcoleDocumentList = autoEcoleDocumentTypeServiceImpl
				.getAllAutoEcoleDocumentTypeByAutoEcoleId(id);
		List<AutoEcoleTypePermis> autoEcoleTypePermisList = autoEcoleTypePermisServiceImpl
				.getAllAutoEcoleTypePermisByAutoEcoleid(id);
		List<Formateur> formateurList = formateurService.getAllFormateurByAuoEcoleId(id);
		List<Candidat> candidatList = candidatService.getAllCandidatByAutoEcoleId(id);
		List<AutoEcoleUser> autoEcoleUserList = autoEcoleUserServiceImpl.getAllAutoEcoleUserByAutoEcoleId(id);
		CnprAutoEcole autoEcole = this.autoEcoleServiceImpl.getCnprAutoEcoleById(id);
		payment.setAutoEcole(autoEcole);

		Summary summaryCDF = this.paymentService.getAmountCDFByAutoEcole(id);

		Summary summaryUSD = this.paymentService.getAmountUSDByAutoEcole(id);

		session.setAttribute("summaryCDF", summaryCDF.getTotal());
		session.setAttribute("summaryUSD", summaryUSD.getTotal());
		session.setAttribute("paymentList", paymentList);
		session.setAttribute("formateurList", formateurList);
		session.setAttribute("candidatList", candidatList);
		session.setAttribute("autoEcoleUserList", autoEcoleUserList);
		session.setAttribute("paymentModeList", paymentModeList);
		session.setAttribute("autoEcoleVehicleList", autoEcoleVehicleList);
		session.setAttribute("autoEcoleDocumentList", autoEcoleDocumentList.toString());
		session.setAttribute("autoEcoleTypePermisList", autoEcoleTypePermisList);
		session.setAttribute("autoEcole", autoEcole);
		session.setAttribute("payment", payment);

		return "/autoEcole/view";
	}

	@GetMapping("/prixTypePermis/{id}")
	public String prixTypePermis(@PathVariable("id") long id, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		PrixTypePermisAutoEcole prixTypePermis = new PrixTypePermisAutoEcole();
		List<PrixTypePermisAutoEcole> prixTypePermisAutoEcoleList = prixPermisTypeAutoServiceImpl.getAllPrixTypePermisAutoEcoleById(id);
		List<AutoEcoleTypePermis> autoEcoleTypePermisList = null;
		List<TypePermis> typePermisList = null;
		if (id == 1) {
			typePermisList = typePermisServiceImpl.getAllActiveTypePermis();
		} else {
			typePermisList = new ArrayList<TypePermis>();
			autoEcoleTypePermisList = autoEcoleTypePermisServiceImpl.getAllAutoEcoleTypePermisByAutoEcoleid(id);
			for (AutoEcoleTypePermis permisAuto : autoEcoleTypePermisList) {
				typePermisList.add(permisAuto.getTypePermis());
			}

		}
		CnprAutoEcole autoEcole = new CnprAutoEcole();
		autoEcole.setId(id);
		prixTypePermis.setAutoEcole(autoEcole);
		prixTypePermis.setCreatedBy((CnprUser) session.getAttribute("loggedUser"));
		session.setAttribute("prixTypePermisAutoEcoleList", prixTypePermisAutoEcoleList);
		session.setAttribute("typePermisList", typePermisList);
		model.addAttribute("prixTypePermis", prixTypePermis);

		return "/autoEcole/prixTypeBrevet";
	}
	
	@GetMapping("/prixTypePermis/{id}/changeStatus")
	public String prixTypePermisChangeStatus(@PathVariable("id") long id, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		PrixTypePermisAutoEcole prixTypePermis = prixPermisTypeAutoServiceImpl.getPrixTypePermisAutoEcoleById(id);
		
		if (prixTypePermis.isActiveStatus() == false) {
			prixTypePermis.setActiveStatus(true);
		} else if (prixTypePermis.isActiveStatus() == true) {
			prixTypePermis.setActiveStatus(false);
		}
		this.prixPermisTypeAutoServiceImpl.saveOrUpdatePrixTypePermisAutoEcole(prixTypePermis);
		
		return "redirect:/autoEcole/prixTypePermis/"+id;
	}

	@PostMapping("/prixTypePermis/save")
	public String savePrixTypePermis(PrixTypePermisAutoEcole prixTypePermis, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		prixTypePermis.setCreatedBy((CnprUser) session.getAttribute("loggedUser"));
		PrixTypePermisAutoEcole saved = this.prixPermisTypeAutoServiceImpl
				.saveOrUpdatePrixTypePermisAutoEcole(prixTypePermis);
		if (saved != null) {
			redirectAttributes.addFlashAttribute("message", "Prix du type de brevet enregisté avec success");
			return "redirect:/autoEcole/prixTypePermis/" + saved.getAutoEcole().getId();
		}
		;
		redirectAttributes.addFlashAttribute("message", "Save failure");
		return "redirect:/autoEcole/view/"+prixTypePermis.getAutoEcole().getId();
	}

	@GetMapping("/vehicleType/{id}")
	public String vehicleType(@PathVariable("id") long id, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();

		CnprAutoEcole autoEcole = this.autoEcoleServiceImpl.getCnprAutoEcoleById(id);
		List<CnprVehiculeType> vehicleList = vehiculeTypeServiceImpl.getAllActiveCnprVehiculeType();
		List<CnprAutoEcoleVehiculeType> autoEcoleVehicleList = autoEcoleVehiculeTypeServiceImpl
				.getAllAutoEcoleVehiculeTypeByAutoEcoleId(id);
		CnprAutoEcoleVehiculeType autoEcoleVehicle = new CnprAutoEcoleVehiculeType();
		autoEcoleVehicle.setCnprAutoEcole(autoEcole);

		session.setAttribute("vehicleList", vehicleList);
		session.setAttribute("autoEcole", autoEcole);
		session.setAttribute("autoEcoleVehicleList", autoEcoleVehicleList);
		model.addAttribute("autoEcoleVehicle", autoEcoleVehicle);

		return "/autoEcole/typevehicle";
	}

	@GetMapping("/typePermis/{id}")
	public String typePermis(@PathVariable("id") long id, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();

		CnprAutoEcole autoEcole = this.autoEcoleServiceImpl.getCnprAutoEcoleById(id);
		List<TypePermis> typePermisList = typePermisServiceImpl.getAllActiveTypePermis();
		List<AutoEcoleTypePermis> autoEcoleTypePermisList = autoEcoleTypePermisServiceImpl
				.getAllAutoEcoleTypePermisByAutoEcoleid(id);
		AutoEcoleTypePermis autoEcoleTypePermis = new AutoEcoleTypePermis();
		autoEcoleTypePermis.setCnprAutoEcole(autoEcole);

		session.setAttribute("typePermisList", typePermisList);
		session.setAttribute("autoEcole", autoEcole);
		session.setAttribute("autoEcoleTypePermisList", autoEcoleTypePermisList);
		model.addAttribute("autoEcoleTypePermis", autoEcoleTypePermis);

		return "/autoEcole/typepermis";
	}

	@GetMapping("/document/{id}")
	public String document(@PathVariable("id") long id, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();

		CnprAutoEcole autoEcole = this.autoEcoleServiceImpl.getCnprAutoEcoleById(id);

		session.setAttribute("autoEcole", autoEcole);

		return "/autoEcole/document";
	}

	@GetMapping("/photo/{id}")
	public String photo(@PathVariable("id") long id, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();

		CnprAutoEcole autoEcole = this.autoEcoleServiceImpl.getCnprAutoEcoleById(id);

		session.setAttribute("autoEcole", autoEcole);

		return "/autoEcole/photo";
	}

	@GetMapping("/formateur/{id}")
	public String formateur(@PathVariable("id") long id, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Formateur formateur = new Formateur();
		List<Formateur> formateurList = this.formateurService.getAllFormateurByAuoEcoleId(id);

		CnprAutoEcole autoEcole = this.autoEcoleServiceImpl.getCnprAutoEcoleById(id);
		formateur.setCnprAutoEcole(autoEcole);

		session.setAttribute("autoEcole", autoEcole);
		session.setAttribute("formateurList", formateurList);
		model.addAttribute("formateur", formateur);

		return "/autoEcole/formateur";
	}

	@PostMapping("/addFormateur")
	public String addFormateur(Formateur formateur, RedirectAttributes redirectAttributes) {
		List<Formateur> formateurList = this.formateurService
				.getAllFormateurByAuoEcoleId(formateur.getCnprAutoEcole().getId());

		if (formateur.getCnprAutoEcole().getNbInstructeur() == formateurList.size()) {
			redirectAttributes.addFlashAttribute("message", "Vous avez atteint le nombre de formateur renseigné.");
			return "redirect:/autoEcole/formateur/" + formateur.getCnprAutoEcole().getId();
		} else {
			if (this.formateurService.saveOrUpdateFormateur(formateur)) {
				redirectAttributes.addFlashAttribute("message", "Formateur ajouté avec succes");
				return "redirect:/autoEcole/view/" + formateur.getCnprAutoEcole().getId();
			}
			;
		}

		redirectAttributes.addFlashAttribute("message", "Save failure");
		return "redirect:/autoEcole/view/" + formateur.getCnprAutoEcole().getId();
	}

	@GetMapping("/candidat/{id}")
	public String candidats(@PathVariable("id") long id, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Candidat candidat = new Candidat();
		List<Candidat> candidatList = this.candidatService.getAllCandidatByAutoEcoleId(id);

		CnprAutoEcole autoEcole = this.autoEcoleServiceImpl.getCnprAutoEcoleById(id);
		candidat.setCnprAutoEcole(autoEcole);
		candidat.setCreatedBy((CnprUser) session.getAttribute("loggedUser"));

		session.setAttribute("autoEcole", autoEcole);
		session.setAttribute("candidatList", candidatList);
		model.addAttribute("candidat", candidat);

		return "/autoEcole/candidat";
	}

	@PostMapping("/candidat/{id}/approveRecyclage")
	public String approveRecyclage(@PathVariable("id") long id, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Candidat candidat = candidatService.getCandidatById(id);
		candidat.setRecyclageValide(true);

		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		LocalDate currentdate = LocalDate.now();
		Month currentMonth = currentdate.getMonth();

		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int min = calendar.get(Calendar.MINUTE);
		int sec = calendar.get(Calendar.SECOND);

		String codeUnique = "" + year;// 202521133656
		String times = "" + currentMonth.getValue() + day + hour + min + sec;
		codeUnique = candidat.getCnprAutoEcole().getCodeUnique() + "-" + year + candidatService.shuffleString(times);
		candidat.setCodeUnique(codeUnique);

		// List<Candidat> candidatList =
		// this.candidatService.getAllCandidatByAutoEcoleId(id);

		// CnprAutoEcole autoEcole = this.autoEcoleServiceImpl.getCnprAutoEcoleById(id);
		// candidat.setCnprAutoEcole(autoEcole);
		// candidat.setCreatedBy((CnprUser) session.getAttribute("loggedUser"));

		// session.setAttribute("autoEcole", autoEcole);
		// session.setAttribute("candidatList", candidatList);
		candidatService.saveOrUpdateCandidat(candidat);

		return "redirect:/autoEcole/candidat/" + candidat.getCnprAutoEcole().getId();
	}

	@GetMapping("/candidat/{id}/view/{candidatId}")
	public String candidatsView(@PathVariable("id") long id, @PathVariable("candidatId") long candidatId, Model model,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		Candidat candidat = this.candidatService.getCandidatById(candidatId);

		session.setAttribute("candidat", candidat);
		model.addAttribute("candidat", candidat);

		return "/autoEcole/candidat-view";
	}

	@GetMapping("/candidat/{id}/edit/{candidatId}")
	public String candidatsEdit(@PathVariable("id") long id, @PathVariable("candidatId") long candidatId, Model model,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		Candidat candidat = this.candidatService.getCandidatById(candidatId);
		model.addAttribute("candidat", candidat);
		session.setAttribute("candidat", candidat);

		return "/autoEcole/candidat-edit";
	}

	@PostMapping("/addCandidat")
	public String addCandidat(Candidat candidat, RedirectAttributes redirectAttributes) {

		if (this.candidatService.saveOrUpdateCandidat(candidat)) {
			redirectAttributes.addFlashAttribute("message", "Candidat ajouté avec succes");
			return "redirect:/autoEcole/candidat/" + candidat.getCnprAutoEcole().getId();
		}
		;

		redirectAttributes.addFlashAttribute("message", "Save failure");
		return "redirect:/autoEcole/candidat";
	}

	@PostMapping("/editCandidat")
	public String editCandidat(Candidat candidat, RedirectAttributes redirectAttributes) {

		if (this.candidatService.saveOrUpdateCandidat(candidat)) {
			redirectAttributes.addFlashAttribute("message", "Candidat mis a jour avec succes");
			return "redirect:/autoEcole/candidat/" + candidat.getCnprAutoEcole().getId();
		}
		;

		redirectAttributes.addFlashAttribute("message", "Save failure");
		return "redirect:/autoEcole/candidat";
	}

	@GetMapping("/user/{id}")
	public String user(@PathVariable("id") long id, HttpServletRequest request) {
		HttpSession session = request.getSession();
		AutoEcoleUser autoEcoleUser = new AutoEcoleUser();

		List<AutoEcoleUser> autoEcoleUserList = this.autoEcoleUserServiceImpl.getAllAutoEcoleUserByAutoEcoleId(id);
		List<CnprRole> roleList = this.roleServiceImpl.getAllActiveCnprRole();
//		
		CnprAutoEcole autoEcole = this.autoEcoleServiceImpl.getCnprAutoEcoleById(id);
		autoEcoleUser.setCnprAutoEcole(autoEcole);
//		candidat.setCnprAutoEcole(autoEcole);
//		candidat.setCreatedBy((CnprUser)session.getAttribute("loggedUser"));
//		
		session.setAttribute("autoEcole", autoEcole);
		session.setAttribute("roleList", roleList);
		session.setAttribute("autoEcoleUserList", autoEcoleUserList);

		return "/autoEcole/user";
	}

	@PostMapping("/addUser")
	public String addUser(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		AutoEcoleUser autoEcoleUser = new AutoEcoleUser();

		final CnprUser cnprUser = new CnprUser();
		cnprUser.setNom(request.getParameter("nom"));
		cnprUser.setPostnom(request.getParameter("postnom"));
		cnprUser.setPrenom(request.getParameter("prenom"));

		boolean userExists = false;
		String newUsername = cnprUser.getNom().toLowerCase() + cnprUser.getPrenom().toLowerCase().charAt(0);
		CnprUser savedUser = userServiceImpl.getUserByUsername(newUsername);
		if (savedUser != null) {
			userExists = true;
		}
		if (!userExists) {
			// newUsername = user.getNom()+user.getPrenom().charAt(0);
			cnprUser.setUsername(newUsername);
		} else {
			List<CnprUser> userList = (List<CnprUser>) userServiceImpl.getAllCnprUser();
			long countUsernames = userList.stream()
					.filter(u -> u.getUsername()
							.equals(cnprUser.getNom().toLowerCase() + cnprUser.getPrenom().toLowerCase().charAt(0)))
					.count();

			newUsername = cnprUser.getNom().toLowerCase() + cnprUser.getPrenom().toLowerCase().charAt(0)
					+ countUsernames;
			cnprUser.setUsername(newUsername.toLowerCase());
		}

		CnprRole role = new CnprRole();
		role.setId(Long.parseLong(request.getParameter("role")));
		cnprUser.setRole(role);
		cnprUser.setSite(request.getParameter("site"));

		CnprUser returneddUser = userServiceImpl.saveOrAndReturnCnprUser(cnprUser);

		CnprAutoEcole autoEcole = new CnprAutoEcole();
		autoEcole.setId(Long.parseLong(request.getParameter("autoEcoleId")));
		autoEcoleUser.setCnprUser(returneddUser);
		autoEcoleUser.setCnprAutoEcole(autoEcole);

		CnprUser loggedUser = new CnprUser();
		loggedUser.setId(Long.parseLong(request.getParameter("loggedUserId")));
		autoEcoleUser.setCreatedBy(loggedUser);

		if (this.autoEcoleUserServiceImpl.saveOrUpdateAutoEcoleUser(autoEcoleUser)) {
			redirectAttributes.addFlashAttribute("message", "Utilisateur ajouté avec succes");
			return "redirect:/autoEcole/user/" + autoEcoleUser.getCnprAutoEcole().getId();
		}
		;
		redirectAttributes.addFlashAttribute("message", "Save failure");
		return "redirect:/autoEcole/user";
	}

	@PostMapping("/addVehicule")
	public String addVehicule(CnprAutoEcoleVehiculeType autoEcoleVehicule, RedirectAttributes redirectAttributes) {

		if (this.autoEcoleVehiculeTypeServiceImpl.saveOrUpdateAutoEcoleVehiculeType(autoEcoleVehicule)) {
			redirectAttributes.addFlashAttribute("message", "Vehicule ajouté avec succes");
			return "redirect:/autoEcole/vehicleType/" + autoEcoleVehicule.getCnprAutoEcole().getId();
		}
		;
		redirectAttributes.addFlashAttribute("message", "Save failure");
		return "redirect:/autoEcole/addVehicule";
	}

	@PostMapping("/addTypePermis")
	public String addTypePermis(AutoEcoleTypePermis autoEcoleTypePermis, RedirectAttributes redirectAttributes) {

		if (this.autoEcoleTypePermisServiceImpl.saveOrUpdateAutoEcoleTypePermis(autoEcoleTypePermis)) {
			redirectAttributes.addFlashAttribute("message", "Type de permis ajouté avec succes");
			return "redirect:/autoEcole/typePermis/" + autoEcoleTypePermis.getCnprAutoEcole().getId();
		}
		;
		redirectAttributes.addFlashAttribute("message", "Save failure");
		return "redirect:/autoEcole/typePermis";
	}

	@PostMapping("/addDocument")
	public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes,
			HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();

		CnprAutoEcole autoEcole = (CnprAutoEcole) session.getAttribute("autoEcole");

		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int min = calendar.get(Calendar.MINUTE);
		int sec = calendar.get(Calendar.SECOND);
		String uploadDir = day + "" + month + "" + year;

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		String os = System.getProperty("os.name").toLowerCase();
		if (os.contains("win")) {
			// Operating system is based on Windows

			uploadDir = "/cnpr/doc/" + uploadDir + "/" + autoEcole.getId();
		} else if (os.contains("osx")) {
			// Operating system is Apple OSX based
		} else if (os.contains("nix") || os.contains("aix") || os.contains("nux")) {
			// Operating system is based on Linux/Unix/*AIX
			// uploadDir = "/sirh-doc/instructions/"+savedInstruction.getId();
			uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/documents/" + uploadDir + "/"
					+ autoEcole.getId();
		}

		Path uploadPath = null;
		Path filePath = null;
		String extension = "";
		uploadPath = Paths.get(uploadDir);
		filePath = uploadPath.resolve(fileName);
		extension = (filePath.toString()).substring((filePath.toString()).lastIndexOf(".") + 1);
		String documentNewName = request.getParameter("document_name");
		File oldFileName = new File(fileName);
		File newFileName = new File(documentNewName + "." + extension);
		boolean flag = oldFileName.renameTo(newFileName);
		autoEcole.setDocuments(newFileName.getName());

		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

		try {
			InputStream inputStream = file.getInputStream();
			// Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

			Files.move(filePath, filePath.resolveSibling(request.getParameter("document_name") + "." + extension),
					StandardCopyOption.REPLACE_EXISTING);

		} catch (IOException e) {
			throw new IOException("Impossible de charger le fichier " + fileName);
		}

		if (this.autoEcoleServiceImpl.saveOrUpdateCnprAutoEcole(autoEcole)) {
			redirectAttributes.addFlashAttribute("message", "Doucment ajouté avec succes");
			return "redirect:/autoEcole/view/" + autoEcole.getId();
		}
		;
		redirectAttributes.addFlashAttribute("message", "Save failure");
		return "redirect:/autoEcole/view/" + autoEcole.getId();
	}

	@PostMapping("/addPhoto")
	public String addPhoto(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes,
			HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();

		CnprAutoEcole autoEcole = (CnprAutoEcole) session.getAttribute("autoEcole");

		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int min = calendar.get(Calendar.MINUTE);
		int sec = calendar.get(Calendar.SECOND);
		String uploadDir = day + "" + month + "" + year;

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		String os = System.getProperty("os.name").toLowerCase();
		if (os.contains("win")) {
			// Operating system is based on Windows

			uploadDir = "/cnpr/image/" + uploadDir + "/" + autoEcole.getId();
		} else if (os.contains("osx")) {
			// Operating system is Apple OSX based
		} else if (os.contains("nix") || os.contains("aix") || os.contains("nux")) {
			// Operating system is based on Linux/Unix/*AIX
			// uploadDir = "/sirh-doc/instructions/"+savedInstruction.getId();
			uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/photo/" + autoEcole.getId();
		}

		Path uploadPath = null;
		Path filePath = null;
		String extension = "";
		uploadPath = Paths.get(uploadDir);
		filePath = uploadPath.resolve(fileName);
		extension = (filePath.toString()).substring((filePath.toString()).lastIndexOf(".") + 1);
		String documentNewName = request.getParameter("photoEcole");
		File oldFileName = new File(fileName);
		File newFileName = new File(documentNewName + "." + extension);
		boolean flag = oldFileName.renameTo(newFileName);
		autoEcole.setPhotoEcole(newFileName.getName());

		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

		try {
			InputStream inputStream = file.getInputStream();
			// Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

			Files.move(filePath, filePath.resolveSibling(request.getParameter("photoEcole") + "." + extension),
					StandardCopyOption.REPLACE_EXISTING);

		} catch (IOException e) {
			throw new IOException("Impossible de charger le fichier " + fileName);
		}

		if (this.autoEcoleServiceImpl.saveOrUpdateCnprAutoEcole(autoEcole)) {
			redirectAttributes.addFlashAttribute("message", "Photo ajouté avec succes");
			return "redirect:/autoEcole/view/" + autoEcole.getId();
		}
		;
		redirectAttributes.addFlashAttribute("message", "Save failure");
		return "redirect:/autoEcole/view/" + autoEcole.getId();
	}

	@RequestMapping(value = "/viewDoc/{id}", method = RequestMethod.GET)
	public void getFile(HttpServletResponse response, @PathVariable("id") long id, HttpServletRequest request)
			throws IOException {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int min = calendar.get(Calendar.MINUTE);
		String uploadDir = day + "" + month + "" + year;

		String os = System.getProperty("os.name").toLowerCase();
		if (os.contains("win")) {
			// Operating system is based on Windows
			uploadDir = "/cnpr/doc/" + uploadDir + "/";
		} else if (os.contains("osx")) {
			// Operating system is Apple OSX based
		} else if (os.contains("nix") || os.contains("aix") || os.contains("nux")) {
			// Operating system is based on Linux/Unix/*AIX
			// uploadDir = "/sirh-doc/instructions/"+savedInstruction.getId();
			uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/documents/" + uploadDir + "/" + id
					+ "/";
		}

		// String dir = System.getProperty("user.dir") +
		// "/src/main/resources/static/documents/instruction/";
		// String UPLOAD_DIRECTORY = "static/documents";
		CnprAutoEcole autoEcole = autoEcoleServiceImpl.getCnprAutoEcoleById(id);

		response.setContentType("application/pdf");

		InputStream is = new FileInputStream(new File(uploadDir + "/" + autoEcole.getDocuments()));
		;
		IOUtils.copy(is, response.getOutputStream());
		int nread;
		while ((nread = is.read()) != -1) {
			response.getWriter().write(nread);
		}

	}

	@GetMapping("/downloadXls")
	public void exportIntoExcelFile(HttpServletResponse response, HttpServletRequest request) throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		HttpSession session = request.getSession();
		session = request.getSession();
		session.removeAttribute("searchDepartement");
		session.removeAttribute("searchNames");

		Map<String, String> mapMonths = new HashMap<>();
		mapMonths.put("JANUARY", "JANVIER");
		mapMonths.put("FEBRUARY", "FEVRIER");
		mapMonths.put("MARCH", "MARS");
		mapMonths.put("APRIL", "AVRIL");
		mapMonths.put("MAY", "MAI");
		mapMonths.put("JUNE", "JUIN");
		mapMonths.put("JULY", "JUILLET");
		mapMonths.put("AUGUST", "AOUT");
		mapMonths.put("SEPTEMBER", "SEPTEMBRE");
		mapMonths.put("OCTOBER", "OCTOBRE");
		mapMonths.put("NOVEMBER", "NOVEMBRE");
		mapMonths.put("DECEMBER", "DECEMBRE");

		LocalDate currentdate = LocalDate.now();
		Month currentMonth = currentdate.getMonth();

		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);

		String xlsFile = "auto-ecole-" + mapMonths.get(currentMonth.name()) + "-" + year + ".xlsx";

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=" + xlsFile;
		response.setHeader(headerKey, headerValue);

		@SuppressWarnings("unchecked")
		List<CnprAutoEcole> listPointage = new ArrayList<CnprAutoEcole>(); // (List<CnprAutoEcole>)
																			// session.getAttribute("timeSheetViewList");
		listPointage = this.autoEcoleServiceImpl.getAllCnprAutoEcole();
		;

		AutoEcoleExcelGenerator generator2 = new AutoEcoleExcelGenerator("", listPointage);

		generator2.generateExcelFile(response);

	}

	@GetMapping("/downloadPdf")
	public void downloadReportAutoEcole(HttpServletResponse response, HttpServletRequest request)
			throws DocumentException, IOException {
		response.setContentType("application/pdf");
		HttpSession session = request.getSession();

		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		Map<String, String> mapMonths = new HashMap<>();
		mapMonths.put("JANUARY", "JANVIER");
		mapMonths.put("FEBRUARY", "FEVRIER");
		mapMonths.put("MARCH", "MARS");
		mapMonths.put("APRIL", "AVRIL");
		mapMonths.put("MAY", "MAI");
		mapMonths.put("JUNE", "JUIN");
		mapMonths.put("JULY", "JUILLET");
		mapMonths.put("AUGUST", "AOUT");
		mapMonths.put("SEPTEMBER", "SEPTEMBRE");
		mapMonths.put("OCTOBER", "OCTOBRE");
		mapMonths.put("NOVEMBER", "NOVEMBRE");
		mapMonths.put("DECEMBER", "DECEMBRE");
		String departementName = "COMMISSION NATIONALE DES PREVENTIONS\n" + "ROUTIERES";

		LocalDate currentdate = LocalDate.now();
		Month currentMonth = currentdate.getMonth();

		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=autoecole_" + mapMonths.get(currentMonth.name()) + "-" + year
				+ ".pdf";
		response.setHeader(headerKey, headerValue);

		@SuppressWarnings("unchecked")
		List<CnprAutoEcole> listeAutoEcole = this.autoEcoleServiceImpl.getAllCnprAutoEcole();
		// session.setAttribute("title", "MISE JOUR PAIE PNC ACTIFS");

		PdfUtilsAutoEcole exporter = new PdfUtilsAutoEcole(listeAutoEcole,
				"LISTE DES AUTO ECOLES IDENTIFIEES " + mapMonths.get(currentMonth.name()) + "-" + year, departementName,
				"");
		exporter.export(response);

	}

	@PostMapping("/searchCodeUnique")
	public String searchCodeUnique(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("searchProvince");
		session.removeAttribute("searchDistrict");
		session.removeAttribute("searchCommune");
		session.removeAttribute("searchCodeUnique");
		String codeUnique = request.getParameter("codeUnique");
		List<CnprAutoEcole> foundAutoEcoleList = null;
		if (codeUnique.trim().length() == 0) {
			// System.out.println("Code unique size: "+codeUnique.trim().length());
			foundAutoEcoleList = this.autoEcoleServiceImpl.getAllCnprAutoEcole();
		} else {
			foundAutoEcoleList = this.autoEcoleServiceImpl.getAllCnprAutoEcoleByCodeUnique(codeUnique);
			session.setAttribute("searchCodeUnique", codeUnique);
		}
		session.setAttribute("autoEcoleList", foundAutoEcoleList);
		return "autoEcole/list";
	}

	@PostMapping("/searchByProvinceOrDistrictOrCommune")
	public String searchByProvinceOrDistrictOrCommune(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("searchProvince");
		session.removeAttribute("searchDistrict");
		session.removeAttribute("searchCommune");
		session.removeAttribute("searchCodeUnique");
		long province = 0;
		long commune = 0;
		long district = 0;

		List<CnprAutoEcole> foundAutoEcoleList = null;
		if (province == -1 && (request.getParameter("district") == null && request.getParameter("commune") == null)) {// All
																														// is
																														// null
			foundAutoEcoleList = this.autoEcoleServiceImpl.getAllCnprAutoEcole();
		} else if (province != -1 && (Long.parseLong(request.getParameter("district"))) == -1) {// Search by province
																								// only
			province = Long.parseLong(request.getParameter("province"));
			foundAutoEcoleList = this.autoEcoleServiceImpl.getAllCnprAutoEcoleByProvince(province);
		} else if (province != -1 && (Long.parseLong(request.getParameter("district"))) != -1) {// Search by province
																								// and district only
			province = Long.parseLong(request.getParameter("province"));
			district = Long.parseLong(request.getParameter("district"));
			foundAutoEcoleList = this.autoEcoleServiceImpl.getAllCnprAutoEcoleByProvinceOrDistrict(province, district);
		} else if (province != -1 && (Long.parseLong(request.getParameter("district"))) != -1
				&& (Long.parseLong(request.getParameter("district"))) != -1) {// Search by province and district only
			province = Long.parseLong(request.getParameter("province"));
			district = Long.parseLong(request.getParameter("district"));
			foundAutoEcoleList = this.autoEcoleServiceImpl.getAllCnprAutoEcoleByProvinceOrDistrict(province, district);
		}

		System.out.println("Province : " + request.getParameter("province"));
		System.out.println("District : " + request.getParameter("district"));
		System.out.println("Commune : " + request.getParameter("commune"));

		session.setAttribute("autoEcoleList", foundAutoEcoleList);
		return "autoEcole/list";
	}

}
