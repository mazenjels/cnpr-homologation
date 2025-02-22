package com.cnpr.homologation.service;

import java.util.List;

import com.cnpr.homologation.models.CnprUser;

public interface UserService {

	public List<CnprUser> getAllCnprUser();

	public List<CnprUser> getAllActiveCnprUser();

	public boolean saveOrUpdateCnprUser(CnprUser pers);

	public boolean deleteCnprUserById(long id);

	public void enableOrDisableCnprUserById(long id);

	public CnprUser getCnprUserById(long id);
}
