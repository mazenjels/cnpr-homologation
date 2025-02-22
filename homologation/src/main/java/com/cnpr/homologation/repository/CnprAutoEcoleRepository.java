package com.cnpr.homologation.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cnpr.homologation.models.CnprAutoEcole;
import com.cnpr.homologation.pojo.Summary;



public interface CnprAutoEcoleRepository extends JpaRepository<CnprAutoEcole, Long>{

//	@Query("select t from CnprAutoEcole where t.codeUnique=?1")
//	List<CnprAutoEcole> getAllCnprAutoEcoleByCodeUnique(long codeUnique);

	@Query("select t from CnprAutoEcole t where t.province.id=?1 or t.district.id=?2 or t.commune.id=?3")
	List<CnprAutoEcole> getAllCnprAutoEcoleByProvinceOrDistrictOrCommune(long provinceId, long districtId,
			long communeId);

	@Query("select t from CnprAutoEcole t where t.province.id=?1 or t.district.id=?2")
	List<CnprAutoEcole> getAllCnprAutoEcoleByProvinceOrDistrict(long provinceId, long districtId);

	@Query("select t from CnprAutoEcole t where t.codeUnique=?1")
	List<CnprAutoEcole> getAllCnprAutoEcoleByCodeUnique(String codeUnique);
	
	@Query("select t from CnprAutoEcole t")
	List<CnprAutoEcole> getAllCnprAutoEcole();

	@Query("select t from CnprAutoEcole t where t.planification.id=?1")
	List<CnprAutoEcole> getAllCnprAutoEcoleByPlanification(long planificationId);

	@Query("select new com.cnpr.homologation.pojo.Summary(count(t)) from CnprAutoEcole t")
	Summary getEcoles();

	@Query("select new com.cnpr.homologation.pojo.Summary(count(t)) from CnprAutoEcole t where t.activeStatus=true")
	Summary getHomologations();

	@Query("select t from CnprAutoEcole t where t.province.id=?1")
	List<CnprAutoEcole> getAllCnprAutoEcoleByProvince(long provinceId);

	@Query("select t from CnprAutoEcole t")
	Page<CnprAutoEcole> getAllPageable(Pageable pageable);


	
	
}
