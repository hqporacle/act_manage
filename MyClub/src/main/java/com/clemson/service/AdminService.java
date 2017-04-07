package com.clemson.service;

import com.clemson.model.Admin;

public interface AdminService {
	public void insertAdmin(Admin admin);
	public Admin getAdminByLogin(String name, String adminPassword);
}
