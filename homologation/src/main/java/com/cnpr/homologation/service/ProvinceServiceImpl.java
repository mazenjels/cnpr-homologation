package com.cnpr.homologation.service;

import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cnpr.homologation.models.CnprQuestion;
import com.cnpr.homologation.models.Province;
import com.cnpr.homologation.repository.ProvinceRepository;

@Service
@Transactional
public class ProvinceServiceImpl implements ProvinceService{

	@Autowired
	ProvinceRepository provinceRepo;
	
	@Override
	public List<Province> getAllProvince() {
		// TODO Auto-generated method stub
		return (List<Province>)provinceRepo.getAllProvince();
	}

	@Override
	public List<Province> getAllActiveProvince() {
		// TODO Auto-generated method stub
		return (List<Province>)provinceRepo.getAllActiveProvince();
	}

	@Override
	public boolean saveOrUpdateProvince(Province pers) {
		Province province = provinceRepo.save(pers);

		if (provinceRepo.findById(province.getId()) != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteProvinceById(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void enableOrDisableProvinceById(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Province getProvinceById(long id) {
		// TODO Auto-generated method stub
		return (Province) provinceRepo.getById(id);
	}

	@SuppressWarnings("unchecked")
	public String getProvinceList() {
		JSONArray provinceList = new JSONArray();

		for (Iterator<Province> iterator = provinceRepo.findAll().iterator(); iterator.hasNext();) {
			Province prv = iterator.next();
			if (prv.isActiveStatus() == true) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("province_id", prv.getId());
				jsonObj.put("province_designation", prv.getDesignation());

				provinceList.add(jsonObj);
			}
		}

		return (provinceList.toString());
	}

	public Page<Province> getAllPageable(Pageable pageable) {
		// TODO Auto-generated method stub
		return (Page<Province>)provinceRepo.getAllPageable(pageable);
	}

	
}
