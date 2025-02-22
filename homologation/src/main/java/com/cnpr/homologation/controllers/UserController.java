package com.cnpr.homologation.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cnpr.homologation.models.AutoEcoleUser;
import com.cnpr.homologation.models.CnprAutoEcole;
import com.cnpr.homologation.models.CnprRole;
import com.cnpr.homologation.models.CnprUser;
import com.cnpr.homologation.models.ViewUser;
import com.cnpr.homologation.service.AutoEcoleUserServiceImpl;
import com.cnpr.homologation.service.RoleServiceImpl;
import com.cnpr.homologation.service.UserServiceImpl;

@Controller
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	UserServiceImpl userService;
	
	@Autowired
	RoleServiceImpl roleService;
	
	@Autowired
	AutoEcoleUserServiceImpl autoEcoleUserServiceImpl;

	
	@GetMapping({"/list", "/user/list"})
	public String  viewPersonnelList(@ModelAttribute("message") String message, Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		  List<CnprUser> userList = userService.getAllCnprUser();
		  List<CnprRole> roleList = roleService.getAllActiveCnprRole();
		  model.addAttribute("userList", userList);
		  model.addAttribute("message", message);
		  session.setAttribute("roleList", roleList);
		
		return "user/list";
	}
	
	@GetMapping("/userdetails")
    public String userDetails(Model model, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("username", username);
        }
        return "user/userdetails"; // Name of the JSP page
    }
	
	@GetMapping("/new")
	public String viewDocumentNew(@ModelAttribute("message") String message, Model model) {
		CnprUser user = new CnprUser();
		
		model.addAttribute("user", user);
		model.addAttribute("message", message);
		
		return "user/new";
	}
	
	@PostMapping("/save")
	public String saveDocument(CnprUser user, RedirectAttributes redirectAttributes) {
		
		boolean userExists = false;
		String newUsername = user.getNom().toLowerCase()+user.getPrenom().toLowerCase().charAt(0);
		CnprUser savedUser = userService.getUserByUsername(newUsername);
		if(savedUser != null) {
			userExists = true;
		}
		if(!userExists) {
			//newUsername = user.getNom()+user.getPrenom().charAt(0);
			user.setUsername(newUsername);
		}else {
			List<CnprUser> userList = (List<CnprUser>) userService.getAllCnprUser();
			long countUsernames = userList.stream().filter(u-> u.getUsername().equals(user.getNom().toLowerCase()+user.getPrenom().toLowerCase().charAt(0))).count();
			
			newUsername = user.getNom().toLowerCase()+user.getPrenom().toLowerCase().charAt(0)+countUsernames;
			user.setUsername(newUsername.toLowerCase());
		}
		if(this.userService.saveOrUpdateCnprUser(user)) {
			redirectAttributes.addFlashAttribute("message", "Save success");
			return "redirect:/user/list";
		};
		redirectAttributes.addFlashAttribute("message", "Save failure");
		return "redirect:/user/new";
	}
	
	@PostMapping("/edit")
	public String editBanque(CnprUser user, RedirectAttributes redirectAttributes,HttpServletRequest request) {

		if (this.userService.saveOrUpdateCnprUser(user)) {
			redirectAttributes.addFlashAttribute("message", "Edit success");
			return "redirect:/user/list";
		}
		;
		redirectAttributes.addFlashAttribute("message", "Edit failure");
		return "redirect:/user/edit/" + user.getId();
	}
	
	@PostMapping("/changeDefaultPassword")
	public String changeDefaultPassword(RedirectAttributes redirectAttributes,HttpServletRequest request) {
		//System.out.println("Creating new user...");
		// System.out.println("Selected role : "+createUser.getRole().getName());
		//user.setPassword(user.getUsername());
		//String generatedPassword = request.getParameter("text-genarated-password");
		String newPassword = request.getParameter("newPassword");
		String confirmNewPassword = request.getParameter("confirmNewPassword");
		
		BCryptPasswordEncoder passwordEncoder =  new BCryptPasswordEncoder();
		String passwordBCrypted = passwordEncoder.encode(confirmNewPassword.trim());
		
		ViewUser user  = (ViewUser) request.getSession().getAttribute("loggedUser");
		
			if(userService.updatePassword(passwordBCrypted,user.getUsername())==1) {
				//redirectAttributes.addFlashAttribute("message", "Le mot de passe de l'utilisateur "+username +"a été reinitialisé.");
				return "redirect:/";
			}
		
		//redirectAttributes.addFlashAttribute("message", "Echec de modification du mot de passe");
		return "redirect:/";
	}
//	@GetMapping("/delete/{id}")
//	public String deleteDocument(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
//
//		if (this.userService.deleteDocumentById(id)) {
//			redirectAttributes.addFlashAttribute("message", "Delete success");
//			return "redirect:/user/list";
//		}
//		;
//		redirectAttributes.addFlashAttribute("message", "Delete failure");
//		return "redirect:/user/list";
//	}
	
	@GetMapping("/changeStatus/{id}")
	public String enableDisableDocument(@PathVariable("id") long id, Model model) {

		CnprUser user = this.userService.getCnprUserById(id);

		if (user.isActiveStatus() == false) {
			user.setActiveStatus(true);
			user.setAccountLocked(false);
		} else if (user.isActiveStatus() == true) {
			user.setActiveStatus(false);
			user.setAccountLocked(true);
		}
		this.userService.saveOrUpdateCnprUser(user);

		return "redirect:/user/list";
	}	

	@GetMapping("/edit/{id}")
	public String showFormEditDocument(@PathVariable("id") long id, Model model) {
		CnprUser user = this.userService.getCnprUserById(id);
		model.addAttribute("user", user);
		return "/user/edit";
	}
	
	@GetMapping("/autoecole/{id}")
	public String view(@PathVariable("id") long id, Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) {
		HttpSession session= request.getSession();

		List<AutoEcoleUser> autoEcoleUserList = autoEcoleUserServiceImpl.getAutoEcoleUserByUserId(id);
		
		if(autoEcoleUserList == null || autoEcoleUserList.size()==0) {
			redirectAttributes.addFlashAttribute("message", "L'utilisateur n'est affilié au aucune auto école");
			return "redirect:/user/list";
		}
		CnprAutoEcole autoEcole = autoEcoleUserList.get(0).getCnprAutoEcole();
		CnprUser user = autoEcoleUserList.get(0).getCnprUser();
		
		session.setAttribute("user", user);
		session.setAttribute("autoEcole", autoEcole);

		
		return "/user/autoecole";
	}
}
