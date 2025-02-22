package com.cnpr.homologation.service;

import java.util.List;

import com.cnpr.homologation.models.CnprPayment;

public interface PaymentService {

	public List<CnprPayment> getAllCnprPayment();

	public List<CnprPayment> getAllActiveCnprPayment();

	public boolean saveOrUpdateCnprPayment(CnprPayment pers);

	public boolean deleteCnprPaymentById(long id);

	public void enableOrDisableCnprPaymentById(long id);

	public CnprPayment getCnprPaymentById(long id);
}
