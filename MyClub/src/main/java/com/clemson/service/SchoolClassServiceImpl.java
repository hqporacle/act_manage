package com.clemson.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clemson.mappers.SchoolMapper;
import com.clemson.mappers.ClassMapper;
import com.clemson.model.Class;
import com.clemson.model.School;


@Service("SchoolClassService")
public class SchoolClassServiceImpl implements SchoolClassService{
	
	@Autowired
	private SchoolMapper schoolMapper;
	@Autowired
	private ClassMapper classMapper;
	
	public void insertSchool(School school){
		schoolMapper.insertSchool(school);		
	}
	
	public ArrayList<School> getAllSchool(){
		return schoolMapper.getAllSchool();		
	}
	
	public void setSchoolBySchoolId(School school){
		schoolMapper.setSchoolBySchoolId(school);
	}
	
	public void setStatusBySchoolId(School school){
		schoolMapper.setStatusBySchoolId(school);
	}
	
	public void insertClass(Class classroom){
		classMapper.insertClass(classroom);
	}
	
	public void deleteSchool(School school){
		schoolMapper.deleteSchool(school);		
	}

	
	public ArrayList<Class> getAllClass(){
		return classMapper.getAllClass();		
	}
	
	public ArrayList<Class> getClassBySchool(int schoolId){
		return classMapper.getClassBySchool(schoolId);
	}
	public void setClassByClassId(Class classroom){
		classMapper.setClassByClassId(classroom);
		
	}
	
	public void setStatusByClassId(Class classroom){
		classMapper.setStatusByClassId(classroom);
		
	}
	
	public void deleteClass(Class classroom){
		classMapper.deleteClass(classroom);
		
	}

}
