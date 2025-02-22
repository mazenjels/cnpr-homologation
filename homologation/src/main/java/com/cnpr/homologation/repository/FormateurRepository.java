package com.cnpr.homologation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cnpr.homologation.models.Formateur;


public interface FormateurRepository extends JpaRepository<Formateur, Long>{

	
	@Query("SELECT t FROM Formateur t")
	List<Formateur> getAllFormateur();
	
	@Query("SELECT t FROM Formateur t where t.activeStatus=true")
	List<Formateur> getAllActiveFormateur();
	
	@Query("SELECT t FROM Formateur t where t.cnprAutoEcole.id=?1")
	List<Formateur> getAllFormateurByAutoEcoleId(long autoEcoleId);
	
	
}
