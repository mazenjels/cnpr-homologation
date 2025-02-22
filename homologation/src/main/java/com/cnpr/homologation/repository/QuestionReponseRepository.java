package com.cnpr.homologation.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cnpr.homologation.models.CnprQuestionResponse;


public interface QuestionReponseRepository extends JpaRepository<CnprQuestionResponse, Long>{

	
	@Query("SELECT t FROM CnprQuestionResponse t where t.question.id=?1")
	List<CnprQuestionResponse> getAllCnprQuestionResponseByQuestionId(long questionId);
	
	@Query("SELECT t FROM CnprQuestionResponse t where t.question.activeStatus=true")
	Page<CnprQuestionResponse> getAllPageable(Pageable pageable);

	@Query("SELECT t FROM CnprQuestionResponse t where t.question.id IN :questionIds")
	List<CnprQuestionResponse> getAllCnprQuestionResponseByQuestionIds(List<Long> questionIds);
	
//	@Query("SELECT t FROM CnprQuestionResponse t where t.question.id=?1")
//	List<CnprQuestionResponse> getAllActiveCnprQuestionResponse();
	

	
}
