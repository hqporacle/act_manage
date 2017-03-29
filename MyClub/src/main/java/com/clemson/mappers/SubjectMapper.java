package com.clemson.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.clemson.model.SubTestType;
import com.clemson.model.Subject;

public interface SubjectMapper {
	@Insert("INSERT INTO s_subject(subject_name, status, sub_type_id, subject_goal_mark) VALUES(#{subjectName},#{status},#{subTypeId},#{subjectGoalMark})")
	@Options(useGeneratedKeys = true, keyProperty = "sub_type_id", flushCache = true, keyColumn = "sub_type_id")
	public void insertSubject(Subject subject);
	
	@Select("SELECT s_subject.subject_goal_mark as subjectGoalMark, s_subject.subject_id as subjectId, s_subject.subject_name as subjectName, s_subject.status as status, s_subject.sub_type_id as subTypeId, s_sub_type.sub_type_name as subTypeName, s_sub_type.status as subTypeStatus FROM s_subject,s_sub_type where s_subject.sub_type_id = s_sub_type.sub_type_id")
	public ArrayList<Subject> getAllSubject();
	
	@Select("SELECT s_subject.subject_goal_mark as subjectGoalMark, s_subject.subject_id as subjectId, s_subject.subject_name as subjectName, s_subject.status as status, s_subject.sub_type_id as subTypeId, s_sub_type.sub_type_name as subTypeName, s_sub_type.status as subTypeStatus FROM s_subject,s_sub_type where s_subject.sub_type_id = s_sub_type.sub_type_id AND s_subject.sub_type_id=#{subTypeId}")
	public ArrayList<Subject> getSubjectBySubType(int subTypeId);
	
	@Delete("DELETE FROM s_subject where s_subject.subject_id = #{subjectId}")
	public void deleteSubject(Subject subject);
	
	@Delete("DELETE FROM s_subject where s_subject.sub_type_id = #{subTypeId}")
	public void deleteSubjectBySubType(SubTestType subTestType);

	@Update("UPDATE s_subject set subject_name = #{subjectName}, status = #{status}, sub_type_id=#{subTypeId}, subject_goal_mark=#{subjectGoalMark} WHERE subject_id = #{subjectId}")
	public void editSubject(Subject subject); 

	@Update("update s_subject set status = #{status} WHERE sub_type_id=#{sub_type_id}")
	public void editSubTestTypeStatus(SubTestType subTesttype);
	
	@Select("SELECT s_subject.subject_goal_mark as subjectGoalMark, s_subject.subject_id as subjectId, s_subject.subject_name as subjectName, s_subject.status as status, s_subject.sub_type_id as subTypeId, s_sub_type.sub_type_name as subTypeName, s_sub_type.status as subTypeStatus FROM s_subject,s_sub_type WHERE s_subject.sub_type_id = s_sub_type.sub_type_id AND s_subject.subject_id = #{subjectId}")
	public Subject getSubjectBySubjectId(int subjectId);
}
