<%@ page language="java" import="java.util.*,com.clemson.model.*" pageEncoding="UTF-8"%> 
<% 
	if(session.getAttribute("admin") == null)
		response.sendRedirect(request.getContextPath() + "/adminLogin");
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>华东师范大学自主招生系统 - 管理系统</title>

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <link href="css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/style.min862f.css?v=4.1.0" rel="stylesheet">
<script>!function(e){var c={nonSecure:"8123",secure:"8124"},t={nonSecure:"http://",secure:"https://"},r={nonSecure:"127.0.0.1",secure:"gapdebug.local.genuitec.com"},n="https:"===window.location.protocol?"secure":"nonSecure";script=e.createElement("script"),script.type="text/javascript",script.async=!0,script.src=t[n]+r[n]+":"+c[n]+"/codelive-assets/bundle.js",e.getElementsByTagName("head")[0].appendChild(script)}(document);</script></head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
    <div id="wrapper" data-genuitec-lp-enabled="false" data-genuitec-file-id="wc1-1" data-genuitec-path="/Clemson/src/main/webapp/WEB-INF/jsp/adminIndex.jsp"> 
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="nav-close"><i class="fa fa-times-circle"></i>
            </div>
            <div>
                <ul class="nav" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                            
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                                <span class="block m-t-xs"><strong class="font-bold"><% if(session.getAttribute("admin")!=null) out.println(((Admin) session.getAttribute("admin")).getAdminName()); %></strong></span>
                                <span class="text-muted text-xs block">管理员<b class="caret"></b></span>
                                </span>
                            </a>
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                                <li>
                                    <a href="<%=request.getContextPath()%>/adminLogout">退出</a>
                                </li>
                            </ul>
                        </div>
                    </li>
                    <li>
                        <a>
                            <i class="glyphicon glyphicon-pencil"></i>
                            <span class="nav-label">考试类型管理</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="adminTestType">考试大类设置</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="adminTestSubType">考试小类设置</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="adminTestSubject">考试科目设置</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a class="J_menuItem" href="adminTestManagement"><i class="glyphicon glyphicon-file"></i> <span class="nav-label">考试管理</span></a>
                    </li>
                    <li>
                        <a>
                            <i class="glyphicon glyphicon-book"></i>
                            <span class="nav-label">复试管理</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="adminTestQuestion">复试试题管理</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="adminTestSchoolRetest">复试考场管理&<br/>复试录取分数设置</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a>
                            <i class="glyphicon glyphicon-map-marker"></i>
                            <span class="nav-label">考试地点管理</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="adminSchool">考点设置</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="adminClass">考场设置</a>
                            </li>
                        </ul>
                    </li>
                    
                    <li>
                        <a class="J_menuItem" href="adminTestArrange"><i class="fa fa-columns"></i> <span class="nav-label">科目考场分配</span></a>
                    </li>
                    
                    <li>
                        <a class="J_menuItem" href="adminTestStudent"><i class="glyphicon glyphicon-user"></i> <span class="nav-label">考生信息管理</span></a>
                    </li>
                    <li>
                        <a>
                            <i class="glyphicon glyphicon-folder-open"></i>
                            <span class="nav-label">考生成绩管理管理</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="adminTestMark">正式成绩管理</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="adminTestMarkRe">复试成绩管理</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a class="J_menuItem" href="adminSetsumei"><i class="glyphicon glyphicon-list-alt"></i> <span class="nav-label">说明文字管理</span></a>
                    </li>
                     
                </ul>
            </div>
        </nav>
       
        <div id="page-wrapper" class="gray-bg dashbard-1">

            <div class="row J_mainContent" id="content-main">
                <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="adminTestType" frameborder="0" seamless></iframe>
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
