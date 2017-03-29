package com.clemson.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.clemson.model.Test;

public interface TestMapper {
	@Select("SELECT test_id as testId, s_test.sub_type_id as subTypeId, test_name as testName, s_test.status as status FROM s_test, s_sub_type WHERE s_test.sub_type_id = s_sub_type.sub_type_id and s_sub_type.type_id = #{typeId}")
	public ArrayList<Test> getTestByTypeId(int typeId);
	
	@Select("SELECT s_type.type_name as typeName, test_id as testId, s_test.sub_type_id as subTypeId, test_name as testName, s_test.status as status, sub_type_name as subTypeName FROM s_test, s_sub_type, s_type WHERE s_test.sub_type_id = s_sub_type.sub_type_id AND s_sub_type.type_id = s_type.type_id AND test_id = #{testId}")
	public Test getTestByTestId(int testId);

	@Select("SELECT s_test.test_id as testId, s_test.sub_type_id as subTypeId, s_test.test_name as testName, s_test.status as status, s_sub_type.sub_type_name as subTypeName, s_sub_type.status as subTypeStatus FROM s_test, s_sub_type WHERE s_test.sub_type_id = s_sub_type.sub_type_id order by test_id desc")
	public ArrayList<Test> getAllTest();
	
	@Insert("INSERT INTO s_test(test_name, status, sub_type_id)VALUES(#{testName},#{status},#{subTypeId})")
	@Options(useGeneratedKeys = true, keyProperty = "sub_type_id", flushCache = true, keyColumn = "sub_type_id")
	public void insertTest(Test test);
	
	@Delete("DELETE FROM s_test where s_test.test_id = #{testId}")
	public void deleteTest(Test test);
	
	@Update("UPDATE s_test set test_name = #{testName}, status = #{status}, sub_type_id=#{subTypeId} WHERE test_id = #{testId}")
	public void editTest(Test test); 
}
