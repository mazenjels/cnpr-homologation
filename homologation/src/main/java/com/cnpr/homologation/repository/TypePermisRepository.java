package com.cnpr.homologation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cnpr.homologation.models.TypePermis;


public interface TypePermisRepository extends JpaRepository<TypePermis, Long>{


	
//	@Query("SELECT t FROM TypePermis t")
//	Page<TypePermis> getAllAutoEcole(Pageable pageable);
	
	@Query("SELECT t FROM TypePermis t")
	List<TypePermis> getAllTypePermis();
	
	@Query("SELECT t FROM TypePermis t where t.activeStatus=true")
	List<TypePermis> getAllActiveTypePermis();
}
