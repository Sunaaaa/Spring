<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>title</title>

</head>
<body>
	<h2>로그인 폼</h2>
	<form action="login.do" id="form1" name="form1" method="post">
	
	<table border="1">
		<tr>
			<td>ID</td>
			<td><input type="text" id="id" name="userid" required="required"></td>
		</tr>
		<tr>
			<td>PW</td>
			<td><input type="text" id="pw" name="userpwd" required="required"
						></td>
		</tr>
		<tr align="center">
			<td colspan="2">
				<input type="submit" value="로그인">
				<input type="reset" value="취소">
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<a href="#">회원가입</a>
			</td>
		</tr>
	</table>
	
	</form>	
	
</body>
</html>