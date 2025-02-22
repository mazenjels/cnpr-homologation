package com.cnpr.homologation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cnpr.homologation.models.PaymentMode;



public interface PaymentModeRepository extends JpaRepository<PaymentMode, Long>{

	
	@Query("SELECT t FROM PaymentMode t")
	List<PaymentMode> getAllPaymentMode();
	
	@Query("SELECT t FROM PaymentMode t where t.activeStatus=true")
	List<PaymentMode> getAllActivePaymentMode();

	
}
