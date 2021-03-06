/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.30
 * Generated at: 2012-10-16 17:38:11 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import java.sql.*;
import org.apache.commons.lang3.StringUtils;

public final class signup_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
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
      response.setContentType("text/html; charset=UTF-8");
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

	String[] countries = {"한국", "미국", "영국", "일본", "중국"};
	String[] idols = {"아이유", "카라", "소녀시대", "2NE1", "씨스타"};
	String[][] genders = {{"M", "남성"}, {"F", "여성"}};
	
	String errorMsg = null;

	String actionUrl;
	// DB 접속을 위한 준비
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	
	String dbUrl = "jdbc:mysql://localhost:3306/web2012";
	String dbUser = "web";
	String dbPassword = "asdf";
	
	// 사용자 정보를 위한 변수 초기화
	String userid = "";
	String name = "";
	String pwd = "";
	String email = "";
	String country = "";
	String gender = "";
	String favorites = "";
	List<String> favoriteList = null;
	
	// Request로 ID가 있는지 확인
	int id = 0;
	try {
		id = Integer.parseInt(request.getParameter("id"));
	} catch (Exception e) {}

	if (id > 0) {
		// Request에 id가 있으면 update모드라 가정
		actionUrl = "update.jsp";
		try {
		    Class.forName("com.mysql.jdbc.Driver");

		    // DB 접속
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

	 		// 질의 준비
			stmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
			stmt.setInt(1, id);
			
			// 수행
	 		rs = stmt.executeQuery();
			
			if (rs.next()) {
				userid = rs.getString("userid");
				name = rs.getString("name");
				pwd = rs.getString("pwd");
				email = rs.getString("email");
				country = rs.getString("country");
				gender = rs.getString("gender");
				favorites = rs.getString("favorites");
				if (favorites != null && favorites.length() > 0) {
					favoriteList = Arrays.asList(StringUtils.split(favorites, ","));
				}
			}
		}catch (SQLException e) {
			errorMsg = "SQL 에러: " + e.getMessage();
		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
	} else {
		actionUrl = "register.jsp";
	}

      out.write("    \r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("\t<meta charset=\"UTF-8\">\r\n");
      out.write("\t<title>회원목록</title>\r\n");
      out.write("\t<link href=\"css/bootstrap.min.css\" rel=\"stylesheet\">\r\n");
      out.write("\t<link href=\"css/base.css\" rel=\"stylesheet\">\r\n");
      out.write("\t<script src=\"js/jquery-1.8.2.min.js\"></script>\r\n");
      out.write("\t<script src=\"js/bootstrap.min.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "share/header.jsp" + (("share/header.jsp").indexOf('?')>0? '&': '?') + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("current", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("Sign Up", request.getCharacterEncoding()), out, false);
      out.write("\r\n");
      out.write("\r\n");
      out.write(" <div class=\"container\">\r\n");
      out.write(" ");

 if (errorMsg != null && errorMsg.length() > 0 ) {
		// SQL 에러의 경우 에러 메시지 출력
		out.print("<div class='alert'>" + errorMsg + "</div>");
 }
 
      out.write("\r\n");
      out.write("\t  <div>\r\n");
      out.write("\t\t  <form class=\"form-horizontal\" action=\"");
      out.print(actionUrl);
      out.write("\" method=\"post\">\r\n");
      out.write("\t\t\t<fieldset>\r\n");
      out.write("        <legend class=\"legend\">Sign Up</legend>\r\n");
      out.write("\t\t\t  \t");

			  	if (id > 0) {
			  		out.println("<input type='hidden' name='id' value='"+id+"'>");
			  	}
			  	
      out.write("\r\n");
      out.write("\t\t\t\t<div class=\"control-group\">\r\n");
      out.write("\t\t\t\t\t<label class=\"control-label\" for=\"userid\">ID</label>\r\n");
      out.write("\t\t\t\t\t<div class=\"controls\">\r\n");
      out.write("\t\t\t\t\t\t<input type=\"text\" name=\"userid\" value=\"");
      out.print(userid);
      out.write("\">\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<div class=\"control-group\">\r\n");
      out.write("\t\t\t\t\t<label class=\"control-label\" for=\"name\">Name</label>\r\n");
      out.write("\t\t\t\t\t<div class=\"controls\">\r\n");
      out.write("\t\t\t\t\t\t<input type=\"text\" placeholder=\"홍길동\" name=\"name\" value=\"");
      out.print(name);
      out.write("\">\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t");
 if (id <= 0) { 
      out.write("\r\n");
      out.write("\t\t\t\t\t");
      out.write("\r\n");
      out.write("\t\t\t\t\t<div class=\"control-group\">\r\n");
      out.write("\t\t\t\t\t\t<label class=\"control-label\" for=\"pwd\">Password</label>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"controls\">\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"password\" name=\"pwd\">\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t\t\t\t\t<div class=\"control-group\">\r\n");
      out.write("\t\t\t\t\t\t<label class=\"control-label\" for=\"pwd_confirm\">Password Confirmation</label>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"controls\">\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"password\" name=\"pwd_confirm\">\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t");
 } 
      out.write("\r\n");
      out.write("\t\t\t\t<div class=\"control-group\">\r\n");
      out.write("\t\t\t\t\t<label class=\"control-label\" for=\"email\">E-mail</label>\r\n");
      out.write("\t\t\t\t\t<div class=\"controls\">\r\n");
      out.write("\t\t\t\t\t\t<input type=\"email\" placeholder=\"hong@abc.com\" name=\"email\" value=\"");
      out.print(email);
      out.write("\">\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<div class=\"control-group\">\r\n");
      out.write("\t\t\t\t\t<label class=\"control-label\">Country</label>\r\n");
      out.write("\t\t\t\t\t<div class=\"controls\">\r\n");
      out.write("\t\t\t\t\t\t<select name=\"country\">\r\n");
      out.write("\t\t\t\t\t\t\t");
 
							for(String countryName: countries) {
								out.print("<option");
								if (countryName.equals(country)) {
									out.print(" selected");
								}
								out.println(">"+countryName+"</option>");	
							}
							
      out.write("\r\n");
      out.write("\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<div class=\"control-group\">\r\n");
      out.write("\t\t\t\t\t<label class=\"control-label\">Gender</label>\r\n");
      out.write("\t\t\t\t\t<div class=\"controls\">\r\n");
      out.write("\t\t\t\t\t\t");
 for(String[] genderOption : genders) { 
      out.write(" \r\n");
      out.write("\t\t\t\t\t\t\t<label class=\"radio\"> \r\n");
      out.write("\t\t\t\t\t\t\t  <input type=\"radio\" value=\"");
      out.print(genderOption[0] );
      out.write("\" name=\"gender\"\r\n");
      out.write("\t\t\t\t\t\t\t  ");
 if (genderOption[0].equals(gender)) { out.print("checked");} 
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t  > \r\n");
      out.write("\t\t\t\t\t\t\t  ");
      out.print(genderOption[1] );
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t</label>\r\n");
      out.write("\t\t\t\t\t\t");
 } 
      out.write(" \r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<div class=\"control-group\">\r\n");
      out.write("\t\t\t\t\t<label class=\"control-label\">Favorites</label>\r\n");
      out.write("\t\t\t\t\t<div class=\"controls\">\r\n");
      out.write("\t\t\t\t\t\t");
 
						for (String idolName: idols) {
							
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t<label class=\"checkbox\"> \r\n");
      out.write("\t\t\t\t\t\t\t  <input type=\"checkbox\" name=\"favorites\" value=\"");
      out.print(idolName);
      out.write("\"\r\n");
      out.write("\t\t\t\t\t\t\t  ");
 
							  	if (favoriteList != null && favoriteList.contains(idolName)) { 
								  	out.print("checked");
								 	} 
								 
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t  >\r\n");
      out.write("\t\t\t\t\t\t\t  ");
      out.print(idolName );
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t</label> \r\n");
      out.write("\t\t\t\t\t\t\t");
				
						}						
						
      out.write("\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<div class=\"form-actions\">\r\n");
      out.write("\t\t\t\t\t<a href=\"index.jsp\" class=\"btn\">목록으로</a>\r\n");
      out.write("\t\t\t\t\t");
 if (id <= 0) { 
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<input type=\"submit\" class=\"btn btn-primary\" value=\"가입\">\r\n");
      out.write("\t\t\t\t\t");
 } else { 
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<input type=\"submit\" class=\"btn btn-primary\" value=\"수정\">\r\n");
      out.write("\t\t\t\t\t");
 } 
      out.write("\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</fieldset>\r\n");
      out.write("\t\t  </form>\r\n");
      out.write("    </div>\r\n");
      out.write("  </div>\r\n");
      out.write("  \r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
