package com.clemson.mappers;
import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.clemson.model.Class;

public interface ClassMapper {
	@Insert("INSERT INTO s_class(class_no, school_id, status) VALUES (#{classNo}, #{schoolId},#{status})")
	@Options(useGeneratedKeys = true, keyProperty = "class_id", flushCache = true, keyColumn = "class_id")
	public void insertClass(Class classroom);
	
	@Select("SELECT s_class.class_id as classId, s_class.class_no as classNo, s_school.school_name as schoolName, s_school.school_id as schoolId, s_class.status as status FROM s_class, s_school WHERE s_school.school_id = s_class.school_id order by class_id desc")
	public ArrayList<Class> getAllClass();
	
	@Select("SELECT s_class.class_id as classId, s_class.class_no as classNo, s_school.school_name as schoolName, s_school.school_id as schoolId, s_class.status as status FROM s_class, s_school WHERE s_school.school_id = s_class.school_id AND s_class.school_id = #{schoolId}")
	public ArrayList<Class> getClassBySchool(int schoolId);
	
	@Update("update s_class set class_no = #{classNo}, school_id = #{schoolId}, status = #{status} where class_id = #{classId}")
    public void setClassByClassId(Class classroom);
	
	@Update("update s_class set status = #{status} where class_id = #{classId}")
    public void setStatusByClassId(Class classroom);
	
	@Delete("Delete FROM s_class WHERE class_id = #{classId}")
	public void deleteClass(Class classroom);
}
