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

<script>!function(e){var c={nonSecure:"8123",secure:"8124"},t={nonSecure:"http://",secure:"https://"},r={nonSecure:"127.0.0.1",secure:"gapdebug.local.genuitec.com"},n="https:"===window.location.protocol?"secure":"nonSecure";script=e.createElement("script"),script.type="text/javascript",script.async=!0,script.src=t[n]+r[n]+":"+c[n]+"/codelive-assets/bundle.js",e.getElementsByTagName("head")[0].appendChild(script)}(document);</script></head>
<body  style="background-size:cover;overflow:-Scroll;"> 
    <!-- navbar -->
    <%@include file="navbar.jsp" %> 
    <!-- end navbar -->

    <!-- sidebar -->
    <%@include file="sidebar.jsp" %> 
    <!-- end sidebar -->


    <!-- main container -->
        <!-- main container -->
    <div class="content" data-genuitec-lp-enabled="false" data-genuitec-file-id="wc1-57" data-genuitec-path="/Clemson/src/main/webapp/WEB-INF/jsp/enrollDetail.jsp">      
        <div class="container-fluid">
            <div id="pad-wrapper" class="form-page">
                <div class="row-fluid form-wrapper">
                    <!-- left column -->
                    <div class="span8 column b-r">
                        <form method="post" action="<%=request.getContextPath()%>/${postURL}" accept-charset="utf-8" name="form1">
                            <div class="ibox float-e-margins">
                                <div class="ibox-title">
                                    <h2>您的考试信息</h2>
                                </div>
                                <table class="table table-striped">
                                	<tr>
                                        <td>考试报名号：</td>
                                    	<td style="font-size:20px;color:red;">${enroll.enrollNum}</td>
                                    </tr>
                                    <tr>
                                        <td>考试名称：</td>
                                    	<td>${enroll.testName}</td>
                                    </tr>
                                    <tr>
                                        <td>考试大类：</td>
                                        <td>${enroll.typeName}</td>
                                    </tr>
                                    <tr>
                                        <td>考试小类：</td>
                                        <td>${enroll.subTypeName}</td>
                                    </tr>
                                    <tr>
                                        <td>获得奖项及等级证书：</td>
                                        <td>${enroll.awards}</td>
                                    </tr>
                                    <tr>
                                        <td>考点名称：</td>
                                        <td>${enroll.schoolName}</td>
                                    </tr>
                                    <tr>
                                        <td>考点地址：</td>
                                        <td>${enroll.schoolAddress}</td>
                                    </tr>
                                   	<tr>
                                        <td>准考证号：</td>
                                        <td>${enroll.testNum}</td>
                                    </tr>
                                </table>
                                
                                <div class="ibox-title">
                                    <h2>科目</h2>
                                </div>
                                
                                <c:forEach items="${testArranges}" var="testArrange">
                                <table class="table table-striped">
			                        <tr>
			                            <td style="width:30%">科目名称</td><td><c:out value="${testArrange.subjectName}" /></td>
			                        </tr>
			                        <tr>
			                            <td style="width:30%">教室</td><td><c:out value="${testArrange.classNo}" /></td>
			                        </tr>
			                        <tr>
			                            <td style="width:30%">日期</td><td><c:out value="${testArrange.date}" /></td>
			                        </tr>
			                    </table>
			                    </c:forEach>
                                
                                <div class="ibox-title">
                                    <h2>复试</h2>
                                </div>
                                
                                <table class="table table-striped">
			                        <tr>
			                            <td style="width:30%">复试教室</td><td><c:out value="${testSchoolRetest.classNo}" /></td>
			                        </tr>
			                        <tr>
			                            <td style="width:30%">复试开始时间</td><td><c:out value="${testSchoolRetest.retestStartTime}" /></td>
			                        </tr>
			                        <tr>
			                            <td style="width:30%">复试结束时间</td><td><c:out value="${testSchoolRetest.retestEndTime}" /></td>
			                        </tr>
			                    </table>
                                
                                <div class="fluid">
	                                <div class="span12">
	                                    <a class="btn-flat inverse" href="../studentIndex" style="width:80px;text-align:center;float:right;">返回</a>
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



 
   