package com.cnpr.homologation.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cnpr.homologation.models.CnprVehiculeType;
import com.cnpr.homologation.models.Permission;
import com.cnpr.homologation.models.TypePermis;
import com.cnpr.homologation.models.CnprLesson;
import com.cnpr.homologation.models.CnprLessonModule;
import com.cnpr.homologation.models.CnprQuestion;
import com.cnpr.homologation.models.CnprQuestionResponse;
import com.cnpr.homologation.models.CnprUser;
import com.cnpr.homologation.service.QuestionServiceImpl;
import com.cnpr.homologation.service.LessonModuleServiceImpl;
import com.cnpr.homologation.service.LessonServiceImpl;
import com.cnpr.homologation.service.PermisTypeServiceImpl;
import com.cnpr.homologation.service.QuestionReponseServiceImpl;

@Controller
@RequestMapping(value = "/question")
public class QuestionController {

	@Autowired
	QuestionServiceImpl questionServiceImpl;

	@Autowired
	QuestionReponseServiceImpl questionReponseServiceImpl;

	@Autowired
	PermisTypeServiceImpl permisTypeServiceImpl;

	@Autowired
	LessonServiceImpl lessonServiceImpl;

	@Autowired
	LessonModuleServiceImpl lessonModuleServiceImpl;

	@GetMapping({ "/list", "/question/list" })
	public String viewPersonnelList(Model model, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "5") int size, HttpServletRequest request,
			@ModelAttribute("message") String message) {
		HttpSession session = request.getSession();
		Pageable pageable = PageRequest.of(page - 1, size);
		Page<CnprQuestion> pageQuestion= questionServiceImpl.getAllPageable(pageable);
		
		List<CnprQuestion> questionList =  pageQuestion.getContent(); //questionServiceImpl.getAllCnprQuestion();
		model.addAttribute("questionList", questionList);
		model.addAttribute("currentPage", pageQuestion.getNumber() + 1);
		model.addAttribute("totalItems", pageQuestion.getTotalElements());
		model.addAttribute("totalPages", pageQuestion.getTotalPages());
		model.addAttribute("pageSize", size);
		model.addAttribute("message", message);

		session.removeAttribute("queryResult");

		return "question/list";
	}

	@GetMapping("/apercu")
	public String apercu(Model model, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "5") int size, HttpServletRequest request,
			@ModelAttribute("message") String message) {
		HttpSession session = request.getSession();

		Pageable pageable = PageRequest.of(page - 1, size);
		Page<CnprQuestion> pageQuestion= questionServiceImpl.getAllPageable(pageable);
		//System.out.println("pageQuestion size : "+pageQuestion.getSize());
		List<CnprQuestion> questionList = pageQuestion.getContent();
		//System.out.println("questionList size : "+questionList.size());
		
		//List<CnprQuestionResponse> questionReponses =questionReponseServiceImpl.getAllCnprQuestionResponse();
		
		List<Long> questionIds = new ArrayList<Long>();
		Map<CnprQuestion, Object> mapQuestionTitles = new HashMap<CnprQuestion, Object>();
		questionList.forEach(q -> {
			mapQuestionTitles.put(q, null);
			questionIds.add(q.getId());
		});
		//questionIds.forEach(i->{System.out.println(i);});
		
		List<CnprQuestionResponse> questionReponses =questionReponseServiceImpl.getAllCnprQuestionResponseByQuestionIds(questionIds);
		
		List<CnprQuestionResponse> questionsAssertions = new ArrayList<CnprQuestionResponse>();

		Map<CnprQuestion, List<CnprQuestionResponse>> mapQuestion = new HashMap<CnprQuestion, List<CnprQuestionResponse>>();

		for (Map.Entry<CnprQuestion, Object> entry : mapQuestionTitles.entrySet()) {
			// String qTitle = entry.getKey();
			CnprQuestion question = entry.getKey();
			// Object val = entry.getValue();
			for (Iterator iterator = questionReponses.iterator(); iterator.hasNext();) {
				CnprQuestionResponse cnprQuestionResponse = (CnprQuestionResponse) iterator.next();

				if (cnprQuestionResponse.getQuestion().getTitle().equals(question.getTitle())) {
					questionsAssertions.add(cnprQuestionResponse);
					mapQuestion.put(question, questionsAssertions);
				} else {

				}
				// mapQuestion.put(qTitle, questionsAssertions);

			}
			questionsAssertions = new ArrayList<CnprQuestionResponse>();
			continue;
			// questionsAssertions.clear();

		}

		session.setAttribute("mapQuestion", mapQuestion);
		model.addAttribute("currentPage", pageQuestion.getNumber() + 1);
		model.addAttribute("totalItems", pageQuestion.getTotalElements());
		model.addAttribute("totalPages", pageQuestion.getTotalPages());
		model.addAttribute("pageSize", size);
		return "question/questionnaire";
	}

	@GetMapping("/new")
	public String viewDocumentNew(@ModelAttribute("message") String message, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("addnewQuestion");
		List<CnprLesson> lessons = this.lessonServiceImpl.getAllActiveCnprLesson();
		List<TypePermis> typePermisList = this.permisTypeServiceImpl.getAllActiveTypePermis();

		CnprQuestion question = new CnprQuestion();
		question.setCreatedBy((CnprUser) session.getAttribute("loggedUser"));

		session.setAttribute("question", question);
		model.addAttribute("message", message);
		session.setAttribute("lessonsList", lessons);
		session.setAttribute("typePermisList", typePermisList);

		return "question/new";
	}

	@PostMapping("/save")
	public String save(RedirectAttributes redirectAttributes, HttpServletRequest request) {
		HttpSession session = request.getSession();
		CnprQuestion question = new CnprQuestion();

		question.setActiveStatus(false);
		question.setAudioUrl(request.getParameter("audioUrl"));

		CnprUser user = new CnprUser();
		user.setId(Long.parseLong(request.getParameter("createdBy")));

		question.setCreatedBy(user);
		question.setDescription(request.getParameter("description"));
		question.setImageUrl(request.getParameter("imageUrl"));

		question.setVideoUrl(request.getParameter("videoUrl"));

		question.setTitle(request.getParameter("title"));
		question.setReponse("na");

		CnprLessonModule lessonModule = (CnprLessonModule) session.getAttribute("lessonModule");
		TypePermis typePermis = (TypePermis) session.getAttribute("typePermis");

		question.setLesson(lessonModule.getLesson());
		question.setLessonModule(lessonModule);
		question.setTypePermis(typePermis);

		CnprQuestion savedQuestion = this.questionServiceImpl.saveOrUpdateCnprQuestion(question);

		CnprQuestionResponse questionReponse = null;

		String[] responses = request.getParameterValues("reponse");
		List<CnprQuestionResponse> questionResponses = new ArrayList<CnprQuestionResponse>();

		String reponse = "";
		for (int i = 0; i < responses.length; i++) {
			questionReponse = new CnprQuestionResponse();
			questionReponse.setCorrect(false);
			questionReponse.setQuestion(savedQuestion);

			reponse = responses[i].trim();
			questionReponse.setValue(reponse);
			questionReponse.setCreatedBy(user);

			// System.out.println(permissionshortCode);
			// this.permissionServiceImpl.saveOrUpdatePermission(permission);
			questionResponses.add(questionReponse);
		}

		questionReponseServiceImpl.bulkInsertQuestionResponses(questionResponses);

		// if(this.questionReponseServiceImpl.saveOrUpdateCnprQuestionResponse(questionReponse))
		// {
		redirectAttributes.addFlashAttribute("message",
				"Question enregistrée avec succès. Veuillez renseigner la bonne réponse.");
		return "redirect:/question/viewItems/" + savedQuestion.getId();
		// };
		// redirectAttributes.addFlashAttribute("message", "Save failure");
		// return "redirect:/question/new";
	}

	@PostMapping("/edit")
	public String editCnprQuestion(CnprQuestion question, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {

		if (this.questionServiceImpl.saveOrUpdateCnprQuestion(question) != null) {
			redirectAttributes.addFlashAttribute("message", "Edit success");
			return "redirect:/admin/question/list";
		}
		;
		redirectAttributes.addFlashAttribute("message", "Edit failure");
		return "redirect:/question/edit/" + question.getId();
	}

	@GetMapping("/changeStatus/{id}")
	public String enableDisableCnprQuestion(@PathVariable("id") long id, Model model) {

		CnprQuestion question = this.questionServiceImpl.getCnprQuestionById(id);

		if (question.isActiveStatus() == false) {
			question.setActiveStatus(true);
		} else if (question.isActiveStatus() == true) {
			question.setActiveStatus(false);
		}
		this.questionServiceImpl.saveOrUpdateCnprQuestion(question);

		return "redirect:/question/list";
	}

	@GetMapping("/edit/{id}")
	public String showFormEditDocument(@PathVariable("id") long id, Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();

		CnprQuestion question = this.questionServiceImpl.getCnprQuestionById(id);

		String queryResult = "Cours de : " + question.getLessonModule().getLesson().getTitle() + " / Module : "
				+ question.getLessonModule().getTitle() + " / Type de brevet concerné : Brevet de catégorie "
				+ question.getTypePermis().getDesignation();
		model.addAttribute("question", question);
		session.setAttribute("queryResult", queryResult);
		return "/question/edit";
	}

	@GetMapping("/viewItems/{id}")
	public String showQuestionItems(@PathVariable("id") long id, Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();

		CnprQuestion question = this.questionServiceImpl.getCnprQuestionById(id);

		List<CnprQuestionResponse> items = new ArrayList<CnprQuestionResponse>();
		items = this.questionReponseServiceImpl.getAllCnprQuestionResponseByQuestionId(question.getId());

		String queryResult = "Cours de : " + question.getLessonModule().getLesson().getTitle() + " / Module : "
				+ question.getLessonModule().getTitle() + " / Type de brevet concerné : Brevet de catégorie "
				+ question.getTypePermis().getDesignation();
		model.addAttribute("question", question);
		session.setAttribute("queryResult", queryResult);
		session.setAttribute("questionReponses", items);
		return "/question/items";
	}

	@PostMapping("/viewItems/save")
	public String saveItems(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		HttpSession session = request.getSession();
		Long questionId = Long.parseLong(request.getParameter("questionId"));

		if (request.getParameter("questionReponseId").equals("-1")) {
			redirectAttributes.addFlashAttribute("message", "Veuillez selectionner la bonne reponse");
			return "redirect:/question/viewItems/" + questionId;
		}

		Long questionResponseId = Long.parseLong(request.getParameter("questionReponseId"));

		CnprQuestion question = this.questionServiceImpl.getCnprQuestionById(questionId);

		this.questionReponseServiceImpl.deactivateAllByQuestionId(question);

		CnprQuestionResponse goodResponse = this.questionReponseServiceImpl
				.getCnprQuestionResponseById(questionResponseId);
		goodResponse.setCorrect(true);
		question.setReponse(goodResponse.getValue());

		if (this.questionReponseServiceImpl.saveOrUpdateCnprQuestionResponse(goodResponse) == true) {
			CnprQuestion updatedQuestion = this.questionServiceImpl.saveOrUpdateCnprQuestion(question);

			session.setAttribute("addnewQuestion", "addNewQuestion");
			// if(this.questionServiceImpl.saveOrUpdateCnprQuestion(question) !=null) {
			return "redirect:/question/viewItems/" + questionId;
			// }
		}

		return "redirect:/question/viewItems/" + questionId;
	}

	@PostMapping("/searchByLessonAndModule")
	public String searchByLessonAndModule(RedirectAttributes redirectAttributes, HttpServletRequest request) {

		HttpSession session = request.getSession();
		Long lessonModuleId = Long.parseLong(request.getParameter("moduleQuestionId"));

		CnprLessonModule lessonModule = this.lessonModuleServiceImpl.getCnprLessonModuleById(lessonModuleId);
		TypePermis typePermis = this.permisTypeServiceImpl
				.getTypePermisById(Long.parseLong(request.getParameter("typePermis")));

		String queryResult = "Cours de : " + lessonModule.getLesson().getTitle() + " / Module : "
				+ lessonModule.getTitle() + " / Type de brevet concerné : Brevet de catégorie "
				+ typePermis.getDesignation();

		if (lessonModule != null) {
			session.setAttribute("queryResult", queryResult);
			session.setAttribute("lessonModule", lessonModule);
			session.setAttribute("typePermis", typePermis);

			// redirectAttributes.addFlashAttribute("message", queryResult);
			return "redirect:/question/new";
		}
		;
		redirectAttributes.addFlashAttribute("message", "Aucun resultat");
		return "redirect:/question/new/";
	}
}
