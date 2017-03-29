package com.clemson.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.clemson.model.Enroll;

public interface EnrollMapper {
	@Insert("INSERT INTO d_enroll(student_id, test_id, awards, enroll_status, school_id, test_num) VALUES (#{studentId}, #{testId}, #{awards}, #{enrollStatus}, #{schoolId}, #{testNum})")
	@Options(useGeneratedKeys = true, keyProperty = "enroll_num", flushCache = true, keyColumn = "enroll_num")
	public void insertEnroll(Enroll enroll);

	@Select("SELECT s_enroll_status.meaning as meaning, test_name as testName, enroll_num as enrollNum, student_id as studentId, d_enroll.test_id as testId, awards as awards, d_enroll.enroll_status as enrollStatus, school_id as schoolId, test_num as testNum FROM d_enroll, s_test, s_enroll_status WHERE student_id = #{studentId} AND d_enroll.test_id = s_test.test_id AND d_enroll.enroll_status = s_enroll_status.enroll_status order by enroll_num desc")
	public ArrayList<Enroll> getEnrollByStudentId(int studentId);
	
	@Select("SELECT s_student.idc_number as idcNumber, s_student.cee_number as ceeNumber, s_student.art_number as artNumber, s_student.student_name as studentName, s_student.student_type_id as studentTypeId, s_student.student_type_name as studentTypeName, s_student.student_sex as studentSex, s_student.student_nation as studentNation, s_student.student_birthday as studentBirthday, s_student.student_address as studentAddress, s_student.student_school as studentSchool, s_student.student_email as studentEmail, s_enroll_status.meaning as meaning, test_name as testName, enroll_num as enrollNum, s_student.student_id as studentId, d_enroll.test_id as testId, awards as awards, d_enroll.enroll_status as enrollStatus, school_id as schoolId, test_num as testNum FROM d_enroll, s_test, s_enroll_status, s_student WHERE s_student.student_id = d_enroll.student_id AND d_enroll.test_id = s_test.test_id AND d_enroll.enroll_status = s_enroll_status.enroll_status order by enroll_num desc")
	public ArrayList<Enroll> getAllEnroll();
	
	@Select("SELECT s_type.type_name as typeName, s_sub_type.sub_type_name as subTypeName, s_school.school_name as schoolName, s_school.school_address as schoolAddress, s_student.idc_number as idcNumber, s_student.cee_number as ceeNumber, s_student.art_number as artNumber, s_student.student_name as studentName, s_student.student_type_id as studentTypeId, s_student.student_type_name as studentTypeName, s_student.student_sex as studentSex, s_student.student_nation as studentNation, s_student.student_birthday as studentBirthday, s_student.student_address as studentAddress, s_student.student_school as studentSchool, s_student.student_email as studentEmail, s_enroll_status.meaning as meaning, test_name as testName, enroll_num as enrollNum, s_student.student_id as studentId, d_enroll.test_id as testId, awards as awards, d_enroll.enroll_status as enrollStatus, d_enroll.school_id as schoolId, test_num as testNum FROM d_enroll, s_test, s_sub_type, s_type, s_enroll_status, s_student, s_school WHERE s_student.student_id = d_enroll.student_id AND d_enroll.test_id = s_test.test_id AND s_test.sub_type_id = s_sub_type.sub_type_id AND s_type.type_id = s_sub_type.type_id AND d_enroll.enroll_status = s_enroll_status.enroll_status AND d_enroll.school_id = s_school.school_id AND enroll_num = #{enrollNum}")
	public Enroll getEnrollByEnrollNum(Enroll enroll);
	
	@Update("update d_enroll set enroll_status = #{enrollStatus} where enroll_num = #{enrollNum}")
    public void setEnrollStatusByEnrollNum(Enroll enroll);
	
	@Update("update d_enroll set school_id = #{schoolId} where enroll_num = #{enrollNum}")
    public void setSchoolIdByEnrollNum(Enroll enroll);
	
	@Update("update d_enroll set test_num = #{testNum} where enroll_num = #{enrollNum}")
    public void setTestNumByEnrollNum(Enroll enroll);
	
	@Select("SELECT s_type.type_name as typeName, s_sub_type.sub_type_name as subTypeName, s_school.school_name as schoolName, s_school.school_address as schoolAddress, " +
			"s_student.idc_number as idcNumber, s_student.cee_number as ceeNumber, s_student.art_number as artNumber, " +
			"s_student.student_name as studentName, s_student.student_type_id as studentTypeId, s_student.student_type_name as studentTypeName, " +
			"s_student.student_sex as studentSex, s_student.student_nation as studentNation, s_student.student_birthday as studentBirthday, " +
			"s_student.student_address as studentAddress, s_student.student_school as studentSchool, s_student.student_email as studentEmail, " +
			"s_enroll_status.meaning as meaning, test_name as testName, enroll_num as enrollNum, s_student.student_id as studentId, d_enroll.test_id as testId, " +
			"awards as awards, d_enroll.enroll_status as enrollStatus, d_enroll.school_id as schoolId, test_num as testNum " +
			"FROM d_enroll, s_test, s_sub_type, s_type, s_enroll_status, s_student, s_school " +
			"WHERE s_student.student_id = d_enroll.student_id AND d_enroll.test_id = s_test.test_id AND s_test.sub_type_id = s_sub_type.sub_type_id " +
			"AND s_type.type_id = s_sub_type.type_id AND d_enroll.enroll_status = s_enroll_status.enroll_status AND d_enroll.school_id = s_school.school_id " +
			"AND d_enroll.school_id = #{schoolId}")
	public ArrayList<Enroll> getEnrollBySchoolId(int schoolId);
}
