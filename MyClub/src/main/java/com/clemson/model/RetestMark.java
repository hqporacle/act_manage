package com.clemson.model;

public class RetestMark {
	private int enrollNum;
	private int expertId;
	private int retestEnMark;
	private int retestProMark;
	private int retestPeoMark;
	private String createTime;
	
	//外键关联
	private String studentName;
	private String testName;
	
	public int getEnrollNum() {
		return enrollNum;
	}
	public void setEnrollNum(int enrollNum) {
		this.enrollNum = enrollNum;
	}
	public int getExpertId() {
		return expertId;
	}
	public void setExpertId(int expertId) {
		this.expertId = expertId;
	}
	public int getRetestEnMark() {
		return retestEnMark;
	}
	public void setRetestEnMark(int retestEnMark) {
		this.retestEnMark = retestEnMark;
	}
	public int getRetestProMark() {
		return retestProMark;
	}
	public void setRetestProMark(int retestProMark) {
		this.retestProMark = retestProMark;
	}
	public int getRetestPeoMark() {
		return retestPeoMark;
	}
	public void setRetestPeoMark(int retestPeoMark) {
		this.retestPeoMark = retestPeoMark;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
}
