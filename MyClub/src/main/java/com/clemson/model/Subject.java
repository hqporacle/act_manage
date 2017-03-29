package com.clemson.model;

public class Subject {
	private int subjectId;
	private String subjectName;
	private int status;
	private int subTypeId;
	private String subTypeName;
	private int subTypeStatus;
	private int subjectGoalMark;
	
	public int getSubTypeStatus() {
		return subTypeStatus;
	}
	public void setSubTypeStatus(int subTypeStatus) {
		this.subTypeStatus = subTypeStatus;
	}
	public String getSubTypeName() {
		return subTypeName;
	}
	public void setSubTypeName(String subTypeName) {
		this.subTypeName = subTypeName;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getSubTypeId() {
		return subTypeId;
	}
	public void setSubTypeId(int subTypeId) {
		this.subTypeId = subTypeId;
	}
	public int getSubjectGoalMark() {
		return subjectGoalMark;
	}
	public void setSubjectGoalMark(int subjectGoalMark) {
		this.subjectGoalMark = subjectGoalMark;
	}
	
}
