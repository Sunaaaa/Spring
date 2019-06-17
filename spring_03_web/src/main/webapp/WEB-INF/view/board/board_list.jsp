<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/table.css">

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/board/search.do" method="post">
		<table>
			<tr>
				<td>
					<h3>게시글 검색</h3>
				</td>
				<td>
				<select name="searchCondition">
						<option value="title">제목</option>
						<option value="writer">작성자</option>
						<option value="content">내용</option>
				</select> 
				<input type="text" name="searchKeyword" > 
				<input class="btn" type="submit" value="검색">
				</td>
			</tr>
		</table>
	</form>
	<hr style="color: pink">
	<table>
		<tr>
			<th>No</th>
			<th>작성자</th>
			<th>제목</th>
			<th>내용</th>
			<th>등록일</th>
			<th>조회수</th>
		</tr>

		<c:forEach var="board" items="${boards}">
			<tr>
				<td>${board.seq }</td>
				<td>${board.writer }</td>
				<td><a href="${pageContext.request.contextPath}/board/view.do?seq=${board.seq }">${board.title }</a></td>
				<td><a href="${pageContext.request.contextPath}/board/view.do?seq=${board.seq }">${board.content }</a></td>
				<td>${board.regdate }</td>
				<td>${board.cnt }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>