package com.cnpr.homologation.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cnpr.homologation.models.CnprAutoEcole;
import com.cnpr.homologation.models.Planification;
import com.cnpr.homologation.models.ViewPlanification;
import com.cnpr.homologation.repository.CnprAutoEcoleRepository;
import com.cnpr.homologation.repository.ViewPlanificationRepository;
import com.cnpr.homologation.service.PlanificationServiceImpl;

@RestController
@CrossOrigin("*")
public class PlanificationRestController {

	@Autowired 
	private PlanificationServiceImpl planificationServiceImpl;
	
	@Autowired
	ViewPlanificationRepository planificationRepo;
	
	@Autowired
	CnprAutoEcoleRepository autoecoleRepo;
	
	@GetMapping("/api/planification/getAllActive")
	public List<Planification> getAllActivePlaniications(){
		return planificationServiceImpl.getAllActivePlanification();
	}
	
	@GetMapping("/planification")
	public ResponseEntity<List<ViewPlanification>> getAllTutorials(@RequestParam(required = false) String title) {
		try {
			List<ViewPlanification> planificationa = new ArrayList<ViewPlanification>();

			if (planificationa.size() == 0) {
				planificationRepo.findAll().forEach(p -> planificationa.add(p));
			} else if (planificationa.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(planificationa, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/autoecole")
	  public ResponseEntity<CnprAutoEcole> createAutoEcole(@RequestBody CnprAutoEcole autoEcole) {
	    try {
	    	CnprAutoEcole autoecole = autoecoleRepo .save(autoEcole);
	      return new ResponseEntity<>(autoEcole, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
}
