package com.cnpr.homologation.service;

import java.util.List;

import com.cnpr.homologation.models.Module;

public interface ModuleService {

	public List<Module> getAllModule();

	public List<Module> getAllActiveModule();

	public boolean saveOrUpdateModule(Module pers);

	public boolean deleteModuleById(long id);

	public void enableOrDisableModuleById(long id);

	public Module getModuleById(long id);
}
