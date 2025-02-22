package com.cnpr.homologation.service;

import java.util.List;

import com.cnpr.homologation.models.CnprAutoEcoleVehiculeType;


public interface AutoEcoleVehiculeTypeService {

	public List<CnprAutoEcoleVehiculeType> getAllAutoEcoleVehiculeType();

	public List<CnprAutoEcoleVehiculeType> getAllActiveAutoEcoleVehiculeType();

	public boolean saveOrUpdateAutoEcoleVehiculeType(CnprAutoEcoleVehiculeType pers);

	public boolean deleteAutoEcoleVehiculeTypeById(long id);

	public void enableOrDisableAutoEcoleVehiculeTypeById(long id);

	public CnprAutoEcoleVehiculeType getAutoEcoleVehiculeTypeById(long id);
}
