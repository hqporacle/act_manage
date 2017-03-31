<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<% 
	if(session.getAttribute("student") != null)
		response.sendRedirect("studentIndex");
%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>My Club</title>
    <link rel="shortcut icon" href="favicon.ico"> 
    <link rel="stylesheet" href="css/libs/oj/v3.0.0/alta/oj-alta-min.css" type="text/css"/>
    
    <script data-main="js/login" src="js/libs/require/require.js"></script>
</head>

<body>
<header class="dfml-appheader" style="padding:10px;">
            <div class="oj-flex oj-flex-items-pad dfml-appheader-classic">
                <div class="oj-flex-item">
                    <div class="dfml-appheader-logo" >
                        <div class="dfml-appheader-logo-block">
                            <span role="img" class="oj-icon dfml-appheader-logo-oracle" data-bind="attr:{title:labels.logoTitle}"></span>  
                        </div>
                        <div class="dfml-appheader-appname-block">
                            <span class="dfml-appheader-appname" data-bind="text: labels.appName"></span>
                        </div>
                    </div>
                </div>
            </div>
        </header>
   <div class="oj-flex">
            <div id="dfml-main-content-area" class="oj-flex-item"> 
                <div role="main" class="demo-page-content-area">
                    <div class="oj-flex oj-flex-items-pad oj-sm-justify-content-center" style="padding-top:15%;" >  
                        <div class="oj-flex-item oj-sm-8 oj-md-4 oj-lg-3">    
                        <div id="form-container1" class="oj-form-layout">
                            <form class="oj-form" data-bind="submit:doLogin">
                                <div class="oj-flex">  
                                    <div class="oj-flex-item">
                                        <label for="username" data-bind="text:labels.userName"><span data-bi></span></label>
                                        <input id="username" type="text" 
                                            data-bind="value: username, ojComponent: {component: 'ojInputText', value: username, required:true, invalidComponentTracker: tracker, rootAttributes: { style: 'max-width:25em'}}"/>
                                    </div>
                                </div>
                                <div class="oj-flex">  
                                    <div class="oj-flex-item">
                                        <label for="password" data-bind="text:labels.password"></label>
                                        <input id="password" type="text" 
                                           data-bind="value: password, ojComponent: {component: 'ojInputPassword', value: password,  required:true, invalidComponentTracker: tracker,  rootAttributes: { style: 'max-width:25em'}}"/>
                                    </div>
                                </div>
                                <div class="oj-flex">  
                                    <div class="oj-flex-item">
                                            <button id="loginButton" type="submit" 
                                                    data-bind="ojComponent: { component: 'ojButton', label: labels.actionLable,  rootAttributes: { style: 'max-width:25em'}}">
                                                 </button>
                                    </div>
                                </div>
                                <div class="oj-flex">  
                                    <div class="oj-flex-item loginerror" data-bind="text:errorMessage">
                                    </div>
                                </div>
                            </form>
                        </div>
                        </div>    
                    </div>
                </div>
            </div>
        </div>
</body>
</html>
