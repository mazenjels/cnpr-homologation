package com.cnpr.homologation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cnpr.homologation.models.AutoEcoleTypePermis;


public interface AutoEcoleTypePermisRepository extends JpaRepository<AutoEcoleTypePermis, Long>{


	
//	@Query("SELECT t FROM AutoEcoleTypePermis t")
//	Page<AutoEcoleTypePermis> getAllAutoEcoleTypePermis(Pageable pageable);
	
	@Query("SELECT t FROM AutoEcoleTypePermis t")
	List<AutoEcoleTypePermis> getAllAutoEcoleTypePermis();
	
	@Query("SELECT t FROM AutoEcoleTypePermis t where t.cnprAutoEcole.id=?1")
	List<AutoEcoleTypePermis> getAllAutoEcoleTypePermisByAutoEcoleid(long id);

	
}
