package com.cnpr.homologation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cnpr.homologation.models.CnprAutoEcoleVehiculeType;
import com.cnpr.homologation.models.CnprDocumentType;
import com.cnpr.homologation.repository.CnprDocumentTypeRepository;

@Service
@Transactional
public class DocumentTypeServiceImpl implements DocumentTypeService {

	@Autowired
	CnprDocumentTypeRepository documentRepo;
	
	@Override
	public List<CnprDocumentType> getAllCnprDocumentType() {
		// TODO Auto-generated method stub
		return (List<CnprDocumentType>) documentRepo.getAllCnprDocumentType();
	}

	@Override
	public List<CnprDocumentType> getAllActiveCnprDocumentType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveOrUpdateCnprDocumentType(CnprDocumentType pers) {
		CnprDocumentType doc = documentRepo.save(pers);

		if (documentRepo.findById(doc.getId()) != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteCnprDocumentTypeById(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void enableOrDisableCnprDocumentTypeById(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CnprDocumentType getCnprDocumentTypeById(long id) {
		// TODO Auto-generated method stub
		return (CnprDocumentType)documentRepo.getById(id);
	}

	
}
