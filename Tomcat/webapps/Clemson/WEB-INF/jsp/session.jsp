<% 
	if(session.getAttribute("student") == null)
		response.sendRedirect(request.getContextPath() + "/login");
%>