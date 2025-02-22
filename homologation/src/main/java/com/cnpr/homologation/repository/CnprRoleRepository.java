package com.cnpr.homologation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cnpr.homologation.models.CnprRole;



public interface CnprRoleRepository extends JpaRepository<CnprRole, Long>{

	
//	@Query("SELECT t FROM CnprRole t")
//	Page<CnprRole> getAllCnprRole(Pageable pageable);
	
	@Query("SELECT t FROM CnprRole t")
	List<CnprRole> getAllCnprRole();
	
	@Query("SELECT t FROM CnprRole t where t.activeStatus=true and t.designation!='Administrateur'")
	List<CnprRole> getAllActiveCnprRole();

}
