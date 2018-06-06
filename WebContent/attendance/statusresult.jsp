<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="status" value="${requestScope.statuslist}"/>
<table class="table">
	<tr>
		<th>출장</th><td>${status[0] }</td>
		<th>병가</th><td>${status[2] }</td>
	</tr>
	<tr>
		<th>연차</th><td>${status[1] }</td>
		<th>조퇴</th><td>${status[3] }</td>
	</tr>
</table>