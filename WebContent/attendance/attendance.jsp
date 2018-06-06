<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../container/header.jsp"%>
<%-- <c:set var="gt" value="${requestScope.Gunte}"/> --%>

<div id="gt1">
	<div class="title" align="left">
		<span><i class="fa fa-clock-o"></i></span> <label>근태현황</label>
	</div>
	<!-- <div align="right">
		<a href='basicsetting.jsp' class="btn btn-default">★</a>
	</div> -->
	<br> &nbsp;
	<div class="gtstyle">
		<div class="status"></div>
		<div id="gt1">
			<span id="cstyle"> 
				<i class="far fa-clock"></i><span id="clock"></span>
			</span>&nbsp;
			<span class="btnstyle">
				<button class="btn btn-primary btn-lg" id="inbtn">출근</button>
				<button class="btn btn-primary btn-lg" id="outbtn">퇴근</button>
			</span>
		</div>
	</div>
	<br>
	<hr>
	<div id='calendar'></div>
</div>

<script type="text/javascript">
	jQuery(document).ready(function() {
		var chuljang1 = $('#chuljang1').text();
		var yeoncha1 = $('#yeoncha1').text();
		var byunga1 = $('#byunga1').text();
		var jotae1 = $('#jotae1').text();

		//시계출력문
		startTime();
		$('#calendar').fullCalendar({
			theme : true,
			themeSystem : 'bootstrap4',
			header : {
				left : false,
				right : 'prev,next today',
				center : 'title'
			},
			//navLinks: true, // can click day/week names to navigate views
			editable : true,
			eventLimit : true, // allow "more" link when too many events
			events : function(start, end, timezone, callback) {

				$.ajax({
					url : '${pageContext.request.contextPath}/gtapply.do',
					dataType : 'json',
					data : {
						start : start.unix(),
						end : end.unix()
					},
					success : function(data) {
						var events = [];


						$.each(data.Gunte, function(index, gt) {
							var lotime = gt.in_time;
							var arrLotime = lotime.split(':');
							console.log("parseInt(arrLotime[0]");
							console.log("parseInt(arrLotime[1]");
							if (parseInt(arrLotime[0]) <= 9 &&
								parseInt(arrLotime[1]) >= 0) {
								setColor = "#81B1FF"; //정상출근
								setTitle = "[출근] ";
							} else {
								setColor = "#E9573E"; //지각
								setTitle = "[지각] ";
							}

							events.push({
								title : setTitle + gt.in_time,
								start : gt.in_day,
								color : setColor
							});
						});

						$.each(data.Gunte, function(index, gt) {
							events.push({
								title : "[퇴근] " + gt.out_time,
								start : gt.in_day,
								color : "#6371DD"
							});
						});

						$.each(data.chuljanglist, function(index, dg) {
							events.push({
								title : "[출장]",
								start : dg.start_date,
								end : dg.end_date,
								color : "#5B3863" //회자주빛
							});
						});

						$.each(data.yeonchlist, function(index, dg) {
							events.push({
								title : "[연차]",
								start : dg.start_date,
								end : dg.end_date,
								color : "#FFC22C" //노란색
							});
						});

						$.each(data.bunggalist, function(index, dg) {
							events.push({
								title : "[병가]",
								start : dg.start_date,
								end : dg.end_date,
								color : "#EBB28A" //빛주황색
							});
						});

						$.each(data.jotaelist, function(index, dg) {
							events.push({
								title : "[조퇴]",
								start : dg.start_date,
								end : dg.end_date,
								color : "#5FBE4B" //녹색
							});
						});
						callback(events);
						
						
				        
					},
					error : function() {
						alert("실패!");
					}
				});
				var date = new Date(jQuery("#calendar").fullCalendar("getDate"));
				var yyyy = date.getFullYear().toString();
		        var mm = (date.getMonth()+1).toString();
				$.ajax({
					url:'${pageContext.request.contextPath}/gtapply.do',
					method:'POST',
					data:{
						type:"cnt",
						years:yyyy,
						months:mm
					},
					success:function(data){
						$('.status').empty();
						$('.status').html(data);
					}
				});
				 

			}
		});
	});

	var className = 'attendance';
	$('div#menutab li.' + className).addClass('active');
	console.log($('div#menutab li.' + className));
	$('ul#side-menu').find('li.' + className).show();

	//실시간 시계
	function startTime() {
		var today = new Date();
		var h = today.getHours();
		var m = today.getMinutes();
		var s = today.getSeconds();
		m = checkTime(m);
		s = checkTime(s);
		document.getElementById('clock').innerHTML = h + ":" + m + ":" + s;
		var t = setTimeout(startTime, 500); //0.5초
	}
	function checkTime(i) {
		if (i < 10) {
			i = "0" + i
		}
		; // 숫자가 10보다 작을 경우 앞에 0을 붙여줌
		return i;
	}

	//출근버튼 퇴근버튼 클릭시 gtapplycontroller로
	$(function() {
		$('.gtstyle button').click(function() {
			if ($(this).text() == '출근') {
				location.href = "${pageContext.request.contextPath}/gtapply.do?mode=in"
				alert("출근하셨습니다.");
			} else if ($(this).text() == '퇴근') {
				location.href = "${pageContext.request.contextPath}/gtapply.do?mode=out"
				alert("퇴근하셨습니다.");
			}
			return false;
		});
	});
</script>
<style>
/* body {
	margin: 40px 10px;
	padding: 0;
	font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
	font-size: 14px;
} */
div.title {
	font-size: 30px;
}

div.gtstyle {
	border: 2px solid #A6BED6;
	border-radius: 10px;
	padding: 20px;
	width:100%;
	height:105px;
}

div.status {
	float: right;
	width: 65%;
}

div.gtstyle #gt1 {
	float: left;
	width: 35%;
}

button{
	width: 88px;
	height: 55px;
	font-size: 100%;
}

span#cstyle {
	border: 2px solid #C2E0FF;
	border-radius: 10px;
	padding: 20px;
}

#calendar {
	margin: 0 auto;
}
</style>
<%@include file="../container/footer.jsp"%>