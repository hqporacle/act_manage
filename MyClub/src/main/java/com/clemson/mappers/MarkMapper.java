package com.clemson.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.clemson.model.Mark;

public interface MarkMapper {
	@Insert("INSERT INTO d_mark(enroll_num, subject_id, expert_id, mark, create_time, mark_description) VALUES (#{enrollNum}, #{subjectId}, #{expertId}, #{mark}, #{createTime}, #{markDescription})")
	@Options(flushCache = true)
	public void insertMark(Mark Mark);

	@Select("SELECT d_mark.enroll_num as enrollNum, d_mark.mark_description as markDescription, d_mark.subject_id as subjectId, d_mark.mark as mark, s_subject.subject_name as subjectName, s_test.test_name as testName FROM d_mark, s_subject, s_test, d_enroll WHERE d_mark.subject_id = s_subject.subject_id AND d_mark.enroll_num = d_enroll.enroll_num AND d_enroll.test_id = s_test.test_id AND d_mark.enroll_num = #{enrollNum}")
	public ArrayList<Mark> getMarkByEnrollNum(int enrollNum);
	
	@Select("SELECT d_mark.enroll_num as enrollNum, d_mark.mark_description as markDescription, d_mark.subject_id as subjectId, d_mark.mark as mark, s_subject.subject_name as subjectName, s_test.test_name as testName, s_student.student_name as studentName FROM d_mark, s_subject, s_test, d_enroll, s_student WHERE d_mark.subject_id = s_subject.subject_id AND d_mark.enroll_num = d_enroll.enroll_num AND d_enroll.test_id = s_test.test_id AND d_enroll.student_id = s_student.student_id")
	public ArrayList<Mark> getAllMark();
	
	@Update("update d_mark set mark = #{mark} WHERE enroll_num = #{enrollNum} AND subject_id = #{subjectId} AND mark_description = #{markDescription}")
    public void editMark(Mark mark);
}
