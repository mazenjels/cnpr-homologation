package com.cnpr.homologation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cnpr.homologation.models.CnprModuleContent;


public interface ModuleContentRepository extends JpaRepository<CnprModuleContent, Long>{

	
	
	@Query("SELECT t FROM CnprModuleContent t")
	List<CnprModuleContent> getAllCnprModuleContent();
	
	@Query("SELECT t FROM CnprModuleContent t where t.activeStatus=true")
	List<CnprModuleContent> getAllActiveCnprModuleContent();
	
	
	@Query("SELECT t FROM CnprModuleContent t where t.lessonModule.id=?1")
	List<CnprModuleContent> getAllCnprModuleContentByLessonModuleId(long lessonModuleId);
	

	
}
