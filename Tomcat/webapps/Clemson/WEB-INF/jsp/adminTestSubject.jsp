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
	style="background-size:cover;overflow:-Scroll;">
	<div class="wrapper wrapper-content animated fadeInRight" data-genuitec-lp-enabled="false" data-genuitec-file-id="wc1-13" data-genuitec-path="/Clemson/src/main/webapp/WEB-INF/jsp/adminTestSubject.jsp">

		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h1>考试科目设置</h1>
					</div>
					<div class="ibox-content">
						<div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group" style="width:100%;">
								<button type="button" class="btn btn-outline btn-default"
								data-toggle="modal" data-target="#addCourse">
									<i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
								</button>
								<input type="text" class="form-control input-sm m-b-xs" id="filter" placeholder="搜索表格..." style="width:25%;float:right;"/>
						</div>
						<table class="footable table table-stripped toggle-arrow-tiny" data-page-size="9" data-filter="#filter">
							<thead>
								<tr>
									<th style="width:20%;">考试科目名称</th>
									<th style="width:20%;">所属小类</th>
									<th style="width:20%;">录取分数</th>
									<th style="width:20%;">状态</th>
									<th style="width:20%;">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${subjectList}" var="subject">
								<tr>
									<td>${subject.subjectName}</td>
									<td>${subject.subTypeName}</td>
									<td>${subject.subjectGoalMark}  </td>
									<td>
										<c:choose>
											<c:when test="${subject.status == 0}">
												启用
											</c:when>
											<c:otherwise>
												禁用
											</c:otherwise>
										</c:choose>									
									</td>
									<td>
										<button type="button" onclick="deleteSubject('${subject.subjectId}')"class="btn btn-outline btn-default">
											<i class="glyphicon glyphicon-trash "
												aria-hidden="true"></i>
										</button>                                   
										<button type="button"onclick="editSubject('${subject.subTypeId}','${subject.subTypeName}','${subject.subjectName}','${subject.subjectId}', '${subject.subjectGoalMark}')"  class="btn btn-outline btn-default"
											data-toggle="modal" data-target="#editSubject">
											<i class="glyphicon glyphicon-wrench" aria-hidden="true"></i>
										</button> 
										<c:choose>
										<c:when test="${subject.subTypeStatus == 0}">
										<c:choose>
											<c:when test="${subject.status == 0}">
											<button type="button" onclick="disableOrEnable(1,'${subject.subjectId}','${subject.subTypeId}','${subject.subjectName}')"class="btn btn-outline btn-default">禁用</button>
											</c:when>
											<c:otherwise>
											<button type="button" onclick="disableOrEnable(0,'${subject.subjectId}','${subject.subTypeId}','${subject.subjectName}')" class="btn btn-outline btn-primary">启用</button>
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
						<div class="modal inmodal" id="addCourse" tabindex="-1"
							role="dialog" aria-hidden="true" style="display:none;">
							<div class="modal-dialog">
								<div class="modal-content animated flipInY">
									<div class="modal-header" style="padding:10px;">
										<button type="button" class="close" data-dismiss="modal">
											<span aria-hidden="true">&times;</span><span class="sr-only"></span>
										</button>
										<h2 style="text-align:center;">新增考试科目</h2>
									</div>
									<div class="modal-body">
										<form class="form-horizontal m-t" action="adminTestSubjectAdd" name="addForm" method="POST" id="">
											<div class="form-group">
												<label class="col-sm-3 control-label">所属小类：</label>
												<div class="col-sm-8">
													<select class="form-control m-b" name="subTypeId">
														<c:forEach items="${subTypeList}" var="subType">														
														<option value="${subType.subTypeId}">${subType.subTypeName}</option>
														</c:forEach>	
													</select>
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label">科目名称：</label>
												<div class="col-sm-8">
													<input id="cname" name="subjectName" type="text" class="form-control">
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label">录取分数：</label>
												<div class="col-sm-8">
													<input id="cname" name="subjectGoalMark" type="text" class="form-control">
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
						<div class="modal inmodal" id="editSubject" tabindex="-1"
							role="dialog" aria-hidden="true" style="display:none;">
							<div class="modal-dialog">
								<div class="modal-content animated flipInY">
									<div class="modal-header" style="padding:10px;">
										<button type="button" class="close" data-dismiss="modal">
											<span aria-hidden="true">&times;</span><span class="sr-only"></span>
										</button>
										<h2 style="text-align:center;">编辑考试科目信息</h2>
									</div>
									<div class="modal-body">
										<form class="form-horizontal m-t" action="adminTestSubjectEdit" name="editForm" method="POST" id="">
											<div class="form-group">
												<label class="col-sm-3 control-label">所属小类：</label>
												<div class="col-sm-8">
													<select class="form-control m-b" id="editSubTypeId"name="editSubTypeId">
														<c:forEach items="${subTypeList}" var="subType">														
															<option  value="${subType.subTypeId}">${subType.subTypeName}</option>
														</c:forEach>	 
													</select>
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label">科目名称：</label>
												<div class="col-sm-8">
													<input id="editSubjectName" name="editSubjectName" type="text" class="form-control">
												</div>
												<div class="col-sm-8" style="display:none;">
													<input id="subjectId" name="subjectId" type="text" class="form-control" >
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label">录取分数：</label>
												<div class="col-sm-8">
													<input id="editSubjectGoalMark" name="editSubjectGoalMark" type="text" class="form-control">
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
			$(".footable2").footable();
		});
	</script>
	<script src="js/plugins/sweetalert/sweetalert.min.js"></script>
	<script>
		function disableOrEnable(status, subjectId, editSubTypeId, subjectName){
			var str ="status="+status+"&subjectId="+subjectId+"&editSubTypeId="+editSubTypeId+"&editSubjectName="+subjectName;
			$.ajax({
				url : "adminTestSubjectEdit",
				type : "post",
				data : str, 
				success:function(data){ 
					document.location.reload();
					}
				});		
		}
		
		 function deleteSubject(id) { 
		 		var subjectId = id;
		 		var str = "subjectId="+subjectId;
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
							url : "adminTestSubjectDelete",
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
		function editSubject(subTypeId, subTypeName, subjectName, subjectId, subjectGoalMark){   
			$("[name='editSubTypeId']").children().remove("[value='"+subTypeId+"']");
		 	$("[name='editSubTypeId']").prepend("<option value='"+subTypeId+"' selected>"+subTypeName+"</option>"); 
		 	$("#editSubjectName").val(subjectName);
		 	$("#subjectId").val(subjectId);
		 	$("#editSubjectGoalMark").val(subjectGoalMark);
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
