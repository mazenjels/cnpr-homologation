package com.cnpr.homologation.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cnpr.homologation.models.CnprPayment;
import com.cnpr.homologation.pojo.Summary;



public interface PaymentRepository extends JpaRepository<CnprPayment, Long>{

	
	@Query("SELECT t FROM CnprPayment t")
	List<CnprPayment> getAllPayment();
	
	@Query("SELECT t FROM CnprPayment t where t.autoEcole.id=?1")
	List<CnprPayment> getAllPaymentByAutoEcole(long autoEcoleId);
	

	 @Query("select new com.cnpr.homologation.pojo.Summary(SUM(m.amount)) from CnprPayment m where m.currencyCode='USD'")
	 public Summary getAmountUSD();
	 
	 @Query("select new com.cnpr.homologation.pojo.Summary(SUM(m.amount)) from CnprPayment m where m.currencyCode='CDF'")
	 public Summary getAmountCDF();
	 
	 @Query("select new com.cnpr.homologation.pojo.Summary(SUM(m.amount)) from CnprPayment m where m.autoEcole.id=?1 and m.currencyCode='USD'")
	 public Summary getAmountUSDByAutoEcole(long autEcoleId);
	 
	 @Query("select new com.cnpr.homologation.pojo.Summary(SUM(m.amount)) from CnprPayment m where m.autoEcole.id=?1 and m.currencyCode='CDF'")
	 public Summary getAmountCDFByAutoEcole(long autEcoleId);

	 @Query("select t from CnprPayment t")
	Page<CnprPayment> getAllPageable(Pageable pageable);
}
