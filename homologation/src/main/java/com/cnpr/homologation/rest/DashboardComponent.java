package com.cnpr.homologation.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cnpr.homologation.service.AutoEcoleServiceImpl;
import com.cnpr.homologation.service.CommuneServiceImpl;
import com.cnpr.homologation.service.DistrictServiceImpl;
import com.cnpr.homologation.service.LessonModuleServiceImpl;
import com.cnpr.homologation.service.ModuleServiceImpl;
import com.cnpr.homologation.service.PermissionServiceImpl;
import com.cnpr.homologation.service.ProvinceServiceImpl;

@RestController
@CrossOrigin
public class DashboardComponent {

	@Autowired
	AutoEcoleServiceImpl autoEcoleServiceImpl;
	
	@Autowired
	DistrictServiceImpl districtServiceImpl;
	
	@Autowired
	ModuleServiceImpl moduleServiceImpl;
	
	@Autowired
	LessonModuleServiceImpl lessonModuleServiceImpl;
	
	@Autowired
	PermissionServiceImpl permissionServiceImpl;
	
	@Autowired
	CommuneServiceImpl communeServiceImpl;
	
	@Autowired
	ProvinceServiceImpl provinceServiceImpl;
	
	@GetMapping("/cnpr/api/dashboard-summary")
	@CrossOrigin
	public String summary(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String effectifTotal="";
		//if (session.getAttribute("loggedUser") != null) {
		 effectifTotal = autoEcoleServiceImpl.getDashboardSummary();
		//}
		return effectifTotal;
	}
	
	@GetMapping("/api/provinceList")
	@CrossOrigin
	public String viewProvinceList() {
		String provinceList = provinceServiceImpl.getProvinceList();
		return provinceList;
	}
	
	@RequestMapping(value = "/api/districtByProvince", method = RequestMethod.GET)
	@ResponseBody
	@CrossOrigin
	public String districtByProvince(@RequestParam String provinceId) {
		String districtListByProvince = districtServiceImpl.districtByProvinceId(provinceId);

		return districtListByProvince;
	}
	
	@RequestMapping(value = "/api/communeByDistrict", method = RequestMethod.GET)
	@ResponseBody
	@CrossOrigin
	public String communeByDistrict(@RequestParam String districtId) {
		String communeByDistrict = communeServiceImpl.communeByDistrictId(districtId);

		return communeByDistrict;
	}
	
	@RequestMapping(value = "/api/permissionByModule", method = RequestMethod.GET)
	@ResponseBody
	@CrossOrigin
	public String permissionByModule(@RequestParam String moduleShortCode) {
		String permissionListByModule = permissionServiceImpl.permissionByModule(moduleShortCode);

		return permissionListByModule;
	}
	
	@RequestMapping(value = "/api/moduleByLesson", method = RequestMethod.GET)
	@ResponseBody
	@CrossOrigin
	public String moduleByLesson(@RequestParam String lessonId) {
		String moduleListByLesson = lessonModuleServiceImpl.getAllActiveCnprLessonModuleByLessonId(Long.parseLong(lessonId));

		return moduleListByLesson;
	}
}
