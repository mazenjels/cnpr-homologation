package com.cnpr.homologation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cnpr.homologation.models.CnprRole;
import com.cnpr.homologation.repository.CnprRoleRepository;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

	@Autowired
	CnprRoleRepository roleRepo;
	
	@Override
	public List<CnprRole> getAllCnprRole() {
		// TODO Auto-generated method stub
		return (List<CnprRole>) roleRepo.getAllCnprRole();
	}

	@Override
	public List<CnprRole> getAllActiveCnprRole() {
		// TODO Auto-generated method stub
		return (List<CnprRole>) roleRepo.getAllActiveCnprRole();
	}

	@Override
	public boolean saveOrUpdateCnprRole(CnprRole pers) {
		CnprRole doc = roleRepo.save(pers);

		if (roleRepo.findById(doc.getId()) != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteCnprRoleById(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void enableOrDisableCnprRoleById(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CnprRole getCnprRoleById(long id) {
		// TODO Auto-generated method stub
		return (CnprRole) roleRepo.getById(id);
	}

	
}
