package com.cnpr.homologation.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cnpr.homologation.models.Planification;


public interface PlanificationRepository extends JpaRepository<Planification, Long>{

	
	@Query("SELECT t FROM Planification t")
	List<Planification> getAllPlanification();
	
	@Query("SELECT t FROM Planification t where t.activeStatus=true")
	List<Planification> getAllActivePlanification();

	@Query("SELECT t FROM Planification t ")
	Page<Planification> getAllPageable(Pageable pageable);
	

}
