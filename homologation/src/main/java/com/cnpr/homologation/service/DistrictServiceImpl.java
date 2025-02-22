package com.cnpr.homologation.service;

import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.cnpr.homologation.models.District;
import com.cnpr.homologation.repository.DistrictRepository;

@Service
@Transactional
public class DistrictServiceImpl implements DistrictService{

	@Autowired
	DistrictRepository districtRepo;
	
	@Override
	public List<District> getAllDistrict() {
		// TODO Auto-generated method stub
		return (List<District>)districtRepo.getAllDistrict();
	}

	@Override
	public List<District> getAllActiveDistrict() {
		// TODO Auto-generated method stub
		return (List<District>)districtRepo.getAllActiveDistrict();
	}
	
	public List<District> getAllActiveDistrictPlanification(Long provinceId) {
		// TODO Auto-generated method stub
		return (List<District>)districtRepo.getAllActiveDistrictPlanification(provinceId);
	}

	@Override
	public boolean saveOrUpdateDistrict(District pers) {
		District district = districtRepo.save(pers);

		if (districtRepo.findById(district.getId()) != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteDistrictById(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void enableOrDisableDistrictById(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public District getDistrictById(long id) {
		// TODO Auto-generated method stub
		return (District) districtRepo.getById(id);
	}

	public List<District> getAllDistrictByProvinceId(Long provinceIds) {
		// TODO Auto-generated method stub
		return (List<District>)districtRepo.findAllDistrictsByProvinceId(provinceIds);
	}

	@SuppressWarnings("unchecked")
	public String districtByProvinceId(String provinceId) {
		// TODO Auto-generated method stub
		JSONArray districtList = new JSONArray();

		for (Iterator<District> iterator = districtRepo.findAll().iterator(); iterator.hasNext();) {
			District district = iterator.next();
			//System.out.println("Departement ID : "+departementId);
			if (district.getProvince().getId() == Long.valueOf(provinceId)) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("district_id", district.getId());
				jsonObj.put("district_designation", district.getDesignation());
				districtList.add(jsonObj);
			}
		}

		return (districtList.toString());
	}

	
}
