
<%@page contentType="text/html; charset=utf-8" isELIgnored="false"%>
<html>
<head>
<title>게시글 수정</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script language="JavaScript">
	function boardModify() {
		f.action = "update.do";
		f.submit();
	}

	function boardList() {
		f.action = "list.do";
		f.submit();
	}
</script>
</head>
<body>
	<h3>게시글 수정</h3>
	<form name="f" method="post" action="">
		<input name="seq" value="${board.seq}" type="hidden">

		<table>
			<tr>
				<td>제목</td>
				<td><input type="text" name="title" value="${board.title }"></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${board.writer }</td>
			</tr>
			<tr>
				<td>내용</td>
				<td><input type="text" name="content" value="${board.content }"></td>
			</tr>
			<tr>
				<td>등록일</td>
				<td>${board.regdate }</td>
			</tr>
			<tr>
				<td>조회수</td>
				<td>${board.cnt }</td>
			</tr>
			<tr>
				<td align="center">
				<input type="button" value="수정" onClick="boardModify()">
					&nbsp; 
					<input type="button" value="목록" onClick="boardList()">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>