package com.clemson.model;

import mockit.Expectations;
  

 
import org.testng.Assert;
import org.testng.annotations.Test;
 
public class StudentTest {
	private Student student = new Student();
	@Test
	public void getStudentIdTest(){
		student.setStudentId(100);
		Assert.assertEquals(100, student.getStudentId());
	}
	@Test
	public void getIdcNumberTest(){
		student.setIdcNumber("12345");
		Assert.assertEquals("12345", student.getIdcNumber());
	}
	@Test
	public void getStudentPasswordTest(){
		student.setStudentPassword("12345");
		Assert.assertEquals("12345", student.getStudentPassword());
	}
	@Test
	public void getCeeNumberTest(){
		student.setCeeNumber("12345");
		Assert.assertEquals("12345", student.getCeeNumber());
	}
	@Test
	public void getArtNumberTest(){
		student.setArtNumber("12345");
		Assert.assertEquals("12345", student.getArtNumber());
	}
	@Test
	public void getStudentNameTest(){
		student.setStudentName("12345");
		Assert.assertEquals("12345", student.getStudentName());
	}
	@Test
	public void getStudentTypeIdTest(){
		student.setStudentTypeId(12345);
		Assert.assertEquals(12345, student.getStudentTypeId());
	}
	@Test
	public void getStudentTypeNameTest(){
		student.setStudentTypeName("12345");
		Assert.assertEquals("12345", student.getStudentTypeName());
	}
	@Test
	public void getStudentSexTest(){
		student.setStudentSex("12345");
		Assert.assertEquals("12345", student.getStudentSex());
	}
	@Test
	public void getStudentNationTest(){
		student.setStudentNation("12345");
		Assert.assertEquals("12345", student.getStudentNation());
	}
	@Test
	public void getStudentBirthdayTest(){
		student.setStudentBirthday("12345");
		Assert.assertEquals("12345", student.getStudentBirthday());
	}
	@Test
	public void getStudentAddressTest(){
		student.setStudentAddress("12345");
		Assert.assertEquals("12345", student.getStudentAddress());
	}
	@Test
	public void getStudentSchoolTest(){
		student.setStudentSchool("12345");
		Assert.assertEquals("12345", student.getStudentSchool());
	}
	@Test
	public void getStudentEmailTest(){
		student.setStudentEmail("12345");
		Assert.assertEquals("12345", student.getStudentEmail());
	}
	@Test
	public void test(){
		new Expectations(){};
	}
}
