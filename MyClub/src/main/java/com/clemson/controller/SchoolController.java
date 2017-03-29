package com.clemson.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.clemson.model.School;
import com.clemson.service.SchoolClassService;

@Controller
public class SchoolController {
	// 不写在AdminController里纯属偷懒
	@Autowired
	private SchoolClassService schoolClassService;
	
	@RequestMapping(value="/adminSchool", method=RequestMethod.GET)
	public String adminSchool(Model model) {
		model.addAttribute("schoolList", schoolClassService.getAllSchool());
		return "adminSchool";
	}
	
	@RequestMapping(value="/adminSchoolEditStatus", method=RequestMethod.POST)
	public String adminSchoolEditStatus(Model model, HttpServletRequest request) {
		int schoolId = Integer.parseInt(request.getParameter("schoolId"));
		int status = Integer.parseInt(request.getParameter("status"));
		School school = new School();
		school.setSchoolId(schoolId);
		school.setStatus(status);
		schoolClassService.setStatusBySchoolId(school);
		return "adminSchool";
	}
	
	@RequestMapping(value="/adminSchoolEdit", method=RequestMethod.POST)
	public String adminSchoolEdit(Model model, HttpServletRequest request) {
		int schoolId = Integer.parseInt(request.getParameter("schoolId"));
		String schoolName = (String) request.getParameter("schoolName");
		String schoolAddress = (String) request.getParameter("schoolAddress");
		School school = new School();
		school.setSchoolId(schoolId);
		school.setSchoolName(schoolName);
		school.setSchoolAddress(schoolAddress);
		schoolClassService.setSchoolBySchoolId(school);
		return "redirect:/adminSchool";
	}
	
	@RequestMapping(value="/adminSchoolAdd", method=RequestMethod.POST)
	public String adminSchoolAdd(Model model, HttpServletRequest request) {
		String schoolName = (String) request.getParameter("schoolName");
		String schoolAddress = (String) request.getParameter("schoolAddress");
		School school = new School();
		school.setSchoolName(schoolName);
		school.setSchoolAddress(schoolAddress);
		schoolClassService.insertSchool(school);
		return "redirect:/adminSchool";
	}
	
	@RequestMapping(value="/adminSchoolDelete", method=RequestMethod.POST)
	public String adminSchoolDelete(Model model, HttpServletRequest request) {
		int schoolId = Integer.parseInt(request.getParameter("schoolId"));
		School school = new School();
		school.setSchoolId(schoolId);
		schoolClassService.deleteSchool(school);
		return "redirect:/adminSchool";
	}
}
