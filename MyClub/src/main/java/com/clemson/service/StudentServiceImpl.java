package com.clemson.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clemson.mappers.StudentMapper;
import com.clemson.model.Student;

@Service("StudentService")
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentMapper studentMapper;

	@Transactional
	public void insertStudent(Student student) {
		studentMapper.insertStudent(student);
	}
	
	public Student getStudentByLogin(String idcNumber, String studentPassword) {
		Student student = studentMapper.getStudentByIDCNumber(idcNumber);
		if (student != null && student.getStudentPassword().equals(studentPassword)) {
			return student;
		}
		return null;
	}

	public boolean getStudentByIDCNumber(String idcNumber) {
		Student student = studentMapper.getStudentByIDCNumber(idcNumber);
		if (student != null) {
			return true;
		}
		return false;
	}
	
	public ArrayList<Student> getStudentBySchoolId(int schoolId) {
		return studentMapper.getStudentBySchoolId(schoolId);
	}
	
	public Student getStudentObjectByIDCNumber(String idcNumber) {
		return studentMapper.getStudentByIDCNumber(idcNumber);
	}
}
