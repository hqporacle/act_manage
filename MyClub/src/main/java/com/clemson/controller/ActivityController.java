package com.clemson.controller;

import com.clemson.model.Activity;
import com.clemson.model.User;
import com.clemson.service.ActivityService;
import com.clemson.service.ParticipationService;

import com.clemson.util.CommonInfo;
import org.json.JSONArray;
import org.json.JSONObject;
import org.restsql.core.Config;
import org.restsql.core.SqlResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
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
    @Autowired
    private ParticipationService participationService;
    //private DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    private DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    
    @RequestMapping(value="/activities", method=RequestMethod.GET)
    public String activitiesGet(Model model) throws SqlResourceException {
        List<Activity> activityList = activityService.getAllActivity();
        model.addAttribute("activityList", activityList);
        return "activities";
    }

    @RequestMapping(value="/activitySearch", method=RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String activitySearch(Model model, HttpServletRequest request) throws SqlResourceException {
        String activityName = request.getParameter("name");
        Activity activity = new Activity();
        if (!activityName.equals("undefined")) {
        	activity.setName(activityName);        	
        }
        Date startDate = new Date();
        Date endDate = new Date();
        Date deadline = new Date();
        
		try {
			if (!request.getParameter("startDate").equals("undefined") && !request.getParameter("startDate").isEmpty()) {
				startDate = sdf.parse(request.getParameter("startDate"));	
				 activity.setStartDate(startDate);
			}
			if (!request.getParameter("endDate").equals("undefined") && !request.getParameter("startDate").isEmpty()) {
				endDate = sdf.parse(request.getParameter("endDate"));
				activity.setEndDate(endDate);
			}
			if (!request.getParameter("deadline").equals("undefined") && !request.getParameter("startDate").isEmpty()) {
				deadline = sdf.parse(request.getParameter("deadline"));	
				 activity.setDeadline(deadline);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        List<Activity> activitySearchResults = activityService.getActivityByCondition(activity);
        JSONObject responseDetailsJson = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (Activity item : activitySearchResults) {
        	JSONObject detailsJson = new JSONObject();
            detailsJson.put("id", item.getId());
            detailsJson.put("name", item.getName());
            detailsJson.put("description", item.getDescription());
            detailsJson.put("startDate", item.getStartDate());
            detailsJson.put("endDate", item.getEndDate());
            detailsJson.put("deadline", item.getDeadline());
            detailsJson.put("status", item.getStatus());
            jsonArray.put(detailsJson);
        }
        responseDetailsJson.put("response", jsonArray);
        return responseDetailsJson.toString();
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
        participationService.updateParticipant(activity.getId(),0, CommonInfo.participation_status.NEW.ordinal(),"");
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
    
    @RequestMapping(value="/participants", method=RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String participantsGet(Model model, HttpServletRequest request) throws SqlResourceException {
    	int id = Integer.parseInt(request.getParameter("id")); 
    	List<User> users = participationService.getParticipantByActivityId(id);
    	JSONObject responseDetailsJson = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i<users.size(); i++) {
        	JSONObject detailsJson = new JSONObject();
            detailsJson.put("UserId", users.get(i).getId());
            detailsJson.put("UserName", users.get(i).getUsername());
            jsonArray.put(detailsJson);
        }
        responseDetailsJson.put("response", jsonArray);
        return responseDetailsJson.toString();
    }
    
    @RequestMapping(value="/participantsQuit", method=RequestMethod.POST)
    public String participantsQuit(Model model, HttpServletRequest request) throws SqlResourceException {
    	int userId = Integer.parseInt(request.getParameter("userId")); 
    	int activityId = Integer.parseInt(request.getParameter("activityId")); 
    	participationService.deleteParticipantByBothId(activityId, userId);
        return "activities";
    }
    
    @RequestMapping(value="/participantsJoin", method=RequestMethod.POST)
    public String participantsJoin(Model model, HttpServletRequest request) throws SqlResourceException {
    	int userId = Integer.parseInt(request.getParameter("userId")); 
    	int activityId = Integer.parseInt(request.getParameter("activityId")); 
    	participationService.insertParticipant(activityId, userId);
        return "activities";
    }
}
