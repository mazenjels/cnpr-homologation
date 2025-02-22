package com.cnpr.homologation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.cnpr.homologation.models.CnprRole;
import com.cnpr.homologation.models.CnprUser;
import com.cnpr.homologation.repository.CnprUserRepository;

@Service
@Transactional
public class  UserServiceImpl implements UserService{

	@Autowired
	CnprUserRepository userRepo;
	
	private  BCryptPasswordEncoder bCryptPasswordEncoder =  null;
	
	@Override
	public List<CnprUser> getAllCnprUser() {
		// TODO Auto-generated method stub
		return (List<CnprUser>) userRepo.getAllCnprUser();
	}

	@Override
	public List<CnprUser> getAllActiveCnprUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveOrUpdateCnprUser(CnprUser user) {
		
		bCryptPasswordEncoder = new BCryptPasswordEncoder();
		user.setPassword(bCryptPasswordEncoder.encode(user.getUsername()));
		
		CnprUser doc = userRepo.save(user);

		if (userRepo.findById(doc.getId()) != null) {
			return true;
		}
		return false;
	}
	
		public CnprUser saveOrAndReturnCnprUser(CnprUser user) {
		
		bCryptPasswordEncoder = new BCryptPasswordEncoder();
		user.setPassword(bCryptPasswordEncoder.encode(user.getUsername()));
		
		CnprUser doc = userRepo.save(user);

		if (userRepo.findById(doc.getId()) != null) {
			return doc;
		}
		return null;
	}

	@Override
	public boolean deleteCnprUserById(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void enableOrDisableCnprUserById(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CnprUser getCnprUserById(long id) {
		// TODO Auto-generated method stub
		return this.userRepo.findById(id).get();
	}

	public CnprUser getUserByUsername(String username) {
		// TODO Auto-generated method stub
				
		return this.userRepo.getUserByUsername(username);
	}

	public int updatePassword(String passwordBCrypted, String username) {
		// TODO Auto-generated method stub
		return this.userRepo.updatePassword(passwordBCrypted,username);
	}
	
}
