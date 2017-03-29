package com.clemson.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import com.clemson.mappers.SubTestTypeMapper;
import com.clemson.model.SubTestType;
import com.clemson.model.Type;

@Service("SubTestTypeService")
public class SubTestTypeServiceImpl implements SubTestTypeService {
	@Autowired
	private SubTestTypeMapper subTestTypeMapper;
	
	public void insertSubTestType(SubTestType subTestType){
		subTestTypeMapper.insertSubTestType(subTestType);
	}
	
	public ArrayList<SubTestType> getAllSubTestType(){
		return subTestTypeMapper.getAllSubTypeTest();
	}
	
	public void deleteSubTestTypeById(SubTestType subTestType){
		subTestTypeMapper.deleteSubTestTypeById(subTestType);
	}
	
	public void deleteSubTestTypeByType(Type type){
		subTestTypeMapper.deleteSubTestTypeByType(type);
	}
	
	public void editSubTestType(SubTestType subTestType){
		subTestTypeMapper.editSubTestType(subTestType);
	}
	
	public void editSubTestTypeStatus(Type type){
		subTestTypeMapper.editSubTestTypeStatus(type);
	}
}
