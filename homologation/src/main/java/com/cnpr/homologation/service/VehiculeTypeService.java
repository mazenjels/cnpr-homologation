package com.cnpr.homologation.service;

import java.util.List;

import com.cnpr.homologation.models.CnprVehiculeType;

public interface VehiculeTypeService {

	public List<CnprVehiculeType> getAllCnprVehiculeType();

	public List<CnprVehiculeType> getAllActiveCnprVehiculeType();

	public boolean saveOrUpdateCnprVehiculeType(CnprVehiculeType pers);

	public boolean deleteCnprVehiculeTypeById(long id);

	public void enableOrDisableCnprVehiculeTypeById(long id);

	public CnprVehiculeType getCnprVehiculeTypeById(long id);
}
