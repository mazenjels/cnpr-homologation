package com.cnpr.homologation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cnpr.homologation.models.CnprUser;


public interface UserRepository extends JpaRepository<CnprUser, Long>{

	
	@Query("SELECT t FROM CnprUser t")
	List<CnprUser> getAllCnprUser();
	
	Optional<CnprUser> findByUsername(String username);

}
