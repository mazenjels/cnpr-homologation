package com.cnpr.homologation.service;

import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.cnpr.homologation.models.CnprLesson;
import com.cnpr.homologation.models.CnprLessonModule;
import com.cnpr.homologation.models.Permission;
import com.cnpr.homologation.repository.LessonModuleRepository;
import com.cnpr.homologation.repository.LessonRepository;

@Service
@Transactional
public class LessonModuleServiceImpl implements LessonModuleService{

	@Autowired
	LessonModuleRepository lessonModuleRepo;
	
	@Override
	public List<CnprLessonModule> getAllCnprLessonModule() {
		// TODO Auto-generated method stub
		return (List<CnprLessonModule>)lessonModuleRepo.getAllCnprLessonModule();
	}

	@Override
	public List<CnprLessonModule> getAllActiveCnprLessonModule() {
		// TODO Auto-generated method stub
		return (List<CnprLessonModule>)lessonModuleRepo.getAllActiveCnprLessonModule();
	}
	
	public List<CnprLessonModule> getAllCnprLessonModuleByLessonId(long lessonId) {
		// TODO Auto-generated method stub
		return (List<CnprLessonModule>)lessonModuleRepo.getAllCnprLessonModuleByLessonId(lessonId);
	}
	
	
	
	
	

	@Override
	public boolean saveOrUpdateCnprLessonModule(CnprLessonModule pers) {
		CnprLessonModule lessonModule = lessonModuleRepo.save(pers);

		if (lessonModuleRepo.findById(lessonModule.getId()) != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteCnprLessonModuleById(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void enableOrDisableCnprLessonModuleById(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CnprLessonModule getCnprLessonModuleById(long id) {
		// TODO Auto-generated method stub
		return (CnprLessonModule) lessonModuleRepo.getById(id);
	}

	@SuppressWarnings("unchecked")
	public String getAllActiveCnprLessonModuleByLessonId(Long lessonId) {
		JSONArray moduleLessonList = new JSONArray();

		for (Iterator<CnprLessonModule> iterator = lessonModuleRepo.getAllActiveCnprLessonModuleByLessonId(lessonId).iterator(); iterator.hasNext();) {
			CnprLessonModule lessonModule = iterator.next();
			//System.out.println("Departement ID : "+departementId);
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("lesson_module_id", lessonModule.getId());
				jsonObj.put("lesson_module_title", lessonModule.getTitle());
				moduleLessonList.add(jsonObj);
			
		}

		return (moduleLessonList.toString());
		
		
}




	
}
