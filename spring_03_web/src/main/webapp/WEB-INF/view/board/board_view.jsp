<%@page contentType="text/html; charset=utf-8" isELIgnored="false"%>
 
 
<html>
<head>
<title>게시글 관리</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script >
	function boardList() {
		f.action="list.do"
		f.submit();
	}
	function boardModify() {
		f.action="modify.do";
		f.submit();
	}
	function boardRemove() {
		if ( confirm("정말 삭제하시겠습니까?") ) {
			f.action="remove.do";
			/* ?userid=${user.userid} */
					f.submit();
		}
	}
</script>
</head>
<body >
<table width=780 border=0 cellpadding=0 cellspacing=0>
	<tr>
	  <td width="20"></td>
	  <td>
  <!--contents-->
	  <table width=590 border=0 cellpadding=0 cellspacing=0>
		  <tr>
			<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>게시글 관리 - 게시글 정보보기</b></td>
		  </tr>
	  </table>  
	  <br>
	  
	  <!-- view Form  -->
	  <form name="f" method="post" action="">
	  <input name="seq" value="${board.seq}" type="hidden">
	  <table border="0" cellpadding="0" cellspacing="1" width="610" bgcolor="BBBBBB">
		<tr>
			<td>제목</td>
			<td>${board.title }</td>			
		</tr>
		<tr>
			<td>작성자</td>
			<td>${board.writer }</td>
		</tr>
		<tr>
			<td>아이디</td>
			<td>${board.userid }</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>${board.content }</td>
		</tr>
		<tr>
			<td>등록일</td>
			<td>${board.regdate }</td>
		</tr>
		<tr>
			<td>조회수</td>
			<td>${board.cnt }</td>
		</tr>
	  </table>
	  </form>
	  <table width=610 border=0 cellpadding=0 cellspacing=0>
		  <tr>
			<td align=center>
			<input type="button" value="수정" onClick="boardModify()"> &nbsp;
			<input type="button" value="삭제" onClick="boardRemove()"> &nbsp;
			<input type="button" value="목록" onClick="boardList()"> 
			</td>
		  </tr>
	  </table>
	  </td>
	</tr>
</table>  

</body>
</html>