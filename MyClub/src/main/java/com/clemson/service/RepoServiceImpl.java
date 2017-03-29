package com.clemson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clemson.mappers.RepoMapper;
import com.clemson.model.Repo;

@Service("RepoService")
public class RepoServiceImpl implements RepoService {
	@Autowired
	RepoMapper repoMapper;
	
	public Repo getRepoByK(String k) {
		return repoMapper.getRepoByK(k);
	}
	
	public void setVByK(Repo repo) {
		repoMapper.setVByK(repo);
	}
}
