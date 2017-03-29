package com.clemson.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Select;

import com.clemson.model.RetestMark;

public interface RetestMarkMapper {
	@Select("INSERT INTO d_retest_mark(enroll_num, expert_id, retest_en_mark, retest_pro_mark, retest_peo_mark, createTime) VALUES (#{enrollNum}, #{expertId}, #{retestEnMark}, #{retestProMark}, #{retestPeoMark}, #{createTime})")
	public void insertRetestMark(RetestMark retestMark);
	
	@Select("SELECT enroll_num as enrollNum, retest_en_mark as retestEnMark, retest_pro_mark as retestProMark, retest_peo_mark as retestPeoMark, createTime FROM d_retest_mark WHERE enroll_num = #{enrollNum}")
	public RetestMark getRetestMarkByEnrollNum(int enrollNum);
	
	@Select("SELECT d_retest_mark.enroll_num as enrollNum, d_retest_mark.retest_en_mark as retestEnMark, d_retest_mark.retest_pro_mark as retestProMark, d_retest_mark.retest_peo_mark as retestPeoMark, d_retest_mark.createTime, s_test.test_name as testName, s_student.student_name as studentName FROM d_retest_mark, d_enroll, s_test, s_student WHERE d_retest_mark.enroll_num = d_enroll.enroll_num AND d_enroll.test_id = s_test.test_id AND d_enroll.student_id = s_student.student_id")
	public ArrayList<RetestMark> getAllMarkRe();
}
