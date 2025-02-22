package com.cnpr.homologation.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cnpr.homologation.models.BankPaiement;


public interface BankPaiementRepository extends JpaRepository<BankPaiement, Long>{

	@Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE public.tb_cnpr_bank_payment", nativeQuery = true)
    void truncateTable();
	
}
