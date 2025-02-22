package com.cnpr.homologation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cnpr.homologation.models.CnprDocumentType;


public interface CnprDocumentTypeRepository extends JpaRepository<CnprDocumentType, Long>{

	
//	@Query("SELECT t FROM CnprDocumentType t")
//	Page<CnprDocumentType> getAllCnprDocumentType(Pageable pageable);
	
	@Query("SELECT t FROM CnprDocumentType t")
	List<CnprDocumentType> getAllCnprDocumentType();
}
