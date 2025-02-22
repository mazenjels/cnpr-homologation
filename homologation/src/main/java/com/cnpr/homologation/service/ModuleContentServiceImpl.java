package com.cnpr.homologation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.cnpr.homologation.models.CnprModuleContent;
import com.cnpr.homologation.repository.ModuleContentRepository;

@Service
@Transactional
public class ModuleContentServiceImpl implements ModuleContentService{

	@Autowired
	ModuleContentRepository moduleContentRepo;
	
	@Override
	public List<CnprModuleContent> getAllCnprModuleContent() {
		// TODO Auto-generated method stub
		return (List<CnprModuleContent>)moduleContentRepo.getAllCnprModuleContent();
	}

	@Override
	public List<CnprModuleContent> getAllActiveCnprModuleContent() {
		// TODO Auto-generated method stub
		return (List<CnprModuleContent>)moduleContentRepo.getAllActiveCnprModuleContent();
	}
	
	public List<CnprModuleContent> getAllCnprModuleContentByLessonModuleId(long lessonId) {
		// TODO Auto-generated method stub
		return (List<CnprModuleContent>)moduleContentRepo.getAllCnprModuleContentByLessonModuleId(lessonId);
	}
	
	
	
	
	

	@Override
	public boolean saveOrUpdateCnprModuleContent(CnprModuleContent pers) {
		CnprModuleContent moduleContent = moduleContentRepo.save(pers);

		if (moduleContentRepo.findById(moduleContent.getId()) != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteCnprModuleContentById(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void enableOrDisableCnprModuleContentById(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CnprModuleContent getCnprModuleContentById(long id) {
		// TODO Auto-generated method stub
		return (CnprModuleContent) moduleContentRepo.getById(id);
	}




	
}
