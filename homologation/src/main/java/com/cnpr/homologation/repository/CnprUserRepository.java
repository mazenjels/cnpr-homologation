package com.cnpr.homologation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cnpr.homologation.models.CnprUser;


public interface CnprUserRepository extends JpaRepository<CnprUser, Long>{

	
//	@Query("SELECT t FROM CnprUser t")
//	Page<CnprUser> getAllCnprUser(Pageable pageable);
	
	@Query("SELECT t FROM CnprUser t")
	List<CnprUser> getAllCnprUser();
	
	@Query("SELECT u FROM CnprUser u WHERE u.username = ?1")
	public CnprUser getUserByUsername(String username);

	@Modifying(clearAutomatically = true)
	@Query("UPDATE  CnprUser u set u.password=:password WHERE u.username = :username")
	public int updatePassword(@Param("password") String password,@Param("username") String username);
}
