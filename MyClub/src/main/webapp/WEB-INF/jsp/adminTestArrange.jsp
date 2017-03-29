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
						<h1>科目考场分配</h1>
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
									<th style="width:30%;">考试名称</th>
									<th style="width:10%;">科目</th>
									<th style="width:20%;">学校</th>
									<th style="width:15%;">教室</th>
									<th style="width:15%;">日期</th>
									<th style="width:10%;">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${testArrangeList}" var="testArrange">
								<tr>
									<td>${testArrange.testName}</td>
									<td>${testArrange.subjectName}</td>
									<td>${testArrange.schoolName}</td>	
									<td>${testArrange.classNo}</td>		
									<td>${testArrange.date}</td>				
									<td>
										<button type="button" onclick="deleteTestArrange('${testArrange.testId}','${testArrange.subjectId}','${testArrange.schoolId}','${testArrange.classId}')"class="btn btn-outline btn-default">
											<i class="glyphicon glyphicon-trash "
												aria-hidden="true"></i>
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
										<form class="form-horizontal m-t" action="adminTestArrangeAdd" name="addForm" method="POST" id="">
											<div class="form-group">
												<label class="col-sm-3 control-label">考试名称：</label>
												<div class="col-sm-8">
													<select class="form-control m-b" name="testId">
														<c:forEach items="${testList}" var="test">														
														<option value="${test.testId}">${test.testName}</option>
														</c:forEach>	
													</select>
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label">科目名称：</label>
												<div class="col-sm-8">
													<select class="form-control m-b" name="subjectId">														 
													</select>
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label">学校名称：</label>
												<div class="col-sm-8">
													<select class="form-control m-b" name="schoolId">
														<c:forEach items="${schoolList}" var="school">														
														<option value="${school.schoolId}">${school.schoolName}</option>
														</c:forEach>														 
													</select>
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label">教室：</label>
												<div class="col-sm-8">
													<select class="form-control m-b" name="classId">														 
													</select>
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label">考试日期：</label>
												<div class="col-sm-8">
													<input id="cname" name="date" type="text" class="form-control" value="2016/04/06">
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
			$(".footable2").footable();
			$("[name='testId']").change(
				function(){
					var testId = $("[name='testId']").val();
					var str = "testId="+testId;
					$.ajax({
    	 				url:"getSubjectByTest",
    	 				type:"post",
    	 				data:str,
    	 				dataType:"json",
    	 				success: function(data){
    	 				$("[name='subjectId']").html("");
    	 				var html="";
    	 				var i=1;
    	 				$.each(data,function(key,value){  
    	 					 html+="<option value="+value.subjectId+">"+value.subjectName+"</option>";			 
    	 				});
    	 				$("[name='subjectId']").html(html);
    	 		}
    	 		});
			});
			$(function(){
				var testId = $("[name='testId']").val();
				var str = "testId="+testId;
					$.ajax({
    	 				url:"getSubjectByTest",
    	 				type:"post",
    	 				data:str,
    	 				dataType:"json",
    	 				success: function(data){
    	 				$("[name='subjectId']").html("");
    	 				var html="";
    	 				var i=1;
    	 				$.each(data,function(key,value){  
    	 					 html+="<option value="+value.subjectId+">"+value.subjectName+"</option>";			 
    	 				});
    	 				$("[name='subjectId']").html(html);
    	 		}
    	 		});				
			});
			$(function(){
					var schoolId = $("[name='schoolId']").val();
					var str = "schoolId="+schoolId;
					$.ajax({
    	 				url:"getClassBySchool",
    	 				type:"post",
    	 				data:str,
    	 				dataType:"json",
    	 				success: function(data){
    	 				$("[name='classId']").html("");
    	 				var html="";
    	 				var i=1;
    	 				$.each(data,function(key,value){  
    	 					 html+="<option value="+value.classId+">"+value.classNo+"</option>";			 
    	 				});
    	 				$("[name='classId']").html(html);
    	 		}
			});
			});
			$("[name='schoolId']").change(
				function(){
					var schoolId = $("[name='schoolId']").val();
					var str = "schoolId="+schoolId;
					$.ajax({
    	 				url:"getClassBySchool",
    	 				type:"post",
    	 				data:str,
    	 				dataType:"json",
    	 				success: function(data){
    	 				$("[name='classId']").html("");
    	 				var html="";
    	 				$.each(data,function(key,value){  
    	 					 html+="<option value="+value.classId+">"+value.classNo+"</option>";			 
    	 				});
    	 				$("[name='classId']").html(html);
    	 		}
    	 		});
			});
		});
	</script>
	<script src="js/plugins/sweetalert/sweetalert.min.js"></script>
	<script> 
		 function deleteTestArrange(testId, subjectId, schoolId, classId) { 
		 		var str = "testId="+testId
		 					+"&subjectId="+subjectId
		 					+"&schoolId="+schoolId
		 					+"&classId="+classId;
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
							url : "adminTestArrangeDelete",
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
	  
		function addFormSubmit(){
			document.addForm.submit();
		}
	</script>
</body>

</html>
