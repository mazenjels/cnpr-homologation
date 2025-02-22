package com.cnpr.homologation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cnpr.homologation.models.Candidat;
import com.cnpr.homologation.pojo.Summary;


public interface CandidatRepository extends JpaRepository<Candidat, Long>{

	
	@Query("SELECT t FROM Candidat t")
	List<Candidat> getAllCandidat();
	
	@Query("SELECT t FROM Candidat t where t.activeStatus=true")
	List<Candidat> getAllActiveCandidat();
	
	@Query("SELECT t FROM Candidat t where t.cnprAutoEcole.id=?1")
	List<Candidat> getAllCandidatByAutoEcoleId(long autoEcoleId);
	
	@Query("select new com.cnpr.homologation.pojo.Summary(COUNT(m) )from Candidat m where m.recyclageValide=true")
	 public Summary getTotalCandidatInscrit();
	
	
}
