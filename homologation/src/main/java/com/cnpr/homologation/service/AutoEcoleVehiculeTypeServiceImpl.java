package com.cnpr.homologation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cnpr.homologation.models.CnprAutoEcoleVehiculeType;
import com.cnpr.homologation.repository.CnprAutoEcoleVehiculeRepository;

@Service
@Transactional
public class AutoEcoleVehiculeTypeServiceImpl implements AutoEcoleVehiculeTypeService{

	@Autowired
	CnprAutoEcoleVehiculeRepository autoVehiculeRepo;
	
	@Override
	public List<CnprAutoEcoleVehiculeType> getAllAutoEcoleVehiculeType() {
		// TODO Auto-generated method stub
		return (List<CnprAutoEcoleVehiculeType>) autoVehiculeRepo.getAllCnprAutoEcoleVehiculeType();
	}

	@Override
	public List<CnprAutoEcoleVehiculeType> getAllActiveAutoEcoleVehiculeType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveOrUpdateAutoEcoleVehiculeType(CnprAutoEcoleVehiculeType pers) {
		CnprAutoEcoleVehiculeType autoEcoleVeh = autoVehiculeRepo.save(pers);

		if (autoVehiculeRepo.findById(autoEcoleVeh.getId()) != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteAutoEcoleVehiculeTypeById(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void enableOrDisableAutoEcoleVehiculeTypeById(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CnprAutoEcoleVehiculeType getAutoEcoleVehiculeTypeById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<CnprAutoEcoleVehiculeType> getAllAutoEcoleVehiculeTypeByAutoEcoleId(long id) {
		// TODO Auto-generated method stub
		return (List<CnprAutoEcoleVehiculeType>) autoVehiculeRepo.getAllCnprAutoEcoleVehiculeTypeByAutoEcoleid(id);
	}

	
}
