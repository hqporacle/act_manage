package com.clemson.controller;

import mockit.Injectable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
 
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.Tested;
 
import org.testng.Assert;
import org.testng.annotations.Test; 
import org.springframework.ui.Model;
 

import com.clemson.controller.AdminController;
import com.clemson.model.*;
import com.clemson.service.EnrollService;
import com.clemson.service.TypeService;

@Test
public class AdminControllerTest {
	@Tested
	private AdminController adminController;
	
	@Injectable
	private TypeService typeService;
	
	@Mocked
	Model model;
	
	@Mocked
	HttpServletRequest request;
	public void adminLoginTest(){
		Assert.assertEquals("adminLogin", adminController.adminLogin(model));
	}
	
	/*
	public void adminIndexTest(){
		Assert.assertEquals("adminIndex", adminController.adminIndex(model));
	}
	
	public void adminTestTypeEditTest(){ 
	}
	
	public void adminTestSubjectTest(){
		Assert.assertEquals("adminTestSubject", adminController.adminTestSubject(model));
	}
	
	public void adminSchoolTest(){
		Assert.assertEquals("adminSchool", adminController.adminSchool(model));
	}
	
	public void adminClass(){
		Assert.assertEquals("adminClass", adminController.adminClass(model));
	}
	
	public void adminTestSchool(){
		Assert.assertEquals("adminTestSchool", adminController.adminTestSchool(model));
	}
	*/
}
