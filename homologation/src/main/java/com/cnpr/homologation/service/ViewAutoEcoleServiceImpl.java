package com.cnpr.homologation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cnpr.homologation.models.ViewAutoEcole;
import com.cnpr.homologation.repository.ViewAutoEcoleRepository;
import com.cnpr.homologation.repository.PaymentRepository;

@Service
@Transactional
public class ViewAutoEcoleServiceImpl implements ViewAutoEcoleService {

	@Autowired
	ViewAutoEcoleRepository autoEcoleRepo;

	@Autowired
	PaymentRepository paymentRepository;

	@Override
	public List<ViewAutoEcole> getAllViewAutoEcole() {
		// TODO Auto-generated method stub
		return null;
	}
	public List<ViewAutoEcole> getAllViewAutoEcoleByPlanification(long planificationId) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public List<ViewAutoEcole> getAllActiveViewAutoEcole() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveOrUpdateViewAutoEcole(ViewAutoEcole pers) {
		ViewAutoEcole autoEcole = autoEcoleRepo.save(pers);

		if (autoEcoleRepo.findById(autoEcole.getId()) != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteViewAutoEcoleById(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void enableOrDisableViewAutoEcoleById(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public ViewAutoEcole getViewAutoEcoleById(long id) {
		// TODO Auto-generated method stub
		return (ViewAutoEcole) autoEcoleRepo.getById(id);
	}
	@Override
	public String getDashboardSummary() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
