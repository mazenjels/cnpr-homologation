package com.cnpr.homologation.service;

import java.util.List;

import com.cnpr.homologation.models.Planification;

public interface PlanificationService {

	public List<Planification> getAllPlanification();

	public List<Planification> getAllActivePlanification();

	public boolean saveOrUpdatePlanification(Planification pers);

	public boolean deletePlanificationById(long id);

	public void enableOrDisablePlanificationById(long id);

	public Planification getPlanificationById(long id);
}
