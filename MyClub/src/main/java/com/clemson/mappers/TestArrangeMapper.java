package com.clemson.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Delete;
import com.clemson.model.TestArrange;

public interface TestArrangeMapper {
	@Insert("INSERT INTO s_test_school_subject_class (test_id, school_id, subject_id, class_id, Date)VALUES(#{testId}, #{schoolId}, #{subjectId}, #{classId}, #{date})")
	public void insertTestArrange(TestArrange testArrange);

	@Select("SELECT s_test.test_id as testId,s_test.test_name as testName, s_school.school_id as schoolId, s_school.school_name as schoolName, " +
			"s_subject.subject_id as subjectId, s_subject.subject_name as subjectName, s_class.class_id as classId, s_class.class_no as classNo, " +
			"s_test_school_subject_class.Date as date " +
			"FROM s_test_school_subject_class, s_test, s_school, s_class, s_subject " +
			"WHERE s_test_school_subject_class.test_id = s_test.test_id AND s_test_school_subject_class.school_id = s_school.school_id " +
			"AND s_test_school_subject_class.subject_id = s_subject.subject_id AND s_test_school_subject_class.class_id = s_class.class_id")
	public ArrayList<TestArrange> getTestArrange();
	
	@Delete("DELETE FROM  s_test_school_subject_class where s_test_school_subject_class.test_id = #{testId} AND s_test_school_subject_class.school_id=#{schoolId} AND s_test_school_subject_class.subject_id=#{subjectId} AND s_test_school_subject_class.class_id= #{classId}")
	public void deleteTestArrange(TestArrange testArrange);
	
	@Select("SELECT s_test.test_id as testId,s_test.test_name as testName, s_school.school_id as schoolId, s_school.school_name as schoolName, " +
			"s_subject.subject_id as subjectId, s_subject.subject_name as subjectName, s_class.class_id as classId, s_class.class_no as classNo, " +
			"s_test_school_subject_class.Date as date " +
			"FROM s_test_school_subject_class, s_test, s_school, s_class, s_subject " +
			"WHERE s_test_school_subject_class.test_id = s_test.test_id AND s_test_school_subject_class.school_id = s_school.school_id " +
			"AND s_test_school_subject_class.subject_id = s_subject.subject_id AND s_test_school_subject_class.class_id = s_class.class_id " +
			"AND s_test_school_subject_class.school_id = #{schoolId}")
	public ArrayList<TestArrange> getTestArrangeBySchoolId(int schoolId);
	
	@Select("SELECT s_test.test_id as testId,s_test.test_name as testName, s_school.school_id as schoolId, s_school.school_name as schoolName, " +
			"s_subject.subject_id as subjectId, s_subject.subject_name as subjectName, s_class.class_id as classId, s_class.class_no as classNo, " +
			"s_test_school_subject_class.Date as date " +
			"FROM s_test_school_subject_class, s_test, s_school, s_class, s_subject " +
			"WHERE s_test_school_subject_class.test_id = s_test.test_id AND s_test_school_subject_class.school_id = s_school.school_id " +
			"AND s_test_school_subject_class.subject_id = s_subject.subject_id AND s_test_school_subject_class.class_id = s_class.class_id " +
			"AND s_test_school_subject_class.school_id = #{schoolId} " +
			"AND s_test_school_subject_class.test_id = #{testId}")
	public ArrayList<TestArrange> getTestArrangeByTestIdAndSchoolId(TestArrange testArrange);
}
