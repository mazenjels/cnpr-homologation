package com.cnpr.homologation.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.cnpr.homologation.models.CnprLesson;
import com.cnpr.homologation.models.CnprLessonModule;
import com.cnpr.homologation.models.CnprModuleContent;
import com.cnpr.homologation.models.CnprUser;
import com.cnpr.homologation.service.LessonModuleServiceImpl;
import com.cnpr.homologation.service.LessonServiceImpl;
import com.cnpr.homologation.service.ModuleContentServiceImpl;




@Controller
@RequestMapping(value="/lesson")
public class LessonController {

	@Autowired
	LessonServiceImpl lessonService;
	
	@Autowired
	LessonModuleServiceImpl lessonModuleService;
	
	@Autowired
	ModuleContentServiceImpl moduleContentService;

	
	@GetMapping({"/list", "/lesson/list"})
	public String  viewPersonnelList(@ModelAttribute("message") String message, Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		  List<CnprLesson> lessonList = lessonService.getAllCnprLesson();
		  session.removeAttribute("lessonModule");
		  model.addAttribute("lessonList", lessonList);
		  model.addAttribute("message", message);
		
		return "lesson/list";
	}
	
	@GetMapping("/new")
	public String viewDocumentNew(@ModelAttribute("message") String message, Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		CnprLesson lesson = new CnprLesson();
		lesson.setCreatedBy((CnprUser) session.getAttribute("loggedUser"));
		
		model.addAttribute("lesson", lesson);
		model.addAttribute("message", message);
		
		return "lesson/new";
	}
	
	@PostMapping("/save")
	public String saveDocument(CnprLesson lesson, RedirectAttributes redirectAttributes) {
		
		if(this.lessonService.saveOrUpdateCnprLesson(lesson)) {
			redirectAttributes.addFlashAttribute("message", "Save success");
			return "redirect:/lesson/list/";
			
			//return "redirect:/lesson/list";
		};
		redirectAttributes.addFlashAttribute("message", "Save failure");
		return "redirect:/lesson/new";
	}
	
	@PostMapping("/edit")
	public String editBanque(CnprLesson lesson, RedirectAttributes redirectAttributes,HttpServletRequest request) {

		if (this.lessonService.saveOrUpdateCnprLesson(lesson)) {
			redirectAttributes.addFlashAttribute("message", "Edit success");
			return "redirect:/lesson/list";
		}
		;
		redirectAttributes.addFlashAttribute("message", "Edit failure");
		return "redirect:/lesson/edit/" + lesson.getId();
	}
	
	
//	@GetMapping("/delete/{id}")
//	public String deleteDocument(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
//
//		if (this.lessonService.deleteDocumentById(id)) {
//			redirectAttributes.addFlashAttribute("message", "Delete success");
//			return "redirect:/lesson/list";
//		}
//		;
//		redirectAttributes.addFlashAttribute("message", "Delete failure");
//		return "redirect:/lesson/list";
//	}
	
	@GetMapping("/changeStatus/{id}")
	public String enableDisableDocument(@PathVariable("id") long id, Model model) {

		CnprLesson lesson = this.lessonService.getCnprLessonById(id);

		if (lesson.isActiveStatus() == false) {
			lesson.setActiveStatus(true);
		} else if (lesson.isActiveStatus() == true) {
			lesson.setActiveStatus(false);
		}
		this.lessonService.saveOrUpdateCnprLesson(lesson);

		return "redirect:/lesson/list";
	}	
	
	

	@GetMapping("/edit/{id}")
	public String showFormEditDocument(@PathVariable("id") long id, Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		CnprLesson lesson = this.lessonService.getCnprLessonById(id);
		lesson.setCreatedBy((CnprUser) session.getAttribute("loggedUser"));
		model.addAttribute("lesson", lesson);
		return "/lesson/edit";
	}
	
	@GetMapping("/addModules/{id}")
	public String addModules(@PathVariable("id") long id, Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		CnprLesson lesson = null;
		if(session.getAttribute("lesson") == null) {
			 lesson = this.lessonService.getCnprLessonById(id);
		}else {
			lesson=(CnprLesson)session.getAttribute("lesson") ;
		}
		
		CnprLessonModule lessonModule = new CnprLessonModule();
		lessonModule.setLesson(lesson);
		lessonModule.setCreatedBy((CnprUser) session.getAttribute("loggedUser"));
		
		List<CnprLessonModule> lessonModuleList = this.lessonModuleService.getAllCnprLessonModuleByLessonId(lesson.getId());	
		session.setAttribute("lessonModuleList", lessonModuleList);

		model.addAttribute("lessonModule", lessonModule);
		session.setAttribute("lessonModule", lessonModule);
		session.setAttribute("lesson", lesson);
		return "/lesson/module";
	}	
	
	@GetMapping("/modules/{lessonModuleId}/changeStatus")
	public String changeStatusModule(@PathVariable("lessonModuleId") long id, Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		CnprLessonModule lessonModule = this.lessonModuleService.getCnprLessonModuleById(id);

		if (lessonModule.isActiveStatus() == false) {
			lessonModule.setActiveStatus(true);
		} else if (lessonModule.isActiveStatus() == true) {
			lessonModule.setActiveStatus(false);
		}
		this.lessonModuleService.saveOrUpdateCnprLessonModule(lessonModule);
		return "redirect:/lesson/addModules/"+lessonModule.getLesson().getId();
	}
	
	@GetMapping("/content/{moduleContentId}/changeStatus")
	public String changeStatusContent(@PathVariable("moduleContentId") long id, Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		CnprModuleContent moduleContent = this.moduleContentService.getCnprModuleContentById(id);

		if (moduleContent.isActiveStatus() == false) {
			moduleContent.setActiveStatus(true);
		} else if (moduleContent.isActiveStatus() == true) {
			moduleContent.setActiveStatus(false);
		}
		this.moduleContentService.saveOrUpdateCnprModuleContent(moduleContent);
		return "redirect:/lesson/modules/"+moduleContent.getLessonModule().getId();
	}
	
	@GetMapping("/content/{moduleContentId}/edit")
	public String editModuleContent(@PathVariable("moduleContentId") long id, Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		CnprModuleContent moduleContent = this.moduleContentService.getCnprModuleContentById(id);

		model.addAttribute("moduleContent",moduleContent);
//		if (moduleContent.isActiveStatus() == false) {
//			moduleContent.setActiveStatus(true);
//		} else if (moduleContent.isActiveStatus() == true) {
//			moduleContent.setActiveStatus(false);
//		}
		//this.moduleContentService.saveOrUpdateCnprModuleContent(moduleContent);
		return "/lesson/contenu";
	}
	
	@PostMapping("/saveModule")
	public String saveModule(CnprLessonModule lessonModule, RedirectAttributes redirectAttributes) {
		
		if(this.lessonModuleService.saveOrUpdateCnprLessonModule(lessonModule)) {
			redirectAttributes.addFlashAttribute("message", "Save success");
			return "redirect:/lesson/addModules/"+lessonModule.getLesson().getId();
			
			//return "redirect:/lesson/list";
		};
		redirectAttributes.addFlashAttribute("message", "Save failure");
		return "redirect:/lesson/addModules/"+lessonModule.getLesson().getId();
	}
	
	@GetMapping("/modules/{lessonModuleId}")
	public String lessonModuleId(@PathVariable("lessonModuleId") long lessonModuleId, Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		CnprLessonModule lessonModule = this.lessonModuleService.getCnprLessonModuleById(lessonModuleId);
		CnprModuleContent moduleContent = new CnprModuleContent();
		
		moduleContent.setLessonModule(lessonModule);
		
//		lessonModule.setLesson(lesson);
		moduleContent.setCreatedBy((CnprUser) session.getAttribute("loggedUser"));
//		
		List<CnprModuleContent> moduleContentList = this.moduleContentService.getAllCnprModuleContentByLessonModuleId(lessonModule.getId());	
//		session.setAttribute("lessonModuleList", lessonModuleList);


		model.addAttribute("moduleContent", moduleContent);
		session.setAttribute("moduleContent", moduleContent);
		session.setAttribute("moduleContentList", moduleContentList);
		session.setAttribute("lessonModule", lessonModule);
		return "/lesson/contenu";
	}	
	
	@PostMapping("/module/saveContent")
	public String saveContent(CnprModuleContent moduleContent, RedirectAttributes redirectAttributes) {
		
		if(this.moduleContentService.saveOrUpdateCnprModuleContent(moduleContent)) {
			redirectAttributes.addFlashAttribute("message", "Save success");
			return "redirect:/lesson/modules/"+moduleContent.getLessonModule().getId();
			
			//return "redirect:/lesson/list";
		};
		redirectAttributes.addFlashAttribute("message", "Save failure");
		return "redirect:/lesson/modules/"+moduleContent.getLessonModule().getId();
	}
	
	@GetMapping("/medias")
	public String media( HttpServletRequest request, Model model) throws UnknownHostException {
		
		//String directoryPath = "/opt/cnpr-media/image"; // Replace with your actual directory path
		  String directoryPath = "C:/cnpr-media/image"; // Replace with your actual directory path
		
        File directory = new File(directoryPath);
        
        InetAddress inetAddress = InetAddress.getLocalHost();
        String ipAddress = inetAddress.getHostAddress();

        if (directory.exists() && directory.isDirectory()) {
            List<String> files = Arrays.stream(directory.listFiles())
                    .map(File::getName)
                    .collect(Collectors.toList());
            model.addAttribute("ipAddress", ipAddress);
            model.addAttribute("files", files);
        } else {
            model.addAttribute("error", "Directory does not exist or is not accessible.");
        }
		return "/lesson/medias";
	}	
	
	@Value("${spring.servlet.multipart.location}")
    private String uploadDir;//
	
	@PostMapping("/medias/save")
	public String saveMediaType(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes, HttpServletRequest request) 
			throws IOException {
		//String uploadDir = System.getProperty("user.dir")+"/src/main/resources/static/img/lesson/media/";
		String serverPath = request.getServletContext().toString();
		System.out.println("serverPath : "+serverPath);
//		System.out.println("Directory : "+dir);
//		if(file.isEmpty()) {
//			redirectAttributes.addFlashAttribute("errorMessage", "Please select a file to upload.");
//			return "redirect:/";
//		}
//		int index = file.getOriginalFilename().lastIndexOf(".");
//		String photoName= file.getOriginalFilename().substring(0, index + 1) + file.getOriginalFilename().substring(index + 1, file.getOriginalFilename().length()).toLowerCase();
//		System.out.println("File fullpath : "+dir+photoName);
//		
//		
//		
//		Path path = Paths.get(dir, file.getOriginalFilename());
//		System.out.println("Path : "+path.toString());
//		String extension = (path.toString()).substring((path.toString()).lastIndexOf(".") + 1);
//		Files.write(path, file.getBytes());
//		Files.move(path, path.resolveSibling(request.getParameter("type_media")+request.getParameter("file")+"."+extension),StandardCopyOption.REPLACE_EXISTING);
//		//long personnelId = Long.parseLong(request.getParameter("personnel_id"));
//		String photoRenamed = request.getParameter("type_media")+request.getParameter("file")+"."+extension;
//		//int photoUpdated = personnelService.updatePersonnelPhoto(photoRenamed, personnelId);
//		redirectAttributes.addFlashAttribute("message", "File upload successfully, uploaded file name: " + file.getOriginalFilename());
//		String os = System.getProperty("os.name").toLowerCase();
//		String prefix="";
//		if (os.contains("win")) {
//			// Operating system is based on Windows
//			if(request.getParameter("type_media").equals("image")) {
//				uploadDir = "/cnpr-media/images/";
//				prefix="img_";
//			}else if(request.getParameter("type_media").equals("video")) {
//				uploadDir = "/cnpr-media/videos/";
//				prefix="vid_";
//			}else if(request.getParameter("type_media").equals("audio")) {
//				uploadDir = "/cnpr-media/audio/";
//				prefix="aud_";
//			}
//
//			
//		} else if (os.contains("osx")) {
//			// Operating system is Apple OSX based
//		} else if (os.contains("nix") || os.contains("aix") || os.contains("nux")) {
//			// Operating system is based on Linux/Unix/*AIX
//			// uploadDir = "/sirh-doc/instructions/"+savedInstruction.getId();
//			if(request.getParameter("type_media").equals("image")) {
//				uploadDir = "/opt/cnpr-media/image/";
//				prefix="img_";
//			}else if(request.getParameter("type_media").equals("video")) {
//				uploadDir = "/opt/cnpr-media/video/";
//				prefix="vid_";
//			}else if(request.getParameter("type_media").equals("audio")) {
//				uploadDir = "/opt/cnpr-media/audio/";
//				prefix="aud_";
//			}
//			//uploadDir = System.getProperty("user.dir")+"/src/main/resources/static/img/lesson/media/";
//		}
//		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//		
//
//		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//		Path uploadPath = null;
//		Path filePath = null;
//		String extension = "";
//		uploadPath = Paths.get(uploadDir);
//		filePath = uploadPath.resolve(fileName);
//		extension = (filePath.toString()).substring((filePath.toString()).lastIndexOf(".") + 1);
//		String documentNewName = prefix+timestamp.getTime()+"";//request.getParameter("document_name");
//		
//		File oldFileName = new File(fileName);
//		File newFileName = new File(documentNewName + "." + extension);
//		boolean flag = oldFileName.renameTo(newFileName);
//		//instructionToSave.setDocument(newFileName.getName());
//
//		//InstructionProvinciale updatedInstructionProvinciale = instructionProvincialeService.saveOrUpdateInstructionProvinciale(instructionProvincialeToSave);
//
//		if (!Files.exists(uploadPath)) {
//			Files.createDirectories(uploadPath);
//		}
//
//		try {
//			InputStream inputStream = file.getInputStream();
//			// Path filePath = uploadPath.resolve(fileName);
//			//logger.debug(filePath.toFile().getAbsolutePath());
//			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
//
//			//Files.move(filePath,filePath.resolveSibling(request.getParameter("document_name") + "." + extension),StandardCopyOption.REPLACE_EXISTING);
//			Files.move(filePath,filePath.resolveSibling(documentNewName + "." + extension),StandardCopyOption.REPLACE_EXISTING);
//
//		} catch (IOException e) {
//			throw new IOException("Impossible de charger le fichier " + fileName);
//		}
		try {
            // Ensure the upload directory exists
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Save the file
            File destination = new File(directory, file.getOriginalFilename());
            file.transferTo(destination);

            return "redirect:/lesson/medias";
        } catch (IOException e) {
            e.printStackTrace();
            return "File upload failed: " + e.getMessage();
        }
		
	}
	
//	@GetMapping("/directory")
//    public String listDirectoryContents(Model model) {
//       // String directoryPath = "C:/cnpr-media/image"; // Replace with your actual directory path
//        String directoryPath = "/opt/cnpr-media/image"; // Replace with your actual directory path
//        File directory = new File(directoryPath);
//
//        if (directory.exists() && directory.isDirectory()) {
//            List<String> files = Arrays.stream(directory.listFiles())
//                    .map(File::getName)
//                    .collect(Collectors.toList());
//            model.addAttribute("files", files);
//        } else {
//            model.addAttribute("error", "Directory does not exist or is not accessible.");
//        }
//
//        return "directory";
//    }
	
}
