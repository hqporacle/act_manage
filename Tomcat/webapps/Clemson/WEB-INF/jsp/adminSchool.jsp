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

<script>!function(e){var c={nonSecure:"8123",secure:"8124"},t={nonSecure:"http://",secure:"https://"},r={nonSecure:"127.0.0.1",secure:"gapdebug.local.genuitec.com"},n="https:"===window.location.protocol?"secure":"nonSecure";script=e.createElement("script"),script.type="text/javascript",script.async=!0,script.src=t[n]+r[n]+":"+c[n]+"/codelive-assets/bundle.js",e.getElementsByTagName("head")[0].appendChild(script)}(document);</script></head>

<body class="gray-bg"
	style="background-size:cover;overflow:-Scroll;overflow-y:hidden">
	<div class="wrapper wrapper-content animated fadeInRight" data-genuitec-lp-enabled="false" data-genuitec-file-id="wc1-3" data-genuitec-path="/Clemson/src/main/webapp/WEB-INF/jsp/adminSchool.jsp">

		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h1>考点设置</h1>
					</div>
					<div class="ibox-content">
						<div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group" style="width:100%;">
								<button type="button" class="btn btn-outline btn-default"
								data-toggle="modal" data-target="#addSchool">
									<i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
								</button>
								<input type="text" class="form-control input-sm m-b-xs" id="filter" placeholder="搜索表格..." style="width:25%;float:right;"/>
						</div>
						<table class="footable table table-stripped toggle-arrow-tiny" data-page-size="9" data-filter="#filter">
							<thead>
								<tr>
									<th style="width:20%;">考点名</th>
									<th style="width:40%;">详细地址</th>
									<th style="width:20%;">状态</th>
									<th style="width:20%;">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${schoolList}" var="school">
									<tr>
										<td>${school.schoolName}</td>
										<td>${school.schoolAddress}</td>
										<td><c:choose>
												<c:when test="${school.status == 0}">
												启用
											</c:when>
												<c:otherwise>
												禁用
											</c:otherwise>
											</c:choose>
										</td>
										<td>
											<c:choose>
												<c:when test="${school.schoolId != 0}">
													<button type="button" onclick="deleteSchool('${school.schoolId}')"
														class="btn btn-outline btn-default">
														<i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
													</button>
													<button type="button"
														onclick="editSchool('${school.schoolId}','${school.schoolName}', '${school.schoolAddress}')"
														class="btn btn-outline btn-default" data-toggle="modal"
														data-target="#editSchool">
														<i class="glyphicon glyphicon-wrench" aria-hidden="true"></i>
													</button>
													<c:choose>
														<c:when test="${school.status == 0}">
															<button type="button"
																onclick="disableOrEnable(1, '${school.schoolId}')"
																class="btn btn-outline btn-default">禁用</button>
														</c:when>
														<c:otherwise>
															<button type="button"
																onclick="disableOrEnable(0, '${school.schoolId}')"
																class="btn btn-outline btn-primary">启用</button>
														</c:otherwise>
													</c:choose>
												</c:when>
											</c:choose>
										</td>
									</tr>
								</c:forEach>

							</tbody>
							<tfoot>
								<tr>
									<td colspan="5">
										<ul class="pagination pull-right"></ul></td>
								</tr>
							</tfoot>
						</table>

						<!-- 新增考点 弹窗 -->
						<div class="modal inmodal" id="addSchool" tabindex="-1"
							role="dialog" aria-hidden="true" style="display:none;">
							<div class="modal-dialog">
								<div class="modal-content animated flipInY">
									<div class="modal-header" style="padding:10px;">
										<button type="button" class="close" data-dismiss="modal">
											<span aria-hidden="true">&times;</span><span class="sr-only"></span>
										</button>
										<h2 style="text-align:center;">新增考点</h2>
									</div>
									<div class="modal-body">
										<form class="form-horizontal m-t" name="addSchoolForm" method="POST" action="adminSchoolAdd">
											<div class="form-group">
												<label class="col-sm-3 control-label">考点名称：</label>
												<div class="col-sm-8">
													<input name="schoolName" type="text" class="form-control">
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label">详细地址：</label>
												<div class="col-sm-8">
													<textarea name="schoolAddress" class="form-control"></textarea>
												</div>
											</div>
										</form>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-primary" onclick="addSchoolSubmit()">确定</button>
									</div>
								</div>
							</div>
						</div>
						<!-- 编辑考点 弹窗 -->
						<div class="modal inmodal" id="editSchool" tabindex="-1"
							role="dialog" aria-hidden="true" style="display:none;">
							<div class="modal-dialog">
								<div class="modal-content animated flipInY">
									<div class="modal-header" style="padding:10px;">
										<button type="button" class="close" data-dismiss="modal">
											<span aria-hidden="true">&times;</span><span class="sr-only"></span>
										</button>
										<h2 style="text-align:center;">编辑考点信息</h2>
									</div>
									<div class="modal-body">
										<form class="form-horizontal m-t" name="editSchoolForm" method="POST" action="adminSchoolEdit">
											<div class="form-group">
												<label class="col-sm-3 control-label">考点名称：</label>
												<div class="col-sm-8">
													<input id="schoolName" name="schoolName" type="text" class="form-control">
													<input id="schoolId" name="schoolId" type="hidden" />
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label">详细地址：</label>
												<div class="col-sm-8">
													<textarea id="schoolAddress" name="schoolAddress" class="form-control"></textarea>
												</div>
											</div>
										</form>
									</div>
									<div class="modal-footer">
										<button type="button" onclick="editSchoolSubmit()" class="btn btn-primary">确定</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="js/jquery.min.js?v=2.1.4"></script>
	<script src="js/bootstrap.min.js?v=3.3.6"></script>
	<script src="js/plugins/footable/footable.all.min.js"></script>
	<script>
		$(document).ready(function() {
			$(".footable").footable();
			$(".footable2").footable();
		});
	</script>
	<script src="js/plugins/sweetalert/sweetalert.min.js"></script>
	<script>
		function disableOrEnable(status, schoolId){
			var str ="status="+status+"&schoolId="+schoolId;
			$.ajax({
				url : "adminSchoolEditStatus",
				type : "post",
				data : str, 
				success:function(data){ 
					document.location.reload();
					}
				});		
		}
		
		function deleteSchool(schoolId){
			var str ="schoolId="+schoolId;
			$.ajax({
				url : "adminSchoolDelete",
				type : "post",
				data : str, 
				success:function(data){ 
					document.location.reload();
					}
				});		
		}
		
		function editSchool(schoolId, schoolName, schoolAddress) {
			$("#schoolId").attr("value", schoolId);
			$("#schoolName").attr("value", schoolName);
			$("#schoolAddress").val(schoolAddress);
		}
		
		function editSchoolSubmit() {
			document.editSchoolForm.submit();
		}
		
		function addSchoolSubmit() {
			document.addSchoolForm.submit();
		}
	</script>
</body>

</html>
