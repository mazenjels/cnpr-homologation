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

import com.cnpr.homologation.models.Commune;
import com.cnpr.homologation.models.District;
import com.cnpr.homologation.models.Province;
import com.cnpr.homologation.service.ProvinceServiceImpl;
import com.cnpr.homologation.service.CommuneServiceImpl;
import com.cnpr.homologation.service.DistrictServiceImpl;

@Controller
@RequestMapping(value = "/province")
public class ProvinceController {

	@Autowired
	ProvinceServiceImpl provinceServiceImpl;

	@Autowired
	DistrictServiceImpl districtServiceImpl;

	@Autowired
	CommuneServiceImpl communeServiceImpl;

	@GetMapping({ "/list", "/province/list" })
	public String viewPersonnelList(Model model, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int size, HttpServletRequest request,
			@ModelAttribute("message") String message) {
		Pageable pageable = PageRequest.of(page - 1, size);
		Page<Province> pageQuestion = provinceServiceImpl.getAllPageable(pageable);

		List<Province> provinceList = pageQuestion.getContent();

		model.addAttribute("provinceList", provinceList);
		model.addAttribute("currentPage", pageQuestion.getNumber() + 1);
		model.addAttribute("totalItems", pageQuestion.getTotalElements());
		model.addAttribute("totalPages", pageQuestion.getTotalPages());
		model.addAttribute("pageSize", size);
		model.addAttribute("message", message);

		return "province/list";
	}

	@GetMapping("/new")
	public String viewDocumentNew(@ModelAttribute("message") String message, Model model) {
		Province province = new Province();

		model.addAttribute("province", province);
		model.addAttribute("message", message);

		return "province/new";
	}

	@PostMapping("/save")
	public String save(Province province, RedirectAttributes redirectAttributes) {

		if (this.provinceServiceImpl.saveOrUpdateProvince(province)) {
			redirectAttributes.addFlashAttribute("message", "Save success");
			return "redirect:/province/list";
		}
		;
		redirectAttributes.addFlashAttribute("message", "Save failure");
		return "redirect:/province/new";
	}

	@PostMapping("/edit")
	public String editBanque(Province province, RedirectAttributes redirectAttributes, HttpServletRequest request) {

		if (this.provinceServiceImpl.saveOrUpdateProvince(province)) {
			redirectAttributes.addFlashAttribute("message", "Edit success");
			return "redirect:/admin/province/list";
		}
		;
		redirectAttributes.addFlashAttribute("message", "Edit failure");
		return "redirect:/admin/province/edit/" + province.getId();
	}

//	@GetMapping("/delete/{id}")
//	public String deleteDocument(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
//
//		if (this.provinceServiceImpl.deleteDocumentById(id)) {
//			redirectAttributes.addFlashAttribute("message", "Delete success");
//			return "redirect:/document/list";
//		}
//		;
//		redirectAttributes.addFlashAttribute("message", "Delete failure");
//		return "redirect:/document/list";
//	}

	@GetMapping("/changeStatus/{id}")
	public String enableDisableDocument(@PathVariable("id") long id, Model model) {

		Province document = this.provinceServiceImpl.getProvinceById(id);

		if (document.isActiveStatus() == false) {
			document.setActiveStatus(true);
		} else if (document.isActiveStatus() == true) {
			document.setActiveStatus(false);
		}
		this.provinceServiceImpl.saveOrUpdateProvince(document);

		return "redirect:/province/list";
	}

	@GetMapping("/edit/{id}")
	public String showFormEditDocument(@PathVariable("id") long id, Model model) {
		Province province = this.provinceServiceImpl.getProvinceById(id);
		model.addAttribute("province", province);
		return "/province/edit";
	}

	@GetMapping("/{provinceId}/changeStatus/{districtId}")
	public String enableDisableDistrictProvince(@PathVariable("districtId") long provinceId,
			@PathVariable("districtId") long districtId, Model model) {

		District district = this.districtServiceImpl.getDistrictById(districtId);

		if (district.isActiveStatus() == false) {
			district.setActiveStatus(true);
		} else if (district.isActiveStatus() == true) {
			district.setActiveStatus(false);
		}
		this.districtServiceImpl.saveOrUpdateDistrict(district);

		return "redirect:/province/" + district.getProvince().getId() + "/districts";
	}

	@GetMapping("/{id}/districts")
	public String listProvinceDistrict(@PathVariable("id") long id, HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		Province province = this.provinceServiceImpl.getProvinceById(id);
		// List<District> districtList = districtService.getAllDistrict();
		// List<Long> provinceIds = List.of(Long.valueOf(id)) ;
		List<District> districtList = districtServiceImpl.getAllDistrictByProvinceId(id);
		model.addAttribute("districtList", districtList);
		session.setAttribute("province", province);
		return "/province/list_districts";
	}

	@GetMapping("/{id}/districts/new")
	public String viewProvinceAddDistrict(@ModelAttribute("message") String message, Model model,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		Province province = (Province) session.getAttribute("province");
		District district = new District();
		district.setProvince(province);
		model.addAttribute("district", district);
		session.setAttribute("province", province);
		model.addAttribute("province", province);
		model.addAttribute("message", message);

		return "province/new_district";
	}

	@PostMapping("/{id}/districts/save")
	public String saveProvinceDistrict(District district, RedirectAttributes redirectAttributes) {
		// System.out.println("Selected Province Id : "+district.getProvince().getId());
		if (this.districtServiceImpl.saveOrUpdateDistrict(district)) {
			redirectAttributes.addFlashAttribute("message", "Save success");
			return "redirect:/province/" + district.getProvince().getId() + "/districts";
		}
		;
		redirectAttributes.addFlashAttribute("message", "Save failure");
		return "redirect:/province/new_district";
	}

	@GetMapping("/{provinceId}/district/{districtId}/communes")
	public String listProvinceDistrictCommunes(@PathVariable("provinceId") long provinceId,
			@PathVariable("districtId") long districtId, HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		District district = this.districtServiceImpl.getDistrictById(districtId);
		// List<District> districtList = districtService.getAllDistrict();
		// List<Long> provinceIds = List.of(Long.valueOf(id)) ;
		List<Commune> communeList = communeServiceImpl.getAllCommuneByDistrictId(districtId);
		model.addAttribute("communeList", communeList);
		session.setAttribute("district", district);
		return "/province/list_communes";
	}

	@GetMapping("/{provinceId}/district/{districtId}/commune/new")
	public String newCommune(@PathVariable("provinceId") long provinceId, @PathVariable("districtId") long districtId,
			@ModelAttribute("message") String message, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Province province = (Province) session.getAttribute("province");
		Commune commune = new Commune();
		District district = districtServiceImpl.getDistrictById(districtId);
		commune.setDistrict(district);

		district.setProvince(province);
		model.addAttribute("commune", commune);
		model.addAttribute("message", message);

		return "province/new_commune";
	}

//	@GetMapping("/{provinceId}/district/{districtId}/changeStatus/{communeId}")
//	public String enableDisableCommune(@PathVariable("districtId") long provinceId,@PathVariable("districtId") long districtId, Model model) {
//
//		District district = this.districtServiceImpl.getDistrictById(districtId);
//
//		if (district.isActiveStatus() == false) {
//			district.setActiveStatus(true);
//		} else if (district.isActiveStatus() == true) {
//			district.setActiveStatus(false);
//		}
//		this.districtServiceImpl.saveOrUpdateDistrict(district);
//
//		return "redirect:/province/"+district.getProvince().getId()+"/districts";
//	}

}
