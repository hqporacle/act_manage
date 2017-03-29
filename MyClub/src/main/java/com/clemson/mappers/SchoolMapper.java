package com.clemson.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.clemson.model.School;

public interface SchoolMapper {
	@Insert("INSERT INTO s_school(school_name, school_address, status) VALUES (#{schoolName}, #{schoolAddress}, #{status})")
	@Options(useGeneratedKeys = true, keyProperty = "school_id", flushCache = true, keyColumn = "school_id")
	public void insertSchool(School school);
	
	@Select("SELECT s_school.school_id as schoolId, s_school.school_name as schoolName, s_school.status as status, s_school.school_address as schoolAddress FROM s_school order by s_school.school_id ")
	public ArrayList<School> getAllSchool();
	
	@Update("update s_school set school_name = #{schoolName}, school_address = #{schoolAddress}, status = #{status} where school_id = #{schoolId}")
    public void setSchoolBySchoolId(School school);
	
	@Update("update s_school set status = #{status} where school_id = #{schoolId}")
    public void setStatusBySchoolId(School school);
	
	@Delete("Delete FROM s_school WHERE school_id = #{schoolId}")
	public void deleteSchool(School school);
}
