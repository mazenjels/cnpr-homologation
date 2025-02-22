package com.cnpr.homologation.service;

import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.cnpr.homologation.models.CnprLesson;
import com.cnpr.homologation.repository.LessonRepository;

@Service
@Transactional
public class LessonServiceImpl implements LessonService{

	@Autowired
	LessonRepository lessonRepo;
	
	@Override
	public List<CnprLesson> getAllCnprLesson() {
		// TODO Auto-generated method stub
		return (List<CnprLesson>)lessonRepo.getAllCnprLesson();
	}

	@Override
	public List<CnprLesson> getAllActiveCnprLesson() {
		// TODO Auto-generated method stub
		return (List<CnprLesson>)lessonRepo.getAllActiveCnprLesson();
	}
	
	
	

	@Override
	public boolean saveOrUpdateCnprLesson(CnprLesson pers) {
		CnprLesson lesson = lessonRepo.save(pers);

		if (lessonRepo.findById(lesson.getId()) != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteCnprLessonById(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void enableOrDisableCnprLessonById(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CnprLesson getCnprLessonById(long id) {
		// TODO Auto-generated method stub
		return (CnprLesson) lessonRepo.getById(id);
	}




	
}
