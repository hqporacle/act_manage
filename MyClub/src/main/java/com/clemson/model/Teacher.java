package com.clemson.model;

public class Teacher {
	private int teacherid;
	private String teacherName;
	private String teacherPassword;
	private String idcNumber;
	public int getTeacherid() {
		return teacherid;
	}
	public void setTeacherid(int teacherid) {
		this.teacherid = teacherid;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getTeacherPassword() {
		return teacherPassword;
	}
	public void setTeacherPassword(String teacherPassword) {
		this.teacherPassword = teacherPassword;
	}
	public String getIdcNumber() {
		return idcNumber;
	}
	public void setIdcNumber(String idcNumber) {
		this.idcNumber = idcNumber;
	}
	
}
