package com.clemson.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.clemson.service.SchoolClassService;
import com.clemson.model.Class;

@Controller
public class ClassroomController {
	@Autowired
	SchoolClassService schoolClassService;
	
	@RequestMapping(value="/adminClass", method=RequestMethod.GET)
	public String adminClassRoom(Model model) {
		model.addAttribute("classList", schoolClassService.getAllClass());
		model.addAttribute("schoolList", schoolClassService.getAllSchool());
		return "adminClass";
	}
	
	@RequestMapping(value="/adminClassRoomAdd", method=RequestMethod.POST)
	public String adminClassRoomAdd(Model model, HttpServletRequest request) {
		String classRoomName = (String) request.getParameter("classRoomName");
		int schoolId = Integer.parseInt(request.getParameter("schoolId"));
		Class classRoom = new Class();
		classRoom.setClassNo(classRoomName);
		classRoom.setSchoolId(schoolId);
		schoolClassService.insertClass(classRoom);
		return "redirect:/adminClass";
	}
	
	@RequestMapping(value="/adminClassRoomDelete", method=RequestMethod.POST)
	public String adminClassRoomDelete(Model model, HttpServletRequest request) {
		int classRoomId = Integer.parseInt(request.getParameter("classId"));
		Class classRoom = new Class();
		classRoom.setClassId(classRoomId);
		schoolClassService.deleteClass(classRoom);
		return "redirect:/adminSchool";
	}
	
	@RequestMapping(value="/adminClassRoomEdit", method=RequestMethod.POST)
	public String adminClassRoomEdit(Model model, HttpServletRequest request) {
		int classId = Integer.parseInt(request.getParameter("classId"));
		int schoolId = Integer.parseInt(request.getParameter("editClassSchool"));
		String classNo = (String) request.getParameter("classNo");
		Class classRoom = new Class();
		classRoom.setClassId(classId);
		classRoom.setSchoolId(schoolId);
		classRoom.setClassNo(classNo);
		schoolClassService.setClassByClassId(classRoom);
		return "redirect:/adminClass";
	}
	

	@RequestMapping(value="/adminClassRoomEditStatus", method=RequestMethod.POST)
	public String adminClassRoomEditStatus(Model model, HttpServletRequest request) {
		int classId = Integer.parseInt(request.getParameter("classId"));
		int status = Integer.parseInt(request.getParameter("status"));
		Class classRoom = new Class();
		classRoom.setClassId(classId);
		classRoom.setStatus(status);
		schoolClassService.setStatusByClassId(classRoom);
		return "adminSchool";
	}
}
