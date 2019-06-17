<%@page import="spring.biz.user.dao.UserDAO_JDBC"%>
<%@page import="spring.biz.user.dao.UserDAO"%>
<%@page import="java.util.List"%>
<%@page import="spring.biz.user.vo.UserVO"%>

<%@page import="spring.biz.user.service.UserServiceImpl"%>
<%@page import="spring.biz.user.service.UserService"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="./css/table.css">
<h3>userinfo List</h3>
<table>
	<tr>
		<th>userid</th>
		<th>username</th>
		<th>email</th>
		<th>phone</th>
		<th>address</th>
	</tr>
	<c:forEach items="${users}" var="user">
		<tr>
			<td>${user.userid }</td>
			<td>${user.username }</td>
			<td>${user.email }</td>
			<td>${user.phone }</td>
			<td>${user.address }</td>
		</tr>
	</c:forEach>
</table>



