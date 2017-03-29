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
	<div class="wrapper wrapper-content animated fadeInRight" data-genuitec-lp-enabled="false" data-genuitec-file-id="wc1-0" data-genuitec-path="/Clemson/src/main/webapp/WEB-INF/jsp/adminClass.jsp">

		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h1>考场设置</h1>
					</div>
					<div class="ibox-content">
						<div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group" style="width:100%;">
								<button type="button" class="btn btn-outline btn-default"
								data-toggle="modal" data-target="#addClassRoom">
									<i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
								</button>
								<input type="text" class="form-control input-sm m-b-xs" id="filter" placeholder="搜索表格..." style="width:20%;float:right;"/>
						</div>
						<table class="footable table table-stripped toggle-arrow-tiny" data-page-size="9" data-filter="#filter">
							<thead>
								<tr>
									<th style="width:20%;">考场名称</th>
									<th style="width:40%;">所属考点</th>
									<th style="width:20%;">状态</th>
									<th style="width:20%;">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${classList}" var="classRoom">
									<tr>
										<td>${classRoom.classNo}</td>
										<td>${classRoom.schoolName}</td>
										<td><c:choose>
												<c:when test="${classRoom.status == 0}">
												启用
											</c:when>
												<c:otherwise>
												禁用
											</c:otherwise>
											</c:choose>
										</td>
										<td>
											<c:choose>
												<c:when test="${classRoom.classId != 0}">
													<button type="button" onclick="deleteClassRoom('${classRoom.classId}')"
														class="btn btn-outline btn-default">
														<i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
													</button>
													<button type="button"
														onclick="editClassRoom('${classRoom.classId}','${classRoom.schoolName}', '${classRoom.classNo}', '${classRoom.schoolId}')"
														class="btn btn-outline btn-default" data-toggle="modal"
														data-target="#editClassRoom">
														<i class="glyphicon glyphicon-wrench" aria-hidden="true"></i>
													</button>
													<c:choose>
														<c:when test="${classRoom.status == 0}">
															<button type="button"
																onclick="disableOrEnable(1, '${classRoom.classId}')"
																class="btn btn-outline btn-default">禁用</button>
														</c:when>
														<c:otherwise>
															<button type="button"
																onclick="disableOrEnable(0, '${classRoom.classId}')"
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

						<!-- 新增考场 弹窗 -->
						<div class="modal inmodal" id="addClassRoom" tabindex="-1"
							role="dialog" aria-hidden="true" style="display:none;">
							<div class="modal-dialog">
								<div class="modal-content animated flipInY">
									<div class="modal-header" style="padding:10px;">
										<button type="button" class="close" data-dismiss="modal">
											<span aria-hidden="true">&times;</span><span class="sr-only"></span>
										</button>
										<h2 style="text-align:center;">新增考场</h2>
									</div>
									<div class="modal-body">
										<form class="form-horizontal m-t" action="adminClassRoomAdd" name="addForm" method="POST">
											<div class="form-group">
												<label class="col-sm-3 control-label">所属考点：</label>
												<div class="col-sm-8">
													<select class="form-control m-b" name="schoolId">
														<c:forEach items="${schoolList}" var="school">														
														<option value="${school.schoolId}">${school.schoolName}</option>
														</c:forEach>	
													</select>
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label">考场名称：</label>
												<div class="col-sm-8">
													<input id="cname" name="classRoomName" type="text" class="form-control">
												</div>
											</div>
										</form>
									</div>
									<div class="modal-footer">
										<button type="button" onclick="addFormSubmit()" class="btn btn-primary">
											 确定
										</button>
									</div>
								</div>
							</div>
						</div>
						
						<!-- 编辑考场 弹窗 -->
						<div class="modal inmodal" id="editClassRoom" tabindex="-1"
							role="dialog" aria-hidden="true" style="display:none;">
							<div class="modal-dialog">
								<div class="modal-content animated flipInY">
									<div class="modal-header" style="padding:10px;">
										<button type="button" class="close" data-dismiss="modal">
											<span aria-hidden="true">&times;</span><span class="sr-only"></span>
										</button>
										<h2 style="text-align:center;">编辑考场</h2>
									</div>
									<div class="modal-body">
										<form class="form-horizontal m-t" name="editForm" method="POST" action="adminClassRoomEdit">
											<div class="form-group">
												<label class="col-sm-3 control-label">考场名称：</label>
												<div class="col-sm-8">
													<input id="classNo" name="classNo" type="text" class="form-control">
													<input id="classId" name="classId" type="hidden" />
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label">所属考点：</label>
												<div class="col-sm-8">
													<select class="form-control m-b" id="editClassSchool" name="editClassSchool">
														<c:forEach items="${schoolList}" var="schoolList">														
															<option  value="${schoolList.schoolId}">${schoolList.schoolName}</option>
														</c:forEach>	 
													</select>
												</div>
											</div>
										</form>
									</div>
									<div class="modal-footer">
										<button type="button" onclick="editFormSubmit()" class="btn btn-primary">确定</button>
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
			$(".footable2").footable()
		});
	</script>
	<script src="js/plugins/sweetalert/sweetalert.min.js"></script>
	<script>
		function deleteClassRoom(id) { 
		 		var classRoomId = id;
		 		var str = "classId="+classRoomId;
				swal({
					title : "您确定要删除这条记录吗",
					text : "删除后将无法恢复，请谨慎操作！",
					type : "warning",
					showCancelButton : true,
					confirmButtonColor : "#DD6B55",
					confirmButtonText : "是的，我要删除！",
					cancelButtonText : "让我再考虑一下…",
					closeOnConfirm : false,
					closeOnCancel : false
				}, function(isConfirm) {
					if (isConfirm) {
					swal("删除成功！", "您已经永久删除了这条记录。", "success");	
						$.ajax({
							url : "adminClassRoomDelete",
							type : "post",
							data : str, 
							success:function(data){ 
								document.location.reload();
							}
						});											
					} else {
						swal("已取消", "您取消了删除操作！", "error");
					} 
			});
		}
		
		function disableOrEnable(status, classId){
			var str ="status="+status+"&classId="+classId;
			$.ajax({
				url : "adminClassRoomEditStatus",
				type : "post",
				data : str, 
				success:function(data){ 
					document.location.reload();
					}
				});		
		}
		
		function editClassRoom(classId, schoolName, classNo, schoolId) {
			$("[name='editClassSchool']").children().remove("[value='"+schoolId+"']");
		 	$("[name='editClassSchool']").prepend("<option value='"+schoolId+"' selected>"+schoolName+"</option>"); 
			$("#classId").attr("value", classId);
			$("#classNo").attr("value", classNo);
		}
		
		function addFormSubmit() {
			document.addForm.submit();
		}
		
		function editFormSubmit() {
			document.editForm.submit();
		}
	</script>
</body>


<!-- Mirrored from www.zi-han.net/theme/hplus/table_foo_table.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:20:03 GMT -->
</html>
