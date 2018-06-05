<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../container/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pb" value="${requestScope.pagebean}" />
<c:set var="totalCount" value="${requestScope.totalCount}"/>
<c:set var="list" value="${pb.list}" />
<c:set var="list2" value="${requestScope.list2}" />
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
	<th>상태</th>
	</tr>
</thead>
<tbody>
<c:forEach var="b" items="${list}" end="10" >
	<tr>
	<td>${b.rownum}</td>
	<td><a href="javascript:functionrt('${b.mail_num}');">${b.mail_title}</a></td>
	<td>${b.members.name}</td>
	<td>${b.send_date}</td>
	<c:choose>
	<c:when test="${b.watch_yn eq '1'}">
	<td>읽음</td>
	</c:when>
	<c:otherwise>
	<td>안읽음</td>
	</c:otherwise>
	</c:choose>
	</tr>
</c:forEach>
</tbody>
</table>

<h1>보낸 쪽지함</h1>
<table class="table table-border table-hover">
<thead>
	<tr>
	<th>num</th>
	<th width="50%">제목</th>
	<th>보낸이</th>
	<th>발신일</th>
	<th>상태</th>
	</tr>
</thead>
<tbody>
<c:forEach var="b2" items="${list2}" end="10" >
	<tr>
	<td>${b2.rownum}</td>
	<td><a href="javascript:functionrt('${b2.mail_num}','my');">${b2.mail_title}</a></td>
	<td>${b2.members.name}</td>
	<td>${b2.send_date}</td>
	<c:choose>
	<c:when test="${b2.watch_yn eq '1'}">
	<td>읽음</td>
	</c:when>
	<c:otherwise>
	<td>안읽음</td>
	</c:otherwise>
	</c:choose>
	</tr>
</c:forEach>
</tbody>

</table>
<input type="button" value="쪽지쓰기" onclick = "gowrite()">
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
function gowrite() {
	location.href= "mailwrite.do?mode=writeview"
}
function functionrt(data,data2) {
	console.log(data);
		location.href = "mailcont.do?mail_num=" + data + "&mode=read&state="+data2;
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
