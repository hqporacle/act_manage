package com.clemson.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.clemson.model.Admin;

public interface AdminMapper {
	@Insert("INSERT INTO s_admin(admin_name, admin_password, idc_number)VALUES(#{adminName},#{adminPassword},#{idcNumber})")
	@Options(useGeneratedKeys = true, keyProperty = "admin_id", flushCache = true, keyColumn = "admin_id")
	public void insertAdmin(Admin admin);
	
	@Select("SELECT admin_id as adminId, admin_name as adminName,idc_number as idcNumber, admin_password as adminPassword from s_admin WHERE idc_number = #{idcNumber}")
	public Admin getAdminByIDCNumber(String idcNumber);
}
