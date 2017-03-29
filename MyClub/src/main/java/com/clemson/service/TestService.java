package com.clemson.service;

import java.util.ArrayList;

import com.clemson.model.Test;

public interface TestService {
	public ArrayList<Test> getTestByTypeId(int typeId);
	
	public Test getTestByTestId(int testId);
	
	public ArrayList<Test> getAllTest();
	
	public void insertTest(Test test);
	
	public void deleteTest(Test test);
	
	public void editTest(Test test); 
}
