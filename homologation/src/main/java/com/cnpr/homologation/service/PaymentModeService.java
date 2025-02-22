package com.cnpr.homologation.service;

import java.util.List;

import com.cnpr.homologation.models.PaymentMode;

public interface PaymentModeService {

	public List<PaymentMode> getAllPaymentMode();

	public List<PaymentMode> getAllActivePaymentMode();

	public boolean saveOrUpdatePaymentMode(PaymentMode pers);

	public boolean deletePaymentModeById(long id);

	public void enableOrDisablePaymentModeById(long id);

	public PaymentMode getPaymentModeById(long id);
}
