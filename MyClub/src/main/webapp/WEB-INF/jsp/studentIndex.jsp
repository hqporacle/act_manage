<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" import="java.util.*,com.clemson.model.*" pageEncoding="UTF-8"%> 
<%@include file="session.jsp" %>   
<html>
<head>
	<title>华东师范大学自主招生系统</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
    <!-- bootstrap -->
    <link href="css/bootstrap/bootstrap.css" rel="stylesheet" />
    <link href="css/bootstrap/bootstrap-responsive.css" rel="stylesheet" />
    <link href="css/bootstrap/bootstrap-overrides.css" type="text/css" rel="stylesheet" /> 
    <link href="css/lib/font-awesome.css" type="text/css" rel="stylesheet" /> 
    <!-- global styles -->
    <link rel="stylesheet" type="text/css" href="css/layout.css" />
    <link rel="stylesheet" type="text/css" href="css/elements.css" />
    <link rel="stylesheet" type="text/css" href="css/icons.css" />

    <!-- this page specific styles -->
    <link rel="stylesheet" href="css/compiled/index.css" type="text/css" media="screen" />     
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
	
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script type="text/javascript" src="js/jquery.min.js"></script>
</head>
<body> 
    <!-- navbar -->
	<%@include file="navbar.jsp" %> 
    <!-- end navbar --> 
    
    <!-- sidebar -->
    <%@include file="sidebar.jsp" %> 
    <!-- end sidebar -->  
	
	<!-- main container -->
    <div class="content">
       <div id="pad-wrapper" class="users-list">
			<div class="row-fluid header">
            	<h3><strong>已报名考试</strong></h3>
        	</div>
          	<div class="row-fluid span12 table">
                    <table class=" table table-hover">
                        <thead>
                            <tr>
                                <th class="span2 sortable" style="width:40%">考试名称</th>
                                <!-- 
                                <th class="span2 sortable">
                                    <span class="line"></span>考试时间
                                </th>
                                 -->
                                <th class="span2 sortable" style="width:20%">
                                    <span class="line"></span>状态
                                </th>
                                <th class="span2 sortable" style="width:20%">
                                    <span class="line"></span>详细信息
                                </th>
                                <th class="span2 sortable" style="width:20%">
                                    <span class="line"></span>考试成绩</th>
                            </tr>
                        </thead>
                        
                        <tbody>
                        
                        <c:choose>
                        	<c:when test="${empty enrollList}">
                        		 <tr class="first">
                        		 	<td>
                        		 		您暂未报名任何考试！
                        		 	</td>
                        		 	<td>
                        		 	</td>
                        		 	<td>
                        		 	</td>
                        		 </tr>
                        	</c:when>
                        </c:choose>
                        
                        <!-- row -->
                        <c:forEach items="${enrollList}" var="enroll">  
                            <tr class="first">
                                <td>
                                    <strong><c:out value="${enroll.testName}" /></strong>
                                </td>
                                <td>
                                <c:out value="${enroll.meaning}" />

    									<c:choose>
    									<c:when test="${enroll.enrollStatus == 1}">
    										<!-- 链接生成 -->
    										<a data-toggle="modal" data-target="#myModal<c:out value="${enroll.enrollNum}" />">选择考点</a>
    										
    										<!-- 弹出层生成 -->
    										<div class="modal inmodal" id="myModal<c:out value="${enroll.enrollNum}" />" tabindex="-1" role="dialog" aria-hidden="true" style="display:none;">
                                            <div class="modal-dialog">
                                                <div class="modal-content animated flipInY">
                                                    <div class="modal-header">
                                                    	<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only"></span></button>
                                                    
                                                        <h2  style="margin:20px;text-align:center;line-height:1.5">为  <c:out value="${enroll.testName}" />  选择考点</h2>
                                                    </div>
                                                    <form method="POST" action="<%=request.getContextPath()%>/enroll/${enroll.enrollNum}/school" accept-charset="utf-8" id="selectSchoolForEnroll${enroll.enrollNum}">
                                                    <div class="modal-body">
                                                        <p>请根据您的实际情况选择合适的考点，一旦确定选择，将不能修改。如需修改，请联系华东师范大学自主招生招生办，电话：<strong>110</strong> </p>
                                                        <table style="width:100%;">
                                                            <tr>
                                                                <td style="width:30%;">
                                                                     <p>可供选择的考点：</p>
                                                                </td>
                                                                <td style="width:70%;">
                                                                    <div class="ui-select" style="width:100%;height:30px;">
                                                                    
                                                                        <select name="schoolId" style="width: 100%;font-size:16px;">
                                                                        <c:forEach items="${enroll.schoolIdAndName}" var="schoolIdAndNameList">  
    															            <option value="${schoolIdAndNameList.schoolId}" />${schoolIdAndNameList.schoolName}
    															        </c:forEach>  
                                                                        </select>
                                                                    
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                        </table> 
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-primary" onClick="formSubmitNow('selectSchoolForEnroll${enroll.enrollNum}');">确定</button>
                                                    </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
    									</c:when>
    									<c:when test="${enroll.enrollStatus == 2}">
    										<a href="pdf/${enroll.testNum}.pdf" />下载准考证</a>
    									</c:when>
    									</c:choose>

                                </td>
                                <td>
                                    <a href="enroll/${enroll.enrollNum}">详细信息</a>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${enroll.isMarkExisted == 0}">
                                            <p>
                                                考试成绩尚未发布
                                            </p>
                                        </c:when>
                                        <c:when test="${enroll.isMarkExisted == 1}">
                                            <p>
                                                成绩已经发布，请点击<a href="<%=request.getContextPath()%>/mark/<c:out value="${enroll.enrollNum}" />">查询</a>
                                            </p>
                                        </c:when>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                        
                        </tbody>
                    </table>
			</div>
		</div>
    </div>
 
    <script src="js/bootstrap.min.js"></script> 
    <script src="js/jquery-latest.js"></script>
    <script>
    	$(function(){
    		$("[href='<%=request.getContextPath()%>/studentIndex']").parent().addClass("active");
    		$("<div class='pointer'><div class='arrow'></div><div class='arrow_border'></div></div>").appendTo($("[href='<%=request.getContextPath()%>/studentIndex']").parent());
    	
    	});
    	
    	function formSubmitNow(formId) {
        	document.getElementById(formId).submit();
        }
    </script>
 
</body>
</html>