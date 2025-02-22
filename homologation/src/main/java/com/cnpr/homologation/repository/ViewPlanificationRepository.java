package com.cnpr.homologation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cnpr.homologation.models.ViewPlanification;



public interface ViewPlanificationRepository extends JpaRepository<ViewPlanification, Long>{

	
	@Query("SELECT t FROM ViewPlanification t")
	List<ViewPlanification> getAllPlanification();
	
	@Query("SELECT t FROM ViewPlanification t where t.activeStatus=true")
	List<ViewPlanification> getAllActivePlanification();
	
}
