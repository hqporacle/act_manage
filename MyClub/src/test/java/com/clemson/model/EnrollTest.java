package com.clemson.model;

import mockit.Expectations;
  

 
import org.testng.Assert;
import org.testng.annotations.Test;
 
public class EnrollTest {
	private Enroll enroll = new Enroll();
	@Test
	public void getErollNumTest(){
		enroll.setEnrollNum(100);
		Assert.assertEquals(100, enroll.getEnrollNum());
	}
	
	@Test
	public void getStudentIdTest(){
		enroll.setStudentId(100);
		Assert.assertEquals(100, enroll.getStudentId());
	}
	
	@Test
	public void getTestIdTest(){
		enroll.setTestId(100);
		Assert.assertEquals(100, enroll.getTestId());
	}
	
	@Test
	public void getAwardsTest(){
		enroll.setAwards("awards");
		Assert.assertEquals("awards", enroll.getAwards());
	}
	@Test
	public void getEnrollStatusTest(){
		enroll.setEnrollStatus(1);
		Assert.assertEquals(1, enroll.getEnrollStatus());
	}
		@Test
	public void getSchoolIdTest(){
		enroll.setSchoolId(10);
		Assert.assertEquals(10, enroll.getSchoolId());
	}
	
	@Test
	public void getTestNumTest(){
		enroll.setTestNum("10");
		Assert.assertEquals("10", enroll.getTestNum());
	}
	
	@Test
	public void getTestNameTest(){
		enroll.setTestName("name");
		Assert.assertEquals("name", enroll.getTestName());
	}
	
	@Test
	public void getMeaningTest(){
		enroll.setMeaning("meaning");
		Assert.assertEquals("meaning", enroll.getMeaning());
	} 
}
