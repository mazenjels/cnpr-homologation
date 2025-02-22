package com.cnpr.homologation.service;

import java.util.List;

import com.cnpr.homologation.models.CnprAutoEcoleDocumentType;


public interface AutoEcoleDocumentTypeService {

	public List<CnprAutoEcoleDocumentType> getAllAutoEcoleDocumentType();

	public List<CnprAutoEcoleDocumentType> getAllActiveAutoEcoleDocumentType();

	public boolean saveOrUpdateAutoEcoleDocumentType(CnprAutoEcoleDocumentType pers);

	public boolean deleteAutoEcoleDocumentTypeById(long id);

	public void enableOrDisableAutoEcoleDocumentTypeById(long id);

	public CnprAutoEcoleDocumentType getAutoEcoleDocumentTypeById(long id);
}
