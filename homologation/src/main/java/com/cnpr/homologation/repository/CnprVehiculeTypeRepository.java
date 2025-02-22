package com.cnpr.homologation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cnpr.homologation.models.CnprVehiculeType;


public interface CnprVehiculeTypeRepository extends JpaRepository<CnprVehiculeType, Long>{


	
//	@Query("SELECT t FROM CnprVehiculeType t")
//	Page<CnprVehiculeType> getAllAutoEcole(Pageable pageable);
	
	@Query("SELECT t FROM CnprVehiculeType t")
	List<CnprVehiculeType> getAllCnprVehiculeType();
	
	@Query("SELECT t FROM CnprVehiculeType t where t.activeStatus=true")
	List<CnprVehiculeType> getAllActiveCnprVehiculeType();
}
