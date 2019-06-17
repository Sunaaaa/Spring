<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib  prefix="form" uri="http://www.springframework.org/tags/form" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/table.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/button.css">

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Board Write</title>
<script language="JavaScript">
function boardCreate() {	
	f.action="./add.do";
	f.method="post";
	f.submit();
}

function boardList() {
	f.action = "list.do";
	f.submit();
}
</script>
</head>
<body>
<%= session.getAttribute("User")%>
<form name="f" method="post" method="">
	<h3>새 게시글 작성</h3>
	 	   <input type="hidden" name="userid" value="${user.userid }"/>
	 	   <input type="hidden" name="writer" value="${user.username }"/> 
	<table>
		<tr>
			<td>제목</td>
			<td><input type="text" name="title"></td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${user.username }</td>
		</tr>
		<tr>
			<td>아이디</td>
			<td>${user.userid }</td>
		</tr>
		<tr>
			<td>내용</td>
			<td><input type="text" name="content"></td>
		</tr>
		<tr>
			<td>등록일</td>
			<td>2019-06-17</td>
		</tr>
		<tr>
			<td>조회수</td>
			<td>0</td>
		</tr>
		  <tr>
			<td align=center>
			<input class="btn" type="button" value="새 글 작성" onClick="boardCreate()"> &nbsp;
			<input class="btn" type="button" value="목록보기" onClick="boardList()">
			</td>
		  </tr>
	</table>
</form>

</body>
</html>