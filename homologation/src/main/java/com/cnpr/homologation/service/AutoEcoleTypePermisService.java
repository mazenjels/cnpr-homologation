package com.cnpr.homologation.service;

import java.util.List;

import com.cnpr.homologation.models.AutoEcoleTypePermis;


public interface AutoEcoleTypePermisService {

	public List<AutoEcoleTypePermis> getAllAutoEcoleTypePermis();

	public List<AutoEcoleTypePermis> getAllActiveAutoEcoleTypePermis();

	public boolean saveOrUpdateAutoEcoleTypePermis(AutoEcoleTypePermis pers);

	public boolean deleteAutoEcoleTypePermisById(long id);

	public void enableOrDisableAutoEcoleTypePermisById(long id);

	public AutoEcoleTypePermis getAutoEcoleTypePermisById(long id);
}
