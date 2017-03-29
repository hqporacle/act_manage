package com.clemson.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clemson.mappers.SubjectMapper;
import com.clemson.model.SubTestType;
import com.clemson.model.Subject;

@Service("SubjectService")
public class SubjectServiceImpl implements SubjectService{
	@Autowired
	SubjectMapper subjectMapper;
	
	public void insertSubject(Subject subject){
		subjectMapper.insertSubject(subject);
	}
	
	public ArrayList<Subject> getAllSubject(){
		return subjectMapper.getAllSubject();
	}
	
	public void deleteSubject(Subject subject){
		subjectMapper.deleteSubject(subject);
	}
	
	public void deleteSubjectBySubType(SubTestType subTestType){
		subjectMapper.deleteSubjectBySubType(subTestType);
	}
	
	public void editSubject(Subject subject){
		subjectMapper.editSubject(subject);
	}
	public ArrayList<Subject> getSubjectBySubType(int subTypeId){
		return subjectMapper.getSubjectBySubType(subTypeId);
	}
	
	public void editSubTestTypeStatus(SubTestType subTestType){
		subjectMapper.editSubTestTypeStatus(subTestType);
	}
	
	public Subject getSubjectBySubjectId(int subjectId) {
		return subjectMapper.getSubjectBySubjectId(subjectId);
	}
	
}
