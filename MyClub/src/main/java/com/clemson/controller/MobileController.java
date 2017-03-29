package com.clemson.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clemson.model.Enroll;
import com.clemson.model.Mark;
import com.clemson.model.Question;
import com.clemson.model.RetestMark;
import com.clemson.model.Student;
import com.clemson.model.SubTestType;
import com.clemson.model.Subject;
import com.clemson.model.Test;
import com.clemson.model.TestArrange;
import com.clemson.model.TestSchoolRetest;
import com.clemson.model.Type;
import com.clemson.model.Class;
import com.clemson.service.EnrollService;
import com.clemson.service.MarkService;
import com.clemson.service.QuestionService;
import com.clemson.service.RetestMarkService;
import com.clemson.service.SchoolClassService;
import com.clemson.service.StudentService;
import com.clemson.service.SubTestTypeService;
import com.clemson.service.SubjectService;
import com.clemson.service.TestArrangeService;
import com.clemson.service.TestSchoolRetestService;
import com.clemson.service.TestService;
import com.clemson.service.TypeService;

@Controller
public class MobileController {
	@Autowired
	MarkService markService;
	@Autowired
	EnrollService enrollService;
	@Autowired
	StudentService studentService;
	@Autowired
	TypeService typeService;
	@Autowired
	SubTestTypeService subTypeService;
	@Autowired
	SubjectService subjectService;
	@Autowired
	SchoolClassService schoolClassService;
	@Autowired
	TestArrangeService testArrangeService;
	@Autowired
	TestService testService;
	@Autowired
	TestSchoolRetestService testSchoolRetestService;
	@Autowired
	QuestionService questionService;
	@Autowired
	RetestMarkService retestMarkService;

	// 第一阶段：接受现场报名考生
	
	@ResponseBody
	@RequestMapping(value = "/school/{schoolId}/enroll", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public int acceptEnroll(@PathVariable("schoolId") int schoolId, HttpServletRequest request) {
		String enrollsJSONArrayString = (String) request.getParameter("enrolls");

		// Mock
		// String enrollsJSONArrayString = "[{'idc_number':'320402199407024013','cee_number':'315164','awards':'2016年nba状元秀','art_number':null,'student_name':'朱力霄','student_type_id':'2','student_sex':'男','student_nation':'汉','student_birthday':'34517','student_address':'常州市新北区府翰苑','student_school':'常州市北郊中学','student_email':'863716623@qq.com','type_id':'2','test_id':'1'},{'idc_number':'331315199312111316','cee_number':'356489','awards':'少管所围棋大赛一等奖','art_number':null,'student_name':'周则宙','student_type_id':'2','student_sex':'男','student_nation':'汉','student_birthday':'34315','student_address':'台州市公安局监狱101床位','student_school':'台州市少管所','student_email':'111111111@qq.com','type_id':'2','test_id':'1'},{'idc_number':'313315199405051645','cee_number':'346848','awards':'公安局技巧大赛一等奖','art_number':null,'student_name':'周湘杰','student_type_id':'2','student_sex':'男','student_nation':'汉','student_birthday':'34551','student_address':'大连市公安局监狱','student_school':'大连市二十四中','student_email':'222222222@qq.com','type_id':'2','test_id':'1'}]";

		JSONArray enrollsJSONArray = new JSONArray(enrollsJSONArrayString);

		for (int i = 0; i != enrollsJSONArray.length(); i++) {
			JSONObject enrollJSONObject = enrollsJSONArray.getJSONObject(i);
			
			String idcNumber = enrollJSONObject.getString("idc_number");
			
			if (!studentService.getStudentByIDCNumber(idcNumber)) {
				// insert student
				Student student = new Student();
				student.setIdcNumber(idcNumber);
				student.setCeeNumber(enrollJSONObject.getString("cee_number"));
				if (!enrollJSONObject.isNull("art_number")) {
					student.setArtNumber(enrollJSONObject.getString("art_number"));
				}
				student.setStudentName(enrollJSONObject.getString("student_name"));
				student.setStudentSex(enrollJSONObject.getString("student_sex"));
				student.setStudentNation(enrollJSONObject.getString("student_nation"));
				student.setStudentBirthday(enrollJSONObject.getString("student_birthday"));
				student.setStudentAddress(enrollJSONObject.getString("student_address"));
				student.setStudentSchool(enrollJSONObject.getString("student_school"));
				student.setStudentEmail(enrollJSONObject.getString("student_email"));
				student.setStudentTypeId(2);
				student.setStudentTypeName("现场报名自荐");
				
				student.setStudentPassword("test");
				
				studentService.insertStudent(student);
				
				
				// enroll
				Enroll enroll = new Enroll();
				enroll.setStudentId(studentService.getStudentObjectByIDCNumber(idcNumber).getStudentId());
				enroll.setTestId(enrollJSONObject.getInt("test_id"));
				enroll.setAwards(enrollJSONObject.getString("awards"));
				enroll.setEnrollStatus(0);
				enroll.setSchoolId(schoolId);
				enrollService.insertEnroll(enroll);
			} else {
				// enroll
				Student student = studentService.getStudentObjectByIDCNumber(idcNumber);
				Enroll enroll = new Enroll();
				enroll.setStudentId(student.getStudentId());
				enroll.setTestId(enrollJSONObject.getInt("test_id"));
				enroll.setAwards(enrollJSONObject.getString("awards"));
				enroll.setEnrollStatus(0);
				enroll.setSchoolId(schoolId);
				enrollService.insertEnroll(enroll);
			}
			studentService.getStudentByIDCNumber(idcNumber);
		}
		// 约定返回1
		return 1;
	}
		
	// 第一阶段结束
	
	// 第二阶段：准备考试
	@ResponseBody
	@RequestMapping(value = "/school/{schoolId}/ready/student", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public ArrayList<Student> readyStudent(@PathVariable("schoolId") int schoolId, HttpServletRequest request) {
		return studentService.getStudentBySchoolId(schoolId);
	}

	@ResponseBody
	@RequestMapping(value = "/school/{schoolId}/ready/type", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public ArrayList<Type> readyType(@PathVariable("schoolId") int schoolId, HttpServletRequest request) {
		return typeService.getAllType();
	}
	
	@ResponseBody
	@RequestMapping(value = "/school/{schoolId}/ready/subType", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public ArrayList<SubTestType> readySubType(@PathVariable("schoolId") int schoolId, HttpServletRequest request) {
		return subTypeService.getAllSubTestType();
	}
	
	@ResponseBody
	@RequestMapping(value = "/school/{schoolId}/ready/subject", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public ArrayList<Subject> readySubject(@PathVariable("schoolId") int schoolId, HttpServletRequest request) {
		return subjectService.getAllSubject();
	}

	@ResponseBody
	@RequestMapping(value = "/school/{schoolId}/ready/classroom", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public ArrayList<Class> readyClassroom(@PathVariable("schoolId") int schoolId, HttpServletRequest request) {
		// schoolId = 20
		return schoolClassService.getAllClass();
	}

	@ResponseBody
	@RequestMapping(value = "/school/{schoolId}/ready/test", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public ArrayList<Test> readyTest(@PathVariable("schoolId") int schoolId, HttpServletRequest request) {
		return testService.getAllTest();
	}

	@ResponseBody
	@RequestMapping(value = "/school/{schoolId}/ready/testSchoolSubjectClass", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public ArrayList<TestArrange> readyTestSchoolSubjectClass(@PathVariable("schoolId") int schoolId, HttpServletRequest request) {
		return testArrangeService.getTestArrangeBySchoolId(schoolId);
	}	
	
	@ResponseBody
	@RequestMapping(value = "/school/{schoolId}/ready/enroll", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public ArrayList<Enroll> readyEnroll(@PathVariable("schoolId") int schoolId, HttpServletRequest request) {
		return enrollService.getEnrollBySchoolId(schoolId);
	}	
	
	@ResponseBody
	@RequestMapping(value = "/school/{schoolId}/ready/question", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public ArrayList<Question> readyQuestion(@PathVariable("schoolId") int schoolId, HttpServletRequest request) {
		return questionService.getAllQuestion();
	}	
	
	@ResponseBody
	@RequestMapping(value = "/school/{schoolId}/ready/questionType", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public ArrayList<Question> readyQuestionType(@PathVariable("schoolId") int schoolId, HttpServletRequest request) {
		return questionService.getAllQuestionType();
	}
	
	@ResponseBody
	@RequestMapping(value = "/school/{schoolId}/ready/testSchoolRetest", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public ArrayList<TestSchoolRetest> readyTestSchoolRetest(@PathVariable("schoolId") int schoolId, HttpServletRequest request) {
		return testSchoolRetestService.getTestSchoolRetestBySchoolId(schoolId);
	}	
	
	// 第二阶段结束
	
	// 第三阶段：获得成绩
	
	// 从移动端服务器获得分数信息，并插入数据库
	@ResponseBody
	@RequestMapping(value = "/school/{schoolId}/mark", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public int acceptMark(@PathVariable("schoolId") int schoolId,
			HttpServletRequest request) {
		String marksJSONObjectString = (String) request.getParameter("marks");

		// Mock
		///String marksJSONObjectString = "{'first_mark':[{'enroll_num':'1517','subject_id':'13','expert_id':'1','impression_mark':'8','mark':'50','createTime':'2016-03-31 13:58:02'},{'enroll_num':'1518','subject_id':'13','expert_id':'1','impression_mark':'6','mark':'40','createTime':'2016-03-31 13:58:02'},{'enroll_num':'1519','subject_id':'13','expert_id':'1','impression_mark':'7','mark':'55','createTime':'2016-03-31 13:58:02'},{'enroll_num':'1517','subject_id':'14','expert_id':'2','impression_mark':'7','mark':'30','createTime':'2016-03-31 13:51:36'},{'enroll_num':'1518','subject_id':'14','expert_id':'2','impression_mark':'10','mark':'50','createTime':'2016-03-31 13:51:36'},{'enroll_num':'1519','subject_id':'14','expert_id':'2','impression_mark':'9','mark':'70','createTime':'2016-03-31 13:51:36'}],'retest_mark':[{'enroll_num':'1517','subject_id':'13','expert_id':'1','retest_en_mark':'15','retest_pro_mark':'20','retest_peo_mark':'25','createTime':'2016-03-31 13:58:02'},{'enroll_num':'1518','subject_id':'13','expert_id':'1','retest_en_mark':'20','retest_pro_mark':'25','retest_peo_mark':'30','createTime':'2016-03-31 13:58:02'},{'enroll_num':'1519','subject_id':'13','expert_id':'1','retest_en_mark':'30','retest_pro_mark':'25','retest_peo_mark':'25','createTime':'2016-03-31 13:58:02'}]}";
		JSONObject pristine = new JSONObject(marksJSONObjectString);
		
		JSONArray firstMarksJSONArray = pristine.getJSONArray("first_mark");

		for (int i = 0; i != firstMarksJSONArray.length(); i++) {
			JSONObject markJSONObject = firstMarksJSONArray.getJSONObject(i);
			
			Mark markImpression = new Mark();
			markImpression.setCreateTime(markJSONObject.getString("createTime"));
			markImpression.setEnrollNum(Integer.valueOf(markJSONObject
					.getString("enroll_num")));
			markImpression.setExpertId(Integer.valueOf(markJSONObject
					.getString("expert_id")));
			markImpression.setMark(Double.valueOf(markJSONObject.getString("impression_mark")));
			markImpression.setSubjectId(Integer.valueOf(markJSONObject
					.getString("subject_id")));
			markImpression.setMarkDescription("印象分");
			markService.insertMark(markImpression);
			
			Mark markFinal = new Mark();
			markFinal.setCreateTime(markJSONObject.getString("createTime"));
			markFinal.setEnrollNum(Integer.valueOf(markJSONObject
					.getString("enroll_num")));
			markFinal.setExpertId(Integer.valueOf(markJSONObject
					.getString("expert_id")));
			markFinal.setMark(Double.valueOf(markJSONObject.getString("mark")));
			markFinal.setSubjectId(Integer.valueOf(markJSONObject
					.getString("subject_id")));
			markFinal.setMarkDescription("正式分");
			markService.insertMark(markFinal);
		}
		
		JSONArray retestMarksJSONArray = pristine.getJSONArray("retest_mark");
		
		for (int i = 0; i != retestMarksJSONArray.length(); i++) {
			JSONObject markJSONObject = retestMarksJSONArray.getJSONObject(i);
			
			RetestMark retestMark = new RetestMark();
			retestMark.setEnrollNum(Integer.valueOf(markJSONObject
					.getString("enroll_num")));
			retestMark.setExpertId(Integer.valueOf(markJSONObject
					.getString("expert_id")));
			retestMark.setRetestEnMark(Integer.valueOf(markJSONObject
					.getString("retest_en_mark")));
			retestMark.setRetestProMark(Integer.valueOf(markJSONObject
					.getString("retest_pro_mark")));
			retestMark.setRetestPeoMark(Integer.valueOf(markJSONObject
					.getString("retest_peo_mark")));
			retestMark.setCreateTime(markJSONObject.getString("createTime"));
			retestMarkService.insertRetestMark(retestMark);
		}

		// 约定返回1
		return 1;
	}
	
	// 第三阶段结束
}
