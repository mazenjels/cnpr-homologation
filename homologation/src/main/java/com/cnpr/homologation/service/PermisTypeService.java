package com.cnpr.homologation.service;

import java.util.List;

import com.cnpr.homologation.models.TypePermis;

public interface PermisTypeService {

	public List<TypePermis> getAllTypePermis();

	public List<TypePermis> getAllActiveTypePermis();

	public boolean saveOrUpdateTypePermis(TypePermis pers);

	public boolean deleteTypePermisById(long id);

	public void enableOrDisableTypePermisById(long id);

	public TypePermis getTypePermisById(long id);
}
