package com.cnpr.homologation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cnpr.homologation.models.PrixTypePermisAutoEcole;
import com.cnpr.homologation.models.TypePermis;


public interface PrixTypePermisRepository extends JpaRepository<PrixTypePermisAutoEcole, Long>{


	
//	@Query("SELECT t FROM TypePermis t")
//	Page<TypePermis> getAllAutoEcole(Pageable pageable);
	
	@Query("SELECT t FROM PrixTypePermisAutoEcole t")
	List<PrixTypePermisAutoEcole> getAllPrixTypePermisAutoEcole();
	
	@Query("SELECT t FROM PrixTypePermisAutoEcole t where t.activeStatus=true")
	List<PrixTypePermisAutoEcole> getAllActivePrixTypePermisAutoEcole();
}
