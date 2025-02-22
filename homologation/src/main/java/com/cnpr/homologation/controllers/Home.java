package com.cnpr.homologation.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.cnpr.homologation.models.AutoEcoleUser;
import com.cnpr.homologation.models.CnprUser;
import com.cnpr.homologation.models.Login;
import com.cnpr.homologation.models.RolePermission;
import com.cnpr.homologation.models.ViewUser;
import com.cnpr.homologation.repository.ViewUserRepository;
import com.cnpr.homologation.service.AutoEcoleUserServiceImpl;
import com.cnpr.homologation.service.RolePermissionServiceImpl;


@Controller
public class Home {

	HttpSession session;

	

	
	
	@Autowired
	ViewUserRepository userRepo;
	
	@Autowired
	RolePermissionServiceImpl rolePermissionService;
	
	@Autowired
	AutoEcoleUserServiceImpl autoEcoleUserServiceImpl;
	
//	@Autowired
//	GlobalModuleServiceImpl globalModuleServiceImpl;

	@GetMapping({ "/", "/login" })
	public String login(@RequestParam(value = "name", defaultValue = "World", required = true) String name,
			Model model) {

		return "login";
	}
	
	@GetMapping(value = "/dashboard")
	public String dashboard(@RequestParam(value = "name", defaultValue = "World", required = true) String name,
			Model model, Authentication authentication,HttpServletRequest request) {
		HttpSession session = request.getSession();
		
//		List<GlobalModule> activeGlobalModule = globalModuleServiceImpl.getAllActiveGlobalModule();
		
		Map<String,Boolean> mapGlobalModule =  new HashMap<>();
		
		
		
//		activeGlobalModule.forEach(e->mapGlobalModule.put(e.getDesignation(), e.isActiveStatus()));
		session.setAttribute("mapGlobalModule", mapGlobalModule);
//
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		ViewUser userRole = userRepo.getRoleByUsername(userDetails.getUsername());
		ViewUser user = userRepo.findByUsername(userDetails.getUsername());
		CnprUser cnprUser = new CnprUser();
		cnprUser.setId(user.getId());
		BCryptPasswordEncoder passwordEncoder =  new BCryptPasswordEncoder();
		
		String usernameBcrypted = passwordEncoder.encode(userDetails.getUsername());
		String passwordBcrypted = userDetails.getPassword();
		
		boolean samePwd = BCrypt.checkpw(userDetails.getUsername(),passwordBcrypted);//BCrypt.checkpw(passwordBCrypted,user.getPassword());
		
		if(samePwd) {
			session.setAttribute("loggedUser", user);
			session.setAttribute("authenticated",true);
			return "user/reinitPwd"; 
		}else {
			
		}
	   // model.addAttribute("username", userDetails.getUsername());
	   
	    List<RolePermission> rolePermissions = this.rolePermissionService.getAllRolePermissionByRoleName(userRole.getRole());
	    List<AutoEcoleUser> autoEcoleUserList = this.autoEcoleUserServiceImpl.getAutoEcoleUserByLoggedUserId(user.getId());
	    Map<String,Boolean> mapPermission = new HashMap<>();
	    System.out.println("autoEcoleUserList : "+autoEcoleUserList.size());
	    System.out.println("loggedUser : "+user.getId()+", username : "+user.getUsername());
	    if(autoEcoleUserList.size()>0) {
	    	AutoEcoleUser autoEcoleUser = autoEcoleUserList.get(0);
	    	
	    	session.setAttribute("autoEcoleUser", autoEcoleUser);
	    }
		
		rolePermissions.forEach(rp->mapPermission.put(rp.getPermission().getShortCode(), rp.getPermission().isActiveStatus()));
	  //  System.out.println("username : "+userDetails.getUsername());
		session.setAttribute("loggedUser", cnprUser);
	    session.setAttribute("username", userDetails.getUsername());
	    session.setAttribute("loggedUserRole", userRole.getRole());
	    session.setAttribute("loggedUserPermission", mapPermission);
	    model.addAttribute("roles", userDetails.getAuthorities());
	    
//	//	return "login";
		return "dashboard";
	}

	@GetMapping(value = "/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		session.removeAttribute("searchDepartement");
		session.removeAttribute("searchNames");
		if (session != null) {
			session.invalidate();
		}
		return "redirect:/"; // Where you go after logout here.
	}

	@GetMapping("/index")
	public String index(HttpServletRequest request) {
//		HttpSession session = request.getSession();
//		session.removeAttribute("searchDepartement");
//		session.removeAttribute("searchNames");
//		List<Departement> departement = this.departementServiceImpl.getAllActiveDepartement();
//		session.setAttribute("departementList", departement);
		return "dashboard";
	}
	
	
	@PostMapping(path = "/login", params = "action=login")
	public String login(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("uploadedTimeSheetList");
		session.removeAttribute("searchDepartement");
		session.removeAttribute("searchNames");
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		Login login = new Login();
		if (username.equalsIgnoreCase("cnpr") && password.equals("12345")) {
			return "redirect:/index";
		}

		return "redirect:/";
	}

	
	
	
	
	
}
