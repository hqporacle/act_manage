package com.clemson.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import com.clemson.model.Teacher;

public interface TeacherMapper {
	@Insert("INSERT INTO s_teacher(teacher_name, teacher_password, idc_number)VALUES(#{teacherName},#{teacherPassword},#{idcNumber})")
	@Options(useGeneratedKeys = true, keyProperty = "teacher_id", flushCache = true, keyColumn = "teacher_id")
	public void insertTeacher(Teacher teacher);
	
	@Select("SELECT teacher_id as teacherId, teacher_name as teacherName, idc_number as idcNumber, teacher_password as teacherPassword from s_teacher WHERE idc_number = #{idcNumber}")
	public Teacher getTeacherByIDCNumber(String idcNumber);
}
