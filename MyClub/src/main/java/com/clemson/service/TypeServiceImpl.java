package com.clemson.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clemson.mappers.TypeMapper;
import com.clemson.model.Type;

@Service("TypeService")
public class TypeServiceImpl implements TypeService {
	@Autowired
	TypeMapper typeMapper;
	
	public ArrayList<Type> getAllType() {
		return typeMapper.getAllType();
	}
	
	public void insertType(Type type){
		typeMapper.insertType(type);
	}
	
	public void deleteType(Type type){
		typeMapper.deleteType(type);
	}
	public void editType(Type type){
		typeMapper.editType(type);
	}
}
