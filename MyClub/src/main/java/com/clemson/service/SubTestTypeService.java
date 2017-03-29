package com.clemson.service;

import java.util.ArrayList;

import com.clemson.model.SubTestType;
import com.clemson.model.Type;

public interface SubTestTypeService {
	public void insertSubTestType(SubTestType subTestType);
	
	public ArrayList<SubTestType> getAllSubTestType();
	
	public void deleteSubTestTypeById(SubTestType subTestType);
	
	public void deleteSubTestTypeByType(Type type);
	
	public void editSubTestType(SubTestType subTestType);
	
	public void editSubTestTypeStatus(Type type);
}
