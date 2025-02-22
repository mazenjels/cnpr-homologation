package com.cnpr.homologation.service;

import java.util.List;

import com.cnpr.homologation.models.CnprAutoEcole;

public interface AutoEcoleService {

	public List<CnprAutoEcole> getAllCnprAutoEcole();

	public List<CnprAutoEcole> getAllActiveCnprAutoEcole();

	public boolean saveOrUpdateCnprAutoEcole(CnprAutoEcole pers);

	public boolean deleteCnprAutoEcoleById(long id);

	public void enableOrDisableCnprAutoEcoleById(long id);

	public CnprAutoEcole getCnprAutoEcoleById(long id);

	public String getDashboardSummary();
}
