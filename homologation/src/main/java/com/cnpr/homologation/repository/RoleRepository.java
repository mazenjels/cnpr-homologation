package com.cnpr.homologation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cnpr.homologation.models.CnprRole;


public interface RoleRepository extends JpaRepository<CnprRole, Long>{

	
	@Query("SELECT t FROM CnprRole t")
	List<CnprRole> getAllCnprRole();
	

}
