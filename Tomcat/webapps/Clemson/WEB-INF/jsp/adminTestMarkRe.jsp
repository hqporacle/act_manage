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
    <div class="wrapper wrapper-content animated fadeInRight" data-genuitec-lp-enabled="false" data-genuitec-file-id="wc1-8" data-genuitec-path="/Clemson/src/main/webapp/WEB-INF/jsp/adminTestMarkRe.jsp">

            <div class="row">
                <div class="col-sm-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h1>考生复试成绩</h1>
                        </div>
                        <div class="ibox-content">
                        	<h3>考生复试成绩检索</h3>
							<input type="text" class="form-control input-sm m-b-xs" id="filter" placeholder="搜索表格(条件可以为考生姓名、报名号)..."/>
							<br/>
                            <table class="footable table table-stripped toggle-arrow-tiny" data-page-size="8" data-filter="#filter">
                                <thead>
                                <tr>

                                    <th data-toggle="true">报名号</th>
                                    <th>考生姓名</th>
                                    <th>报名考试</th>
                                    <th>复试英语成绩</th>
                                    <th>复试专业成绩</th>
                                    <th>复试人文成绩</th>
                                    <th>操作</th>         
                                </tr>
                                </thead>
                                <tbody>
                                
                                <c:forEach items="${markReList}" var="markRe">  
                                    <tr>
                                        <td><c:out value="${markRe.enrollNum}" /></td>
                                        <td><c:out value="${markRe.studentName}" /></td>
                                        <td><c:out value="${markRe.testName}" /></td>
                                        <td><c:out value="${markRe.retestEnMark}" /></td> 
                                        <td><c:out value="${markRe.retestProMark}" /></td> 
                                        <td><c:out value="${markRe.retestPeoMark}" /></td>   
                                        <td>
                               				<button type="button" onclick=""  class="btn btn-outline btn-default"
											data-toggle="modal" data-target="">
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
