<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" import="java.util.*,com.clemson.model.*"
	pageEncoding="UTF-8"%>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<title>华东师范大学自主招生系统 - 管理系统</title>
	
	<link rel="shortcut icon" href="favicon.ico">
	<link href="css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
	<link href="css/plugins/footable/footable.core.css" rel="stylesheet">
	<link href="css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
	
	<link href="css/animate.min.css" rel="stylesheet">
	<link href="css/style.min862f.css?v=4.1.0" rel="stylesheet">
	
	<link href="css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
</head>

<body>
	<div class="span8 column b-r">
		<form method="post" action="<%=request.getContextPath()%>/adminSetsumeiEdit"
			accept-charset="utf-8" name="form1">
			<div class="ibox float-e-margins">
				<div class="ibox-content">
				<h2>说明文字编辑</h2>
					<table class="table table-striped">
						<tr>
							<td>高考报名号：</td>
							<td><textarea name="ceeNumberDes" class="span12" rows="4" cols="50" placeholder="请输入高考报名号说明">${ceeNumberDes}</textarea></td>
						</tr>
						<tr>
							<td>艺术号：</td>
							<td><textarea name="artNumberDes" class="span12" rows="4" cols="50" placeholder="请输入艺术号说明">${artNumberDes}</textarea></td>
						</tr>
						<tr>
							<td>
								<button type="button" onclick="formSubmit();" class="btn btn-outline btn-primary">提交修改</button>
							</td>
							<td></td>
						</tr>
					</table>
				</div>
			</div>
		</form>
	</div>
	
	<script> 
		function formSubmit(){
			document.form1.submit();
		}
	</script>
</body>
</html>
