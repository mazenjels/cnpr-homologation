package com.cnpr.homologation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cnpr.homologation.models.AutoEcoleUser;
import com.cnpr.homologation.repository.AutoEcoleUserRepository;

@Service
@Transactional
public class AutoEcoleUserServiceImpl implements AutoEcoleUserService{

	@Autowired
	AutoEcoleUserRepository autoUserRepo;
	
	@Override
	public List<AutoEcoleUser> getAllAutoEcoleUser() {
		// TODO Auto-generated method stub
		return (List<AutoEcoleUser>) autoUserRepo.getAllAutoEcoleUser();
	}

	@Override
	public List<AutoEcoleUser> getAllActiveAutoEcoleUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveOrUpdateAutoEcoleUser(AutoEcoleUser pers) {
		AutoEcoleUser autoEcoleVeh = autoUserRepo.save(pers);

		if (autoUserRepo.findById(autoEcoleVeh.getId()) != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteAutoEcoleUserById(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void enableOrDisableAutoEcoleUserById(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AutoEcoleUser getAutoEcoleUserById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<AutoEcoleUser> getAllAutoEcoleUserByAutoEcoleId(long id) {
		// TODO Auto-generated method stub
		return (List<AutoEcoleUser>) autoUserRepo.getAllAutoEcoleUserByAutoEcoleid(id);
	}

	public List<AutoEcoleUser> getAutoEcoleUserByLoggedUserId(long id) {
		// TODO Auto-generated method stub
		return (List<AutoEcoleUser>) autoUserRepo.getAutoEcoleUserByLoggedUserId(id);
	}

	public List<AutoEcoleUser> getAutoEcoleUserByUserId(long id) {
		// TODO Auto-generated method stub
		return (List<AutoEcoleUser>) autoUserRepo.getAutoEcoleUserByUserId(id);
	}

	
}
