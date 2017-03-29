package com.clemson.model;

public class Test {
	private int testId;
	private int subTypeId;
	private String testName;
	private int status;
	
	// 外键关联项
	private String subTypeName;
	private String typeName;
	private int subTypeStatus;

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}
	
	public int getSubTypeId() {
		return subTypeId;
	}

	public void setSubTypeId(int subTypeId) {
		this.subTypeId = subTypeId;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	//
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

	public int getSubTypeStatus() {
		return subTypeStatus;
	}

	public void setSubTypeStatus(int subTypeStatus) {
		this.subTypeStatus = subTypeStatus;
	}

}
