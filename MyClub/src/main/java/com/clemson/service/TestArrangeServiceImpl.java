package com.clemson.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.clemson.mappers.TestArrangeMapper;
import com.clemson.model.TestArrange;

@Service("TestArrangeService")
public class TestArrangeServiceImpl implements TestArrangeService{
	@Autowired 
	private TestArrangeMapper testArrangeMapper;
	
	public void insertTestArrange(TestArrange testArrange){
		testArrangeMapper.insertTestArrange(testArrange);
	}
	
	public ArrayList<TestArrange> getTestArrange(){
		return testArrangeMapper.getTestArrange();
	}	

	public void deleteTestArrange(TestArrange testArrange){
		testArrangeMapper.deleteTestArrange(testArrange);
	}
	
	public ArrayList<TestArrange> getTestArrangeBySchoolId(int schoolId) {
		return testArrangeMapper.getTestArrangeBySchoolId(schoolId);
	}
	
	public ArrayList<TestArrange> getTestArrangeByTestIdAndSchoolId(TestArrange testArrange) {
		return testArrangeMapper.getTestArrangeByTestIdAndSchoolId(testArrange);
	}
}
