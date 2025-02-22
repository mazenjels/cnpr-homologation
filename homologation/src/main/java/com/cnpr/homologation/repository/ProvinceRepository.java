package com.cnpr.homologation.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cnpr.homologation.models.Province;


public interface ProvinceRepository extends JpaRepository<Province, Long>{

	
	@Query("SELECT t FROM Province t")
	List<Province> getAllProvince();

	@Query("SELECT t FROM Province t where t.activeStatus=true")
	List<Province> getAllActiveProvince();

	@Query("SELECT t FROM Province t")
	Page<Province> getAllPageable(Pageable pageable);
	

}
