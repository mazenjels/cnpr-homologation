package com.cnpr.homologation.service;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cnpr.homologation.models.CnprAutoEcole;
import com.cnpr.homologation.repository.CandidatRepository;
import com.cnpr.homologation.repository.CnprAutoEcoleRepository;
import com.cnpr.homologation.repository.PaymentRepository;

@Service
@Transactional
public class AutoEcoleServiceImpl implements AutoEcoleService {

	@Autowired
	CnprAutoEcoleRepository autoEcoleRepo;
	
	@Autowired
	CandidatRepository candidatRepo;

	@Autowired
	PaymentRepository paymentRepository;

	@Override
	public List<CnprAutoEcole> getAllCnprAutoEcole() {
		// TODO Auto-generated method stub
		return (List<CnprAutoEcole>) autoEcoleRepo.getAllCnprAutoEcole();
	}
	public List<CnprAutoEcole> getAllCnprAutoEcoleByPlanification(long planificationId) {
		// TODO Auto-generated method stub
		return (List<CnprAutoEcole>) autoEcoleRepo.getAllCnprAutoEcoleByPlanification(planificationId);
	}
	

	@Override
	public List<CnprAutoEcole> getAllActiveCnprAutoEcole() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveOrUpdateCnprAutoEcole(CnprAutoEcole pers) {
		CnprAutoEcole autoEcole = autoEcoleRepo.save(pers);

		if (autoEcoleRepo.findById(autoEcole.getId()) != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteCnprAutoEcoleById(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void enableOrDisableCnprAutoEcoleById(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public CnprAutoEcole getCnprAutoEcoleById(long id) {
		// TODO Auto-generated method stub
		return (CnprAutoEcole) autoEcoleRepo.getById(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getDashboardSummary() {
		// Summary summary = autoEcoleRepo.getTotalEcole();

		Long totalAutoEcole = 0L;
		Long totalHomologation=0L;
		Long totalCandatidat=0L;
		Long totalCDF=0L;
		Long totalUSD =0L;
		if (autoEcoleRepo.getEcoles().getTotal().equals(null)) {
			 totalAutoEcole = 0L;
			 
		}else {
			totalAutoEcole = autoEcoleRepo.getEcoles().getTotal();
		}
		if (autoEcoleRepo.getEcoles().getTotal().equals(null)) {
			totalHomologation=0L;
		}else {
			totalHomologation = autoEcoleRepo.getHomologations().getTotal();
		}
		if (autoEcoleRepo.getEcoles().getTotal().equals(null)) {
			totalCDF =0L;
		}else {
			totalCDF = paymentRepository.getAmountCDF().getTotal();
		}
		if (autoEcoleRepo.getEcoles().getTotal().equals(null)) {
			totalUSD =0L;
		}else {
			totalUSD = paymentRepository.getAmountUSD().getTotal();
		}
		if (candidatRepo.getTotalCandidatInscrit().equals(null)) {
			totalCandatidat =0L;
		}else {
			totalCandatidat = candidatRepo.getTotalCandidatInscrit().getTotal();
		}
//		try {
//			
//			 totalAutoEcole = autoEcoleRepo.getEcoles().getTotal();
//			 totalHomologation = autoEcoleRepo.getHomologations().getTotal();
//			 totalCDF = paymentRepository.getAmountCDF().getTotal();
//			 totalUSD = paymentRepository.getAmountUSD().getTotal();
//			
//		} catch (NullPointerException e) {
//			// TODO: handle exception
//		}finally {
//			 totalAutoEcole = 0;
//			 totalHomologation=0;
//			 totalCDF=0;
//			 totalUSD =0;
//		}
		

		JSONObject jsonEffectif = new JSONObject();
		jsonEffectif.put("auto_ecole", totalAutoEcole);
		jsonEffectif.put("total_cdf", totalCDF);
		jsonEffectif.put("total_usd", totalUSD);
		jsonEffectif.put("total_usd", totalUSD);
		jsonEffectif.put("total_candidats_inscrits", totalCandatidat);
		return jsonEffectif.toJSONString();
	}
	public List<CnprAutoEcole> getAllCnprAutoEcoleByCodeUnique(String codeUnique) {
		// TODO Auto-generated method stub
		return (List<CnprAutoEcole>) autoEcoleRepo.getAllCnprAutoEcoleByCodeUnique(codeUnique);
	}
	
	public List<CnprAutoEcole> getAllCnprAutoEcoleByProvinceOrDistrictOrCommune(long provinceId,long districtId, long communeId) {
		// TODO Auto-generated method stub
		return (List<CnprAutoEcole>) autoEcoleRepo.getAllCnprAutoEcoleByProvinceOrDistrictOrCommune(provinceId,districtId,communeId);
	}
	
	public List<CnprAutoEcole> getAllCnprAutoEcoleByProvinceOrDistrict(long provinceId,long districtId) {
		// TODO Auto-generated method stub
		return (List<CnprAutoEcole>) autoEcoleRepo.getAllCnprAutoEcoleByProvinceOrDistrict(provinceId,districtId);
	}
	
	public List<CnprAutoEcole> getAllCnprAutoEcoleByProvince(long provinceId) {
		// TODO Auto-generated method stub
		return (List<CnprAutoEcole>) autoEcoleRepo.getAllCnprAutoEcoleByProvince(provinceId);
	}
	public Page<CnprAutoEcole> getAllPageable(Pageable pageable) {
		// TODO Auto-generated method stub
		return (Page<CnprAutoEcole>)autoEcoleRepo.getAllPageable(pageable);
	}
	

}
