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
	<div class="wrapper wrapper-content animated fadeInRight" data-genuitec-lp-enabled="false" data-genuitec-file-id="wc1-9" data-genuitec-path="/Clemson/src/main/webapp/WEB-INF/jsp/adminTestQuestion.jsp">

		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h1>复试题目设置</h1>
					</div>
					<div class="ibox-content">
						<div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group" style="width:100%;">
								<button type="button" class="btn btn-outline btn-default"
								data-toggle="modal" data-target="#addQuestion">
									<i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
								</button>
								<input type="text" class="form-control input-sm m-b-xs" id="filter" placeholder="搜索表格..." style="width:25%;float:right;"/>
						</div>
						<table class="footable table table-stripped toggle-arrow-tiny" data-page-size="9" data-filter="#filter">
							<thead>
								<tr>
									<th style="width:20%;">题目类型</th>
									<th style="width:20%;">所属小类</th>
									<th style="width:40%;">题目详细</th>
									<th style="width:20%;">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${questionList}" var="question">
								<tr>
									<td>${question.questionTypeName}</td>
									<td>${question.subTypeName}</td>
									<td>${question.questionContent}</td>
									<td>
										<button type="button" onclick="deleteQuestion('${question.questionId}')"class="btn btn-outline btn-default">
											<i class="glyphicon glyphicon-trash "
												aria-hidden="true"></i>
										</button>                                   
										<button type="button"onclick="editQuestion('${question.subTypeId}','${question.subTypeName}','${question.questionContent }',
											'${question.questionId}','${question.questionTypeName}','${question.questionTypeId}')"  class="btn btn-outline btn-default"
											data-toggle="modal" data-target="#editQuestion">
											<i class="glyphicon glyphicon-wrench" aria-hidden="true"></i>
										</button>
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

						<!-- 新增考试题目 弹窗 -->
						<div class="modal inmodal" id="addQuestion" tabindex="-1"
							role="dialog" aria-hidden="true" style="display:none;">
							<div class="modal-dialog">
								<div class="modal-content animated flipInY">
									<div class="modal-header" style="padding:10px;">
										<button type="button" class="close" data-dismiss="modal">
											<span aria-hidden="true">&times;</span><span class="sr-only"></span>
										</button>
										<h2 style="text-align:center;">新增复试题目</h2>
									</div>
									<div class="modal-body">
										<form class="form-horizontal m-t" action="adminTestQuestionAdd" name="addForm" method="POST">
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
												<label class="col-sm-3 control-label">题目类型：</label>
												<div class="col-sm-8">
													<select class="form-control m-b" name="questionTypeId">
														<c:forEach items="${questionTypeList}" var="questionType">														
														<option value="${questionType.questionTypeId}">${questionType.questionTypeName}</option>
														</c:forEach>	
													</select>
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label">题目设置：</label>
												<div class="col-sm-8">
													<textarea rows="3" cols="20" name="questionContent"  class="form-control"></textarea>
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
						<!-- 编辑复试题目 弹窗 -->
						<div class="modal inmodal" id="editQuestion" tabindex="-1"
							role="dialog" aria-hidden="true" style="display:none;">
							<div class="modal-dialog">
								<div class="modal-content animated flipInY">
									<div class="modal-header" style="padding:10px;">
										<button type="button" class="close" data-dismiss="modal">
											<span aria-hidden="true">&times;</span><span class="sr-only"></span>
										</button>
										<h2 style="text-align:center;">编辑复试试题</h2>
									</div>
									<div class="modal-body">
										<form class="form-horizontal m-t" action="adminTestQuestionEdit" name="editForm" method="POST" id="">
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
												<label class="col-sm-3 control-label">题目类型：</label>
												<div class="col-sm-8">
													<select class="form-control m-b" id="editQuestionTypeId" name="editQuestionTypeId">
														<c:forEach items="${questionTypeList}" var="questionType">														
															<option  value="${questionType.questionTypeId}">${questionType.questionTypeName}</option>
														</c:forEach>	 
													</select>
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label">题目设置：</label>
												<div class="col-sm-8">
													<textarea rows="3" cols="20" id="editQuestionContent" name="editQuestionContent"  class="form-control"></textarea>
												</div>
												<div class="col-sm-8" style="display:none;">
													<input id="questionId" name="questionId" type="text" class="form-control" >
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
		 function deleteQuestion(id) { 
		 		var questionId = id;
		 		var str = "questionId="+questionId;
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
							url : "adminTestQuestionDelete",
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
								
		function editQuestion(subTypeId, subTypeName, questionContent,questionId,questionTypeName,questionTypeId){   
			$("[name='editSubTypeId']").children().remove("[value='"+subTypeId+"']");
		 	$("[name='editSubTypeId']").prepend("<option value='"+subTypeId+"' selected>"+subTypeName+"</option>"); 
		 	$("[name='editQuestionTypeId']").children().remove("[value='"+questionTypeId+"']");
		 	$("[name='editQuestionTypeId']").prepend("<option value='"+questionTypeId+"' selected>"+questionTypeName+"</option>"); 
		 	$("#editQuestionContent").val(questionContent);
		 	$("#questionId").val(questionId);
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
