<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<script>!function(e){var c={nonSecure:"8123",secure:"8124"},t={nonSecure:"http://",secure:"https://"},r={nonSecure:"127.0.0.1",secure:"gapdebug.local.genuitec.com"},n="https:"===window.location.protocol?"secure":"nonSecure";script=e.createElement("script"),script.type="text/javascript",script.async=!0,script.src=t[n]+r[n]+":"+c[n]+"/codelive-assets/bundle.js",e.getElementsByTagName("head")[0].appendChild(script)}(document);</script></head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight" data-genuitec-lp-enabled="false" data-genuitec-file-id="wc1-7" data-genuitec-path="/Clemson/src/main/webapp/WEB-INF/jsp/adminTestMark.jsp">

            <div class="row">
                <div class="col-sm-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h1>考生成绩管理</h1>
                        </div>
                        <div class="ibox-content">
                        	<h3>考生成绩检索</h3>
							<input type="text" class="form-control input-sm m-b-xs" id="filter" placeholder="搜索表格(条件可以为考生姓名、报名号)..."/>
							<br/>
                            <table class="footable table table-stripped toggle-arrow-tiny" data-page-size="8" data-filter="#filter">
                                <thead>
                                <tr>

                                    <th data-toggle="true">报名号</th>
                                    <th>考生姓名</th>
                                    <th>报名考试</th>
                                    <th>科目</th>
                                    <th>成绩类型</th>
                                    <th>成绩</th>
                                    <th>操作</th> 
                                </tr>
                                </thead>
                                <tbody>
                                
                                <c:forEach items="${markList}" var="markList">  
                                    <tr>
                                        <td><c:out value="${markList.enrollNum}" /></td>
                                        <td><c:out value="${markList.studentName}" /></td>
                                        <td><c:out value="${markList.testName}" /></td>
                                        <td><c:out value="${markList.subjectName}" /></td>
                                        <td><c:out value="${markList.markDescription}" /></td>
                                        <td><c:out value="${markList.mark}" /></td>
                                        <td>
                                          <button type="button" onclick="editMark('${markList.enrollNum}','${markList.subjectId}','${markList.markDescription}','${markList.mark}')"  class="btn btn-outline btn-default"
											data-toggle="modal" data-target="#editMark">
											<i class="glyphicon glyphicon-wrench" aria-hidden="true"></i>
										  </button>
                                        </td>	
                                    </tr>
								</c:forEach>
								
                                </tbody>
                                <tfoot>
                                <tr>
                                    <td colspan="5">
                                        <ul class="pagination pull-right"></ul>
                                    </td>
                                </tr>
                                </tfoot>
                            </table>
                            
                      		<!-- 查询编辑弹窗 -->
                            <div class="modal inmodal" id="editMark" tabindex="-1"
							role="dialog" aria-hidden="true" style="display:none;">
							<div class="modal-dialog">
								<div class="modal-content animated flipInY">
									<div class="modal-header" style="padding:10px;">
										<button type="button" class="close" data-dismiss="modal">
											<span aria-hidden="true">&times;</span><span class="sr-only"></span>
										</button>
										<h2 style="text-align:center;">编辑成绩</h2>
									</div>
									<div class="modal-body">
										<form class="form-horizontal m-t" action="editStudentMark" name="editForm" method="POST" id="">
											<div class="form-group">
												<label class="col-sm-3 control-label">分数：</label>
												<div class="col-sm-8">
													<input id="editStudentMark" name="editStudentMark" type="text" class="form-control"/>
													<input type="hidden" id="editEnrollNum" name="editEnrollNum" />
													<input type="hidden" id="editSubjectId" name="editSubjectId" />
													<input type="hidden" id="editMarkDescription" name="editMarkDescription" />
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
        $(document).ready(function(){$(".footable").footable();$(".footable2").footable()});
    </script>
    <script>
    	function editMark(enrollNum, subjectId, markDescription,mark){   
			$("#editStudentMark").attr("value", mark);
			$("#editEnrollNum").attr("value", enrollNum);
			$("#editSubjectId").attr("value", subjectId);
			$("#editMarkDescription").attr("value", markDescription);
		}
		
		function editFormSubmit(){
			document.editForm.submit();
		}	
    </script>
</body>
</html>
