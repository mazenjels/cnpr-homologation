package com.cnpr.homologation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cnpr.homologation.models.CnprLesson;


public interface LessonRepository extends JpaRepository<CnprLesson, Long>{

	
	@Query("SELECT t FROM CnprLesson t")
	List<CnprLesson> getAllCnprLesson();
	
	@Query("SELECT t FROM CnprLesson t where t.activeStatus=true")
	List<CnprLesson> getAllActiveCnprLesson();
	

	
}
