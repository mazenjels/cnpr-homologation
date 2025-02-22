package com.cnpr.homologation.service;

import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.cnpr.homologation.models.Commune;
import com.cnpr.homologation.models.District;
import com.cnpr.homologation.repository.CommuneRepository;

@Service
@Transactional
public class CommuneServiceImpl implements CommuneService{

	@Autowired
	CommuneRepository communeRepo;
	
	@Override
	public List<Commune> getAllCommune() {
		// TODO Auto-generated method stub
		return (List<Commune>)communeRepo.getAllCommune();
	}

	@Override
	public List<Commune> getAllActiveCommune() {
		// TODO Auto-generated method stub
		return (List<Commune>)communeRepo.getAllActiveCommune();
	}
	
	public List<Commune> getAllActiveCommunePlanification(Long districtId) {
		// TODO Auto-generated method stub
		return (List<Commune>)communeRepo.getAllActiveCommunePlanification(districtId);
	}
	
	
	

	@Override
	public boolean saveOrUpdateCommune(Commune pers) {
		Commune commune = communeRepo.save(pers);

		if (communeRepo.findById(commune.getId()) != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteCommuneById(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void enableOrDisableCommuneById(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Commune getCommuneById(long id) {
		// TODO Auto-generated method stub
		return (Commune) communeRepo.getById(id);
	}

	public List<Commune> getAllCommuneByDistrictId(long districtId) {
		// TODO Auto-generated method stub
		return (List<Commune>)communeRepo.findAllDistrictsByProvinceId(districtId);
	}

	@SuppressWarnings("unchecked")
	public String communeByDistrictId(String districtId) {
		// TODO Auto-generated method stub
		JSONArray communeList = new JSONArray();

		for (Iterator<Commune> iterator = communeRepo.findAll().iterator(); iterator.hasNext();) {
			Commune commune = iterator.next();
			//System.out.println("Departement ID : "+departementId);
			if (commune.getDistrict().getId() == Long.valueOf(districtId)) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("commune_id", commune.getId());
				jsonObj.put("commune_designation", commune.getDesignation());
				communeList.add(jsonObj);
			}
		}

		return (communeList.toString());
	}


	
}
