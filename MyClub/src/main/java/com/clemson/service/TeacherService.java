package com.clemson.service;

import com.clemson.model.Teacher;

public interface TeacherService {
	public void insertTeacher(Teacher teacher);
	public Teacher getTeacherByLogin(String idcNumber, String teacherPassword);
}
