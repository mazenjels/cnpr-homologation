package com.cnpr.homologation.service;

import java.util.List;

import com.cnpr.homologation.models.CnprRole;

public interface RoleService {
	public List<CnprRole> getAllCnprRole();

	public List<CnprRole> getAllActiveCnprRole();

	public boolean saveOrUpdateCnprRole(CnprRole pers);

	public boolean deleteCnprRoleById(long id);

	public void enableOrDisableCnprRoleById(long id);

	public CnprRole getCnprRoleById(long id);
}
