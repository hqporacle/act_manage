package com.clemson.service;

import java.util.ArrayList;

import com.clemson.model.TestSchoolRetest;

public interface TestSchoolRetestService {
	public ArrayList<TestSchoolRetest> getTestSchoolRetestBySchoolId(int schoolId);
	
	public ArrayList<TestSchoolRetest> getTestSchoolRetest();
	
	public void insertTestSchoolRetest(TestSchoolRetest testSchoolRetest);
	
	public void deleteTestSchoolRetest(TestSchoolRetest testSchoolRetest);
	
	public TestSchoolRetest getTestSchoolRetestByTestIdAndSchoolId(TestSchoolRetest testSchoolRetest);
	
	public void setRetestGoalTotalMark(TestSchoolRetest testSchoolRetest);
}
