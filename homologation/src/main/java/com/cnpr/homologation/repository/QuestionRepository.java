package com.cnpr.homologation.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cnpr.homologation.models.CnprQuestion;


public interface QuestionRepository extends JpaRepository<CnprQuestion, Long>{

	
	@Query("SELECT t FROM CnprQuestion t")
	List<CnprQuestion> getAllCnprQuestion();
	
	@Query("SELECT t FROM CnprQuestion t where t.activeStatus=true")
	List<CnprQuestion> getAllActiveCnprQuestion();

	@Query("SELECT t FROM CnprQuestion t")
	Page<CnprQuestion> getAllPageable(Pageable pageable);
	

	
}
