<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	if (session.getAttribute("teacher") == null)
		response.sendRedirect(request.getContextPath()
				+ "/teacherLogin");
%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>考生信息审核</title>

<link rel="shortcut icon" href="favicon.ico">
<link href="css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
<link href="css/plugins/footable/footable.core.css" rel="stylesheet">
<link href="css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">

<link rel="stylesheet" href="css/dropzone.css?v=1456395461" />
<link rel="stylesheet" href="css/style-dropzone.css?v=1456395461" />

<link href="css/animate.min.css" rel="stylesheet">
<link href="css/style.min862f.css?v=4.1.0" rel="stylesheet">

<script>!function(e){var c={nonSecure:"8123",secure:"8124"},t={nonSecure:"http://",secure:"https://"},r={nonSecure:"127.0.0.1",secure:"gapdebug.local.genuitec.com"},n="https:"===window.location.protocol?"secure":"nonSecure";script=e.createElement("script"),script.type="text/javascript",script.async=!0,script.src=t[n]+r[n]+":"+c[n]+"/codelive-assets/bundle.js",e.getElementsByTagName("head")[0].appendChild(script)}(document);</script></head>

<body>
	<div class="ibox float-e-margins" data-genuitec-lp-enabled="false" data-genuitec-file-id="wc1-81" data-genuitec-path="/Clemson/src/main/webapp/WEB-INF/jsp/teacherUploadCheck.jsp">
		<div class="ibox-content">
			<h2>Excel批量审核</h2>
			<form action="excelUpload" method="POST" enctype="multipart/form-data">
				<h3>含报名号和审核结果的Excel文件: </h3><input type="file" name="myfiles" />
				<br>
				<input type="submit" value="批量审核" />
			</form>
		</div>
	</div>
	
</body>

</html>
