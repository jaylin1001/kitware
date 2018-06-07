<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../container/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="now" value="<%=new java.util.Date()%>" />
<c:set var="sysYear"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd" /></c:set>
<c:set var="boardlist" value="${requestScope.board_list}"></c:set>
<c:set var="schelist" value="${requestScope.schedule}"></c:set>
<c:set var="doclist" value="${requestScope.doc_list}"></c:set>
<c:set var="yc" value="${requestScope.Yeoncha}"/>
<c:set var="yg" value="${requestScope.Yeoncha_gigan}"/>
<c:set var="listGunte" value="${requestScope.Gunte}"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Nwagon.css" type="text/css">
<script src="${pageContext.request.contextPath}/js/Nwagon.js"></script>
<h1>Hello KITWARE!</h1>
<div style="width:40%; height:300px; float:left; padding-right:10px;">
<h3>공지입니다</h3>
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
			<c:forEach items="${boardlist}" var="b" varStatus="status">
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
<div id ="Nwagon" style="width:40%; float:left; height:300px; border: 1px solid white /* padding-right:20px; */">
<h3>내 기안문서 진행도</h3>
</div>
 <div id='calendar' style = "float:right">
<hr>
 <div id='calendarmini'>
 </div>
 </div> <!-- 풀캘린더 뜨는 부분 -->
 
<div style="width:80%;height:300px;">
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
		<c:forEach items="${doclist}" var="doc" varStatus="status"
		begin="0" end="4">
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
<div style="width:79%;height:200px;">
<h3>출퇴근 내역</h3>
 <table class="table table-bordered">
 	<thead id="board">
      	<tr>
			<th>총연차</th>
			<th>사용 연차</th>
			<th>잔여 연차</th>
		</tr>
		</thead>
		<tr>
			<td>15</td>
			<td>${use }</td>
			<td>${15-use }</td>
		</tr>
		<thead id="board">
		<tr>
		<th>오늘날짜</th>
		<th>출근시각</th>
		<th>퇴근시각</th>
		</tr>
		</thead>
    
     <c:forEach items="${listGunte}" var="gtlist" varStatus="status">
     <tr>
     <c:if test="${gtlist.in_day eq sysYear}">
    <td>${gtlist.in_day}</td>
     <td>${gtlist.in_time}</td>
    <td>${gtlist.out_time}</td>
    </c:if>
    </tr>
     </c:forEach>
 </table>
 </div>	
<div class="gtstyle">
		<div id="gt1">
			<span id="clock"></span>
			<span class="btnstyle">
				<button class="btn btn-primary btn-lg" id="inbtn">출근</button>
		        <button class="btn btn-primary btn-lg" id="outbtn">퇴근</button>
	        </span>
        </div>
</div>
<hr>
<script>
	
	var list1 = parseInt("${requestScope.chartlist1}");
	var list2 = parseInt("${requestScope.chartlist2}");
	var list3 = parseInt("${requestScope.chartlist3}");
	var list4 = parseInt("${requestScope.chartlist4}");
	var options = {
		'dataset':{
			title: '내 기안 문서',
			values:[list1,list2,list3,list4],
			colorset: ['#2EB400', '#2BC8C9', "#666666", '#f09a93'],
			fields: ['상신', '완료',  '취소', '진행'],
		},
		'donut_width' : 85,
		'core_circle_radius':0,
		'chartDiv': 'Nwagon',
		'chartType': 'pie',
		'chartSize': {width:400, height:200}
	};
	Nwagon.chart(options);
</script>
<script>
function functionrt(data, data1) {
	console.log(data);
	console.log(data1);
		location.href = "docread.do?doc_num=" + data1 + "&doc_kind=" + data;
	}

$(function() {
	$('.gtstyle button').click(function() {
		if ($(this).text() == '출근') {
			location.href = "${pageContext.request.contextPath}/gtapply.do?mode=in"
		}else if($(this).text() == '퇴근') {
			location.href = "${pageContext.request.contextPath}/gtapply.do?mode=out"
		}
		return false;
	});
});

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
        editable: false,
        // add event name to title attribute on mouseover
        eventMouseover: function(event, jsEvent, view) {
            if (view.name !== 'agendaDay') {
                $(jsEvent.target).attr('title', event.title);
            }
        },
        height: 325,
        googleCalendarApiKey : "AIzaSyDcnW6WejpTOCffshGDDb4neIrXVUA1EAE",
		eventSources : [ {
			googleCalendarId : "ko.south_korea#holiday@group.v.calendar.google.com",
			className : "koHolidays",
			color : "#FF0000",
			textColor : "#FFFFFF",
		} ],
        events:  
			function(start, end, timezone, callback) { /* 개인일정 눌렀을 때는 개인일정만 뜰 수 있도록 설정 */
			    $.ajax({
			      url: '${pageContext.request.contextPath}/schpersonal.do',
			      dataType: 'json',
			      data: {
			        start: start.unix(),
			        end: end.unix()
			      },
			      success: function(data) {
			        var events = [];
			        
			        
			        $.each(data.schedule, function(index,sc){
			        	contents = sc.contents;
		        		if (contents == "" ){/* contents 상세일정이 null 이면 없음으로 찍히도록 설정. */
		        			contents = "없음";
		        		}
		        		startmin = sc.startmin;
		        		endmin = sc.endmin;
		        		if(startmin == "" || endmin == ""){
		        			startmin = "00";
		        			endmin = "00";
		        		}
		        		
			        	if(sc.starthour == "00" && sc.endhour == "00"){ /* 종일 일정일 때는 시작시간과 종료시간이 "00" */
							events.push({
					            title: sc.title,
					            start: sc.startdate,
					            color : "#41a6f4",
					            description: "[일정상세] "+contents,
					            category : sc.empno,
					            type : sc.type,
					            contents : sc.contents,
					            schno : sc.schno
				         	 });
			        	}else if(sc.starthour == ""  && sc.endhour == "" ){
			        		events.push({
					            title: sc.title,
					            start: sc.startdate,
					            end: sc.enddate,
					            color : "#41a6f4",
					            description: "[일정상세] "+contents,
					            category : sc.empno,
					            type : sc.type,
					            contents : sc.contents,
					            schno : sc.schno
				         	 });												        		
			        	}else{
							events.push({
					            title: sc.title,
					            start: sc.startdate+'T'+sc.starthour+':'+startmin,
					            end:sc.enddate+'T'+sc.endhour+':'+endmin,
					            color : "#41a6f4",
					            description: "[일정상세] "+contents,
					            category : sc.empno,
					            type : sc.type,
					            starthour : sc.starthour,
					            startmin : startmin,
					            endhour : sc.endhour,
					            endmin : endmin,
					            contents : sc.contents,
					            schno : sc.schno
				         	 });
			        	}
					});
			        	callback(events);
			      }
			    });
     	   }
    });
    $('#calendarmini').fullCalendar({
        header: {
          left: 'today',
          center: 'title',
          right: 'listDay,listWeek,month'
        },

        views: {
          listDay: { buttonText: 'list day' },
          listWeek: { buttonText: 'list week' }
        },

        defaultView: 'listDay',
        height: 325,
        navLinks: true, 
        editable: false,
        eventLimit: true,
        googleCalendarApiKey : "AIzaSyDcnW6WejpTOCffshGDDb4neIrXVUA1EAE",
		eventSources : [ {
			googleCalendarId : "ko.south_korea#holiday@group.v.calendar.google.com",
			className : "koHolidays",
			color : "#FF0000",
			textColor : "#FFFFFF",
		} ],
        events:  
			function(start, end, timezone, callback) { /* 개인일정 눌렀을 때는 개인일정만 뜰 수 있도록 설정 */
			    $.ajax({
			      url: '${pageContext.request.contextPath}/schpersonal.do',
			      dataType: 'json',
			      data: {
			        start: start.unix(),
			        end: end.unix()
			      },
			      success: function(data) {
			        var events = [];
			        
			        $.each(data.schedule, function(index,sc){
			        	contents = sc.contents;
		        		if (contents == "" ){/* contents 상세일정이 null 이면 없음으로 찍히도록 설정. */
		        			contents = "없음";
		        		}
		        		startmin = sc.startmin;
		        		endmin = sc.endmin;
		        		if(startmin == "" || endmin == ""){
		        			startmin = "00";
		        			endmin = "00";
		        		}
		        		
			        	if(sc.starthour == "00" && sc.endhour == "00"){ /* 종일 일정일 때는 시작시간과 종료시간이 "00" */
							events.push({
					            title: sc.title,
					            start: sc.startdate,
					            color : "#41a6f4",
					            description: "[일정상세] "+contents,
					            category : sc.empno,
					            type : sc.type,
					            contents : sc.contents,
					            schno : sc.schno
				         	 });
			        	}else if(sc.starthour == ""  && sc.endhour == "" ){
			        		events.push({
					            title: sc.title,
					            start: sc.startdate,
					            end: sc.enddate,
					            color : "#41a6f4",
					            description: "[일정상세] "+contents,
					            category : sc.empno,
					            type : sc.type,
					            contents : sc.contents,
					            schno : sc.schno
				         	 });												        		
			        	}else{
							events.push({
					            title: sc.title,
					            start: sc.startdate+'T'+sc.starthour+':'+startmin,
					            end:sc.enddate+'T'+sc.endhour+':'+endmin,
					            color : "#41a6f4",
					            description: "[일정상세] "+contents,
					            category : sc.empno,
					            type : sc.type,
					            starthour : sc.starthour,
					            startmin : startmin,
					            endhour : sc.endhour,
					            endmin : endmin,
					            contents : sc.contents,
					            schno : sc.schno
				         	 });
			        	}
							
					});
			      	callback(events);
			      }
			    });
     	   }
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