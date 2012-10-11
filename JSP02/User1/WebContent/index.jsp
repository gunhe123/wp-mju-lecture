<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"  import="java.sql.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	// 현재 메뉴
	String currentMenu = "Sign Up";

	// DB 접속을 위한 준비
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	String dbUrl = "jdbc:mysql://localhost:3306/web2012";
	String dbUser = "web";
	String dbPassword = "asdf";
	
	// 페이지 설정
	int pageNo = 1;
	
	try {
		pageNo = Integer.parseInt(request.getParameter("page"));
	} catch (NumberFormatException ex) {}
	
	int numInPage = 10;												// 한페이지에 출력할 아이템 개수
	int startPos = (pageNo - 1) * numInPage; 	// 몇 번째 아이템 부터 이 페이지에?
	int numItems, numPages;
	
	
%>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원목록</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/base.css" rel="stylesheet">
	<script src="js/jquery-1.8.2.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</head>
<body>
<%@include file="share/header.jsp"%>
 	<div class="container">
 	<%
 	try {
 		// DB 접속
		conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
 		
		stmt = conn.createStatement();
		
		// users 테이블: user 수 페이지수 개산
 		rs = stmt.executeQuery("SELECT COUNT(*) FROM users ORDER BY name");
		rs.next();
		numItems = rs.getInt(1);
		numPages = (int) Math.ceil((double)numItems / (double)numInPage);
		rs.close();
		stmt.close();
		
 		// users 테이블 SELECT
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT * FROM users ORDER BY name LIMIT " + startPos + ", " + numInPage);
		String gender;
 	%>
 		<div class="row">
			<div class="span12 page-info">
				<div class="pull-left">
					Total <b><%=numItems %></b> users 
				</div>
				<div class="pull-right">
					<b><%= pageNo%></b> page / total <b><%= numPages %></b> pages
				</div>
 			</div>
 		</div>
		<table class="table table-bordered table-stripped">
			<thead>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Email</th>
					<th>Gender</th>
					<th>Country</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
			<%
				while(rs.next()) {
					gender = rs.getString("gender").equals("M") ? "남성":"여성";
			%>
				<tr>
					<td><a href="show.jsp?id=<%=rs.getInt("id")%>"><%=rs.getString("userid") %></a></td>
					<td><%=rs.getString("name") %></td>
					<td><%=rs.getString("email") %></td>
					<td><%=gender %></td>
					<td><%=rs.getString("country") %></td>
					<td>
						<a href="signup.jsp?id=<%=rs.getInt("id")%>" class="btn btn-mini">modify</a>
						<a href="#" class="btn btn-mini btn-danger" data-action="delete" data-id="<%=rs.getInt("id") %>" >delete</a>
					</td>
				</tr>
				<%
				}
			%>
			</tbody>
		</table> 


		<div class="pagination pagination-centered">
      <ul>
      	<%
      	// 페이지 네비게이션을 위한 준비
      	int startPageNo, endPageNo;
      	int delta = 5;
      	startPageNo = (pageNo <= delta) ? 1: pageNo - delta;
      	endPageNo = startPageNo + (delta * 2) + 1;
      	
      	if (endPageNo > numPages) {
      		endPageNo = numPages;
      	}
      	
      	// 이전 페이지로
      	if (pageNo <= 1) { 
      	%>
        	<li class="disabled"><a href="#">&laquo;</a></li>
        <% 
      	} else {
        %>
        	<li><a href="index.jsp?page=<%= pageNo - 1%>">&laquo;</a></li>
        <%
        } 
      	
      	// 페이지 목록 출력 (현재-delta ~ 현재+delta까지)
        String className = "";
        for(int i = startPageNo; i <= endPageNo; i++) {
        	className = (i == pageNo) ? "active" : "";
        	out.println("<li class='" + className + "'>");
        	out.println("<a href='index.jsp?page=" + i + "'>" + i + "</a>");
        	out.println("</li>");
        }
        
        // 다음 페이지로
      	if (pageNo >= numPages) { 
      	%>
        	<li class="disabled"><a href="#">&raquo;</a></li>
        <% 
      	} else {
        %>
        	<li><a href="index.jsp?page=<%= pageNo + 1%>">&raquo;</a></li>
        <%
        } 
        %>
     </ul>
    </div>
		<%
		} catch (SQLException e) {
			// SQL 에러의 경우 에러 메시지 출력
			out.print("<div class='alert'>" + e.getLocalizedMessage() + "</div>");
		}finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		%>
		<div class="form-action">
			<a href="signup.jsp" class="btn btn-primary">Sign Up</a>
		</div>	 	
  </div>
<%@include file="share/footer.jsp"%>
</body>
<script>
	$("a[data-action='delete']").click(function() {
		if (confirm("정말로 삭제하시겠습니까?")) {
			location = 'delete.jsp?id=' + $(this).attr('data-id');
		}
		return false;
	});
</script>
</html>