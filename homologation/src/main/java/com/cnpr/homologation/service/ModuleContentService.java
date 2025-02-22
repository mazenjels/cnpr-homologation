package com.cnpr.homologation.service;

import java.util.List;

import com.cnpr.homologation.models.CnprModuleContent;

public interface ModuleContentService {

	public List<CnprModuleContent> getAllCnprModuleContent();

	public List<CnprModuleContent> getAllActiveCnprModuleContent();

	public boolean saveOrUpdateCnprModuleContent(CnprModuleContent pers);

	public boolean deleteCnprModuleContentById(long id);

	public void enableOrDisableCnprModuleContentById(long id);

	public CnprModuleContent getCnprModuleContentById(long id);
}
