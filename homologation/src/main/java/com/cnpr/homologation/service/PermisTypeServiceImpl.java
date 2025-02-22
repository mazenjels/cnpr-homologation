package com.cnpr.homologation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cnpr.homologation.models.TypePermis;
import com.cnpr.homologation.repository.TypePermisRepository;

@Service
@Transactional
public class PermisTypeServiceImpl implements PermisTypeService{

	@Autowired
	TypePermisRepository typePermisRepo;
	
	@Override
	public List<TypePermis> getAllTypePermis() {
		// TODO Auto-generated method stub
		return (List<TypePermis>)typePermisRepo.getAllTypePermis();
	}

	@Override
	public List<TypePermis> getAllActiveTypePermis() {
		// TODO Auto-generated method stub
		return (List<TypePermis>)typePermisRepo.getAllActiveTypePermis();
	}

	@Override
	public boolean saveOrUpdateTypePermis(TypePermis pers) {
		TypePermis autoEcole = typePermisRepo.save(pers);

		if (typePermisRepo.findById(autoEcole.getId()) != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteTypePermisById(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void enableOrDisableTypePermisById(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TypePermis getTypePermisById(long id) {
		// TODO Auto-generated method stub
		return (TypePermis) typePermisRepo.getById(id);
	}

	
}
