<%@ page language="java" import="java.util.*,com.clemson.model.*" pageEncoding="UTF-8"%> 
<% 
	if(session.getAttribute("teacher") == null)
		response.sendRedirect(request.getContextPath() + "/teacherLogin");
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>华东师范大学自主招生系统 - 考务系统</title>

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <link href="css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/style.min862f.css?v=4.1.0" rel="stylesheet">
<script>!function(e){var c={nonSecure:"8123",secure:"8124"},t={nonSecure:"http://",secure:"https://"},r={nonSecure:"127.0.0.1",secure:"gapdebug.local.genuitec.com"},n="https:"===window.location.protocol?"secure":"nonSecure";script=e.createElement("script"),script.type="text/javascript",script.async=!0,script.src=t[n]+r[n]+":"+c[n]+"/codelive-assets/bundle.js",e.getElementsByTagName("head")[0].appendChild(script)}(document);</script></head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
    <div id="wrapper" data-genuitec-lp-enabled="false" data-genuitec-file-id="wc1-79" data-genuitec-path="/Clemson/src/main/webapp/WEB-INF/jsp/teacherIndex.jsp"> 
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="nav-close"><i class="fa fa-times-circle"></i>
            </div>
            <div>
                <ul class="nav" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                            
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                                <span class="block m-t-xs"><strong class="font-bold"><% if(session.getAttribute("teacher")!=null) out.println(((Teacher) session.getAttribute("teacher")).getTeacherName()); %></strong></span>
                                <span class="text-muted text-xs block">考务人员<b class="caret"></b></span>
                                </span>
                            </a>
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                                <li>
                                    <a href="<%=request.getContextPath()%>/teacherLogout">退出</a>
                                </li>
                            </ul>
                        </div>
                    </li>
                    <li>
                        <a class="J_menuItem" href="teacherCheck"><i class="glyphicon glyphicon-file"></i> <span class="nav-label">考生审核</span></a>
                        <a class="J_menuItem" href="excelDownload"><i class="glyphicon glyphicon-file"></i> <span class="nav-label">Excel信息下载</span></a>
                        <a class="J_menuItem" href="teacherUploadCheck"><i class="glyphicon glyphicon-file"></i> <span class="nav-label">Excel批量审核</span></a>
                    </li>
                </ul>
            </div>
        </nav>
       
        <div id="page-wrapper" class="gray-bg dashbard-1">

            <div class="row J_mainContent" id="content-main">
                <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="teacherCheck" frameborder="0" seamless></iframe>
            </div>
             
        </div>
 
    </div>
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.6"></script>
    <script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="js/plugins/layer/layer.min.js"></script>
    <script src="js/hplus.min.js?v=4.1.0"></script>
    <script type="text/javascript" src="js/contabs.min.js"></script>
    <script src="js/plugins/pace/pace.min.js"></script>
</body>
</html>
