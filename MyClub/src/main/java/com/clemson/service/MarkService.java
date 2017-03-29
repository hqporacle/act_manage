package com.clemson.service;

import java.util.ArrayList;

import com.clemson.model.Mark;

public interface MarkService {
	public void insertMark(Mark mark);

	public void insertMarks(ArrayList<Mark> markList);
	
	public ArrayList<Mark> getMarkByEnrollNum(int enrollNum);
	
	public ArrayList<Mark> getAllMark();
	
	public void editMark(Mark mark);
	
}
