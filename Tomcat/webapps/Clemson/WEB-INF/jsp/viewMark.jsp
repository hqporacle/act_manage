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
<body style="background-size:cover;overflow:-Scroll;overflow-y:hidden"> 
    <!-- navbar -->
    <%@include file="navbar.jsp" %> 
    <!-- end navbar -->

    <!-- sidebar -->
    <%@include file="sidebar.jsp" %> 
    <!-- end sidebar -->

    <!-- main container -->
        <!-- main container -->
    <div class="content" data-genuitec-lp-enabled="false" data-genuitec-file-id="wc1-82" data-genuitec-path="/Clemson/src/main/webapp/WEB-INF/jsp/viewMark.jsp">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h2>${testNameForTitle} - 成绩</h2>
                <div class="ibox-tools">
                    <a class="btn-flat default collapse-link">收起</a>
                    <a class="btn-flat default" href="../studentIndex">返回</a>
                </div>
            </div>
            <div class="ibox-content">
                <table class="table table-striped">
                	<thead>
                		<tr>
                			<td>科目</td>
                			<td>成绩类型</td>
                			<td>成绩</td>
                			
                		</tr>
                	</thead>
                    <c:forEach items="${markList}" var="mark">  
                        <tr>
                            <td><c:out value="${mark.subjectName}" /></td>
                            <td><c:out value="${mark.markDescription}" /></td>
                            <td><c:out value="${mark.mark}" /></td>
                        </tr>
                    </c:forEach>
                </table>
                
                <table class="table table-striped">
                	<thead>
                		<tr>
                			<td>复试科目</td>
                			<td>成绩</td>
                			
                		</tr>
                	</thead>
                    	<tr>
                       		<td>复试英语</td>
                        	<td><c:out value="${retestMark.retestEnMark}" /></td>
                        </tr>
                        <tr>
                       		<td>复试专业</td>
                        	<td><c:out value="${retestMark.retestProMark}" /></td>
                        </tr>
                        <tr>
                       		<td>复试人文</td>
                        	<td><c:out value="${retestMark.retestPeoMark}" /></td>
                        </tr>
                </table>
                <h1 style="color:red">${result}</h1>
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
 

</body>
</html>



 
   