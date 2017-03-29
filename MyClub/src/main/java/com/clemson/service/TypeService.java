package com.clemson.service;

import java.util.ArrayList;

import com.clemson.model.Type;

public interface TypeService {
	public ArrayList<Type> getAllType();
	
	public void insertType(Type type);
	
	public void deleteType(Type type);
	
	public void editType(Type type);
}
