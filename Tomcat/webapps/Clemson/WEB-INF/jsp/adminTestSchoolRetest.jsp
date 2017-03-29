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
	<div class="wrapper wrapper-content animated fadeInRight" data-genuitec-lp-enabled="false" data-genuitec-file-id="wc1-10" data-genuitec-path="/Clemson/src/main/webapp/WEB-INF/jsp/adminTestSchoolRetest.jsp">

		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h1>复试考场管理</h1>
					</div>
					<div class="ibox-content">
						<div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group" style="width:100%;">
								<button type="button" class="btn btn-outline btn-default"
								data-toggle="modal" data-target="#add">
									<i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
								</button>
								<input type="text" class="form-control input-sm m-b-xs" id="filter" placeholder="搜索表格..." style="width:25%;float:right;"/>
						</div>
						<table class="footable table table-stripped toggle-arrow-tiny" data-page-size="9" data-filter="#filter">
							<thead>
								<tr>
									<th style="width:25%;">考试名称</th>
									<th style="width:10%;">所在考点</th>
									<th style="width:15%;">所在考场</th>
									<th style="width:15%;">复试开始时间</th>
									<th style="width:15%;">复试结束时间</th>
									<th style="width:10%">复试录取总分</th>
									<th style="width:10%;">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${testSchoolRetestList}" var="testSchoolRetestList">
								<tr>
									<td>${testSchoolRetestList.testName}</td>
									<td>${testSchoolRetestList.schoolName}</td>
									<td>${testSchoolRetestList.classNo}</td>	
									<td>${testSchoolRetestList.retestStartTime}</td>		
									<td>${testSchoolRetestList.retestEndTime}</td>
									<td>${testSchoolRetestList.retestGoalTotalMark}</td>				
									<td>
										<button type="button" onclick="editRetestGoalMark('${testSchoolRetestList.testId}','${testSchoolRetestList.schoolId}','${testSchoolRetestList.retestClassroomId}')"  class="btn btn-outline btn-default"
											data-toggle="modal" data-target="#editRetestGoalMark">
											<i class="glyphicon glyphicon-wrench" aria-hidden="true"></i>
										</button> 
										<button type="button" onclick="deleteTestSchoolRetest('${testSchoolRetestList.testId}','${testSchoolRetestList.schoolId}','${testSchoolRetestList.retestClassroomId}')"class="btn btn-outline btn-default">
											<i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
										</button>                                   
									</td>
								</tr>
								</c:forEach>
							</tbody>
							<tfoot>
								<tr>
									<td colspan="7"><ul class="pagination pull-right"></ul></td>
								</tr>
							</tfoot>
						</table>

						<!-- 新增弹窗 -->
						<div class="modal inmodal" id="add" tabindex="-1"
							role="dialog" aria-hidden="true" style="display:none;">
							<div class="modal-dialog">
								<div class="modal-content animated flipInY">
									<div class="modal-header" style="padding:10px;">
										<button type="button" class="close" data-dismiss="modal">
											<span aria-hidden="true">&times;</span><span class="sr-only"></span>
										</button>
										<h2 style="text-align:center;">新增复试考场</h2>
									</div>
									<div class="modal-body">
										<form class="form-horizontal m-t" action="adminTestSchoolRetestAdd" name="addForm" method="POST" id="">
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
												<label class="col-sm-3 control-label">考点名称：</label>
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
													<select class="form-control m-b" name="classId">														 
													</select>
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label">考试开始日期：</label>
												<div class="col-sm-8">
													<input id="cname" name="retestStartTime" type="text" class="form-control">
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label">考试结束日期：</label>
												<div class="col-sm-8">
													<input id="cname" name="retestEndTime" type="text" class="form-control">
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label">复试录取总分：</label>
												<div class="col-sm-8">
													<input id="cname" name="retestGoalTotalMark" type="text" class="form-control">
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
						
						<!-- 编辑弹窗 -->
						<div class="modal inmodal" id="editRetestGoalMark" tabindex="-1"
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
										<form class="form-horizontal m-t" action="editRetestGoalMark" name="editForm" method="POST" id="">
											<div class="form-group">
												<label class="col-sm-3 control-label">录取分数：</label>
												<div class="col-sm-8">
													<input id="editSubjectGoalMark" name="editRetestGoalMark" type="text" class="form-control">
													<input type="hidden" id="editTestId" name="editTestId" />
													<input type="hidden" id="editSchoolId" name="editSchoolId" />
													<input type="hidden" id="editClassId" name="editClassId" />
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
		 function deleteTestSchoolRetest(testId, schoolId, classId) { 
		 		var str = "testId="+testId
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
							url : "adminTestSchoolRetestDelete",
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
		
		function editRetestGoalMark(testId, schoolId, classId) {
			$("#editTestId").attr("value", testId);
			$("#editSchoolId").attr("value", schoolId);
			$("#editClassId").attr("value", classId);
		}
	  
		function addFormSubmit(){
			document.addForm.submit();
		}
		
		function editFormSubmit(){
			document.editForm.submit();
		}
	</script>
</body>

</html>
