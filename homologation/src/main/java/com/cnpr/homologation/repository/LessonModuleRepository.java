package com.cnpr.homologation.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cnpr.homologation.models.CnprLessonModule;


public interface LessonModuleRepository extends JpaRepository<CnprLessonModule, Long>{

	
	@Query("SELECT t FROM CnprLessonModule t")
	List<CnprLessonModule> getAllCnprLessonModule();
	
	@Query("SELECT t FROM CnprLessonModule t where t.activeStatus=true")
	List<CnprLessonModule> getAllActiveCnprLessonModule();
	
	@Query("SELECT t FROM CnprLessonModule t where t.lesson.id=?1 ")
	List<CnprLessonModule> getAllCnprLessonModuleByLessonId(long lessonId);
	
	@Query("SELECT t FROM CnprLessonModule t where t.lesson.id=?1 and t.activeStatus=true")
	List<CnprLessonModule> getAllActiveCnprLessonModuleByLessonId(long lessonId);


	
	

	
}
