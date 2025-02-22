package com.cnpr.homologation.service;

import java.util.List;

import com.cnpr.homologation.models.Commune;

public interface CommuneService {

	public List<Commune> getAllCommune();

	public List<Commune> getAllActiveCommune();

	public boolean saveOrUpdateCommune(Commune pers);

	public boolean deleteCommuneById(long id);

	public void enableOrDisableCommuneById(long id);

	public Commune getCommuneById(long id);
}
