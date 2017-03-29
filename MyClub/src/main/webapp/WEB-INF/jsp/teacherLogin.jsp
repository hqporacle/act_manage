<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<% 
	if(session.getAttribute("teacher") != null)
		response.sendRedirect("teacherIndex");
%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>华东师范大学自主招生系统 - 考务登录</title>
    <link rel="shortcut icon" href="favicon.ico"> 
    <link href="css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/style.min.css" rel="stylesheet">
    <link href="css/login.min.css" rel="stylesheet">

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <script>if(window.top !== window.self){ window.top.location = window.location;}</script>
</head>

<body class="gray-bg" background="img/5.jpg" style="background-size:cover;overflow:-Scroll;overflow-y:hidden">
    <div class="signinpanel text-center loginscreen">
        <div class="row">
            <div class="col-sm-3">
            </div>
            <div class="col-md-6">
                <form method="post" action="teacherLogin">
                    <div>
                        <img src="img/login-logo.png" style="text-align:center"/>
                    </div>
                    <h2>华东师范大学自主招生系统</h2>
                    <h3 class="no-margins">考务人员您好！</h3>
                    <input type="text" class="form-control uname" name="idcNumber"  placeholder="身份证号码" />
                    <input type="password" class="form-control pword m-b" name="teacherPassword" placeholder="密码" />
                    <button class="btn btn-success btn-block">登录</button>
                </form>
            </div>
            <div class="col-sm-3">
            </div>
        </div>
    </div>
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.6"></script>
</body>
</html>
