package com.cnpr.homologation.service;

import java.util.List;

import com.cnpr.homologation.models.Permission;

public interface PermissionService {

	public List<Permission> getAllPermission();

	public List<Permission> getAllActivePermission();

	public boolean saveOrUpdatePermission(Permission pers);

	public boolean deletePermissionById(long id);

	public void enableOrDisablePermissionById(long id);

	public Permission getPermissionById(long id);
}
