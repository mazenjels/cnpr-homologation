package com.cnpr.homologation.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cnpr.homologation.models.Permission;


public interface PermissionRepository extends JpaRepository<Permission, Long>{

	
	@Query("SELECT t FROM Permission t")
	List<Permission> getAllPermission();

	@Query("SELECT t FROM Permission t where t.activeStatus=true")
	List<Permission> getAllActivePermission();
	
	//@Query(value="SELECT * FROM public.tb_cnpr_permission where short_code like '%?1'", nativeQuery=true)
	List<Permission> findByShortCodeContaining(String shortCode);

	@Query("SELECT t FROM Permission t")
	Page<Permission> getAllPageable(Pageable pageable);
	
	
	

}
