package com.cnpr.homologation.service;

import java.util.List;

import com.cnpr.homologation.models.CnprDocumentType;

public interface DocumentTypeService {

	public List<CnprDocumentType> getAllCnprDocumentType();

	public List<CnprDocumentType> getAllActiveCnprDocumentType();

	public boolean saveOrUpdateCnprDocumentType(CnprDocumentType pers);

	public boolean deleteCnprDocumentTypeById(long id);

	public void enableOrDisableCnprDocumentTypeById(long id);

	public CnprDocumentType getCnprDocumentTypeById(long id);
}
