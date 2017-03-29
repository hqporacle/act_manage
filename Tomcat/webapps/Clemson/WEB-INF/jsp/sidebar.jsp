<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<div id="sidebar-nav" data-genuitec-lp-enabled="false" data-genuitec-file-id="wc1-75" data-genuitec-path="/Clemson/src/main/webapp/WEB-INF/jsp/sidebar.jsp">
        <img class="ecnu" src="<%=request.getContextPath()%>/img/saber.jpg" alt=""/>
        <ul id="dashboard-menu">  
            <li> 
                <a href="<%=request.getContextPath()%>/studentIndex">  
                    <i class="icon-home"></i>
                    <span style="font-size:12px">报名信息首页</span>
                </a>
            </li>        
             <li>
                <a href="<%=request.getContextPath()%>/type"> 
                    <i class="icon-star-half"></i>
                    <span style="font-size:12px">考试报名</span>
                </a>
            </li>
            <li>
                <a href="#"> 
                    <i class="icon-edit"></i>
                    <span style="font-size:12px">修改密码</span>
                </a>
            </li>
            
        </ul>
</div>
