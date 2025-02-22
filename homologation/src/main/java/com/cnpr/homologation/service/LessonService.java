package com.cnpr.homologation.service;

import java.util.List;

import com.cnpr.homologation.models.CnprLesson;

public interface LessonService {

	public List<CnprLesson> getAllCnprLesson();

	public List<CnprLesson> getAllActiveCnprLesson();

	public boolean saveOrUpdateCnprLesson(CnprLesson pers);

	public boolean deleteCnprLessonById(long id);

	public void enableOrDisableCnprLessonById(long id);

	public CnprLesson getCnprLessonById(long id);
}
