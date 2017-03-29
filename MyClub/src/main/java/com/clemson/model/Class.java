package com.clemson.model;

public class Class {
	private int classId;
	private String classNo;
	private int schoolId;
	private int status;
	
	// 外键关联项
	private String schoolName;
		
	public String getSchoolName() {
			return schoolName;
	}
	public void setSchoolName(String schoolName) {
			this.schoolName = schoolName;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public String getClassNo() {
		return classNo;
	}
	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}
	public int getSchoolId() {
		return schoolId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}

}
