<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../container/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="toDay" class="java.util.Date" />
<fmt:formatDate value='${toDay}' pattern='yyyyMMdd' var="nowDate"/>
<c:set var="mail" value="${requestScope.Mailcont}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form id="formwrite" onsubmit="return false;">
<h1>답장하기</h1>
<div style="display:none">
<input type ="text" name ="mail_num" value="${mail.mail_num}" readonly>&nbsp; &nbsp;
<input type ="text" name ="mode" value="update" readonly>&nbsp; &nbsp;
</div>
<label>보낸사람:</label>
<input type ="text" name ="namesend" value="${mail.members.name}" readonly>&nbsp; &nbsp; 
 <label>받는사람:</label>
<input type ="text" name ="namercv" value="${mail.members.name0}" readonly>&nbsp; &nbsp; 
<br>
  <label>수신일 :&nbsp;</label>
  <input type ="text" name ="send_date" value="${mail.send_date}" readonly>&nbsp; &nbsp; 
<br>
  <label>제목 : </label>
   <input type ="text" name ="mail_title" value="RE:${mail.mail_title}" style="width:300px"readonly>&nbsp; &nbsp; 
  <hr>
<textarea class="form-control" id="exampleFormControlTextarea1" rows="10"
	name = "mail_content">${mail.mail_content}
${mail.members.name}/${toDay}
-----------------------------------


</textarea>

 <br>
<input type="submit" value="답장" onclick = "submitfn('${mail.mail_num}')">
<input type="button" value="취소" onclick="window.history.go(-1); return false;">
</form>
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
function submitfn(data) {
	location.href= "mailcont.do"
	document.getElementById("formwrite").submit();
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

