package com.cnpr.homologation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cnpr.homologation.models.ViewUser;



public interface ViewUserRepository extends JpaRepository<ViewUser, Long>{
	
	ViewUser findByUsername(String username);
	@Query("select t from ViewUser t where t.username=?1")
	ViewUser getRoleByUsername(String username);

}
