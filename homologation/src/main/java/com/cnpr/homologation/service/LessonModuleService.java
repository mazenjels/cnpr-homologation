package com.cnpr.homologation.service;

import java.util.List;

import com.cnpr.homologation.models.CnprLesson;
import com.cnpr.homologation.models.CnprLessonModule;

public interface LessonModuleService {

	public List<CnprLessonModule> getAllCnprLessonModule();

	public List<CnprLessonModule> getAllActiveCnprLessonModule();

	public boolean saveOrUpdateCnprLessonModule(CnprLessonModule pers);

	public boolean deleteCnprLessonModuleById(long id);

	public void enableOrDisableCnprLessonModuleById(long id);

	public CnprLessonModule getCnprLessonModuleById(long id);
}
