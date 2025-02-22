package com.cnpr.homologation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cnpr.homologation.models.RolePermission;



public interface RolePermissionRepository extends CrudRepository<RolePermission, Long>{

	@Query("SELECT t FROM RolePermission t WHERE t.role.id IN (:ids)")
	public List<RolePermission> getAllRolePermissionByRoleID(@Param("ids") List<Long> ids);
	
	@Query("SELECT t FROM RolePermission t WHERE t.permission.id IN (:ids)")
	public List<RolePermission> getAllRolePermissionByPermissionID(@Param("ids") List<Long> ids);

	@Query("SELECT t FROM RolePermission t WHERE t.role.designation =?1")
	public List<RolePermission> getAllRolePermissionByRoleName(String role);
	
	
	
	
}
