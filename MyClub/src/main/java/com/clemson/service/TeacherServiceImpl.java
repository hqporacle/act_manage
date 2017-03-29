package com.clemson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clemson.mappers.TeacherMapper;
import com.clemson.model.Teacher;

@Service("TeacherService")
public class TeacherServiceImpl implements TeacherService{
	@Autowired
	private TeacherMapper teacherMapper;
	public void insertTeacher(Teacher teacher){
		teacherMapper.insertTeacher(teacher);
	}
	public Teacher getTeacherByLogin(String idcNumber, String teacherPassword){
		Teacher teacher = teacherMapper.getTeacherByIDCNumber(idcNumber);
		if(teacher !=null && teacher.getTeacherPassword().equals(teacherPassword)){
			return teacher;
		}else{
			return null;
		}
	}
}
