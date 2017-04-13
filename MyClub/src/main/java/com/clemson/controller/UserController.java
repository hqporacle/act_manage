package com.clemson.controller;

import com.clemson.model.Activity;
import com.clemson.model.User;
import com.clemson.service.ParticipationService;
import com.clemson.service.UserService;
import org.json.JSONObject;
import org.restsql.core.SqlResourceException;
import org.restsql.core.impl.serial.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by shiwguo on 2017/4/8.
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ParticipationService participationService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String userLogin(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String userLogin(@ModelAttribute("user") User user, HttpServletRequest request) throws SqlResourceException {
        User userLogin = userService.getUserByLogin(user.getUsername(), user.getPassword());
        if (userLogin != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", userLogin);
            return "success";
        } else {
            return "login failed";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/myActivities", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String getMyActivities(Model mOdel, HttpServletRequest request) throws SqlResourceException {
        User user = (User) request.getSession(true).getAttribute("user");
        String activities = participationService.getActivityByUserId(user.getId());
        return activities;
    }

}
