package com.clemson.controller;

import com.clemson.model.Activity;
import com.clemson.service.ActivityService;
import org.restsql.core.Config;
import org.restsql.core.SqlResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by shiwguo on 2017/4/6.
 */
@Controller
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @RequestMapping(value = "/activities", method = RequestMethod.GET)
    public String activitiesGet(Model model, HttpServletRequest request) throws SqlResourceException {
        ArrayList<Activity> activityList = activityService.getAllActivity();

        model.addAttribute("activityList", activityList);
        return "activities";
    }


    @RequestMapping(value="/activityAdd", method=RequestMethod.POST)
    public String ActivityAdd(Model model, HttpServletRequest request) {
        /*String classRoomName = (String) request.getParameter("classRoomName");
        int schoolId = Integer.parseInt(request.getParameter("schoolId"));
        Class classRoom = new Class();
        classRoom.setClassNo(classRoomName);
        classRoom.setSchoolId(schoolId);
        schoolClassService.insertClass(classRoom);*/
        return "redirect:/adminClass";
    }
}
