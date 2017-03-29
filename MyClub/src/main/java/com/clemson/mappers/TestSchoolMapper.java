package com.clemson.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Select;

import com.clemson.model.TestSchool;

public interface TestSchoolMapper {
	@Select("SELECT DISTINCT s_test_school_subject_class.test_id as testId, s_test_school_subject_class.school_id as schoolId, s_school.school_name as schoolName FROM s_school, s_test_school_subject_class WHERE s_test_school_subject_class.test_id = #{testId} AND s_test_school_subject_class.school_id = s_school.school_id")
	public ArrayList<TestSchool> getSchoolIdAndNameByTestId(int testId);
}
