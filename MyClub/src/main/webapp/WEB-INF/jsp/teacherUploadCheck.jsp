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

</head>

<body>
	<div class="ibox float-e-margins">
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
