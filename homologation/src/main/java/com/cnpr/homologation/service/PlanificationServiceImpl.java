package com.cnpr.homologation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cnpr.homologation.models.Planification;
import com.cnpr.homologation.models.Province;
import com.cnpr.homologation.repository.PlanificationRepository;

@Service
@Transactional
public class PlanificationServiceImpl implements PlanificationService{

	@Autowired
	PlanificationRepository planificationRepo;
	
	@Override
	public List<Planification> getAllPlanification() {
		// TODO Auto-generated method stub
		return (List<Planification>)planificationRepo.getAllPlanification();
	}

	@Override
	public List<Planification> getAllActivePlanification() {
		// TODO Auto-generated method stub
		return (List<Planification>)planificationRepo.getAllActivePlanification();
	}

	@Override
	public boolean saveOrUpdatePlanification(Planification pers) {
		Planification planification = planificationRepo.save(pers);

		if (planificationRepo.findById(planification.getId()) != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deletePlanificationById(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void enableOrDisablePlanificationById(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Planification getPlanificationById(long id) {
		// TODO Auto-generated method stub
		return (Planification) planificationRepo.getById(id);
	}

	public Page<Planification> getAllPageable(Pageable pageable) {
		// TODO Auto-generated method stub
		return (Page<Planification>)planificationRepo.getAllPageable(pageable);
	}

	
}
