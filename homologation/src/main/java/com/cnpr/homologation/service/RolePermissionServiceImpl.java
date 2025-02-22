package com.cnpr.homologation.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cnpr.homologation.models.CnprRole;
import com.cnpr.homologation.models.Permission;
import com.cnpr.homologation.models.RolePermission;
import com.cnpr.homologation.repository.RolePermissionRepository;



@Service
@Transactional
public class RolePermissionServiceImpl implements RolePermissionService{

	@Autowired
	RolePermissionRepository rolePermissionRepo;
	
	 @Autowired
	 private JdbcTemplate jdbcTemplate;
	 
	@Override
	public List<RolePermission> getAllRolePermission() {
		// TODO Auto-generated method stub
		return (List<RolePermission>) rolePermissionRepo.findAll();
	}
	
	public List<RolePermission> getAllRolePermissionByRoleID(List<Long> ids) {
		// TODO Auto-generated method stub
		return (List<RolePermission>) rolePermissionRepo.getAllRolePermissionByRoleID(ids);
	}
	
	public List<RolePermission> getAllRolePermissionByPermissionID(List<Long> permissionId) {
		// TODO Auto-generated method stub
		return (List<RolePermission>) rolePermissionRepo.getAllRolePermissionByPermissionID(permissionId);
	}
	

	@Override
	public List<RolePermission> getAllActiveRolePermission() {
		List<RolePermission> list = new ArrayList<>();

		for (Iterator iterator = rolePermissionRepo.findAll().iterator(); iterator.hasNext();) {
			RolePermission rolePermission = (RolePermission) iterator.next();
			if (rolePermission.isActiveStatus() == true) {
				list.add(rolePermission);
			}
		}
		return list;
	}

	@Override
	public boolean saveOrUpdateRolePermission(RolePermission rolePermission) {
		RolePermission updateRolePermission = rolePermissionRepo.save(rolePermission);

		if (rolePermissionRepo.findById(updateRolePermission.getId()) != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteRolePermissionById(long id) {
		rolePermissionRepo.deleteById(id);

		if (rolePermissionRepo.findById(id) != null) {
			return true;
		}
		return false;
	}

	@Override
	public void enableOrDisableRolePermissionById(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public RolePermission getRolePermissionById(long id) {
		// TODO Auto-generated method stub
		return this.rolePermissionRepo.findById(id).get();
	}

	public void bulkInsertPermissionsToRole(List<Permission> allPermissions, CnprRole role) {
		String sql = "INSERT INTO public.tb_role_permission (role_id,permission_id,active_status) VALUES (?,?,true)";
        jdbcTemplate.batchUpdate(sql, allPermissions, 5, (ps, ts) -> {
            ps.setLong(1, role.getId());
            ps.setLong(2, ts.getId());
        });
		
	}

	public List<RolePermission> getAllRolePermissionByRoleName(String role) {
		// TODO Auto-generated method stub
		return (List<RolePermission>)this.rolePermissionRepo.getAllRolePermissionByRoleName( role);
	}

	


}
