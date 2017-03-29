package com.clemson.controller;
 

import java.util.ArrayList;
 

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
 

import com.clemson.model.*;
import com.clemson.service.EnrollService;
import com.clemson.service.TestService;


 
@Test
public class EnrollControllerTest {
	@Tested
	private EnrollController enrollController = new EnrollController();
	@Mocked
	Model model;  
	@Injectable
	private EnrollService enrollService;
	@Injectable
	private TestService testService;
	@Mocked 
	HttpServletRequest httpRequest;
	@Mocked
	HttpSession session;
	public void testIndex(){
		final Student student = new Student();
		student.setStudentId(1);
		final ArrayList<Enroll> enrollList = new ArrayList<Enroll>();
		enrollList.add(new Enroll()); 
		new NonStrictExpectations(){
			{
				httpRequest.getSession();
				result = session;
				session.getAttribute(anyString);
				result = student;
				enrollService.getEnrollByStudentId(1);
				result = new ArrayList<Enroll>(); 
			}
		};
		Assert.assertEquals("studentIndex", enrollController.studentIndex(model,httpRequest));
	}
	
	public void enrollViewTypeList(){
		Assert.assertEquals("viewTypeList", enrollController.enrollViewTypeList());
	}
	
	public void enrollViewTestList(){  
		new Expectations(){
			{
				testService.getTestByTypeId(anyInt);
				result = new ArrayList<Test>(); 
			}
		};
		Assert.assertEquals("viewTestList", enrollController.enrollViewTestList(model, "art"));
		Assert.assertEquals("viewTestList", enrollController.enrollViewTestList(model, "sport"));
		Assert.assertEquals("viewTestList", enrollController.enrollViewTestList(model, "tech"));
		Assert.assertEquals("viewTypeList", enrollController.enrollViewTestList(model, "other"));
	}	
	
	public void enroll(){
		final Student student = new Student();
		student.setStudentId(10);
		new NonStrictExpectations(){
			{
				httpRequest.getSession();
				result = session;
				session.getAttribute(anyString);
				result = student;
				enrollService.insertEnroll(withAny(new Enroll()));
			}
		};
		enrollController.enroll(new Enroll(), httpRequest);
	}
}
