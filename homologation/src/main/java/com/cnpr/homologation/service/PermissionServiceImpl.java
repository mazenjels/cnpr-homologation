package com.cnpr.homologation.service;

import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cnpr.homologation.models.Permission;
import com.cnpr.homologation.repository.PermissionRepository;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService{

	@Autowired
	PermissionRepository permissionRepo;
	
	 @Autowired
	 private JdbcTemplate jdbcTemplate;
	 
	@Override
	public List<Permission> getAllPermission() {
		// TODO Auto-generated method stub
		return (List<Permission>)permissionRepo.getAllPermission();
	}

	@Override
	public List<Permission> getAllActivePermission() {
		// TODO Auto-generated method stub
		return (List<Permission>)permissionRepo.getAllActivePermission();
	}

	@Override
	public boolean saveOrUpdatePermission(Permission pers) {
		Permission permission = permissionRepo.save(pers);

		if (permissionRepo.findById(permission.getId()) != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deletePermissionById(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void enableOrDisablePermissionById(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Permission getPermissionById(long id) {
		// TODO Auto-generated method stub
		return (Permission) permissionRepo.getById(id);
	}

	public void bulkInsertPermissions(List<Permission> peermissions) {
		// TODO Auto-generated method stub
		 String sql = "INSERT INTO public.tb_cnpr_permission (short_code,active_status) VALUES (?,false)";
	        jdbcTemplate.batchUpdate(sql, peermissions, 5, (ps, ts) -> {
	            ps.setString(1, ts.getShortCode());
	        });
	}

	
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		public String permissionByModule(String shortCode) {
			JSONArray permssionList = new JSONArray();

			for (Iterator<Permission> iterator = permissionRepo.findByShortCodeContaining(shortCode).iterator(); iterator.hasNext();) {
				Permission permission = iterator.next();
				//System.out.println("Departement ID : "+departementId);
					JSONObject jsonObj = new JSONObject();
					jsonObj.put("permission_id", permission.getId());
					jsonObj.put("permission_code", permission.getShortCode());
					permssionList.add(jsonObj);
				
			}

			return (permssionList.toString());
			
			
	}

		public Page<Permission> getAllPageable(Pageable pageable) {
			// TODO Auto-generated method stub
			return (Page<Permission>)permissionRepo.getAllPageable(pageable);
		}

	
	
}
