<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<c:set var="doclist" value="${sessionScope.doc_list}"></c:set>
<c:set var="schelist" value="${sessionScope.schedule}"></c:set>
<c:set var="maillist" value="${sessionScope.mail_list}"></c:set>
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
<script src="${pageContext.request.contextPath}/js/custom/sb-admin-2.js"></script>

<!-- include summernote css/js 추가두줄-->
<script src="${pageContext.request.contextPath}/summernote/summernote.js"></script>
<script src="${pageContext.request.contextPath}/summernote/lang/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/summernote/summernote.css">
<link href='${pageContext.request.contextPath}/css/fullcalendar/fullcalendar.min.css' rel='stylesheet' />
<link href='${pageContext.request.contextPath}/css/fullcalendar/fullcalendar.print.min.css'
	rel='stylesheet' media='print' />
<script src='${pageContext.request.contextPath}/js/fullcalendar/moment.min.js'></script>
<script src='${pageContext.request.contextPath}/js/fullcalendar/fullcalendar.min.js'></script>
<script type=text/javascript src='${pageContext.request.contextPath}/js/fullcalendar/locale/ko.js'></script>
<script src='${pageContext.request.contextPath}/js/fullcalendar/theme-chooser.js'></script>
<script src='${pageContext.request.contextPath}/js/fullcalendar/gcal.js'></script>
<link href='https://use.fontawesome.com/releases/v5.0.6/css/all.css'
	rel='stylesheet'>
<!-- datepicker js CDN-->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
	
<script>
	$(function() {
		$('div#menutab li').click(function() { //상단의 메뉴 버튼을 눌렀을 때 버튼에 따라  1.side menu 변경 , 2.해당 li태그 active 속성. 순서가 중요.
			//버튼 누를때 사이드 메뉴 초기화
			$('ul#side-menu>li').hide();

			//모든 li의  active class를 전부 지우고 현재 클릭한 곳만 active 시킨다.
			$('div#menutab li').removeClass('active');
			
			var className = $(this).attr('class');
			//선택된 li와 동일한 class이름을 가진  sidemenu 불러온다.
			console.log(className);
			$('ul#side-menu').find('li.' + className).show();
			

			
			/* $('div#page-wrapper').html('');*/// 받아온 className이  ~~라면 switch case문 실행.
			switch (className) { // 받아온 className이  ~~라면 switch case문 실행.
			
			case 'schedule': // 일정관리 tab 눌렀을 때 
				location.href='${pageContext.request.contextPath}/schedule/schedulecalendar.jsp?list=개인일정';
				break;
			case 'board': // 게시판 tab 눌렀을 때 
				location.href="${pageContext.request.contextPath}/boardlist.do";
				break;
			case 'attendance': // 근태 tab 눌렀을 때 
				location.href="${pageContext.request.contextPath}/attendance/mymonth.jsp";
					break;
			case 'authorization': // 전자결재 tab 눌렀을 때 
				location.href="${pageContext.request.contextPath}/doclist.do";
					break;
			case 'home' :
				location.href='${pageContext.request.contextPath}/mainview.do';
				break;
			case 'mail' :
				location.href='${pageContext.request.contextPath}/maillist.do';
				break;
			} 
			
			$(this).addClass('active');
			
		});

		//왼쪽 side-menu bar 눌렀을 때
		$('ul#side-menu>li.schedule a').click(function() {
			var list = $(this).text();
			location.href="${pageContext.request.contextPath}/schedule/schedulecalendar.jsp?list="+list;
		});
		//로그아웃 버튼 누른다.
		$('div.navbar-header>a.logout').click(function(){
			location.href="${pageContext.request.contextPath}/logout.do";
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
<link href="${pageContext.request.contextPath}/css/custom/sb-admin-2.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="${pageContext.request.contextPath}/css/font-awesome/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css"
	type="text/css" />
<!-- datepicker css -->
		<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css"
		type="text/css" />		
	
	
<style>
ul#side-menu>li { /*처음에 sidebar menu 전부다 display none 시킨다.*/
	display: none;
}

div.page-wrapper {
	padding-top: 0px;
	margin-top: 0px;
}
div.navbar-header> div.logininfo{
	padding: 18px;
	float:right;
}
div.navbar-header> a.logout{
	float:right;
}
</style>
</head>
<body>
	<div id="wrapper">
		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top">
			<div class="navbar-header">
				<a class="navbar-brand" href="${pageContext.request.contextPath}/home/home.jsp">KIT Ware</a>
				<a class="navbar-brand logout" href="#">Logout</a>
				<div class="logininfo">${sessionScope.loginInfo.name} ${sessionScope.loginInfo.gradeinfo.position_name} 님 로그인 되었습니다.</div>
			</div>
			<!-- /.navbar-header -->

			<div id="menutab" class="navbar-top-links">
				<ul class="nav nav-tabs">
					<!-- active 옵션 -->
					<li class="home"><a href="#" data-toggle="tab"><i
							class="fas fa-home"></i>HOME</a></li>
					<li class="authorization"><a href="#1" data-toggle="tab"><i
							class="fa fa-edit fa-fw"></i>전자결재</a></li>
					<li class="schedule"><a href="#2" data-toggle="tab"><i
							class="fa fa-calendar fa-fw"></i>일정관리</a></li>
					<li class="attendance"><a href="#3" data-toggle="tab"><i
							class="fa fa-clock-o"></i>근태관리</a></li>
					<li class="board"><a href="#4" data-toggle="tab"><i
							class="fa fa-bars"></i>게시판</a></li>
					<li class="mail"><a href="#5" data-toggle="tab"><i
							class="fa fa-envelope"></i>쪽지함</a></li>
					<!-- ***추가됨 -->
				</ul>

			</div>

			<div class="sidebar">
			<div>
					<div style ="padding-left:15px; width: 50%; float:left">
					<img src="${pageContext.request.contextPath}/img/lee.jpg" width="100" height="100"></div>
					<div style = "padding-left:15px; width: 50%; float:left;">
					<h3>${sessionScope.loginInfo.name}</h3><h5>${sessionScope.loginInfo.gradeinfo.position_name}
					<br>${sessionScope.loginInfo.deptinfo.dept_name}
					</h5><br>
					</div>
					</div>
					&nbsp; &nbsp;<a href="${pageContext.request.contextPath}/gjmywaitlist.do">&nbsp;결재할 문서:&nbsp; &nbsp; &nbsp;${doclist}</a>
					<div>&nbsp;</div>
					&nbsp; &nbsp;<a href="${pageContext.request.contextPath}/schedule/schedulecalendar.jsp?list=개인일정">
					오늘의 일정:&nbsp; &nbsp; &nbsp;${schelist}</a>
					<div>&nbsp;</div>
					&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/maillist.do">
					안읽은 쪽지:&nbsp; &nbsp; &nbsp;${maillist.count}</a>
					<hr>
				<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">
					<li class="home">
					<a href="#">빠른메뉴<span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li><a href="${pageContext.request.contextPath}/docgianinfo.do?kind=gian&doc_kind=10">문서작성</a></li>
								<li><a href="${pageContext.request.contextPath}/schedule/schedulecalendar.jsp?list=개인일정">일정추가</a></li>
								<li><a href="${pageContext.request.contextPath}/mailwrite.do?mode=writeview">쪽지보내기</a></li>
							</ul></li>
						<li class="authorization"><a href="#">결재문서함<span
								class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li class = "gj_wait"><a href="${pageContext.request.contextPath}/gjmywaitlist.do">결재대기</a></li>
								<li class = "gj_exp"><a href="${pageContext.request.contextPath}/gjexpectlist.do?exp=ex">결재예정</a></li>
								<li class = "gj_ok"><a href="${pageContext.request.contextPath}/gjoklist.do?exp=ok">결재완료</a></li>
							</ul></li>
							
						<li class="authorization"><a href="#">개인문서함<span
								class="fa arrow"></span></a>
								
							<ul class="nav nav-second-level">
								<li class = "gian_list"><a href="${pageContext.request.contextPath}/gjwaitlist.do?mode=all&page=1">기안문서함</a></li>
								<li class = "gj_list"><a href="${pageContext.request.contextPath}/mygjoklist.do?mode=all&page=1">결재문서함</a></li>
							</ul></li>
						<li class="authorization"><a href="${pageContext.request.contextPath}/docdeptlist.do?mode=all&page=1">부서문서함<span
								class="fa arrow"></span></a>
							</li>
						<li class="authorization"><a href="#" id="write">결재문서작성<span
								class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li class="gian"><a href="${pageContext.request.contextPath}/docgianinfo.do?kind=gian&doc_kind=10">기안서</a></li>
								<li class="pumyee"><a href="${pageContext.request.contextPath}/docgianinfo.do?kind=pumyee&doc_kind=20">품의서</a></li>
								<li class="balju"><a href="${pageContext.request.contextPath}/docgianinfo.do?kind=balju&doc_kind=30">발주서</a></li>
								<li class="chuljang"><a href="${pageContext.request.contextPath}/docgianinfo.do?kind=chuljang&doc_kind=40">출장신청</a></li>
								<li class="byungga"><a href="${pageContext.request.contextPath}/docgianinfo.do?kind=byungga&doc_kind=60">병가신청</a></li>
								<li class="jotae"><a href="${pageContext.request.contextPath}/docgianinfo.do?kind=jotae&doc_kind=80">조퇴신청</a></li>
							</ul></li>
						<div>&nbsp;</div>
						<li class="schedule"><a href="#" id="schperson">개인일정</a> <a href="#" id="schdept">부서일정</a>
							<a href="#" id="schcompany">회사일정</a> <a href="#" id="schtotal">전체일정</a></li>

						<li class="attendance"><a href="#">내 근태 현황<span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li class="mymonth"><a href="${pageContext.request.contextPath}/attendance/attendance.jsp">월간 현황</a></li>
								<li class="myyear"><a href="${pageContext.request.contextPath}/attendance/myyear.jsp">연간 현황</a></li>
							</ul>
						</li>
						<li class="attendance"><a href="${pageContext.request.contextPath}/attendance/myyeoncha.jsp">내 연차 현황</a></li>
						
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