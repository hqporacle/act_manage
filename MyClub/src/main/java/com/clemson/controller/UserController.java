package com.clemson.controller;

import com.clemson.model.Admin;
import com.clemson.model.User;
import com.clemson.service.UserService;
import org.restsql.core.SqlResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by shiwguo on 2017/4/8.
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value="/login", method= RequestMethod.GET)
    public String userLogin(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "userLogin";
    }

    @ResponseBody
    @RequestMapping(value="/login", method=RequestMethod.POST, produces="text/html;charset=UTF-8")
    public String userLogin(@ModelAttribute("user") User user, HttpServletRequest request) throws SqlResourceException {
        User userLogin = userService.getUserByLogin(user.getName(), user.getPassword());
        if (userLogin != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", userLogin);
            return "<script type='text/javascript'>window.location.href='activities';</script>";
        } else {
            return "<script type='text/javascript'>alert('用户名或密码错误，请重新输入！');window.history.back(-1);</script>";
        }
    }
}
