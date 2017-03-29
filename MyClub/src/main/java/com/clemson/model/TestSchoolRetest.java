package com.clemson.model;

public class TestSchoolRetest {
	private int testId;
	private int schoolId;
	private int retestClassroomId;
	private String retestStartTime;
	private String retestEndTime;

	private int retestGoalTotalMark;
	
	//外键关联
	private String testName;
	private String schoolName;
	private String classNo;
	
	public int getTestId() {
		return testId;
	}
	public void setTestId(int testId) {
		this.testId = testId;
	}
	public int getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}
	public int getRetestClassroomId() {
		return retestClassroomId;
	}
	public void setRetestClassroomId(int retestClassroomId) {
		this.retestClassroomId = retestClassroomId;
	}
	public String getRetestStartTime() {
		return retestStartTime;
	}
	public void setRetestStartTime(String retestStartTime) {
		this.retestStartTime = retestStartTime;
	}
	public String getRetestEndTime() {
		return retestEndTime;
	}
	public void setRetestEndTime(String retestEndTime) {
		this.retestEndTime = retestEndTime;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getClassNo() {
		return classNo;
	}
	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}
	
	public int getRetestGoalTotalMark() {
		return retestGoalTotalMark;
	}
	public void setRetestGoalTotalMark(int retestGoalTotalMark) {
		this.retestGoalTotalMark = retestGoalTotalMark;
	}
}
