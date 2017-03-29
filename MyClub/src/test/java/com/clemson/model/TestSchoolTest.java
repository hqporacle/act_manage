package com.clemson.model;

import mockit.Expectations;
  

 
import org.testng.Assert;
import org.testng.annotations.Test;
 
public class TestSchoolTest {
	private TestSchool testschool = new TestSchool();
	@Test
	public void getTestIdTest(){
		testschool.setTestId(100);
		Assert.assertEquals(100, testschool.getTestId());
	}
	@Test
	public void getSchoolIdTest(){
		testschool.setSchoolId(100);
		Assert.assertEquals(100, testschool.getSchoolId());
	}
	@Test
	public void getSchoolNameTest(){
		testschool.setSchoolName("100");
		Assert.assertEquals("100", testschool.getSchoolName());
	}
	@Test
	public void test(){
		new Expectations(){};
	}
}
