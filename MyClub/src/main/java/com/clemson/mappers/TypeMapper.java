package com.clemson.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Select;

import com.clemson.model.Type;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;


public interface TypeMapper {
	@Select("SELECT type_id as typeId, type_name as typeName, status as status FROM s_type")
	public ArrayList<Type> getAllType();
	
	@Insert("INSERT INTO s_type(type_name,status)VALUES(#{typeName},#{status})")
	@Options(useGeneratedKeys = true, keyProperty = "sub_type_id", flushCache = true, keyColumn = "sub_type_id")
	public void insertType(Type type);
	
	@Delete("Delete FROM s_type WHERE type_id = #{typeId}")
	public void deleteType(Type type);
	
	@Update("Update s_type set type_name = #{typeName}, status = #{status} where type_id = #{typeId}")
	public void editType(Type type);
}
