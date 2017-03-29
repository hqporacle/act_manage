package com.clemson.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.clemson.model.Enroll;
import com.clemson.model.Teacher;
import com.clemson.service.EnrollService;
import com.clemson.service.TeacherService;

@Controller
public class TeacherController {
	@Autowired
	private EnrollService enrollService;
	@Autowired
	private TeacherService teacherService;
	
	@RequestMapping(value="/teacherLogin", method=RequestMethod.GET)
	public String teacherLogin(Model model) {
		Teacher teacher = new Teacher();
		model.addAttribute("teacher", teacher);
		return "teacherLogin";
	}
	
	@ResponseBody
	@RequestMapping(value="/teacherLogin", method=RequestMethod.POST, produces="text/html;charset=UTF-8")
	public String teacherLogin(@ModelAttribute("teacher") Teacher teacher, HttpServletRequest request) {
		Teacher teacherLogin = teacherService.getTeacherByLogin(teacher.getIdcNumber(), teacher.getTeacherPassword());
	    if (teacherLogin != null) {
	    	HttpSession session = request.getSession(true);
			session.setAttribute("teacher", teacherLogin);
			return "<script type='text/javascript'>window.location.href='teacherIndex';</script>";
	    } else {
	    	return "<script type='text/javascript'>alert('用户名或密码错误，请重新输入！');window.history.back(-1);</script>";
	    }
	}
	
	@RequestMapping(value="/teacherIndex", method=RequestMethod.GET)
	public String adminIndex(Model model) {
		return "teacherIndex";
	}
	
	@RequestMapping(value="/teacherCheck", method=RequestMethod.GET)
	public String check(Model model) {
		ArrayList<Enroll> enrollList = enrollService.getAllEnroll();
		model.addAttribute("enrollList", enrollList);
		return "teacherCheck";
	}
	
	@RequestMapping(value="/teacherUploadCheck", method=RequestMethod.GET)
	public String uploadCheck(Model model) {
		return "teacherUploadCheck";
	}
	
	@ResponseBody
	@RequestMapping(value="/teacherLogout", method=RequestMethod.GET, produces="text/html;charset=UTF-8")
	public String logout(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		session.invalidate();
		return "<script type='text/javascript'>alert('注销成功！');window.location.href='teacherLogin';</script>";
	}
	
	@ResponseBody
	@RequestMapping(value="/enroll/{enrollNum}/status/{enrollStatus}", method=RequestMethod.POST, produces="text/html;charset=UTF-8")
	public String setEnrollStatus(Model model, @PathVariable("enrollNum") int enrollNum, @PathVariable("enrollStatus") int enrollStatus) {
		// 缺一个身份验证，以后补
		Enroll enroll = new Enroll();
		enroll.setEnrollNum(enrollNum);
		enroll.setEnrollStatus(enrollStatus);
		
		Enroll enrollJudge = enrollService.getEnrollByEnrollNum(enroll);
		// 如果已经有考点了（现场报名考生）
		if (enrollJudge.getSchoolId() != 0) {
			enrollJudge.setEnrollStatus(2);
			enrollService.setEnrollStatusByEnrollNum(enrollJudge);
			
			enroll = enrollService.getEnrollByEnrollNum(enroll);
			enrollService.generateTestNum(enroll);
			enrollService.generatePDF(enroll);
			enrollService.setTestNumByEnrollNum(enroll);
			return "<script type='text/javascript'>alert('审核成功！');window.location.href='../../../teacherCheck';</script>";
		}
		
		enrollService.setEnrollStatusByEnrollNum(enroll);
		return "<script type='text/javascript'>alert('审核成功！');window.location.href='../../../teacherCheck';</script>";
	}
	
	// 批量处理文件
	
	@ResponseBody
	@RequestMapping(value="/excelUpload", method=RequestMethod.POST, produces="text/html;charset=UTF-8")  
    public String addUser(@RequestParam MultipartFile[] myfiles, HttpServletRequest request) throws IOException {  
        //如果只是上传一个文件，则只需要MultipartFile类型接收文件即可，而且无需显式指定@RequestParam注解  
        //如果想上传多个文件，那么这里就要用MultipartFile[]类型来接收文件，并且还要指定@RequestParam注解  
        //并且上传多个文件时，前台表单中的所有<input type="file"/>的name都应该是myfiles，否则参数里的myfiles无法获取到所有上传的文件  
        for(MultipartFile myfile : myfiles){  
            if(myfile.isEmpty()){  
                System.out.println("文件未上传");  
            }else{  
                System.out.println("文件长度: " + myfile.getSize());  
                System.out.println("文件类型: " + myfile.getContentType());  
                System.out.println("文件名称: " + myfile.getName());  
                System.out.println("文件原名: " + myfile.getOriginalFilename());  
                System.out.println("========================================");  
                //如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中  
                String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");  
                //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的  
                FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, myfile.getOriginalFilename()));
                
                
                
                
                
                String theRealRealPath = realPath + "/" + myfile.getOriginalFilename();
                Workbook workbook;
        		HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
        		try {
        			workbook = Workbook.getWorkbook(new File(theRealRealPath));
        			Sheet sheet = workbook.getSheet(0);
        			for (int i = 1; i != sheet.getRows(); i++) {
        				hashMap.put(Integer.parseInt(sheet.getCell(0, i).getContents()), sheet.getCell(1, i).getContents());
        			}
        			Iterator<Integer> iter = hashMap.keySet().iterator();
        			while (iter.hasNext()) {
        				int enrollNum = (Integer) iter.next();
        				String val = hashMap.get(enrollNum);
        				
        				if (val.equals("通过")) {
        					Enroll enroll = new Enroll();
        					enroll.setEnrollNum(enrollNum);
        					enroll.setEnrollStatus(1);
        					
        					Enroll enrollJudge = enrollService.getEnrollByEnrollNum(enroll);
        					// 如果已经有考点了（现场报名考生）
        					if (enrollJudge.getSchoolId() != 0) {
        						enrollJudge.setEnrollStatus(2);
        						enrollService.setEnrollStatusByEnrollNum(enrollJudge);
        						
        						enroll = enrollService.getEnrollByEnrollNum(enroll);
        						enrollService.generateTestNum(enroll);
        						enrollService.generatePDF(enroll);
        						enrollService.setTestNumByEnrollNum(enroll);
        					}
        					enrollService.setEnrollStatusByEnrollNum(enroll);
        				}
        			}
        		} catch (BiffException e) {
        			e.printStackTrace();
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
        		
        		return "<script type='text/javascript'>alert('批量审核完成！');</script>";
                
            }  
        }
        
        return "ok";  
    }  
	
	// 导出Excel文件
	
	@ResponseBody
	@RequestMapping(value="/excelDownload", method=RequestMethod.GET, produces="text/html;charset=UTF-8")
	public String excelDownload(Model model, HttpServletRequest request) {
		ArrayList<Enroll> enrollList = enrollService.getAllEnroll();
		
		WritableWorkbook writableworkbook = null;
		String theRealRealPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload") + "/output.xls";
		try {
			writableworkbook = Workbook.createWorkbook(new File(theRealRealPath));
			WritableSheet writablesheet = writableworkbook.createSheet("第一页", 0);

			Label label = null;
			
			label = new Label(0, 0, "报名号");
			writablesheet.addCell(label);
			label = new Label(1, 0, "审核结果");
			writablesheet.addCell(label);
			label = new Label(2, 0, "学生姓名");
			writablesheet.addCell(label);
			label = new Label(3, 0, "报名考试");
			writablesheet.addCell(label);
			label = new Label(4, 0, "身份证号");
			writablesheet.addCell(label);
			label = new Label(5, 0, "性别");
			writablesheet.addCell(label);
			label = new Label(6, 0, "民族");
			writablesheet.addCell(label);
			label = new Label(7, 0, "出生日期");
			writablesheet.addCell(label);
			label = new Label(8, 0, "专业奖项");
			writablesheet.addCell(label);
			label = new Label(9, 0, "高考报名号");
			writablesheet.addCell(label);
			label = new Label(10, 0, "艺术号");
			writablesheet.addCell(label);
			label = new Label(11, 0, "考生类型");
			writablesheet.addCell(label);
			
			int j = 1;
			for (Enroll enroll : enrollList) {
				label = new Label(0, j, String.valueOf(enroll.getEnrollNum()));
				writablesheet.addCell(label);
				label = new Label(1, j, "");
				writablesheet.addCell(label);
				label = new Label(2, j, enroll.getStudentName());
				writablesheet.addCell(label);
				label = new Label(3, j, enroll.getTestName());
				writablesheet.addCell(label);
				label = new Label(4, j, enroll.getIdcNumber());
				writablesheet.addCell(label);
				label = new Label(5, j, enroll.getStudentSex());
				writablesheet.addCell(label);
				label = new Label(6, j, enroll.getStudentNation());
				writablesheet.addCell(label);
				label = new Label(7, j, enroll.getStudentBirthday());
				writablesheet.addCell(label);
				label = new Label(8, j, enroll.getAwards());
				writablesheet.addCell(label);
				label = new Label(9, j, enroll.getCeeNumber());
				writablesheet.addCell(label);
				label = new Label(10, j, enroll.getArtNumber());
				writablesheet.addCell(label);
				label = new Label(11, j, enroll.getStudentTypeName());
				writablesheet.addCell(label);
				
				j++;
			}

			
			writableworkbook.write();
			writableworkbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		
		return "<script type='text/javascript'>alert('生成成功！');window.location.href='upload/output.xls';</script>";
	}
	
}
