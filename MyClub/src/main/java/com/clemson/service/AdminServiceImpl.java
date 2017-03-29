package com.clemson.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clemson.mappers.AdminMapper;
import com.clemson.model.Admin;

@Service("AdminService")
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminMapper adminMapper;
	public void insertAdmin(Admin admin){
		adminMapper.insertAdmin(admin);
	}
	public Admin getAdminByLogin(String idcNumber, String adminPassword){
		Admin admin = adminMapper.getAdminByIDCNumber(idcNumber);
		if(admin !=null && admin.getAdminPassword().equals(adminPassword)){
			return admin;
		}else{
			return null;
		}
	}
}
