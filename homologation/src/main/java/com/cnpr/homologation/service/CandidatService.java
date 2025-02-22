package com.cnpr.homologation.service;

import java.util.List;

import com.cnpr.homologation.models.Candidat;

public interface CandidatService {

	public List<Candidat> getAllCandidat();

	public List<Candidat> getAllActiveCandidat();

	public boolean saveOrUpdateCandidat(Candidat pers);

	public boolean deleteCandidatById(long id);

	public void enableOrDisableCandidatById(long id);

	public Candidat getCandidatById(long id);
}
