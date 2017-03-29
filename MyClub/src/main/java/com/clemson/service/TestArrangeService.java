package com.clemson.service;

import java.util.ArrayList;

import com.clemson.model.TestArrange;

public interface TestArrangeService {
	public void insertTestArrange(TestArrange testArrange);
	
	public ArrayList<TestArrange> getTestArrange();
	
	public void deleteTestArrange(TestArrange testArrange);
	
	public ArrayList<TestArrange> getTestArrangeBySchoolId(int schoolId);
	
	public ArrayList<TestArrange> getTestArrangeByTestIdAndSchoolId(TestArrange testArrange);
}
