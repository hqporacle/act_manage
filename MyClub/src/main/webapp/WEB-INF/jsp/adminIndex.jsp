<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" import="java.util.*,com.clemson.model.*"
	pageEncoding="UTF-8"%>

<%
    if(session.getAttribute("user") == null)
        response.sendRedirect(request.getContextPath() + "/login");
%>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>My Club</title>
    <link rel="shortcut icon" href="favicon.ico"> 
    <link rel="stylesheet" href="css/libs/oj/v3.0.0/alta/oj-alta-min.css" type="text/css"/>
    
    <script data-main="js/userActivities" src="js/libs/require/require.js"></script>
    <style>

	</style>
</head>
<script>
var userRole = <%=((User)session.getAttribute("user")).getRole()%>;
var userId = <%=((User)session.getAttribute("user")).getId()%>;
var userEmail = "<%=((User)session.getAttribute("user")).getIdentity()%>";
var userPhone = "<%=((User)session.getAttribute("user")).getPhone()%>";
</script>
<div id="page">
  <div data-bind="ojModule: moduleConfig">
  </div>
<div class="oj-applayout-fixed-bottom">
    <!-- Nav Bar -->
    <div id="navbar" role="navigation" class="oj-hybrid-applayout-navbar-app">
      <div class="oj-navigationlist-stack-icon-label"
        data-bind="ojComponent:{component: 'ojNavigationList', navigationLevel: 'application',
        selection: router.stateId, optionChange: navBarChange, edge: 'top', item: {template: 'navTemplate'}, data: navDataSource}">
      </div>
      <!-- template for rendering navigation bar items -->
      <script type="text/html" id="navTemplate">
        <li><a href="#">
          <span data-bind="css: $data['iconClass']"></span>
          <!-- ko text: $data['name'] --> <!--/ko-->
        </a></li>
      </script>
    </div>
  </div>
</div>

</html>