package com.clemson.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clemson.mappers.TestMapper;
import com.clemson.model.Test;

@Service("TestService")
public class TestServiceImpl implements TestService {
	@Autowired
	TestMapper testMapper;
	
	public ArrayList<Test> getTestByTypeId(int typeId) {
		return testMapper.getTestByTypeId(typeId);
	}
	
	public Test getTestByTestId(int testId) {
		return testMapper.getTestByTestId(testId);
	}
	
	public ArrayList<Test> getAllTest(){
		return testMapper.getAllTest();
	}
	
	public void insertTest(Test test){
		testMapper.insertTest(test);
	}
	
	public void deleteTest(Test test){
		testMapper.deleteTest(test);
	}
	
	public void editTest(Test test){
		testMapper.editTest(test);
	}
}
