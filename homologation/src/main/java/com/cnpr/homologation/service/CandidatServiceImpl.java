package com.cnpr.homologation.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.cnpr.homologation.models.Candidat;
import com.cnpr.homologation.repository.CandidatRepository;

@Service
@Transactional
public class CandidatServiceImpl implements CandidatService{

	@Autowired
	CandidatRepository candidatRepo;
	
	@Override
	public List<Candidat> getAllCandidat() {
		// TODO Auto-generated method stub
		return (List<Candidat>)candidatRepo.getAllCandidat();
	}

	@Override
	public List<Candidat> getAllActiveCandidat() {
		// TODO Auto-generated method stub
		return (List<Candidat>)candidatRepo.getAllActiveCandidat();
	}
	
	public  String shuffleString(String input) {
        // Convert the string to a list of characters
        List<Character> characters = new ArrayList<>();
        for (char c : input.toCharArray()) {
            characters.add(c);
        }

        // Shuffle the list
        Collections.shuffle(characters);

        // Convert the list back to a string
        StringBuilder shuffled = new StringBuilder();
        for (char c : characters) {
            shuffled.append(c);
        }

        return shuffled.toString();
    }
	
	

	@Override
	public boolean saveOrUpdateCandidat(Candidat pers) {
		Candidat candidat = candidatRepo.save(pers);

		if (candidatRepo.findById(candidat.getId()) != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteCandidatById(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void enableOrDisableCandidatById(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Candidat getCandidatById(long id) {
		// TODO Auto-generated method stub
		return (Candidat) candidatRepo.getById(id);
	}

	public List<Candidat> getAllCandidatByAutoEcoleId(long autoEcoleId) {
		// TODO Auto-generated method stub
		return (List<Candidat>)candidatRepo.getAllCandidatByAutoEcoleId(autoEcoleId);
	}



	
}
