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

<body class="gray-bg"
	style="background-size:cover;overflow:-Scroll;">
	<div class="wrapper wrapper-content animated fadeInRight">

		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h1>考试管理</h1>
					</div>
					<div class="ibox-content">
						<div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group" style="width:100%;">
								<button type="button" class="btn btn-outline btn-default"
								data-toggle="modal" data-target="#addTest">
									<i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
								</button>
								<input type="text" class="form-control input-sm m-b-xs" id="filter" placeholder="搜索表格..." style="width:25%;float:right;"/>
						</div>
						<table class="footable table table-stripped toggle-arrow-tiny" data-page-size="9" data-filter="#filter">
							<thead>
								<tr>
									<th style="width:25%;">考试名称</th>
									<th style="width:25%;">考试小类</th>
									<th style="width:25%;">状态</th>
									<th style="width:25%;">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${testList}" var="test">
								<tr>
									<td>${test.testName}</td>
									<td>${test.subTypeName}</td>
									<td>
										<c:choose>
											<c:when test="${test.status == 0}">
												启用
											</c:when>
											<c:otherwise>
												禁用
											</c:otherwise>
										</c:choose>									
									</td>
									<td>
										<button type="button" onclick="deleteTest('${test.testId}')"class="btn btn-outline btn-default">
											<i class="glyphicon glyphicon-trash "
												aria-hidden="true"></i>
										</button>                                   
										<button type="button"onclick="editTest('${test.subTypeId}','${test.subTypeName}','${test.testName }','${test.testId}')"  class="btn btn-outline btn-default"
											data-toggle="modal" data-target="#editTest">
											<i class="glyphicon glyphicon-wrench" aria-hidden="true"></i>
										</button> 
										<c:choose>
										<c:when test="${test.subTypeStatus == 0}">
										<c:choose>
											<c:when test="${test.status == 0}">
											<button type="button" onclick="disableOrEnable(1,'${test.testId}','${test.subTypeId}','${test.testName}')"class="btn btn-outline btn-default">禁用</button>
											</c:when>
											<c:otherwise>
											<button type="button" onclick="disableOrEnable(0,'${test.testId}','${test.subTypeId}','${test.testName}')" class="btn btn-outline btn-primary">启用</button>
											</c:otherwise>
										</c:choose>
										</c:when>
										<c:otherwise>
											父类已被禁用
										</c:otherwise>
										</c:choose>
									</td>
								</tr>
								</c:forEach>
							</tbody>
							<tfoot>
								<tr>
									<td colspan="5"><ul class="pagination pull-right"></ul></td>
								</tr>
							</tfoot>
						</table>

						<!-- 新增科目 弹窗 -->
						<div class="modal inmodal" id="addTest" tabindex="-1"
							role="dialog" aria-hidden="true" style="display:none;">
							<div class="modal-dialog">
								<div class="modal-content animated flipInY">
									<div class="modal-header" style="padding:10px;">
										<button type="button" class="close" data-dismiss="modal">
											<span aria-hidden="true">&times;</span><span class="sr-only"></span>
										</button>
										<h2 style="text-align:center;">新增考试</h2>
									</div>
									<div class="modal-body">
										<form class="form-horizontal m-t" action="adminTestManagementAdd" name="addForm" method="POST" id="">
											<div class="form-group">
												<label class="col-sm-3 control-label">考试小类：</label>
												<div class="col-sm-8">
													<select class="form-control m-b" name="subTypeId">
														<c:forEach items="${subTypeList}" var="subType">														
														<option value="${subType.subTypeId}">${subType.subTypeName}</option>
														</c:forEach>	
													</select>
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label">考试名称：</label>
												<div class="col-sm-8">
													<input id="cname" name="testName" type="text" class="form-control">
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
						<!-- 编辑科目 弹窗 -->
						<div class="modal inmodal" id="editTest" tabindex="-1"
							role="dialog" aria-hidden="true" style="display:none;">
							<div class="modal-dialog">
								<div class="modal-content animated flipInY">
									<div class="modal-header" style="padding:10px;">
										<button type="button" class="close" data-dismiss="modal">
											<span aria-hidden="true">&times;</span><span class="sr-only"></span>
										</button>
										<h2 style="text-align:center;">编辑考试信息</h2>
									</div>
									<div class="modal-body">
										<form class="form-horizontal m-t" action="adminTestManagementEdit" name="editForm" method="POST" id="">
											<div class="form-group">
												<label class="col-sm-3 control-label">考试小类：</label>
												<div class="col-sm-8">
													<select class="form-control m-b" id="editSubTypeId" name="editSubTypeId">
														<c:forEach items="${subTypeList}" var="subType">														
															<option  value="${subType.subTypeId}">${subType.subTypeName}</option>
														</c:forEach>	 
													</select>
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label">科目名称：</label>
												<div class="col-sm-8">
													<input id="editTestName" name="editTestName" type="text" class="form-control">
												</div>
												<div class="col-sm-8" style="display:none;">
													<input id="testId" name="testId" type="text" class="form-control" >
												</div>
											</div>
										</form>
									</div>
									<div class="modal-footer">
										<button onclick="editFormSubmit()" type="button" class="btn btn-primary">确定</button>
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
		function disableOrEnable(status, testId, editSubTypeId, testName){
			var str ="status="+status+"&testId="+testId+"&editSubTypeId="+editSubTypeId+"&editTestName="+testName;
			$.ajax({
				url : "adminTestManagementEdit",
				type : "post",
				data : str, 
				success:function(data){ 
					document.location.reload();
					}
				});		
		}
		
		 function deleteTest(id) { 
		 		var testId = id;
		 		var str = "testId="+testId;
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
							url : "adminTestManagementDelete",
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
		function editTest(subTypeId, subTypeName, testName,testId){   
			$("[name='editSubTypeId']").children().remove("[value='"+subTypeId+"']");
		 	$("[name='editSubTypeId']").prepend("<option value='"+subTypeId+"' selected>"+subTypeName+"</option>"); 
		 	$("#editTestName").val(testName);
		 	$("#testId").val(testId);
		}
		function editFormSubmit(){
			document.editForm.submit();
		}
		
		function addFormSubmit(){
			document.addForm.submit();
		}
	</script>
</body>

</html>
