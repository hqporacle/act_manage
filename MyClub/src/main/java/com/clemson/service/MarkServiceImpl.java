package com.clemson.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clemson.mappers.MarkMapper;
import com.clemson.model.Mark;

@Service("MarkService")
public class MarkServiceImpl implements MarkService{
	@Autowired
	MarkMapper markMapper;
	
	public void insertMark(Mark mark) {
		markMapper.insertMark(mark);
	}
	
	public void insertMarks(ArrayList<Mark> markList) {
		for (Mark mark : markList) {   
			markMapper.insertMark(mark);
		}
		return;
	}
	
	@Override
	public ArrayList<Mark> getMarkByEnrollNum(int enrollNum) {
		return markMapper.getMarkByEnrollNum(enrollNum);
	}
	
	public ArrayList<Mark> getAllMark(){
		return markMapper.getAllMark();
	}
	
	public void editMark(Mark mark){
		markMapper.editMark(mark);
	}
}

