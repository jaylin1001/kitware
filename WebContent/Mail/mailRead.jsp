<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../container/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="mail" value="${requestScope.Mailcont}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<label>보낸사람:</label>
 ${mail.members.name}&nbsp; &nbsp; 
 <label>받는사람:</label>
${mail.members.name0}
<br>
  <label>수신일 : ${mail.send_date}</label>
 <hr>
  <label>제목 : ${mail.mail_title}</label>
  <hr>
<textarea class="form-control" id="exampleFormControlTextarea1" rows="10" readonly>${mail.mail_content}
 </textarea>
 <br>
<%--  <c:if test="${requestScope.state ne 'my'}"> --%>
<input type="button" value="답장" onclick = "editdocnum('${mail.mail_num}')">
<%-- </c:if> --%>
<input type="button" value="확인" onclick="location.href='maillist.do'">

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
function editdocnum(data) {
	location.href= "mailcont.do?mail_num="+data+"&mode=editread"
	console.log(data);
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

