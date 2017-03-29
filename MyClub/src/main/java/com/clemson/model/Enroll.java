package com.clemson.model;

import java.util.ArrayList;

public class Enroll {
	private int enrollNum;
	private int studentId;
	private int testId;
	private String awards;
	private int enrollStatus;
	private int schoolId;
	private String testNum;

	// 外键关联项
	private String testName;
	private String meaning;
	private String idcNumber;
	private String ceeNumber;
	private String artNumber;
	private String studentName;
	private int studentTypeId;
	private String studentTypeName;
	private String studentSex;
	private String studentNation;
	private String studentBirthday;
	private String studentAddress;
	private String studentSchool;
	private String studentEmail;
	private ArrayList<TestSchool> schoolIdAndName;
	private String schoolName;
	private String schoolAddress;
	private String subTypeName;
	private String typeName;
	private int isMarkExisted;

	public int getEnrollNum() {
		return enrollNum;
	}

	public void setEnrollNum(int enrollNum) {
		this.enrollNum = enrollNum;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public String getAwards() {
		return awards;
	}

	public void setAwards(String awards) {
		this.awards = awards;
	}

	public int getEnrollStatus() {
		return enrollStatus;
	}

	public void setEnrollStatus(int enrollStatus) {
		this.enrollStatus = enrollStatus;
	}

	public int getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}

	public String getTestNum() {
		return testNum;
	}

	public void setTestNum(String testNum) {
		this.testNum = testNum;
	}
	
	//
	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	
	public String getIdcNumber() {
		return idcNumber;
	}

	public void setIdcNumber(String idcNumber) {
		this.idcNumber = idcNumber;
	}

	public String getCeeNumber() {
		return ceeNumber;
	}

	public void setCeeNumber(String ceeNumber) {
		this.ceeNumber = ceeNumber;
	}

	public String getArtNumber() {
		return artNumber;
	}

	public void setArtNumber(String artNumber) {
		this.artNumber = artNumber;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getStudentTypeId() {
		return studentTypeId;
	}

	public void setStudentTypeId(int studentTypeId) {
		this.studentTypeId = studentTypeId;
	}

	public String getStudentTypeName() {
		return studentTypeName;
	}

	public void setStudentTypeName(String studentTypeName) {
		this.studentTypeName = studentTypeName;
	}

	public String getStudentSex() {
		return studentSex;
	}

	public void setStudentSex(String studentSex) {
		this.studentSex = studentSex;
	}

	public String getStudentNation() {
		return studentNation;
	}

	public void setStudentNation(String studentNation) {
		this.studentNation = studentNation;
	}

	public String getStudentBirthday() {
		return studentBirthday;
	}

	public void setStudentBirthday(String studentBirthday) {
		this.studentBirthday = studentBirthday;
	}

	public String getStudentAddress() {
		return studentAddress;
	}

	public void setStudentAddress(String studentAddress) {
		this.studentAddress = studentAddress;
	}

	public String getStudentSchool() {
		return studentSchool;
	}

	public void setStudentSchool(String studentSchool) {
		this.studentSchool = studentSchool;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	
	public ArrayList<TestSchool> getSchoolIdAndName() {
		return schoolIdAndName;
	}

	public void setSchoolIdAndName(ArrayList<TestSchool> schoolIdAndName) {
		this.schoolIdAndName = schoolIdAndName;
	}
	
	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSchoolAddress() {
		return schoolAddress;
	}

	public void setSchoolAddress(String schoolAddress) {
		this.schoolAddress = schoolAddress;
	}
	
	public String getSubTypeName() {
		return subTypeName;
	}

	public void setSubTypeName(String subTypeName) {
		this.subTypeName = subTypeName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getIsMarkExisted() {
		return isMarkExisted;
	}

	public void setIsMarkExisted(int isMarkExisted) {
		this.isMarkExisted = isMarkExisted;
	}
}
