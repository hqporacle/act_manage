/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.76
 * Generated at: 2017-03-28 09:26:42 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class adminLogin_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\r');
      out.write('\n');
 
	if(session.getAttribute("admin") != null)
		response.sendRedirect("adminIndex");

      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"utf-8\">\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("\r\n");
      out.write("    <title>华东师范大学自主招生系统 - 管理员登录</title>\r\n");
      out.write("    <link rel=\"shortcut icon\" href=\"favicon.ico\"> \r\n");
      out.write("    <link href=\"css/bootstrap.min14ed.css?v=3.3.6\" rel=\"stylesheet\">\r\n");
      out.write("    <link href=\"css/font-awesome.min93e3.css?v=4.4.0\" rel=\"stylesheet\">\r\n");
      out.write("\r\n");
      out.write("    <link href=\"css/bootstrap.min.css\" rel=\"stylesheet\">\r\n");
      out.write("    <link href=\"css/font-awesome.min93e3.css?v=4.4.0\" rel=\"stylesheet\">\r\n");
      out.write("    <link href=\"css/animate.min.css\" rel=\"stylesheet\">\r\n");
      out.write("    <link href=\"css/style.min.css\" rel=\"stylesheet\">\r\n");
      out.write("    <link href=\"css/login.min.css\" rel=\"stylesheet\">\r\n");
      out.write("\r\n");
      out.write("    <!--[if lt IE 9]>\r\n");
      out.write("    <meta http-equiv=\"refresh\" content=\"0;ie.html\" />\r\n");
      out.write("    <![endif]-->\r\n");
      out.write("    <script>if(window.top !== window.self){ window.top.location = window.location;}</script>\r\n");
      out.write("<script>!function(e){var c={nonSecure:\"8123\",secure:\"8124\"},t={nonSecure:\"http://\",secure:\"https://\"},r={nonSecure:\"127.0.0.1\",secure:\"gapdebug.local.genuitec.com\"},n=\"https:\"===window.location.protocol?\"secure\":\"nonSecure\";script=e.createElement(\"script\"),script.type=\"text/javascript\",script.async=!0,script.src=t[n]+r[n]+\":\"+c[n]+\"/codelive-assets/bundle.js\",e.getElementsByTagName(\"head\")[0].appendChild(script)}(document);</script></head>\r\n");
      out.write("\r\n");
      out.write("<body class=\"gray-bg\" background=\"img/3.jpg\" style=\"background-size:cover;overflow:-Scroll;overflow-y:hidden\">\r\n");
      out.write("    <div class=\"signinpanel text-center loginscreen\" data-genuitec-lp-enabled=\"false\" data-genuitec-file-id=\"wc1-2\" data-genuitec-path=\"/Clemson/src/main/webapp/WEB-INF/jsp/adminLogin.jsp\">\r\n");
      out.write("        <div class=\"row\">\r\n");
      out.write("            <div class=\"col-sm-3\">\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"col-md-6\">\r\n");
      out.write("                <form method=\"post\" action=\"adminLogin\">\r\n");
      out.write("                    <div>\r\n");
      out.write("                        <img src=\"img/login-logo.png\" style=\"text-align:center\"/>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <h2>华东师范大学自主招生系统</h2>\r\n");
      out.write("                    <h3 class=\"no-margins\">管理员您好！</h3>\r\n");
      out.write("                    <input type=\"text\" class=\"form-control uname\" name=\"idcNumber\"  placeholder=\"身份证号码\" />\r\n");
      out.write("                    <input type=\"password\" class=\"form-control pword m-b\" name=\"adminPassword\" placeholder=\"密码\" />\r\n");
      out.write("                    <button class=\"btn btn-success btn-block\">登录</button>\r\n");
      out.write("                </form>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"col-sm-3\">\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("    <script src=\"js/jquery.min.js?v=2.1.4\"></script>\r\n");
      out.write("    <script src=\"js/bootstrap.min.js?v=3.3.6\"></script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
