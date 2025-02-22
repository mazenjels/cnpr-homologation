package com.cnpr.homologation.service;

import java.util.List;

import com.cnpr.homologation.models.District;

public interface DistrictService {

	public List<District> getAllDistrict();

	public List<District> getAllActiveDistrict();

	public boolean saveOrUpdateDistrict(District pers);

	public boolean deleteDistrictById(long id);

	public void enableOrDisableDistrictById(long id);

	public District getDistrictById(long id);
}
