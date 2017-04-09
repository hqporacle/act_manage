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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by shiwguo on 2017/4/6.
 * Updated by Siyuan Hu on 2017/4/8
 */
@Controller
public class ActivityController {
    @Autowired
    private ActivityService activityService;
    //private DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    private DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    
    @RequestMapping(value="/activities", method=RequestMethod.GET)
    public String activitiesGet(Model model) throws SqlResourceException {
        List<Activity> activityList = activityService.getAllActivity();
        model.addAttribute("activityList", activityList);
        return "activities";
    }


    @RequestMapping(value="/activityAdd", method=RequestMethod.POST)
    public String activityAdd(Model model, HttpServletRequest request) throws SqlResourceException {
        String activityName = request.getParameter("name");
        String description = request.getParameter("description");
        int status = Integer.parseInt(request.getParameter("status"));
        Date startDate = new Date();
        Date endDate = new Date();
        Date deadline = new Date();
		try {
			startDate = sdf.parse(request.getParameter("startDate"));
			endDate = sdf.parse(request.getParameter("endDate"));
			deadline = sdf.parse(request.getParameter("deadline"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Activity activity = new Activity();
        activity.setName(activityName);
        activity.setDescription(description);
        activity.setStartDate(startDate);
        activity.setEndDate(endDate);
        activity.setDeadline(deadline);
        activity.setStatus(status);
        activityService.insertActivity(activity);
        return "redirect:/activities";
    }
    
    @RequestMapping(value="/activityEdit", method=RequestMethod.POST)
    public String activityEdit(Model model, HttpServletRequest request) throws SqlResourceException {
        String activityName = request.getParameter("name");
        String description = request.getParameter("description");
        int id = Integer.parseInt(request.getParameter("id"));
        int status = Integer.parseInt(request.getParameter("status"));
        Date startDate = new Date();
        Date endDate = new Date();
        Date deadline = new Date();
		try {
			startDate = sdf.parse(request.getParameter("startDate"));
			endDate = sdf.parse(request.getParameter("endDate"));
			deadline = sdf.parse(request.getParameter("deadline"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Activity activity = new Activity();
        activity.setName(activityName);
        activity.setDescription(description);
        activity.setStartDate(startDate);
        activity.setEndDate(endDate);
        activity.setDeadline(deadline);
        activity.setStatus(status);
        activity.setId(id);
        activityService.editActivity(activity);
        return "redirect:/activities";
    }
    
    @RequestMapping(value="/activityDelete", method=RequestMethod.POST)
    public String activityDelete(Model model, HttpServletRequest request) throws SqlResourceException {
        int id = Integer.parseInt(request.getParameter("id"));
        Activity activity = new Activity();
        activity.setId(id);
        activityService.deleteActivity(activity);
        return "activities";
    }
    
}
