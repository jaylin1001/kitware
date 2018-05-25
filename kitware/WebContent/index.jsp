<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/font-awesome/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>

<style>
body {
	background-color: #337ab7;
}

.title {
	font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
	color: #fff;
}

div.hover {
	width: 100px;
	height: 100px;
	line-height: 100px;
	display: inline-block;
	font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
	color: #fff;
	background-color: #337ab7;
	transition: all 0.5s, color 0.3s;
	cursor: pointer;
}

div.hover:hover {
	color: #000;
	box-shadow: 100px 0 0 0 rgba(255, 255, 255, 1) inset;
}
</style>
<script>
	$(function(){
		$('.hover').click(function(){
			var name = $(this).attr('id');
			location.href="main.jsp?page="+name;
		});
	});
</script>
</head>
<body>
	<center>
		<div class="container">
			<br> <br> <br> <br> <br> <br> <br>
			<div class="title">
				<h1>KIT-WARE</h1>
			</div>
			<br> <br> <br> <br> <br> <br> <br>
			<div class="hover effect1" id="attendance">근태관리</div>
			<div class="hover effect1" id="authorization">전자결재</div>
			<div class="hover effect1" id="schedule">일정관리</div>
			<div class="hover effect1" id="notice">공지사항</div>
			<div class="hover effect1" id="board">게시판</div>
		</div>
	</center>
</body>
</html>