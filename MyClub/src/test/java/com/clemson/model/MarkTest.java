package com.clemson.model;

import mockit.Expectations;
  

 
import org.testng.Assert;
import org.testng.annotations.Test;
 
public class MarkTest {
	private Mark mark = new Mark();
	@Test
	public void getErollNumTest(){
		mark.setEnrollNum(100);
		Assert.assertEquals(100, mark.getEnrollNum());
	}
	@Test
	public void getSubjectIdTest(){
		mark.setSubjectId(100);
		Assert.assertEquals(100, mark.getSubjectId());
	}
	@Test
	public void getExpertIdTest(){
		mark.setExpertId(100);
		Assert.assertEquals(100, mark.getExpertId());
	}
	@Test
	public void getMarkTest(){
		mark.setMark(100.0);
		Assert.assertEquals(100.0, mark.getMark());
	}
	@Test
	public void getCreateTime(){
		mark.setCreateTime("2点30分");
		Assert.assertEquals("2点30分", mark.getCreateTime());
	}
	@Test
	public void test(){
		new Expectations(){};
	}
}
