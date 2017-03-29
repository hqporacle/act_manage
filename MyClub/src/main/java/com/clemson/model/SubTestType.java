package com.clemson.model;

public class SubTestType {
	private int subTypeId;
	private String subTypeName;
	private int status;
	private int typeId;
	private String typeName;
	private int typeStatus;
	
	public int getTypeStatus() {
		return typeStatus;
	}
	public void setTypeStatus(int typeStatus) {
		this.typeStatus = typeStatus;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getSubTypeId() {
		return subTypeId;
	}
	public void setSubTypeId(int subTypeId) {
		this.subTypeId = subTypeId;
	}
	public String getSubTypeName() {
		return subTypeName;
	}
	public void setSubTypeName(String subTypeName) {
		this.subTypeName = subTypeName;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

}
