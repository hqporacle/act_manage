package com.clemson.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clemson.mappers.RetestMarkMapper;
import com.clemson.model.RetestMark;

@Service("RetestMarkService")
public class RetestMarkServiceImpl implements RetestMarkService {
	@Autowired
	RetestMarkMapper retestMarkMapper;
	
	public void insertRetestMark(RetestMark retestMark) {
		retestMarkMapper.insertRetestMark(retestMark);
	}
	
	public RetestMark getRetestMarkByEnrollNum(int enrollNum) {
		return retestMarkMapper.getRetestMarkByEnrollNum(enrollNum);
	}
	
	public ArrayList<RetestMark> getAllMarkRe(){
		return retestMarkMapper.getAllMarkRe();
	}
}
