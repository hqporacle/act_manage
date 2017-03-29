package com.clemson.service;

import java.util.ArrayList;

import com.clemson.model.Enroll;

public interface EnrollService {
	public void insertEnroll(Enroll enroll);

	public ArrayList<Enroll> getEnrollByStudentId(int studentId);
	
	public ArrayList<Enroll> getAllEnroll();
	
	public Enroll getEnrollByEnrollNum(Enroll enroll);
	
	public void setEnrollStatusByEnrollNum(Enroll enroll);
	
	public void setSchoolIdByEnrollNum(Enroll enroll);
	
	public void setTestNumByEnrollNum(Enroll enroll);
	
	public void generateTestNum(Enroll enroll);
	
	public void generatePDF(Enroll enroll);
	
	public void insertEnrolls(ArrayList<Enroll> enrollList);
	
	public ArrayList<Enroll> getEnrollBySchoolId(int schoolId);
}
