package com.clemson.service;

import java.util.ArrayList;
import com.clemson.model.School;
import com.clemson.model.Class;

public interface SchoolClassService {
	public void insertSchool(School school);	
	public ArrayList<School> getAllSchool();
	public void setSchoolBySchoolId(School school);
	public void setStatusBySchoolId(School school);
	public void deleteSchool(School school);

	
	public void insertClass(Class classroom);
	public ArrayList<Class> getAllClass();
	public void setClassByClassId(Class classroom);
	public void setStatusByClassId(Class classroom);
	public void deleteClass(Class classroom);
	public ArrayList<Class> getClassBySchool(int schoolId);

}
