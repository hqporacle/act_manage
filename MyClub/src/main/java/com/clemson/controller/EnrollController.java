package com.clemson.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clemson.model.Enroll;
import com.clemson.model.Mark;
import com.clemson.model.RetestMark;
import com.clemson.model.Student;
import com.clemson.model.Test;
import com.clemson.model.TestSchoolRetest;
import com.clemson.service.EnrollService;
import com.clemson.service.RetestMarkService;
import com.clemson.service.SubjectService;
import com.clemson.service.TestSchoolRetestService;
import com.clemson.service.TestService;
import com.clemson.service.MarkService;

@Controller
public class EnrollController {
	@Autowired
	private EnrollService enrollService;
	@Autowired
	private TestService testService;
	@Autowired
	private MarkService markService;
	@Autowired
	private RetestMarkService retestMarkService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private TestSchoolRetestService testSchoolRetestService;
	
	
	@RequestMapping(value="/studentIndex", method=RequestMethod.GET)
	public String studentIndex(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		int studentId = 0;
		if (session.getAttribute("student") != null) {
			studentId = ((Student) session.getAttribute("student")).getStudentId();
		}
		ArrayList<Enroll> enrollList = enrollService.getEnrollByStudentId(studentId);

		ArrayList<Mark> marklist = null;
		for (Enroll enroll : enrollList) {
			marklist = markService.getMarkByEnrollNum(enroll.getEnrollNum());
			if(marklist.size() != 0){
				enroll.setIsMarkExisted(1);
			}else{
				enroll.setIsMarkExisted(0);
			}
		}

		model.addAttribute("enrollList", enrollList);
		return "studentIndex";
	}
	
	@RequestMapping(value="/type", method=RequestMethod.GET)
	public String enrollViewTypeList() {
		return "viewTypeList";
	}
	
	@RequestMapping(value="/type/{typeName}/", method=RequestMethod.GET)
	public String enrollViewTestList(Model model, @PathVariable("typeName") String typeName) {
		model.addAttribute("typeName", typeName);
		
		// 待改进
		if (typeName.equals("art")) {
			model.addAttribute("typeNameForTitle", "艺术");
			model.addAttribute("testList", testService.getTestByTypeId(1));
			return "viewTestList";
		} 
		if (typeName.equals("sport")) {
			model.addAttribute("typeNameForTitle", "体育");
			model.addAttribute("testList", testService.getTestByTypeId(2));
			return "viewTestList";
		}
		if (typeName.equals("tech")) {
			model.addAttribute("typeNameForTitle", "科技");
			model.addAttribute("testList", testService.getTestByTypeId(3));
			return "viewTestList";
		}
		
		return "viewTypeList";
	}
	
	@RequestMapping(value="/test/{testId}", method=RequestMethod.GET)
	public String enroll(Model model, @PathVariable("testId") int testId, HttpServletRequest request) {
		Test test = testService.getTestByTestId(testId);
		model.addAttribute("test", test);
		
		Enroll enroll = new Enroll();
		model.addAttribute("enroll", enroll);
		
		model.addAttribute("postURL", "test/" + String.valueOf(testId));
		
		HttpSession session = request.getSession(true);
		if (session.getAttribute("student") != null) {
			model.addAttribute("student", (Student)session.getAttribute("student"));
		}
		return "enroll";
	}
	
	@ResponseBody
	@RequestMapping(value="/test/{testId}", method=RequestMethod.POST, produces="text/html;charset=UTF-8")
	public String enroll(@ModelAttribute("enroll") Enroll enroll, HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		Student student = null;
		if (session.getAttribute("student") != null) {
			student = (Student)session.getAttribute("student");
		}
		enroll.setStudentId(student.getStudentId());
		enrollService.insertEnroll(enroll);
		return "<script type='text/javascript'>alert('报名成功！');window.location.href='../studentIndex';</script>";
	}

	@RequestMapping(value="/mark/{enrollNum}", method=RequestMethod.GET)
	public String enrollViewMark(Model model, @PathVariable("enrollNum") int enrollNum, HttpServletRequest request) {
		
		ArrayList<Mark> markList = null;
		markList = markService.getMarkByEnrollNum(enrollNum);
		model.addAttribute("markList", markList);
		String testNameForTitle = markList.get(0).getTestName();
		model.addAttribute("testNameForTitle", testNameForTitle);
		
		RetestMark retestMark = retestMarkService.getRetestMarkByEnrollNum(enrollNum);
		model.addAttribute("retestMark", retestMark);
		
		// 你能被录取吗？
		boolean goalFlag = true;
		
		// 初试各科目不得低于录取分数
		for (Mark mark : markList) {
			if ((subjectService.getSubjectBySubjectId(mark.getSubjectId()).getSubjectGoalMark()) > mark.getMark()) {
				goalFlag = false;
				break;
			}
		}
		
		// 复试各科目总和不得低于某分数
		Enroll enroll = new Enroll();
		enroll.setEnrollNum(retestMark.getEnrollNum());
		enroll = enrollService.getEnrollByEnrollNum(enroll);
		TestSchoolRetest testSchoolRetest = new TestSchoolRetest();
		testSchoolRetest.setTestId(enroll.getTestId());
		testSchoolRetest.setSchoolId(enroll.getSchoolId());
		int retestGoalTotalMark = testSchoolRetestService.getTestSchoolRetestByTestIdAndSchoolId(testSchoolRetest).getRetestGoalTotalMark();
		
		if(retestGoalTotalMark > (retestMark.getRetestEnMark() + retestMark.getRetestPeoMark() + retestMark.getRetestProMark())) {
			goalFlag = false;
		}
		
		if (goalFlag) {
			model.addAttribute("result", "恭喜，您被录取了！");
		} else {
			model.addAttribute("result", "抱歉，您未被录取！");
		}
		
		return "viewMark";
	}
}
