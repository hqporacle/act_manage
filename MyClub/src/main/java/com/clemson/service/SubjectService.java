package com.clemson.service;

import java.util.ArrayList;

import com.clemson.model.*;
 

public interface SubjectService {
	public void insertSubject(Subject subject);
	
	public ArrayList<Subject> getAllSubject();
	
	public void deleteSubject(Subject subject);
	
	public void deleteSubjectBySubType(SubTestType subTestType);

	public void editSubject(Subject subject); 
	
	public void editSubTestTypeStatus(SubTestType subTestType);

	public ArrayList<Subject> getSubjectBySubType(int subTypeId);
	
	public Subject getSubjectBySubjectId(int subjectId);
}
