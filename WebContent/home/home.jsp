<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../container/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="boardlist" value="${requestScope.board_list}"></c:set>
<c:set var="schelist" value="${requestScope.schedule}"></c:set>
<c:set var="doclist" value="${requestScope.doc_list}"></c:set>
<h1>Hello Kitware!</h1>

<div style="width:40%; height:350px; float:left; padding-right:10px;">
<h3>공지사항</h3>
<table class="table table-hover table-bordered">
		<thead id ="board">
			<tr class="table-primary">
				<th width="10%">번호</th>
				<th width="40%">제목</th>
				<th width="15%">작성자</th>
				<th width="30%">작성일</th>
				<th width="10%">hit</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${boardlist}" var="b" varStatus="status"
			 begin="1" end="5">
			<tr>
			<td>${b.seq}</td>
			<td>${b.title}</td>
			<td>${b.name}</td>
			<td>${b.log_time}</td>
			<td>${b.hit}</td>
			</tr>
			</c:forEach>
		</tbody>
</table>
</div>
<div style="width:40%; float:left; height:350px; padding-right:20px;">
<h3>부서게시판</h3>
<table class="table table-hover table-bordered">
		<thead class="thead-light" id ="board">
			<tr>
				<th width="10%">번호</th>
				<th width="40%">제목</th>
				<th width="15%">작성자</th>
				<th width="30%">작성일</th>
				<th width="10%">hit</th>
			</tr>
		</thead>
		<tbody>
			<tr>
			<td>1</td>
			<td>sample</td>
			<td>writer</td>
			<td>180602</td>
			<td>1</td>
			</tr>
		</tbody>
</table>
</div>
 <div id='calendar' style = "float:right">
 <p>
 <div id='calendarmini'>
 </div>
 </div> <!-- 풀캘린더 뜨는 부분 -->
 
<div style="width:80%;height:350px;">
</div>
<div style="width:79%;height:300px;">
<h3>결재 대기문서</h3>
<table class="table table-bordered table-hover">
	<thead id="board">
		<tr class="table-primary">
			<td width="20%">기안일</td>	
			<td width="40%">문서제목</td>
			<td width="15%">문서번호</td>
			<td width="10%">문서상태</td>
			<td width="15%">문서 이름</td>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${doclist}" var="doc" varStatus="status">
			<tr>
				<td>${doc.start_date}</td>
				<td><a href="javascript:functionrt(${doc.doc_kind},'${doc.doc_num}');">${doc.doc_title}</a></td>
				<td>${doc.doc_num}</td>
				<c:choose>
      			 <c:when test="${doc.doc_state eq '1'}">
      			 <td>진행</td>
      			 </c:when>
      			 <c:when test="${doc.doc_state eq '2'}">
      			 <td>완료</td>
      			 </c:when>
      			 <c:when test="${doc.doc_state eq '3'}">
      			 <td>취소</td>
      			 </c:when>
      			 <c:otherwise>
      			 <td>상신</td>
      			 </c:otherwise>
      			 </c:choose>
				<td>${doc.doc_kindvo.doc_name}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>

<div></div>
</div>
<div style="border:1px solid gray; width:80%;height:250px;">
<h3>출퇴근 내역</h3>
</div>
<script>
function functionrt(data, data1) {
	console.log(data);
	console.log(data1);
		location.href = "docread.do?doc_num=" + data1 + "&doc_kind=" + data;
	}

$(document).ready(function() {

    var date = new Date();
    var d = date.getDate();
    var m = date.getMonth();
    var y = date.getFullYear();

    $('#calendar').fullCalendar({
        theme: true,
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek,agendaDay'
        },
        editable: true,
        // add event name to title attribute on mouseover
        eventMouseover: function(event, jsEvent, view) {
            if (view.name !== 'agendaDay') {
                $(jsEvent.target).attr('title', event.title);
            }
        },
        height: 350,
        events: [
            {
            title: 'All Day Event',
            start: new Date(y, m, 1)},
        {
            title: 'Long Event',
            start: new Date(y, m, d - 5),
            end: new Date(y, m, d - 2)},
        {
            id: 999,
            title: 'Repeating Event',
            start: new Date(y, m, d - 3, 16, 0),
            allDay: false},
        {
            id: 999,
            title: 'Repeating Event',
            start: new Date(y, m, d + 4, 16, 0),
            allDay: false},
        {
            title: 'Meeting',
            start: new Date(y, m, d, 10, 30),
            allDay: false},
        {
            title: 'Lunch',
            start: new Date(y, m, d, 12, 0),
            end: new Date(y, m, d, 14, 0),
            allDay: false},
        {
            title: 'Birthday Party',
            start: new Date(y, m, d + 1, 19, 0),
            end: new Date(y, m, d + 1, 22, 30),
            allDay: false},
        {
            title: 'Click for Google',
            start: new Date(y, m, 28),
            end: new Date(y, m, 29),
            url: 'http://google.com/'}
        ]
    });
    
    $('#calendarmini').fullCalendar({
        header: {
          left: 'today',
          center: 'title',
          right: 'listDay,listWeek,month'
        },

        // customize the button names,
        // otherwise they'd all just say "list"
        views: {
          listDay: { buttonText: 'list day' },
          listWeek: { buttonText: 'list week' }
        },

        defaultView: 'listWeek',
        defaultDate: '2018-03-12',
        navLinks: true, // can click day/week names to navigate views
        editable: true,
        eventLimit: true, // allow "more" link when too many events
        events: [
          {
            title: 'All Day Event',
            start: '2018-03-01'
          },
          {
            title: 'Long Event',
            start: '2018-03-07',
            end: '2018-03-10'
          },
          {
            id: 999,
            title: 'Repeating Event',
            start: '2018-03-09T16:00:00'
          },
          {
            id: 999,
            title: 'Repeating Event',
            start: '2018-03-16T16:00:00'
          },
          {
            title: 'Conference',
            start: '2018-03-11',
            end: '2018-03-13'
          },
          {
            title: 'Meeting',
            start: '2018-03-12T10:30:00',
            end: '2018-03-12T12:30:00'
          },
          {
            title: 'Lunch',
            start: '2018-03-12T12:00:00'
          },
          {
            title: 'Meeting',
            start: '2018-03-12T14:30:00'
          },
          {
            title: 'Happy Hour',
            start: '2018-03-12T17:30:00'
          },
          {
            title: 'Dinner',
            start: '2018-03-12T20:00:00'
          },
          {
            title: 'Birthday Party',
            start: '2018-03-13T07:00:00'
          },
          {
            title: 'Click for Google',
            url: 'http://google.com/',
            start: '2018-03-28'
          }
        ]
      });
    

});
var className = 'home';
$('div#menutab li.'+className).addClass('active');
console.log($('div#menutab li.'+className));
$('ul#side-menu').find('li.' + className).show();
</script>
<style>
#board {
	background-color: #337ab7;
	color: white;
	font-weight: bold;
}
#calendar {
    width: 230px;
    margin: 0 auto;
    font-size: 9px;
    padding-left: 1px;
    float: left;
}
.fc-header-title h2 {
    font-size: .6em;
    white-space: normal !important;
}
.fc-view-month .fc-event, .fc-view-agendaWeek .fc-event {
    font-size: 0;
    overflow: hidden;
    height: 2px;
}
.fc-view-agendaWeek .fc-event-vert {
    font-size: 0;
    overflow: hidden;
    width: 2px !important;
}
.fc-agenda-axis {
    width: 20px !important;
    font-size: .6em;
}

.fc-button-content {
    padding: 0;
}
.fc-widget-header{
    background-color:white !important;
}
</style>
<%@include file="../container/footer.jsp"%>