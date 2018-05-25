<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta charset='utf-8' />
<%@include file="../container/header.jsp"%>

<style>
body {
	margin: 0;
	padding: 0;
	font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
	font-size: 14px;
}

.articleTop {
	padding-top: 15;
}

#calendar {
	/* max-width: 800px; */
	margin: 0 auto;
}

.articleTop>#print {
	float: right;
}

.articleTop>#print {
	float: right;
}

.articleTop>#add {
	float: right;
}
</style>
<div class="articleTop">
	<i class="fa fa-calendar-check-o" style="font-size: 25px"></i>
	<button class="btn btn-default" id="print">인쇄</button>
	<a href="scheduleadd.jsp" class="btn btn-default" data-toggle="modal"
		data-target="#myModal" id="add">일정추가</a>

	<div id="myModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- Content will be loaded here from "scheduleform1.html" file -->
			</div>
		</div>

	</div>
</div>

<hr>
<div id='calendar'></div>
<script>
	
	//개인일정,회사일정 articleTop 부분 글씨
	//인쇄버튼 클릭이벤트
	$(function(){
		<%String listText = (String) request.getParameter("list").trim();%>  <!-- 받아온 listText는 개인일정,부서일정 ~~이라는 TEXT -->
			$('div.articleTop >i').text('<%=listText%>');
		$("#print").click(function() {
			window.print();
		})
	});
	//달력 부분 function
	jQuery(document)
			.ready(
					function() {
						$('#calendar')
								.fullCalendar(
										{
											/* bootstrap4 테마사용 */
											theme : true,
											themeSystem : 'bootstrap4',
											/* 사이즈지정 */
											height : 'parent', /* 상위 컨테이너 요소의 높이와 캘린더 전체높이를일치 */
											contentHeight : 600,
											/* 기본뷰를 month로 지정*/
											defaultView : 'month',
											/* customButton 만들기 */
											customButtons : {
												myCustomButton : {
													text : 'custom!',
													click : function() {
														var moment = $(
																'#calendar')
																.fullCalendar(
																		'getDate');
														alert("현재 날짜는"
																+ moment
																		.format('YYYY-MM-DD'));
													}

												}
											},
											/* agenda 형식의 시간 단위 조정 default값 30분  15분으로 조정. */
											slotDuration : '00:15:00',
											/* agenda 형식의 시간 포맷 지정  a(오전,오후) h(시간):mm(분) */
											slotLabelFormat : 'a h:mm ',
											/* agenda 스크롤 타임 지정 9시가 default */
											scrollTime : '09:00:00',
											/* 아이콘 지정 */
											bootstrapFontAwesome : {
												close : 'fa-times',
												prev : 'fa-chevron-left',
												prevYear : 'fa-angle-double-left',
												nextYear : 'fa-angle-double-right',
												myCustomButton : 'fas fa-smile'
											},
											/* 헤더 */
											header : {
												left : 'prevYear,prev,next,nextYear,today,myCustomButton',
												center : 'title',
												right : 'month,agendaWeek,agendaDay,listMonth'
											},
											/* view별로 상세 조정이 가능하다. */
											views : {
												month : { // name of view
													titleFormat : 'YYYY년MM월'
												// other view-specific options here
												}
											},

											navLinks : true, // can click day/week names to navigate views
											editable : true,
											eventLimit : true, // allow "more" link when too many events
											googleCalendarApiKey : "AIzaSyDcnW6WejpTOCffshGDDb4neIrXVUA1EAE",
											eventSources : [ {
												googleCalendarId : "ko.south_korea#holiday@group.v.calendar.google.com",
												className : "koHolidays",
												color : "#FF0000",
												textColor : "#FFFFFF",
											} ],
											events : [ {
												title : 'All Day Event',
												start : '2018-05-01',
											}, {
												title : 'Long Event',
												start : '2018-05-07',
												end : '2018-05-10'
											}, {
												id : 999,
												title : 'Repeating Event',
												start : '2018-05-09T16:00:00'
											}, {
												id : 999,
												title : 'Repeating Event',
												start : '2018-05-16T16:00:00'
											}, {
												title : 'Conference',
												start : '2018-05-11',
												end : '2018-05-13'
											}, {
												title : 'Meeting',
												start : '2018-05-12T10:30:00',
												end : '2018-05-12T12:30:00'
											}, {
												title : 'Lunch',
												start : '2018-05-12T12:00:00'
											}, {
												title : 'Meeting',
												start : '2018-05-12T14:30:00'
											}, {
												title : 'Happy Hour',
												start : '2018-05-12T17:30:00'
											}, {
												title : 'Dinner',
												start : '2018-05-12T20:00:00'
											}, {
												title : 'Birthday Party',
												start : '2018-05-13T07:00:00'
											}, {
												title : 'Click for Google',
												url : 'http://google.com/',
												start : '2018-05-28'
											} ]
										});
					});
	var className = 'schedule';
	$('div#menutab li.'+className).addClass('active');
	console.log($('div#menutab li.'+className));
	$('ul#side-menu').find('li.' + className).show();
</script>
<%@include file="../container/footer.jsp"%>

