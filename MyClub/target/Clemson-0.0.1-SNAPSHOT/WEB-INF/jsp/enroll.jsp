<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="session.jsp" %>
<html>
<head>
    <title>华东师范大学自主招生系统</title>
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    
    <!-- bootstrap -->
    <link href="<%=request.getContextPath()%>/css/bootstrap/bootstrap.css" rel="stylesheet" />
    <link href="<%=request.getContextPath()%>/css/bootstrap/bootstrap-responsive.css" rel="stylesheet" />
    <link href="<%=request.getContextPath()%>/css/bootstrap/bootstrap-overrides.css" type="text/css" rel="stylesheet" />
    
    <link href="<%=request.getContextPath()%>/css/lib/font-awesome.css" type="text/css" rel="stylesheet" />

    <!-- global styles -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/layout.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/elements.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/icons.css" />

    <!-- this page specific styles -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/compiled/index.css" type="text/css" media="screen" />     
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>

    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/compiled/user-list.css" type="text/css" media="screen" /> 
    <!-- libraries -->
    <link href="<%=request.getContextPath()%>/css/lib/jquery-ui-1.10.2.custom.css" rel="stylesheet" type="text/css" />

    <!-- sweetalert -->
    <link href="<%=request.getContextPath()%>/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">

    <!-- global styles -->
    <link rel="shortcut icon" href="favicon.ico">
    <link href="<%=request.getContextPath()%>/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">

    <link href="<%=request.getContextPath()%>/css/animate.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/style.min862f.css?v=4.1.0" rel="stylesheet">

</head>
<body  style="background-size:cover;overflow:-Scroll;"> 
    <!-- navbar -->
    <%@include file="navbar.jsp" %> 
    <!-- end navbar -->

    <!-- sidebar -->
    <%@include file="sidebar.jsp" %> 
    <!-- end sidebar -->


    <!-- main container -->
        <!-- main container -->
    <div class="content">      
        <div class="container-fluid">
            <div id="pad-wrapper" class="form-page">
                <div class="row-fluid form-wrapper">
                    <!-- left column -->
                    <div class="span8 column b-r">
                        <form method="post" action="<%=request.getContextPath()%>/${postURL}" accept-charset="utf-8" name="form1">
                            <div class="ibox float-e-margins">
                                <div class="ibox-title">
                                    <h2>您的基本信息</h2>
                                    <div class="ibox-tools">
                                        <a class="btn-flat default collapse-link">收起</a>
                                    </div>
                                </div>
                                <div class="ibox-content">
                                    <table class="table table-striped">
                                        <tr>
                                            <td>身份证号：</td>
                                            <td>${student.idcNumber}</td>
                                            <td>姓名：</td>
                                            <td>${student.studentName}</td>
                                        </tr>
                                        <tr>
                                            <td>性别：</td>
                                            <td>${student.studentSex}</td>
                                            <td>民族：</td>
                                            <td>${student.studentNation}</td>
                                        </tr>
                                        <tr>
                                            <td>出生日期：</td>
                                            <td>${student.studentBirthday}</td>
                                            <td>家庭住址：</td>
                                            <td>${student.studentAddress}</td>
                                        </tr>
                                        <tr>
                                            <td>就读高中：</td>
                                            <td>${student.studentSchool}</td>
                                            <td>电子邮箱：</td>
                                            <td>${student.studentEmail}</td>
                                        </tr>
                                        <tr>
                                            <td>高考报名号：</td>
                                            <td>${student.ceeNumber}</td>
                                            <td>艺术号：</td>
                                            <td>${student.artNumber}</td>
                                        </tr>
                                    </table>
                                </div>
                                
                                <div class="ibox-title">
                                    <h2>您的报考信息</h2>
                                    <div class="ibox-tools">
                                        <a class="btn-flat default collapse-link">收起</a>
                                    </div>
                                </div>
                                <div class="ibox-content">
                                    <table class="table table-striped">
                                        <tr>
                                            <td>考试大类：</td>
                                            <td>${test.typeName}</td>
                                        </tr>
                                        <tr>
                                            <td>考试小类：</td>
                                            <td>${test.subTypeName}</td>
                                        </tr>
                                        <tr>
                                            <td>获得奖项及等级证书：</td>
                                            <td><textarea name="awards" class="span12" rows="4" placeholder="请输入您的相关获奖信息"></textarea></td>
                                        </tr>
                                    </table>
                                </div>
                                
                                <div class="fluid">
                                <div class="span10"></div>
                                <div class="span2">
                                    <a class="btn-flat inverse" href="../studentIndex" style="width:34px;text-align:center;">取消</a>
                                    <a class="btn-flat inverse" onclick="mySubmit();" style="width:34px;text-align:center;">提交</a>
                                </div>
                            </div>
                            </div>
                        </form>
                    </div>

                    <!-- right column -->
                    <div class="span4 column pull-right" style="text-align:center;font-family:'隶书';">
                        <img src="<%=request.getContextPath()%>/img/login-logo.png"/>
                        <br/>
                        <h1>华东师范大学</h1>
                        <h3>East China Normal University</h3>
                        <img src="<%=request.getContextPath()%>/img/5.jpg" style="margin-top:60px;"/>
                        <h3>华东师范大学中山北路校区大门</h3>
                    </div>

                </div>
            </div>
        </div>
    </div>


 
    <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/jquery-ui-1.10.2.custom.min.js"></script>
    <!-- knob -->
    <script src="<%=request.getContextPath()%>/js/jquery.knob.js"></script> 
    <script src="<%=request.getContextPath()%>/js/theme.js"></script>

    <script src="<%=request.getContextPath()%>/js/content.min.js?v=1.0.0"></script>
    <script src="<%=request.getContextPath()%>/js/plugins/sweetalert/sweetalert.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/jquery-latest.js"></script>
 	
	<script>
    	$(function(){
    		$("[href='<%=request.getContextPath()%>/type']").parent().addClass("active");
    		$("<div class='pointer'><div class='arrow'></div><div class='arrow_border'></div></div>").appendTo($("[href='<%=request.getContextPath()%>/type']").parent());
    	
    	});
    	
    	function mySubmit(){
			document.form1.submit();
		}
    </script> 
</body>
</html>



 
   