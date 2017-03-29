<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	if (session.getAttribute("student") != null)
		response.sendRedirect("studentIndex");
%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>华东师范大学自主招生系统 - 注册页面</title>

<link rel="shortcut icon" href="favicon.ico">
<link href="css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
<link href="css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
<link href="css/animate.min.css" rel="stylesheet">
<link href="css/style.min862f.css?v=4.1.0" rel="stylesheet">
<link href="css/plugins/iCheck/custom.css" rel="stylesheet">
<link href="css/plugins/steps/jquery.steps.css" rel="stylesheet">
<link href="css/plugins/chosen/chosen.css" rel="stylesheet">
<link
	href="css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css"
	rel="stylesheet">
<script>!function(e){var c={nonSecure:"8123",secure:"8124"},t={nonSecure:"http://",secure:"https://"},r={nonSecure:"127.0.0.1",secure:"gapdebug.local.genuitec.com"},n="https:"===window.location.protocol?"secure":"nonSecure";script=e.createElement("script"),script.type="text/javascript",script.async=!0,script.src=t[n]+r[n]+":"+c[n]+"/codelive-assets/bundle.js",e.getElementsByTagName("head")[0].appendChild(script)}(document);</script></head>

<body class="gray-bg" background="img/2.jpg"
	style="background-size:cover;overflow:-Scroll;overflow-y:hidden">
	<div class="wrapper wrapper-content animated fadeInRight" data-genuitec-lp-enabled="false" data-genuitec-file-id="wc1-76" data-genuitec-path="/Clemson/src/main/webapp/WEB-INF/jsp/signup.jsp">
		<div class="row">
			<div class="col-sm-6">
				<div class="ibox">
					<div class="ibox-content"
						style="background-color:rgba(220,220,220,.8)">
						<h2>华东师范大学自主招生系统 - 注册页面</h2>
						<p>East China Normal University</p>
						<form id="form" method="post" action="signup"
							accept-charset="utf-8" class="wizard-big">
							<h1>账户</h1>
							<fieldset>
								<h2>账户信息</h2>
								<div class="row">
									<div class="col-sm-8">
										<div class="form-group">
											<label>身份证号码 *</label> <input id="idc_number"
												name="idcNumber" type="text" class="form-control required">
										</div>
										<div class="form-group">
											<label>密码 *</label> <input id="student_password"
												name="studentPassword" type="password"
												class="form-control required">
										</div>
										<div class="form-group">
											<label>确认密码 *</label> <input id="confirm" name="confirm"
												type="password" class="form-control required">
										</div>
									</div>
									<div class="col-sm-4">
										<div class="text-center">
											<div>
												<img src="img/login-logo.png" style="text-align:center" />
											</div>
										</div>
									</div>
								</div>
							</fieldset>
							<h1>个人资料</h1>
							<fieldset>
								<h2>个人资料信息</h2>
								<div class="row">
									<div class="col-sm-6">
										<div class="form-group">
											<label>姓名 *</label> <input id="student_name"
												name="studentName" type="text" class="form-control required">
										</div>
										<div class="row">
											<div class="col-sm-6">
												<div class="form-group">
													<label>性别 *</label> <br />
													<div class="radio radio-info radio-inline"
														style="margin-top: 7px">
														<input type="radio" id="student_sex_1" value="男"
															name="studentSex" checked> <label
															for="student_sex_1">男</label>
													</div>
													<div class="radio radio-inline" style="margin-top: 7px">
														<input type="radio" id="student_sex2" value="女"
															name="studentSex"> <label for="student_sex2">女</label>
													</div>
												</div>
											</div>
											<div class="col-sm-6">
												<div class="form-group">
													<label>民族 *</label> <input id="student_nation"
														name="studentNation" type="text"
														class="form-control required">
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-sm-12">
												<div class="form-group">
													<label>生日 *</label>
													<div>
														<input readonly class="form-control layer-date required"
															name="studentBirthday" id="birthday"> <label
															class="laydate-icon inline demoicon"
															onclick="laydate({elem: '#birthday'});"></label>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="col-sm-6">
										<div class="form-group">
											<label>Email *</label> <input id="student_email"
												name="studentEmail" type="text"
												class="form-control required email">
										</div>
										<div class="form-group">
											<label>家庭住址 *</label> <input id="student_address"
												name="studentAddress" type="text"
												class="form-control required">
										</div>
										<div class="form-group">
											<label>就读高中 *</label> <input id="student_school"
												name="studentSchool" type="text"
												class="form-control required">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-6">
										<div class="form-group">
											<label>高考报名号 *</label> <input id="cee_number"
												name="ceeNumber" type="text" class="form-control required">
										</div>
									</div>
									<div class="col-sm-6">
										<div class="form-group" style="margin-top：60px;">
											<label>艺术号 （选填）</label> <input id="art_number"
												name="artNumber" type="text" class="form-control">
										</div>
									</div>
								</div>
							</fieldset>

							<h1>注意</h1>
							<fieldset>
								<div class="text-left" style="margin-top：60px;">
									<h2>尊敬的考生您好：</h2>
									<br />
									<h1>请注意，您的基本信息一经注册将不得更改，请确认您的信息无误后，请确认您的信息无误后，请确认您的信息无误后（重要的事情说三遍），再完成注册。</h1>
								</div>
							</fieldset>

							<h1>完成</h1>
							<fieldset>
								<label style="font-size:36px">如果您的信息确认无误，请点击“完成”按钮；<br />反之，返回“上一步”进行修改，或者退出注册。</label>
							</fieldset>
						</form>
					</div>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="jumbotron">
					<h1>欢迎注册</h1>
					<h3>高考报名号说明：${ceeNumberDes.v}</h3>
					<h3>艺术号说明：${artNumberDes.v}</h3>
				</div>
			</div>
		</div>
	</div>

	<script src="js/content.min.js?v=1.0.0"></script>
	<script src="js/plugins/layer/laydate/laydate.js"></script>
	<script>
		laydate({
			elem : "#hello",
			event : "focus"
		});
		var start = {
			elem : "#start",
			format : "YYYY/MM/DD hh:mm:ss",
			min : laydate.now(),
			max : "2099-06-16 23:59:59",
			istime : true,
			istoday : false,
			choose : function(datas) {
				end.min = datas;
				end.start = datas
			}
		};
		var end = {
			elem : "#end",
			format : "YYYY/MM/DD hh:mm:ss",
			min : laydate.now(),
			max : "2099-06-16 23:59:59",
			istime : true,
			istoday : false,
			choose : function(datas) {
				start.max = datas
			}
		};
		laydate(start);
		laydate(end);
	</script>

	<script src="js/jquery.min.js?v=2.1.4"></script>
	<script src="js/bootstrap.min.js?v=3.3.6"></script>
	<script src="js/plugins/staps/jquery.steps.min.js"></script>
	<script src="js/plugins/validate/jquery.validate.min.js"></script>
	<script src="js/plugins/validate/messages_zh.min.js"></script>
	<script src="js/plugins/chosen/chosen.jquery.js"></script>
	<script>
		$(document)
				.ready(
						function() {
							$("#wizard").steps();
							$("#form")
									.steps(
											{
												bodyTag : "fieldset",
												onStepChanging : function(
														event, currentIndex,
														newIndex) {
													if (currentIndex > newIndex) {
														return true
													}
													if (newIndex === 3
															&& Number($("#age")
																	.val()) < 18) {
														return false
													}
													var form = $(this);
													if (currentIndex < newIndex) {
														$(
																".body:eq("
																		+ newIndex
																		+ ") label.error",
																form).remove();
														$(
																".body:eq("
																		+ newIndex
																		+ ") .error",
																form)
																.removeClass(
																		"error")
													}
													form.validate().settings.ignore = ":disabled,:hidden";
													return form.valid()
												},
												onStepChanged : function(event,
														currentIndex,
														priorIndex) {
													if (currentIndex === 2
															&& Number($("#age")
																	.val()) >= 18) {
														$(this).steps("next")
													}
													if (currentIndex === 2
															&& priorIndex === 3) {
														$(this).steps(
																"previous")
													}
												},
												onFinishing : function(event,
														currentIndex) {
													var form = $(this);
													form.validate().settings.ignore = ":disabled";
													return form.valid()
												},
												onFinished : function(event,
														currentIndex) {
													var form = $(this);
													form.submit()
												}
											})
									.validate(
											{
												errorPlacement : function(
														error, element) {
													element.before(error)
												},
												rules : {
													confirm : {
														equalTo : "#student_password"
													}
												}
											})
						});
	</script>
</body>
</html>
