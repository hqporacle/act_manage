package com.clemson.service;

import java.util.ArrayList;

import com.clemson.model.RetestMark;

public interface RetestMarkService {
	public void insertRetestMark(RetestMark retestMark);
	
	public RetestMark getRetestMarkByEnrollNum(int enrollNum);
	
	public ArrayList<RetestMark> getAllMarkRe();
}
