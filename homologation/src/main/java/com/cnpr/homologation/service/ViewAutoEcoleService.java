package com.cnpr.homologation.service;

import java.util.List;

import com.cnpr.homologation.models.ViewAutoEcole;

public interface ViewAutoEcoleService {

	public List<ViewAutoEcole> getAllViewAutoEcole();

	public List<ViewAutoEcole> getAllActiveViewAutoEcole();

	public boolean saveOrUpdateViewAutoEcole(ViewAutoEcole pers);

	public boolean deleteViewAutoEcoleById(long id);

	public void enableOrDisableViewAutoEcoleById(long id);

	public ViewAutoEcole getViewAutoEcoleById(long id);

	public String getDashboardSummary();
}
