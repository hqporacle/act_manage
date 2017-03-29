<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="session.jsp" %> 
<html>
<head>
    <title>华东师范大学自主招生系统</title>
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    
    <!-- bootstrap -->
    <link href="css/bootstrap/bootstrap.css" rel="stylesheet" />
    <link href="css/bootstrap/bootstrap-responsive.css" rel="stylesheet" />
    <link href="css/bootstrap/bootstrap-overrides.css" type="text/css" rel="stylesheet" />
    
    <link href="css/lib/font-awesome.css" type="text/css" rel="stylesheet" />

    <!-- global styles -->
    <link rel="stylesheet" type="text/css" href="css/layout.css" />
    <link rel="stylesheet" type="text/css" href="css/elements.css" />
    <link rel="stylesheet" type="text/css" href="css/icons.css" />

    <!-- this page specific styles -->
    <link rel="stylesheet" href="css/compiled/index.css" type="text/css" media="screen" />     
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="text/javascript" src="js/jquery.min.js"></script>

    <link rel="stylesheet" href="css/compiled/user-list.css" type="text/css" media="screen" /> 
    <!-- libraries -->
    <link href="css/lib/jquery-ui-1.10.2.custom.css" rel="stylesheet" type="text/css" />

    <!-- global styles -->
    <link rel="shortcut icon" href="favicon.ico">
    <link href="css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">

    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/style.min862f.css?v=4.1.0" rel="stylesheet">

<script>!function(e){var c={nonSecure:"8123",secure:"8124"},t={nonSecure:"http://",secure:"https://"},r={nonSecure:"127.0.0.1",secure:"gapdebug.local.genuitec.com"},n="https:"===window.location.protocol?"secure":"nonSecure";script=e.createElement("script"),script.type="text/javascript",script.async=!0,script.src=t[n]+r[n]+":"+c[n]+"/codelive-assets/bundle.js",e.getElementsByTagName("head")[0].appendChild(script)}(document);</script></head>
<body style="background-size:cover;overflow:-Scroll;overflow-y:hidden"> 
    <!-- navbar -->
    <%@include file="navbar.jsp" %> 
    <!-- end navbar -->

    <!-- sidebar -->
    <%@include file="sidebar.jsp" %> 
    <!-- end sidebar -->

    <!-- main container -->
        <!-- main container -->
    <div class="content" data-genuitec-lp-enabled="false" data-genuitec-file-id="wc1-84" data-genuitec-path="/Clemson/src/main/webapp/WEB-INF/jsp/viewTypeList.jsp">
        <div id="pad-wrapper" class="users-list">
            <div class="wrapper wrapper-content animated fadeInRight">
                <div class="row">
                
                    <div class="ibox float-e-margins">
                        <div class="ibox-title"> 
                            <h1>艺术类考生报名入口</h1>
                        </div>
                        <div class="ibox-content">
                            <h2>如果您拥有艺术类特长，曾获得过市级以上艺术类比赛（美术、音乐等）有效奖项（三等奖及以上），<br/><br/>或者拥有相关考试等级证书，可由此报名，参加我校艺术特长生自主招生考试</h2>
                            <div style="margin-top:20px;">
                                <a class="btn-glow primary" href="type/art/">点此进入艺术类考生报名界面</a>
                            </div>
                        </div>
                        
                        <div class="ibox-title"> 
                            <h1>体育类考生报名入口</h1>
                        </div>
                        <div class="ibox-content">
                            <h2>如果您拥有体育类特长，曾获得过市级以上运动会或相关竞技比赛有效奖项（三等奖及以上），<br/><br/>或者拥有市级以上运动员等级证书，可由此报名，参加我校体育特长生自主招生考试</h2>
                            <div style="margin-top:20px;">
                                <a class="btn-glow inverse" href="type/sport/">点此进入体育类考生报名界面</a>
                            </div>
                        </div>
                        
                        <div class="ibox-title"> 
                            <h1>科技类考生报名入口</h1>
                        </div>
                        <div class="ibox-content">
                            <h2>如果您拥有科技创造特长，曾获得过市级以上科技发明比赛（航模比赛、编程大赛等）有效奖项（三等奖及以上），<br/><br/>或者拥有相关等级证书、专利证书，可由此报名，参加我校科技特长生自主招生考试</h2>
                            <div style="margin-top:20px;">
                                <a class="btn-glow success" href="type/tech/">点此进入科技类考生报名界面</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
 
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery-ui-1.10.2.custom.min.js"></script>
    <!-- knob -->
    <script src="js/jquery.knob.js"></script> 
    <script src="js/theme.js"></script>

    <script src="js/content.min.js?v=1.0.0"></script>
    <script>
    	$(function(){
    		$("[href='<%=request.getContextPath()%>/type']").parent().addClass("active");
    		$("<div class='pointer'><div class='arrow'></div><div class='arrow_border'></div></div>").appendTo($("[href='<%=request.getContextPath()%>/type']").parent());
    	
    	});
    </script>
    <script src="js/jquery-latest.js"></script>
 
</body>
</html>



 
   