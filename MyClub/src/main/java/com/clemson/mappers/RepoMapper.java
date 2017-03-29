package com.clemson.mappers;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.clemson.model.Repo;

public interface RepoMapper {
	
	@Select("SELECT * FROM t_repo WHERE k = #{k}")
	public Repo getRepoByK(String k);
	
	@Update("update t_repo set v = #{v} where k = #{k}")
    public void setVByK(Repo repo);
}
