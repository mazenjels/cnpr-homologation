package com.cnpr.homologation.service;

import java.util.List;

import com.cnpr.homologation.models.Formateur;

public interface FormateurService {

	public List<Formateur> getAllFormateur();

	public List<Formateur> getAllActiveFormateur();

	public boolean saveOrUpdateFormateur(Formateur pers);

	public boolean deleteFormateurById(long id);

	public void enableOrDisableFormateurById(long id);

	public Formateur getFormateurById(long id);
}
