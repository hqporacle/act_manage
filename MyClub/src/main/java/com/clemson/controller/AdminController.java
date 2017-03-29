package com.clemson.controller;

 
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clemson.model.*;
import com.clemson.model.Class;
import com.clemson.service.AdminService;
import com.clemson.service.QuestionService;
import com.clemson.service.RepoService;
import com.clemson.service.SchoolClassService;
import com.clemson.service.SubTestTypeService;
import com.clemson.service.SubjectService;
import com.clemson.service.TestArrangeService;
import com.clemson.service.TypeService;
import com.clemson.service.TestService;
import com.clemson.service.TestSchoolRetestService;
import com.clemson.service.MarkService;
import com.clemson.service.RetestMarkService;

@Controller
public class AdminController {
	@Autowired
	private TypeService typeService;
	@Autowired
	private SubTestTypeService subTestTypeService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private TestService testService;
	@Autowired
	private TestArrangeService testArrangeService;
	@Autowired
	private SchoolClassService schoolClassService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private TestSchoolRetestService testSchoolRetestService;
	@Autowired
	private RepoService repoService;
	@Autowired
	private MarkService markService;
	@Autowired
	private RetestMarkService retestMarkService;
	
	@RequestMapping(value="/adminLogin", method=RequestMethod.GET)
	public String adminLogin(Model model) {
		Admin admin = new Admin();
		model.addAttribute("admin", admin);
		return "adminLogin";
	}
	
	@ResponseBody
	@RequestMapping(value="/adminLogin", method=RequestMethod.POST, produces="text/html;charset=UTF-8")
	public String adminLogin(@ModelAttribute("admin") Admin admin, HttpServletRequest request) {
		Admin adminLogin = adminService.getAdminByLogin(admin.getIdcNumber(), admin.getAdminPassword());
	    if (adminLogin != null) {
	    	HttpSession session = request.getSession(true);
			session.setAttribute("admin", adminLogin);
			return "<script type='text/javascript'>window.location.href='adminIndex';</script>";
	    } else {
	    	return "<script type='text/javascript'>alert('用户名或密码错误，请重新输入！');window.history.back(-1);</script>";
	    }
	}
	
	@ResponseBody
	@RequestMapping(value="/adminLogout", method=RequestMethod.GET, produces="text/html;charset=UTF-8")
	public String logout(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		session.invalidate();
		return "<script type='text/javascript'>alert('注销成功！');window.location.href='adminLogin';</script>";
	}
	
	@RequestMapping(value="/adminIndex", method=RequestMethod.GET)
	public String adminIndex(Model model) {
		return "adminIndex";
	}
	
	/*
	 * CURD FOR Type
	 */
	@RequestMapping(value="/adminTestType", method=RequestMethod.GET)
	public String adminTestType(Model model) {
		model.addAttribute("typeList", typeService.getAllType());
		return "adminTestType";
	}
	
 
	@RequestMapping(value="/adminTestTypeEdit", method=RequestMethod.POST)
	public String adminTestTypeEdit(HttpServletRequest request) {
		int typeIdEdit = Integer.valueOf(request.getParameter("typeIdEdit")); 
		String typeNameEdit = (String) request.getParameter("typeNameEdit");
		int status = 0;
		if(request.getParameter("status")!=null){
			status = Integer.parseInt(request.getParameter("status"));
		}
		Type type = new Type();
		type.setStatus(status);
		type.setTypeId(typeIdEdit);
		type.setTypeName(typeNameEdit);
		typeService.editType(type);
		subTestTypeService.editSubTestTypeStatus(type);
		return "redirect:/adminTestType";
	}
	@ResponseBody
	@RequestMapping(value="adminTestTypeTypeDelete",method = RequestMethod.POST)
	public String adminTestTypeDelete(HttpServletRequest request){
		int typeId = Integer.parseInt(request.getParameter("typeId"));
		Type type = new Type();
		type.setTypeId(typeId);
		typeService.deleteType(type);
		subTestTypeService.deleteSubTestTypeByType(type);
		return "adminTestType";
	}
	
	@RequestMapping(value="/adminTestTypeAdd", method=RequestMethod.POST)
	public String adminTestTypeAdd(HttpServletRequest request) { 
		String typeName = request.getParameter("typeName");
		Type type = new Type();
		type.setStatus(0); 
		type.setTypeName(typeName);
		typeService.insertType(type);
		return "redirect:/adminTestType";
	}
	
	/*
	 * CURD FOR SubType
	 */
	@RequestMapping(value="/adminTestSubType", method=RequestMethod.GET)
	public String adminTestSubType(Model model) {
		model.addAttribute("subTypeList", subTestTypeService.getAllSubTestType()); 
		model.addAttribute("typeList", typeService.getAllType());
		return "adminTestSubType";
	}
	 
	@RequestMapping(value="/adminTestSubTypeAdd", method=RequestMethod.POST)
	public String adminTestSubTypeAdd(HttpServletRequest request) {
		int typeId = Integer.parseInt(request.getParameter("typeId"));
		String subTypeName = request.getParameter("subTypeName");
		SubTestType subTestType = new SubTestType();
		subTestType.setStatus(0);
		subTestType.setTypeId(typeId);
		subTestType.setSubTypeName(subTypeName);
		subTestTypeService.insertSubTestType(subTestType);
		return "redirect:/adminTestSubType";
	}
 
	@RequestMapping(value="adminTestSubTypeEdit",method = RequestMethod.POST)
	public String adminSubTestTypeEdit(HttpServletRequest request){
		int subTypeId = Integer.valueOf(request.getParameter("subTestTypeId"));
		String subTypeName = request.getParameter("editSubTypeName");
		int typeId = Integer.valueOf(request.getParameter("editTypeId"));
		int status = 0;
		if( request.getParameter("status")!=null){
			status= Integer.parseInt(request.getParameter("status"));
		}
		SubTestType subTestType = new SubTestType();
		subTestType.setSubTypeId(subTypeId);
		subTestType.setStatus(status);
		subTestType.setSubTypeName(subTypeName);
		subTestType.setTypeId(typeId);
		subTestTypeService.editSubTestType(subTestType);
		return "redirect:/adminTestSubType";
	}
 
	@ResponseBody
	@RequestMapping(value="adminTestSubTypeDelete",method = RequestMethod.POST)
	public String adminSubTestDelete(HttpServletRequest request){
		int subTypeId = Integer.parseInt(request.getParameter("subTypeId"));
		SubTestType subTestType = new SubTestType();
		subTestType.setSubTypeId(subTypeId);
		subTestTypeService.deleteSubTestTypeById(subTestType);
		return "adminTestSubType";
	}
	/*
	 * CURD FOR Subject
	 */
	@RequestMapping(value="/adminTestSubject", method=RequestMethod.GET)
	public String adminTestSubject(Model model) {
		model.addAttribute("subTypeList", subTestTypeService.getAllSubTestType()); 
		model.addAttribute("subjectList", subjectService.getAllSubject());
		return "adminTestSubject";
	}
	
	@RequestMapping(value="/adminTestSubjectAdd", method=RequestMethod.POST)
	public String adminTestSubjectAdd(HttpServletRequest request) {
		int subTypeId = Integer.parseInt(request.getParameter("subTypeId"));
		String subjectName = request.getParameter("subjectName");
		int subjectGoalMark = Integer.parseInt(request.getParameter("subjectGoalMark"));
		Subject subject = new Subject();
		subject.setStatus(0);
		subject.setSubjectName(subjectName); 
		subject.setSubTypeId(subTypeId);
		subject.setSubjectGoalMark(subjectGoalMark);
		subjectService.insertSubject(subject);
		return "redirect:/adminTestSubject";
	}
 
	@RequestMapping(value="adminTestSubjectEdit",method = RequestMethod.POST)
	public String adminTestSubjectEdit(HttpServletRequest request){
		int subjectId = Integer.valueOf(request.getParameter("subjectId"));
		String subjectName = request.getParameter("editSubjectName");
		int subTypeId = Integer.valueOf(request.getParameter("editSubTypeId"));
		int subjectGoalMark = Integer.valueOf(request.getParameter("editSubjectGoalMark"));
		int status = 0;
		if( request.getParameter("status")!=null){
			status= Integer.parseInt(request.getParameter("status"));
		}
		Subject subject = new Subject();
		subject.setStatus(status);
		subject.setSubjectId(subjectId);
		subject.setSubjectName(subjectName);
		subject.setSubTypeId(subTypeId);
		subject.setSubjectGoalMark(subjectGoalMark);
		subjectService.editSubject(subject);
		return "redirect:/adminTestSubject";
	}
	
	@RequestMapping(value="adminTestSubjectDelete",method = RequestMethod.POST)
	public String adminTestSubjectDelete(HttpServletRequest request){
		int subjectId = Integer.valueOf(request.getParameter("subjectId"));
		Subject subject = new Subject();
		subject.setSubjectId(subjectId);
		subjectService.deleteSubject(subject);
		return "redirect:/adminTestSubject";
	}
	
	/*
	 * CURD FOR TestArrange
	 * 
	 */
	@RequestMapping(value="/adminTestArrange", method=RequestMethod.GET)
	public String adminTestArrange(Model model) {
		model.addAttribute("testList", testService.getAllTest()); 
		model.addAttribute("schoolList", schoolClassService.getAllSchool());
		model.addAttribute("testArrangeList", testArrangeService.getTestArrange());
		return "adminTestArrange";
	}
	
	@RequestMapping(value="/adminTestArrangeAdd", method=RequestMethod.POST)
	public String adminTestArrangeAdd(HttpServletRequest request) {
		int testId = Integer.parseInt(request.getParameter("testId"));
		int schoolId = Integer.parseInt(request.getParameter("schoolId"));
		int subjectId = Integer.parseInt(request.getParameter("subjectId"));
		int classId = Integer.parseInt(request.getParameter("classId"));
		String date = request.getParameter("date");
		TestArrange testArrange = new TestArrange();
		testArrange.setTestId(testId);
		testArrange.setClassId(classId);
		testArrange.setDate(date);
		testArrange.setSchoolId(schoolId);
		testArrange.setSubjectId(subjectId);
		testArrangeService.insertTestArrange(testArrange);
		return "redirect:/adminTestArrange";
	}
	
	@ResponseBody
	@RequestMapping(value="getSubjectByTest",method = RequestMethod.POST)
	public ArrayList<Subject> adminTestArrangeGetSubjectByTest(HttpServletRequest request){
		int testId =Integer.parseInt(request.getParameter("testId"));
		ArrayList<Subject> subjectList = subjectService.getSubjectBySubType(testService.getTestByTestId(testId).getSubTypeId());
		return subjectList;
	}
	
	@ResponseBody
	@RequestMapping(value="getClassBySchool",method = RequestMethod.POST)
	public ArrayList<Class> adminTestArrangeGetClassBySchool(HttpServletRequest request){
		int schoolId =Integer.parseInt(request.getParameter("schoolId"));
		ArrayList<Class> classList = schoolClassService.getClassBySchool(schoolId);
		return classList;
	}
	
	@ResponseBody
	@RequestMapping(value="adminTestArrangeDelete",method = RequestMethod.POST)
	public String adminTestArrangeDelete(HttpServletRequest request){
		int testId = Integer.parseInt(request.getParameter("testId"));
		int schoolId = Integer.parseInt(request.getParameter("schoolId"));
		int subjectId = Integer.parseInt(request.getParameter("subjectId"));
		int classId = Integer.parseInt(request.getParameter("classId"));
		TestArrange testArrange = new TestArrange();
		testArrange.setClassId(classId);
		testArrange.setSchoolId(schoolId);
		testArrange.setTestId(testId);
		testArrange.setSubjectId(subjectId);
		testArrangeService.deleteTestArrange(testArrange);
		return "adminTestArrange";
	}
	
	// 下面那个估计是不需要了，用存储过程吧
	// 是根本就不用了，用查询语句！
	/*
	@RequestMapping(value="/adminTestSchool", method=RequestMethod.GET)
	public String adminTestSchool(Model model) {
		return "adminTestSchool";
	}
	*/
	
	/*
	 * CURD FOR Test
	 */
	@RequestMapping(value="/adminTestManagement", method=RequestMethod.GET)
	public String adminTestManagement(Model model) {
		model.addAttribute("subTypeList", subTestTypeService.getAllSubTestType()); 
		model.addAttribute("testList", testService.getAllTest());
		return "adminTestManagement";
	}
	
	@RequestMapping(value="/adminTestManagementAdd", method=RequestMethod.POST)
	public String adminTestManagementAdd(HttpServletRequest request) {
		int subTypeId = Integer.parseInt(request.getParameter("subTypeId"));
		String testName = request.getParameter("testName");
		Test test = new Test();
		test.setStatus(0);
		test.setTestName(testName); 
		test.setSubTypeId(subTypeId);
		testService.insertTest(test);
		return "redirect:/adminTestManagement";
	}
	
	@ResponseBody
	@RequestMapping(value="adminTestManagementDelete",method = RequestMethod.POST)
	public String adminTestManagementDelete(HttpServletRequest request){
		int testId = Integer.parseInt(request.getParameter("testId"));
		Test test = new Test();
		test.setTestId(testId);
		testService.deleteTest(test);
		return "adminTestSubject";
	}
	
	@RequestMapping(value="adminTestManagementEdit",method = RequestMethod.POST)
	public String adminTestManagementEdit(HttpServletRequest request){
		int testId = Integer.valueOf(request.getParameter("testId"));
		String testName = request.getParameter("editTestName");
		int subTypeId = Integer.valueOf(request.getParameter("editSubTypeId"));
		int status = 0;
		if( request.getParameter("status")!=null){
			status= Integer.parseInt(request.getParameter("status"));
		}
		Test test = new Test();
		test.setStatus(status);
		test.setTestId(testId);
		test.setTestName(testName);
		test.setSubTypeId(subTypeId);
		testService.editTest(test);
		return "redirect:/adminTestManagement";
	}
	
	/*
	 * CURD FOR Question
	 */
	@RequestMapping(value="/adminTestQuestion", method=RequestMethod.GET)
	public String adminTestQuestion(Model model) {
		model.addAttribute("subTypeList", subTestTypeService.getAllSubTestType()); 
		model.addAttribute("questionList", questionService.getAllQuestion());
		model.addAttribute("questionTypeList", questionService.getAllQuestionType());
		return "adminTestQuestion";
	}
	
	@RequestMapping(value="/adminTestQuestionAdd", method=RequestMethod.POST)
	public String adminTestQuestionAdd(HttpServletRequest request) {
		int subTypeId = Integer.parseInt(request.getParameter("subTypeId"));
		int questionTypeId = Integer.parseInt(request.getParameter("questionTypeId"));
		String questionContent = request.getParameter("questionContent");
		Question question = new Question();
		question.setQuestionContent(questionContent); 
		question.setSubTypeId(subTypeId);
		question.setQuestionTypeId(questionTypeId);
		questionService.insertQuestion(question);
		return "redirect:/adminTestQuestion";
	}
	
	@ResponseBody
	@RequestMapping(value="adminTestQuestionDelete",method = RequestMethod.POST)
	public String adminTestQuestionDelete(HttpServletRequest request){
		int questionId = Integer.parseInt(request.getParameter("questionId"));
		Question question = new Question();
		question.setQuestionId(questionId);
		questionService.deleteQuestion(question);
		return "adminTestQuestion";
	}
	
	@RequestMapping(value="adminTestQuestionEdit",method = RequestMethod.POST)
	public String adminTestQuestionEdit(HttpServletRequest request){
		int questionId = Integer.valueOf(request.getParameter("questionId"));
		String questionContent = request.getParameter("editQuestionContent");
		int subTypeId = Integer.valueOf(request.getParameter("editSubTypeId"));
		int questionTypeId = Integer.valueOf(request.getParameter("editQuestionTypeId"));
		Question question = new Question();
		question.setQuestionTypeId(questionTypeId);
		question.setQuestionId(questionId);
		question.setQuestionContent(questionContent);
		question.setSubTypeId(subTypeId);
		questionService.editQuestion(question);
		return "redirect:/adminTestQuestion";
	}
	
	/*
	 * CURD FOR TestSchoolRetest
	 */
	@RequestMapping(value="/adminTestSchoolRetest", method=RequestMethod.GET)
	public String adminTestSchoolRetest(Model model) {
		model.addAttribute("testList", testService.getAllTest()); 
		model.addAttribute("schoolList", schoolClassService.getAllSchool());
		model.addAttribute("classroomList", schoolClassService.getAllClass());
		model.addAttribute("testSchoolRetestList", testSchoolRetestService.getTestSchoolRetest());
		return "adminTestSchoolRetest";
	}
	
	@RequestMapping(value="/adminTestSchoolRetestAdd", method=RequestMethod.POST)
	public String adminTestSchoolRetestAdd(HttpServletRequest request) {
		int testId = Integer.parseInt(request.getParameter("testId"));
		int schoolId = Integer.parseInt(request.getParameter("schoolId"));
		int classId = Integer.parseInt(request.getParameter("classId"));
		String retestStartTime = request.getParameter("retestStartTime");
		String retestEndTime = request.getParameter("retestEndTime");
		int retestGoalTotalMark = Integer.parseInt(request.getParameter("retestGoalTotalMark"));
		TestSchoolRetest testSchoolRetest = new TestSchoolRetest();
		testSchoolRetest.setTestId(testId);
		testSchoolRetest.setRetestClassroomId(classId);
		testSchoolRetest.setSchoolId(schoolId);
		testSchoolRetest.setRetestStartTime(retestStartTime);
		testSchoolRetest.setRetestEndTime(retestEndTime);
		testSchoolRetest.setRetestGoalTotalMark(retestGoalTotalMark);
		testSchoolRetestService.insertTestSchoolRetest(testSchoolRetest);
		return "redirect:/adminTestSchoolRetest";
	}
	
	@ResponseBody
	@RequestMapping(value="adminTestSchoolRetestDelete",method = RequestMethod.POST)
	public String adminTestSchoolRetestDelete(HttpServletRequest request){
		int testId = Integer.parseInt(request.getParameter("testId"));
		int schoolId = Integer.parseInt(request.getParameter("schoolId"));
		int classId = Integer.parseInt(request.getParameter("classId"));
		TestSchoolRetest testSchoolRetest = new TestSchoolRetest();
		testSchoolRetest.setRetestClassroomId(classId);
		testSchoolRetest.setSchoolId(schoolId);
		testSchoolRetest.setTestId(testId);
		testSchoolRetestService.deleteTestSchoolRetest(testSchoolRetest);
		return "redirect:/adminTestSchoolRetest";
	}
	
	
	// 复试录取总分编辑
	@RequestMapping(value="editRetestGoalMark",method = RequestMethod.POST)
	public String editRetestGoalMark(HttpServletRequest request){
		int editTestId = Integer.parseInt(request.getParameter("editTestId"));
		int editSchoolId = Integer.parseInt(request.getParameter("editSchoolId"));
		int editClassId = Integer.parseInt(request.getParameter("editClassId"));
		int editRetestGoalMark = Integer.parseInt(request.getParameter("editRetestGoalMark"));
		
		TestSchoolRetest testSchoolRetest = new TestSchoolRetest();
		testSchoolRetest.setTestId(editTestId);
		testSchoolRetest.setSchoolId(editSchoolId);
		testSchoolRetest.setRetestClassroomId(editClassId);
		testSchoolRetest.setRetestGoalTotalMark(editRetestGoalMark);
		testSchoolRetestService.setRetestGoalTotalMark(testSchoolRetest);
		return "redirect:/adminTestSchoolRetest";
	}
	
	// 说明文字
	@RequestMapping(value="adminSetsumei",method = RequestMethod.GET)
	public String adminSetsumei(Model model, HttpServletRequest request){
		model.addAttribute("ceeNumberDes", repoService.getRepoByK("ceeNumberDes").getV());
		model.addAttribute("artNumberDes", repoService.getRepoByK("artNumberDes").getV());
		return "adminSetsumei";
	}
	
	// 说明文字编辑
	@RequestMapping(value="adminSetsumeiEdit",method = RequestMethod.POST)
	public String adminSetsumeiEdit(HttpServletRequest request){
		String ceeNumberDes = request.getParameter("ceeNumberDes");
		String artNumberDes = request.getParameter("artNumberDes");
		System.out.println(ceeNumberDes);
		
		Repo repo = new Repo();
		repo.setK("ceeNumberDes");
		repo.setV(ceeNumberDes);
		repoService.setVByK(repo);
		
		repo.setK("artNumberDes");
		repo.setV(artNumberDes);
		repoService.setVByK(repo);
		return "redirect:/adminTestSchoolRetest";
	}
	
	//考生成绩管理
	@RequestMapping(value="adminTestMark",method = RequestMethod.GET)
	public String adminTestMark(Model model, HttpServletRequest request){
		model.addAttribute("markList", markService.getAllMark());
		return "adminTestMark";
	}
	
	@RequestMapping(value="editStudentMark",method = RequestMethod.POST)
	public String editStudentMark(HttpServletRequest request){
		int enrollNum = Integer.parseInt(request.getParameter("editEnrollNum"));
		int subjectId = Integer.parseInt(request.getParameter("editSubjectId"));
		String markDescription = request.getParameter("editMarkDescription");
		double mark = Double.parseDouble(request.getParameter("editStudentMark"));
		
		Mark studentMark = new Mark();
		studentMark.setEnrollNum(enrollNum);
		studentMark.setSubjectId(subjectId);
		studentMark.setMarkDescription(markDescription);
		studentMark.setMark(mark);
		markService.editMark(studentMark);
		return "redirect:/adminTestMark";
	}
	
	//考生复试成绩管理
	@RequestMapping(value="adminTestMarkRe",method = RequestMethod.GET)
	public String adminTestMarkRe(Model model, HttpServletRequest request){
		model.addAttribute("markReList", retestMarkService.getAllMarkRe());
		return "adminTestMarkRe";
	}
	//考生信息管理
	@RequestMapping(value="adminTestStudent",method = RequestMethod.GET)
	public String adminTestStudent(Model model, HttpServletRequest request){
		return "adminTestStudent";
	}
}
