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
import com.clemson.controller.MobileController;
import com.clemson.model.*;
import com.clemson.service.EnrollService;
import com.clemson.service.MarkService;
import com.clemson.model.Mark;
import java.util.ArrayList;
@Test
public class MobileControllerTest {
	@Tested
	MobileController mobileController;
	@Injectable
	private MarkService markService;
	@Injectable
	private EnrollService enrollService;
	@Mocked
	private HttpServletRequest request;
	public void testTest(){

	}

	public void acceptMark(){

	}
}
