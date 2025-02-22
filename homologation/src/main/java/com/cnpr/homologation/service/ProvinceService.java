package com.cnpr.homologation.service;

import java.util.List;

import com.cnpr.homologation.models.Province;

public interface ProvinceService {

	public List<Province> getAllProvince();

	public List<Province> getAllActiveProvince();

	public boolean saveOrUpdateProvince(Province pers);

	public boolean deleteProvinceById(long id);

	public void enableOrDisableProvinceById(long id);

	public Province getProvinceById(long id);
}
