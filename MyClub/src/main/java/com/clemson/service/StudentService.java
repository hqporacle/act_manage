package com.clemson.service;

import java.util.ArrayList;

import com.clemson.model.Student;

public interface StudentService {
	public void insertStudent(Student student);

	public Student getStudentByLogin(String idcNumber, String studentPassword);

	public boolean getStudentByIDCNumber(String idcNumber);
	
	public ArrayList<Student> getStudentBySchoolId(int schoolId);
	
	public Student getStudentObjectByIDCNumber(String idcNumber);
}