package com.cnpr.homologation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cnpr.homologation.models.CnprVehiculeType;
import com.cnpr.homologation.repository.CnprVehiculeTypeRepository;

@Service
@Transactional
public class VehiculeTypeServiceImpl implements VehiculeTypeService{

	@Autowired
	CnprVehiculeTypeRepository vehiculeTypeRepo;
	
	@Override
	public List<CnprVehiculeType> getAllCnprVehiculeType() {
		// TODO Auto-generated method stub
		return (List<CnprVehiculeType>)vehiculeTypeRepo.getAllCnprVehiculeType();
	}

	@Override
	public List<CnprVehiculeType> getAllActiveCnprVehiculeType() {
		// TODO Auto-generated method stub
		return (List<CnprVehiculeType>)vehiculeTypeRepo.getAllActiveCnprVehiculeType();
	}

	@Override
	public boolean saveOrUpdateCnprVehiculeType(CnprVehiculeType pers) {
		CnprVehiculeType autoEcole = vehiculeTypeRepo.save(pers);

		if (vehiculeTypeRepo.findById(autoEcole.getId()) != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteCnprVehiculeTypeById(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void enableOrDisableCnprVehiculeTypeById(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CnprVehiculeType getCnprVehiculeTypeById(long id) {
		// TODO Auto-generated method stub
		return (CnprVehiculeType) vehiculeTypeRepo.getById(id);
	}

	
}
