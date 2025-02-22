package com.cnpr.homologation.service;

import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.cnpr.homologation.models.Formateur;
import com.cnpr.homologation.models.District;
import com.cnpr.homologation.repository.FormateurRepository;

@Service
@Transactional
public class FormateurServiceImpl implements FormateurService{

	@Autowired
	FormateurRepository formateurRepo;
	
	@Override
	public List<Formateur> getAllFormateur() {
		// TODO Auto-generated method stub
		return (List<Formateur>)formateurRepo.getAllFormateur();
	}

	@Override
	public List<Formateur> getAllActiveFormateur() {
		// TODO Auto-generated method stub
		return (List<Formateur>)formateurRepo.getAllActiveFormateur();
	}
	
	
	

	@Override
	public boolean saveOrUpdateFormateur(Formateur pers) {
		Formateur formateur = formateurRepo.save(pers);

		if (formateurRepo.findById(formateur.getId()) != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteFormateurById(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void enableOrDisableFormateurById(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Formateur getFormateurById(long id) {
		// TODO Auto-generated method stub
		return (Formateur) formateurRepo.getById(id);
	}

	public List<Formateur> getAllFormateurByAuoEcoleId(long autoEcoleId) {
		// TODO Auto-generated method stub
		return (List<Formateur>)formateurRepo.getAllFormateurByAutoEcoleId(autoEcoleId);
	}



	
}
