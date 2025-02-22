package com.cnpr.homologation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cnpr.homologation.models.CnprAutoEcoleVehiculeType;


public interface CnprAutoEcoleVehiculeRepository extends JpaRepository<CnprAutoEcoleVehiculeType, Long>{


	
//	@Query("SELECT t FROM CnprAutoEcoleVehiculeType t")
//	Page<CnprAutoEcoleVehiculeType> getAllCnprAutoEcoleVehiculeType(Pageable pageable);
	
	@Query("SELECT t FROM CnprAutoEcoleVehiculeType t")
	List<CnprAutoEcoleVehiculeType> getAllCnprAutoEcoleVehiculeType();
	
	@Query("SELECT t FROM CnprAutoEcoleVehiculeType t where t.cnprAutoEcole.id=?1")
	List<CnprAutoEcoleVehiculeType> getAllCnprAutoEcoleVehiculeTypeByAutoEcoleid(long id);

	
}
