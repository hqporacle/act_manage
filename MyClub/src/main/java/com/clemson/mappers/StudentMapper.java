package com.clemson.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.clemson.model.Student;

public interface StudentMapper {
	@Insert("INSERT INTO s_student(idc_number, student_password, cee_number, art_number, student_name, student_type_id, student_type_name, student_sex, student_nation, student_birthday, student_address, student_school, student_email) VALUES (#{idcNumber}, #{studentPassword}, #{ceeNumber}, #{artNumber}, #{studentName}, #{studentTypeId}, #{studentTypeName}, #{studentSex}, #{studentNation}, #{studentBirthday}, #{studentAddress}, #{studentSchool}, #{studentEmail})")
	@Options(useGeneratedKeys = true, keyProperty = "student_id", flushCache = true, keyColumn = "student_id")
	public void insertStudent(Student student);

	@Select("SELECT student_id as studentId, idc_number as idcNumber, student_password as studentPassword, cee_number as ceeNumber, art_number as artNumber, student_name as studentName, student_type_id as studentTypeId, student_type_name as studentTypeName, student_sex as studentSex, student_nation as studentNation, student_birthday as studentBirthday, student_address as studentAddress, student_school as studentSchool, student_email as studentEmail FROM s_student WHERE idc_number = #{idcNumber}")
	public Student getStudentByIDCNumber(String idcNumber);
	
	@Select("SELECT s_student.student_id as studentId, s_student.idc_number as idcNumber, s_student.cee_number as ceeNumber," +
			" s_student.art_number as artNumber, s_student.student_name as studentName, s_student.student_type_id as studentTypeId, " +
			"s_student.student_type_name as studentTypeName, s_student.student_sex as studentSex, " +
			"s_student.student_nation as studentNation, s_student.student_birthday as studentBirthday, s_student.student_address as studentAddress, " +
			"s_student.student_school as studentSchool, " +
			"s_student.student_email as studentEmail FROM s_student WHERE s_student.student_id in " +
			"(SELECT DISTINCT student_id FROM d_enroll WHERE school_id = #{schoolId})")
	public ArrayList<Student> getStudentBySchoolId(int schoolId);
}