package com.clemson.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;

import com.clemson.model.SubTestType;
import com.clemson.model.Type;

public interface SubTestTypeMapper {
	@Insert("INSERT INTO s_sub_type(sub_type_name, status, type_id)VALUES(#{subTypeName},#{status},#{typeId})")
	@Options(useGeneratedKeys = true, keyProperty = "sub_type_id", flushCache = true, keyColumn = "sub_type_id")
	public void insertSubTestType(SubTestType subTestType);
	
	@Select("SELECT s_sub_type.sub_type_id as subTypeId,s_sub_type.sub_type_name as subTypeName, s_sub_type.status as status, s_sub_type.type_id as typeId, s_type.type_name as typeName, s_type.status as typeStatus FROM s_sub_type,s_type WHERE s_sub_type.type_id = s_type.type_id")
	public ArrayList<SubTestType> getAllSubTypeTest();

	@Delete("DELETE FROM s_sub_type where sub_type_id = #{subTypeId}")
	public void deleteSubTestTypeById(SubTestType subTestType);
	
	@Delete("DELETE FROM s_sub_type where type_id = #{typeId}")
	public void deleteSubTestTypeByType(Type type);
	
	@Update("update s_sub_type set sub_type_name = #{subTypeName}, status = #{status}, type_id = #{typeId} WHERE sub_type_id = #{subTypeId}")
	public void editSubTestType(SubTestType subTestType);
	
	@Update("update s_sub_type set status = #{status} WHERE type_id=#{typeId}")
	public void editSubTestTypeStatus(Type type);
};
