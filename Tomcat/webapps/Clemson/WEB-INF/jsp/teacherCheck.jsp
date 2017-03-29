<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<% 
	if(session.getAttribute("teacher") == null)
		response.sendRedirect(request.getContextPath() + "/teacherLogin");
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

    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/style.min862f.css?v=4.1.0" rel="stylesheet">
<script>!function(e){var c={nonSecure:"8123",secure:"8124"},t={nonSecure:"http://",secure:"https://"},r={nonSecure:"127.0.0.1",secure:"gapdebug.local.genuitec.com"},n="https:"===window.location.protocol?"secure":"nonSecure";script=e.createElement("script"),script.type="text/javascript",script.async=!0,script.src=t[n]+r[n]+":"+c[n]+"/codelive-assets/bundle.js",e.getElementsByTagName("head")[0].appendChild(script)}(document);</script></head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight" data-genuitec-lp-enabled="false" data-genuitec-file-id="wc1-78" data-genuitec-path="/Clemson/src/main/webapp/WEB-INF/jsp/teacherCheck.jsp">

            <div class="row">
                <div class="col-sm-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h1>考生信息审核</h1>
                        </div>
                        <div class="ibox-content">
                        	<h3>考生检索</h3>
							<input type="text" class="form-control input-sm m-b-xs" id="filter" placeholder="搜索表格(条件可以为考生姓名、报名号、身份证号等)..."/>
							<br/>
                            <table class="footable table table-stripped toggle-arrow-tiny" data-page-size="8" data-filter="#filter">
                                <thead>
                                <tr>

                                    <th data-toggle="true" style="width:20%;">报名号</th>
                                    <th style="width:20%;">考生姓名</th>
                                    <th style="width:40%;">报名考试</th>
                                    <th style="width:20%;">审核状态</th>
                                    
                                    <th data-hide="all">考生身份证号</th>
                                    <th data-hide="all">性别</th>
                                    <th data-hide="all">民族</th>
                                    <th data-hide="all">出生日期</th>
                                    <th data-hide="all">家庭住址</th>
                                    <th data-hide="all">就读高中</th>
                                    <th data-hide="all">电子邮箱</th>
                                    <th data-hide="all">专业奖项</th>
                                    <th data-hide="all">高考报名号</th>
                                    <th data-hide="all">艺术号</th>
                                    <th data-hide="all">考生类型</th>
                                    
                                </tr>
                                </thead>
                                <tbody>
                                
                                <c:forEach items="${enrollList}" var="enroll">  
                                    <tr>
                                        <td><c:out value="${enroll.enrollNum}" /></td>
                                        <td><c:out value="${enroll.studentName}" /></td>
                                        <td><c:out value="${enroll.testName}" /></td> 
                                        
                                        <td>
                                        <c:choose>
											<c:when test="${enroll.enrollStatus == 0}">  
												<table>
													<tr>
														<form method="POST" action="<%=request.getContextPath()%>/enroll/${enroll.enrollNum}/status/1" accept-charset="utf-8" id="pass${enroll.enrollNum}">
															<button class="btn btn-primary dim" type="button" onclick="formSubmitNow('pass${enroll.enrollNum}')">
																<i class="glyphicon glyphicon-ok"></i>
															</button>
														</form>
														<form method="POST" action="<%=request.getContextPath()%>/enroll/${enroll.enrollNum}/status/-1" accept-charset="utf-8" id="deny${enroll.enrollNum}">
															<button class="btn btn-success dim" type="button" onclick="formSubmitNow('deny${enroll.enrollNum}')">
																<i class="glyphicon glyphicon-remove"></i>
															</button>
														</form>
													</tr>
                                            	</table>
											</c:when>
											<c:when test="${enroll.enrollStatus == 1}">  
												已通过
											</c:when>
											<c:when test="${enroll.enrollStatus == -1}">  
												已拒绝
											</c:when>
											<c:when test="${enroll.enrollStatus == 2}">  
												考生已选择考点
											</c:when>
										</c:choose>
                                            
                                        </td>
                                        
                                        <td><c:out value="${enroll.idcNumber}" /></td>
                                        <td><c:out value="${enroll.studentSex}" /></td>
                                        <td><c:out value="${enroll.studentNation}" /></td>
                                        <td><c:out value="${enroll.studentBirthday}" /></td>
                                        <td><c:out value="${enroll.studentAddress}" /></td>
                                        <td><c:out value="${enroll.studentSchool}" /></td>
                                        <td><c:out value="${enroll.studentEmail}" /></td>
                                        <td><c:out value="${enroll.awards}" /></td>
                                        <td><c:out value="${enroll.ceeNumber}" /></td>
                                        <td><c:out value="${enroll.artNumber}" /></td>
                                        <td><c:out value="${enroll.studentTypeName}" /></td>
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
        
        function formSubmitNow(formId) {
        	document.getElementById(formId).submit();
        }
    </script>
</body>
</html>
