package com.cnpr.homologation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cnpr.homologation.models.AutoEcoleUser;



public interface AutoEcoleUserRepository extends JpaRepository<AutoEcoleUser, Long>{

	
	@Query("SELECT t FROM AutoEcoleUser t")
	List<AutoEcoleUser> getAllAutoEcoleUser();
	
	@Query("SELECT t FROM AutoEcoleUser t where t.cnprAutoEcole.id=?1")
	List<AutoEcoleUser> getAllAutoEcoleUserByAutoEcoleid(long id);

	@Query("SELECT t FROM AutoEcoleUser t where t.cnprUser.id=?1")
	List<AutoEcoleUser> getAutoEcoleUserByLoggedUserId(long id);

	@Query("SELECT t FROM AutoEcoleUser t where t.cnprUser.id=?1")
	List<AutoEcoleUser> getAutoEcoleUserByUserId(long id);

	
}
