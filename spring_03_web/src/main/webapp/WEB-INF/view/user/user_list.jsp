<%@ page   contentType="text/html; charset=utf-8"  isELIgnored="false"   %>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/table.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/button.css">

<h2><%= session.getAttribute("User") %></h2>
<a href="${pageContext.request.contextPath}/index.jsp">홈으로</a>

<form action="${pageContext.request.contextPath}/user/search.do" method="post">
	<table>
		<tr>
			<td><h3>회 원 검 색</h3></td>
			<td>
				<select name="searchCondition">
					<option value="username">이름</option>
					<option value="email">이메일</option>
					<option value="userid">아이디</option>
					<option value="phone">전화번호</option>
				</select>	
				<input type="text" name="searchKeyword">
				<input class="btn" type="submit" value="검색">
			</td>
		</tr>
	</table>
</form>

<hr style="color: red">

<title>user_list.jsp(사용자 관리)</title> 
<table border=1>
  <tr>
    <th>userid</th>
    <th>username</th>
    <th>email</th>
    <th>address</th>
	<th>phone</th>
  </tr>
  
<h3>회 원 목 록</h3>

<form name="f" method="get" action="${pageContext.request.contextPath}/user/add.do">

	<!-- 사용자 리스트를 클라이언트에게 보여주기 위하여 출력. -->
 	<c:forEach var="user" items="${users}" >
	  	<tr>
		<td >${user.userid}	</td>
		<td ><a href="${pageContext.request.contextPath}/user/view.do?userid=${user.userid}">${user.username}</a></td>
		<td >${user.email}</td>
		<td >${user.address}</td>
		<td >${user.phone}</td>
		
		</tr>
 	 </c:forEach>
</table>
<table>
	<tr>
		<td><input class="btn" type="submit" value="사용자 추가"/>	</td>
	</tr>
</table>		
</form>