<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String root = "/kitware_50101526/WebContent/";
%>
<!-- jQuery CDN-->
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>KITWare</title>



<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- jQuery-ui CDN -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>

<!-- Bootstrap Core JavaScript  3.3.7 CDN-->
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript 2.7.7 CDN-->
<!-- 메뉴 확장/축소 플러그인 -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/metisMenu/2.7.7/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="../js/custom/sb-admin-2.js"></script>

<!-- include summernote css/js 윤지추가두줄-->
<script src="../summernote/summernote.js"></script>
<script src="../summernote/lang/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="../summernote/summernote.css">
<link href='../css/fullcalendar/fullcalendar.min.css' rel='stylesheet' />
<link href='../css/fullcalendar/fullcalendar.print.min.css'
	rel='stylesheet' media='print' />
<script src='../js/fullcalendar/moment.min.js'></script>
<script src='../js/fullcalendar/fullcalendar.min.js'></script>
<script type=text/javascript src='../js/fullcalendar/locale/ko.js'></script>
<script src='../js/fullcalendar/theme-chooser.js'></script>
<script src='../js/fullcalendar/gcal.js'></script>
<link href='https://use.fontawesome.com/releases/v5.0.6/css/all.css'
	rel='stylesheet'>
<script>
	$(function() {
		$('div#menutab li').click(function() { //상단의 메뉴 버튼을 눌렀을 때 버튼에 따라  1.side menu 변경 , 2.해당 li태그 active 속성. 순서가 중요.
			//버튼 누를때 사이드 메뉴 초기화
			$('ul#side-menu>li').hide();

			//모든 li의  active class를 전부 지우고 현재 클릭한 곳만 active 시킨다.
			$('div#menutab li').removeClass('active');
			var className = $(this).attr('class');
			//선택된 li와 동일한 class이름을 가진  sidemenu 불러온다.
			$('ul#side-menu').find('li.' + className).show();
			/* $('div#page-wrapper').html('');*/// 받아온 className이  ~~라면 switch case문 실행.
			switch (className) { // 받아온 className이  ~~라면 switch case문 실행.
			case 'schedule': // 일정관리 tab 눌렀을 때 
				location.href='../schedule/schedulecalendar.jsp?list=개인일정';
				break;
			case 'board': // 일정관리 tab 눌렀을 때 
				location.href="../board/board.jsp";
				break;
			case 'attendance': // 일정관리 tab 눌렀을 때 
					location.href="../attendance/mymonth.jsp";
					break;
			case 'authorization': // 일정관리 tab 눌렀을 때 
					location.href="../authorization/docwrite.jsp";
					break;
			} 
			


			$(this).addClass('active');

		});

		//왼쪽 side-menu bar 눌렀을 때
		$('ul#side-menu>li.schedule a').click(function() {
			var list = $(this).text();
			location.href="../schedule/schedulecalendar.jsp?list="+list;
		});
		$('.nav>li.gian').click(function() {
			//var form = $('#write>li').attr('class');
			$.ajax({
				method : 'POST',
				url : 'authorization/docwrite.jsp',
				success : function(data) {
					$('div#page-wrapper').empty();
					$('div#page-wrapper').html(data);
				}
			});
		});
		$('.nav>li.balju').click(function() {
			//var form = $('#write>li').attr('class');
			$.ajax({
				method : 'POST',
				url : 'authorization/balju.jsp',
				success : function(data) {
					$('div#page-wrapper').empty();
					$('div#page-wrapper').html(data);
				}
			});
		});
		$('.nav>li.chuljang').click(function() {
			//var form = $('#write>li').attr('class');
			$.ajax({
				method : 'POST',
				url : 'authorization/chuljang.jsp',
				success : function(data) {
					$('div#page-wrapper').empty();
					$('div#page-wrapper').html(data);
				}
			});
		});
		$('.nav>li.byungga').click(function() {
			//var form = $('#write>li').attr('class');
			$.ajax({
				method : 'POST',
				url : 'authorization/byungga.jsp',
				success : function(data) {
					$('div#page-wrapper').empty();
					$('div#page-wrapper').html(data);
				}
			});
		});
		$('.nav>li.jotae').click(function() {
			//var form = $('#write>li').attr('class');
			$.ajax({
				method : 'POST',
				url : 'authorization/jotae.jsp',
				success : function(data) {
					$('div#page-wrapper').empty();
					$('div#page-wrapper').html(data);
				}
			});
		});
	});
</script>



<!-- Bootstrap Core CSS 3.3.7 CDN-->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">

<!-- MetisMenu CSS 2.7.7 CDN-->
<!-- 메뉴 확장/축소 플러그인 -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/metisMenu/2.7.7/metisMenu.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="../css/custom/sb-admin-2.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="../css/font-awesome/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css"
	type="text/css" />
<style>
ul#side-menu>li { /*처음에 sidebar menu 전부다 display none 시킨다.*/
	display: none;
}

div.page-wrapper {
	padding-top: 30px;
}
</style>
</head>
<body>
	<div id="wrapper">
		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top"
			style="margin-bottom: 0">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.jsp">KIT Ware</a>
			</div>
			<!-- /.navbar-header -->

			<div id="menutab" class="navbar-top-links">
				<ul class="nav nav-tabs">
					<li class="home active"><a href="index.jsp">HOME</a></li>
					<!-- active 옵션 -->
					<li class="authorization"><a href="#1" data-toggle="tab"><i
							class="fa fa-edit fa-fw"></i>전자결재</a></li>
					<li class="schedule"><a href="#2" data-toggle="tab"><i
							class="fa fa-calendar fa-fw"></i>일정관리</a></li>
					<li class="attendance"><a href="#3" data-toggle="tab"><i
							class="fa fa-clock-o"></i>근태관리</a></li>
					<li class="board"><a href="#4" data-toggle="tab"><i
							class="fa fa-bars"></i>게시판</a></li>
					<!-- ***추가됨 -->
				</ul>

			</div>

			<div class="sidebar">
				<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">
						<li class="authorization"><a href="#">개인문서함<span
								class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li><a href="#">미완료문서함</a></li>
								<li><a href="#">상신문서함</a></li>
								<li><a href="#">완료문서함</a></li>
							</ul></li>
						<li class="authorization"><a href="#">결재문서함<span
								class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li><a href="#">미완료문서함</a></li>
								<li><a href="#">완료문서함</a></li>
							</ul></li>
						<li class="authorization"><a href="#">협조문서함<span
								class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li><a href="#">미완료문서함</a></li>
								<li><a href="#">상신문서함</a></li>
								<li><a href="#">완료문서함</a></li>
							</ul></li>
						<li class="authorization"><a href="#" id="write">결재문서작성<span
								class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li class="gian"><a href="../authorization/docwrite.jsp">기안서</a></li>
								<li class="chuljang"><a
									href="../authorization/chuljang.jsp">외근신청</a></li>
								<li class="byungga"><a href="../authorization/byungga.jsp">병가신청</a></li>
								<li class="jotae"><a href="../authorization/jotae.jsp">조퇴신청</a></li>
								<li class="balju"><a href="../authorization/balju.jsp">발주신청</a></li>
							</ul></li>
						<li class="schedule"><a href="#">개인일정</a> <a href="#">부서일정</a>
							<a href="#">회사일정</a> <a href="#">전체일정</a></li>

						<li class="attendance"><a href="#">내 근태 현황<span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li class="mymonth"><a href="../attendance/attendance.jsp">월간 현황</a></li>
								<li class="myyear"><a href="../attendance/myyear.jsp">연간 현황</a></li>
							</ul>
						</li>
						<li class="attendance"><a href="../attendance/myyeoncha.jsp">내 연차 현황</a></li>
						
						<li class="board"><a href="#">게시판<span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li><a href="#">공지사항</a></li>
								<li><a href="#">부서공지</a></li>

							</ul></li>
					</ul>
				</div>
				<!-- /.sidebar-collapse -->
			</div>
			<!-- /.navbar-static-side -->
		</nav>
		<div id="page-wrapper">
			<%-- <%@ include file="footer.jsp"%> --%>