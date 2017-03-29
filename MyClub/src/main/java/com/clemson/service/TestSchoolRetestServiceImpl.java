package com.clemson.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clemson.mappers.TestSchoolRetestMapper;
import com.clemson.model.TestSchoolRetest;

@Service("TestSchoolRetestService")
public class TestSchoolRetestServiceImpl implements TestSchoolRetestService {
	@Autowired
	TestSchoolRetestMapper testSchoolRetestMapper;
	
	public ArrayList<TestSchoolRetest> getTestSchoolRetestBySchoolId(int schoolId) {
		return testSchoolRetestMapper.getTestSchoolRetestBySchoolId(schoolId);
	}
	
	public ArrayList<TestSchoolRetest> getTestSchoolRetest(){
		return testSchoolRetestMapper.getTestSchoolRetest();
	}
	public void insertTestSchoolRetest(TestSchoolRetest testSchoolRetest){
		testSchoolRetestMapper.insertTestSchoolRetest(testSchoolRetest);
	}
	
	public void deleteTestSchoolRetest(TestSchoolRetest testSchoolRetest){
		testSchoolRetestMapper.deleteTestSchoolRetest(testSchoolRetest);
	}
	
	public TestSchoolRetest getTestSchoolRetestByTestIdAndSchoolId(TestSchoolRetest testSchoolRetest) {
		return testSchoolRetestMapper.getTestSchoolRetestByTestIdAndSchoolId(testSchoolRetest);
	}
	
	public void setRetestGoalTotalMark(TestSchoolRetest testSchoolRetest) {
		testSchoolRetestMapper.setRetestGoalTotalMark(testSchoolRetest);
	}
}
