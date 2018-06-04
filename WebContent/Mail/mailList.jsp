<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../container/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pb" value="${requestScope.pagebean}" />
<c:set var="totalCount" value="${requestScope.totalCount}"/>
<c:set var="list" value="${pb.list}" />
<c:set var="result" value="${requestScope.result}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>받은 쪽지함</title>
<h1>받은 쪽지함</h1>
</head>
<body>
<table class="table table-border table-hover">
<thead>
	<tr>
	<th>num</th>
	<th width="50%">제목</th>
	<th>보낸이</th>
	<th>수신일</th>
	</tr>
</thead>
<tbody>
<c:forEach var="b" items="${list}" begin="${requestScope.startRow}" end="${requestScope.endRow}" >
	<tr>
	<td>1</td>
	<td><a href="javascript:functionrt('${b.mail_num}');">${b.mail_title}</a></td>
	<td>${b.members.name}</td>
	<td>${b.send_date}</td>
	</tr>
</c:forEach>
</tbody>

</table>
</body>
<style>
.container {
	padding-right: 350px;
	padding-left: 15px;
	margin-right: auto;
	margin-left: auto;
}

body {
	width: 100%;
	margin: 0;
	padding: 0;
}

button {
	background-color: #337ab7; /* Green */
	border: none;
	color: white;
	padding: 8px 10px;
	border-radius: 5px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 4px 2px;
	cursor: pointer;
}
thead {
	background-color: #337ab7;
	color: white;
	font-weight: bold;
}

</style>
<script>
function functionrt(data) {
	console.log(data);
		location.href = "mailcont.do?mail_num=" + data + "&mode=read";
	}

</script>
<script>
	var className = 'mail';
	$('div#menutab li.' + className).addClass('active');
	$('ul#side-menu').find('li.' + className).show();
</script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<%@include file="../container/footer.jsp"%>
</html>
