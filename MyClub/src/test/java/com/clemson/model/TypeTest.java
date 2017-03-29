package com.clemson.model;

import mockit.Expectations;
  

 
import org.testng.Assert;
import org.testng.annotations.Test;
 
public class TypeTest {
	private Type type = new Type();
	@Test
	public void getTypeIdTest(){
		type.setTypeId(100);
		Assert.assertEquals(100, type.getTypeId());
	}
	@Test
	public void getTypeNameTest(){
		type.setTypeName("100");
		Assert.assertEquals("100", type.getTypeName());
	}
	@Test
	public void getStatusTest(){
		type.setStatus(100);
		Assert.assertEquals(100, type.getStatus());
	}
	@Test
	public void test(){
		new Expectations(){};
	}
}
