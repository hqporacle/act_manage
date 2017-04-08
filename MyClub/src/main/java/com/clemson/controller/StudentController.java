package com.clemson.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.clemson.model.*;
import com.clemson.service.*;
import org.restsql.core.SqlResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private EnrollService enrollService;

    @Autowired
    private TestArrangeService testArrangeService;
    @Autowired
    private TestSchoolRetestService testSchoolRetestService;

    @Autowired
    private RepoService repoService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @RequestMapping(value = "/studentlogin", method = RequestMethod.GET)
    public String login(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @ResponseBody
    @RequestMapping(value = "/studentlogin", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String login(@ModelAttribute("user") User user, HttpServletRequest request) throws SqlResourceException {
        User userLogin = userService.getUserByLogin(user.getUsername(), user.getPassword());
        if (userLogin != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", userLogin);
            return "<script type='text/javascript'>window.location.href='studentIndex';</script>";
        } else {
            return "<script type='text/javascript'>alert('用户名或密码错误，请重新输入！');window.history.back(-1);</script>";
        }
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        model.addAttribute("ceeNumberDes", repoService.getRepoByK("ceeNumberDes"));
        model.addAttribute("artNumberDes", repoService.getRepoByK("artNumberDes"));
        return "signup";
    }

    @ResponseBody
    @RequestMapping(value = "/signup", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String signup(@ModelAttribute("student") Student student) {

        student.setStudentTypeId(1);
        student.setStudentTypeName("网上报名自荐");

        if (!studentService.getStudentByIDCNumber(student.getIdcNumber())) {
            studentService.insertStudent(student);
            return "<script type='text/javascript'>alert('注册成功！');window.location.href='login';</script>";
        } else {
            return "<script type='text/javascript'>alert('身份证号已存在！');window.location.href='signup';</script>";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String logout(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.invalidate();
        return "<script type='text/javascript'>alert('注销成功！');window.location.href='login';</script>";
    }

    @ResponseBody
    @RequestMapping(value = "/enroll/{enrollNum}/school", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String setSchoolId(Model model, @PathVariable("enrollNum") int enrollNum, HttpServletRequest request) {
        // 缺一个身份验证，以后补
        int schoolId = Integer.parseInt(request.getParameter("schoolId"));

        // 更新报名状态
        Enroll enroll = new Enroll();
        enroll.setEnrollNum(enrollNum);
        enroll.setSchoolId(schoolId);
        enroll.setEnrollStatus(2);
        enrollService.setSchoolIdByEnrollNum(enroll);
        enrollService.setEnrollStatusByEnrollNum(enroll);

        // 生成准考证号和准考证
        enroll = enrollService.getEnrollByEnrollNum(enroll);
        enrollService.generateTestNum(enroll);
        enrollService.generatePDF(enroll);
        enrollService.setTestNumByEnrollNum(enroll);

        return "<script type='text/javascript'>alert('选择考点成功！');window.location.href='../../studentIndex';</script>";
    }

    @RequestMapping(value = "/enroll/{enrollNum}", method = RequestMethod.GET)
    public String enrollDetail(Model model, @PathVariable("enrollNum") int enrollNum, HttpServletRequest request) {
        Enroll enroll = new Enroll();
        enroll.setEnrollNum(enrollNum);
        enroll = enrollService.getEnrollByEnrollNum(enroll);

        HttpSession session = request.getSession(true);
        if (session.getAttribute("student") != null) {
            String studentName = (((Student) session.getAttribute("student")).getStudentName());
            if (!enroll.getStudentName().equals(studentName)) {
                return "redirect:/studentIndex";
            }
        }

        if (enroll.getSchoolId() != 0) {
            TestArrange testArrange = new TestArrange();
            testArrange.setTestId(enroll.getTestId());
            testArrange.setSchoolId(enroll.getSchoolId());
            model.addAttribute("testArranges", testArrangeService.getTestArrangeByTestIdAndSchoolId(testArrange));

            TestSchoolRetest testSchoolRetest = new TestSchoolRetest();
            testSchoolRetest.setSchoolId(enroll.getSchoolId());
            testSchoolRetest.setTestId(enroll.getTestId());
            model.addAttribute("testSchoolRetest", testSchoolRetestService.getTestSchoolRetestByTestIdAndSchoolId(testSchoolRetest));
        }

        model.addAttribute("enroll", enroll);
        return "enrollDetail";
    }
}
