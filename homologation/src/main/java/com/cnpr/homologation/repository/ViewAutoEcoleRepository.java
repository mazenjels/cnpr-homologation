package com.cnpr.homologation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cnpr.homologation.models.ViewAutoEcole;



public interface ViewAutoEcoleRepository extends JpaRepository<ViewAutoEcole, Long>{
	
//	ViewUser findByUsername(String username);
//	@Query("select t from ViewUser t where t.username=?1")
//	ViewUser getRoleByUsername(String username);

}
