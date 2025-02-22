package com.cnpr.homologation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cnpr.homologation.models.AutoEcoleTypePermis;
import com.cnpr.homologation.repository.AutoEcoleTypePermisRepository;

@Service
@Transactional
public class AutoEcoleTypePermisServiceImpl implements AutoEcoleTypePermisService{

	@Autowired
	AutoEcoleTypePermisRepository autoPermisRepo;
	
	@Override
	public List<AutoEcoleTypePermis> getAllAutoEcoleTypePermis() {
		// TODO Auto-generated method stub
		return (List<AutoEcoleTypePermis>) autoPermisRepo.getAllAutoEcoleTypePermis();
	}

	@Override
	public List<AutoEcoleTypePermis> getAllActiveAutoEcoleTypePermis() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveOrUpdateAutoEcoleTypePermis(AutoEcoleTypePermis pers) {
		AutoEcoleTypePermis autoEcoleVeh = autoPermisRepo.save(pers);

		if (autoPermisRepo.findById(autoEcoleVeh.getId()) != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteAutoEcoleTypePermisById(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void enableOrDisableAutoEcoleTypePermisById(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AutoEcoleTypePermis getAutoEcoleTypePermisById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<AutoEcoleTypePermis> getAllAutoEcoleTypePermisByAutoEcoleid(long id) {
		// TODO Auto-generated method stub
		return (List<AutoEcoleTypePermis>) autoPermisRepo.getAllAutoEcoleTypePermisByAutoEcoleid(id);
	}

	
}
