<%@ page language="java" import="java.util.*,com.clemson.model.*" pageEncoding="UTF-8"%> 
    <div class="navbar navbar-inverse" data-genuitec-lp-enabled="false" data-genuitec-file-id="wc1-73" data-genuitec-path="/Clemson/src/main/webapp/WEB-INF/jsp/navbar.jsp">
        <div class="navbar-inner">
            <a class="brand" href="<%=request.getContextPath()%>/studentIndex">华东师范大学自主招生系统</a>
            <ul class="nav pull-right">   
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle hidden-phone" data-toggle="dropdown" style="font-family: 'Open Sans', sans-serif; font-size:12px; font-weight:600;">你好，<% if(session.getAttribute("student")!=null) out.println(((Student) session.getAttribute("student")).getStudentName()); %><b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="<%=request.getContextPath()%>/logout">注销</a></li>
                        <li><a href="#">修改密码</a></li> 
                    </ul>
                </li> 
            </ul>            
        </div>
    </div>