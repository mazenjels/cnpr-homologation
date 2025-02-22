package com.cnpr.homologation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ModuleRepository extends JpaRepository<com.cnpr.homologation.models.Module, Long>{

	
	@Query("SELECT t FROM Module t")
	List<com.cnpr.homologation.models.Module> getAllModule();
	
	@Query("SELECT t FROM Module t where t.activeStatus=true")
	List<com.cnpr.homologation.models.Module> getAllActiveModule();
	

}
