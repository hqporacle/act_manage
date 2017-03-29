package com.clemson.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.clemson.model.TestSchoolRetest;

public interface TestSchoolRetestMapper {
	@Select("SELECT test_id as testId, school_id as schoolId, retest_classroom_id as retestClassroomId, retest_start_time as retestStartTime, " +
			"retest_end_time as retestEndTime, retest_goal_total_mark as retestGoalTotalMark FROM t_test_school_retest " +
			"WHERE school_id = #{schoolId}")
	public ArrayList<TestSchoolRetest> getTestSchoolRetestBySchoolId(int schoolId);
	
	@Select("SELECT t_test_school_retest.retest_goal_total_mark as retestGoalTotalMark, t_test_school_retest.test_id as testId, t_test_school_retest.school_id as schoolId, t_test_school_retest.retest_classroom_id as retestClassroomId, t_test_school_retest.retest_start_time as retestStartTime, t_test_school_retest.retest_end_time as retestEndTime, s_test.test_name as testName, s_school.school_name as schoolName, s_class.class_no as classNo FROM t_test_school_retest, s_test, s_school, s_class WHERE t_test_school_retest.test_id = s_test.test_id AND t_test_school_retest.school_id = s_school.school_id AND t_test_school_retest.retest_classroom_id = s_class.class_id")
	public ArrayList<TestSchoolRetest> getTestSchoolRetest();
	
	@Insert("INSERT INTO t_test_school_retest (test_id, school_id, retest_classroom_id, retest_start_time, retest_end_time, retest_goal_total_mark) VALUES(#{testId}, #{schoolId}, #{retestClassroomId}, #{retestStartTime}, #{retestEndTime}, #{retestGoalTotalMark})")
	public void insertTestSchoolRetest(TestSchoolRetest testSchoolRetest);
	
	@Delete("DELETE FROM  t_test_school_retest WHERE t_test_school_retest.test_id = #{testId} AND t_test_school_retest.school_id=#{schoolId} AND t_test_school_retest.retest_classroom_id= #{retestClassroomId}")
	public void deleteTestSchoolRetest(TestSchoolRetest testSchoolRetest);
	
	
	@Select("SELECT t_test_school_retest.test_id as testId, t_test_school_retest.school_id as schoolId, " +
			"t_test_school_retest.retest_classroom_id as retestClassroomId, t_test_school_retest.retest_start_time as retestStartTime, " +
			"t_test_school_retest.retest_end_time as retestEndTime, s_test.test_name as testName, s_school.school_name as schoolName, " +
			"s_class.class_no as classNo, t_test_school_retest.retest_goal_total_mark as retestGoalTotalMark " +
			"FROM t_test_school_retest, s_test, s_school, s_class " +
			"WHERE t_test_school_retest.test_id = s_test.test_id AND t_test_school_retest.school_id = s_school.school_id " +
			"AND t_test_school_retest.retest_classroom_id = s_class.class_id " +
			"AND t_test_school_retest.school_id = #{schoolId} " +
			"AND t_test_school_retest.test_id = #{testId}")
	public TestSchoolRetest getTestSchoolRetestByTestIdAndSchoolId(TestSchoolRetest testSchoolRetest);
	
	
	@Update("update t_test_school_retest set retest_goal_total_mark = #{retestGoalTotalMark} WHERE school_id = #{schoolId} AND retest_classroom_id = #{retestClassroomId} " +
			"AND test_id = #{testId}")
    public void setRetestGoalTotalMark(TestSchoolRetest testSchoolRetest);
}
