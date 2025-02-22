package com.cnpr.homologation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cnpr.homologation.models.Module;
import com.cnpr.homologation.repository.ModuleRepository;

@Service
@Transactional
public class ModuleServiceImpl implements ModuleService{

	@Autowired
	ModuleRepository moduleRepo;
	
	@Override
	public List<com.cnpr.homologation.models.Module> getAllModule() {
		// TODO Auto-generated method stub
		return (List<Module>)moduleRepo.getAllModule();
	}

	@Override
	public List<Module> getAllActiveModule() {
		// TODO Auto-generated method stub
		return (List<Module>)moduleRepo.getAllActiveModule();
	}

	@Override
	public boolean saveOrUpdateModule(Module pers) {
		Module module = moduleRepo.save(pers);

		if (moduleRepo.findById(module.getId()) != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteModuleById(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void enableOrDisableModuleById(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Module getModuleById(long id) {
		// TODO Auto-generated method stub
		return (Module) moduleRepo.getById(id);
	}



	
}
