package com.cnpr.homologation.service;

import java.util.List;

import com.cnpr.homologation.models.AutoEcoleUser;


public interface AutoEcoleUserService {

	public List<AutoEcoleUser> getAllAutoEcoleUser();

	public List<AutoEcoleUser> getAllActiveAutoEcoleUser();

	public boolean saveOrUpdateAutoEcoleUser(AutoEcoleUser pers);

	public boolean deleteAutoEcoleUserById(long id);

	public void enableOrDisableAutoEcoleUserById(long id);

	public AutoEcoleUser getAutoEcoleUserById(long id);
}
