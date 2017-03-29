package com.clemson.service;

import com.clemson.model.Repo;

public interface RepoService {
	public Repo getRepoByK(String k);
	
	public void setVByK(Repo repo);
}
