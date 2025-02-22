package com.cnpr.homologation.service;

import java.util.List;

import com.cnpr.homologation.models.RolePermission;


public interface RolePermissionService {

	public List<RolePermission> getAllRolePermission();

	public List<RolePermission> getAllActiveRolePermission();

	public boolean saveOrUpdateRolePermission(RolePermission pers);

	public boolean deleteRolePermissionById(long id);

	public void enableOrDisableRolePermissionById(long id);

	public RolePermission getRolePermissionById(long id);
	
}
