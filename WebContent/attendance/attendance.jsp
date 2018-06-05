<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../container/header.jsp" %>
<%-- <c:set var="gt" value="${requestScope.Gunte}"/> --%>

<div id="gt1">
	<div class="title" align="left"><h2>근태현황</h2></div>
	<!-- <div align="right">
		<a href='basicsetting.jsp' class="btn btn-default">★</a>
	</div> -->
	<br>
	<div class="gtstyle">
		<div id="gt1">
			<span id="clock"></span>
			<span class="btnstyle">
				<button class="btn btn-primary btn-lg" id="inbtn">출근</button>
		        <button class="btn btn-primary btn-lg" id="outbtn">퇴근</button>
	        </span>
        </div>
        <div id="gt2">
        	<table>
        		<tr>
        			<th>abc</th>
        		</tr>
        		<tr>
        			<th>efg</th>
        		</tr>
        	</table>
        </div>
   	</div>
       <br><hr>
<div id='calendar'></div>
</div>
	
<script type="text/javascript">	
    jQuery(document).ready(function() {
    	//시계출력문
    	startTime();
    	
    	 $('#calendar').fullCalendar({
    		  theme: true,
    		  themeSystem:'bootstrap4',
    	      header: {
    	    	left : false,
    	        right: 'prev,next today',
    	        center: 'title'
    	      },
    	      //navLinks: true, // can click day/week names to navigate views
    	      editable: true,
    	      eventLimit: true, // allow "more" link when too many events
    	      events: function(start, end, timezone, callback) {

    	    	  	$.ajax({
				      url: '${pageContext.request.contextPath}/gtapply.do',
				      dataType: 'json',
				      data: {
				        start: start.unix(),
				        end: end.unix()
				      },
				      success: function(data) {
				        var events = [];
				        
				        
				        $.each(data.Gunte, function(index,gt){
				        	var lotime = gt.in_time;
				        	var arrLotime = lotime.split(':');
				        	console.log("parseInt(arrLotime[0]");
				        	console.log("parseInt(arrLotime[1]");
				        	if(parseInt(arrLotime[0]) <= 9 && 
				        			parseInt(arrLotime[1]) >=0 ){
				        		setColor = "#81B1FF"; //정상출근
				        		setTitle = "[출근] ";
				        	}else{
				        		setColor = "#E9573E"; //지각
				        		setTitle = "[지각] ";
				        	} 
				        		
				        	 events.push({
				                title: setTitle + gt.in_time,
				                start: gt.in_day,
				                color : setColor
				                  });
				             });
				        
				        $.each(data.Gunte, function(index,gt){
				        	 events.push({
				                title: "[퇴근] "+gt.out_time,
				                start: gt.in_day,
				                color : "#6371DD"
				                  });
				             });
				        
				        $.each(data.chuljanglist, function(index,dg){
				        	 events.push({
				                title: "[출장]",
				                start: dg.start_date,
				                end: dg.end_date,
				                color : "#5B3863" //회자주빛
				                  });
				             });
				        
				        $.each(data.yeonchlist, function(index,dg){
				        	 events.push({
				                title: "[연차]",
				                start: dg.start_date,
				                end: dg.end_date, 
				                color : "#FFC22C" //노란색
				                  });
				             });
				        
				        $.each(data.bunggalist, function(index,dg){
				        	 events.push({
				                title: "[병가]",
				                start: dg.start_date,
				                end: dg.end_date, 
				                color : "#EBB28A" //빛주황색
				                  });
				             });
				        
				        $.each(data.jotaelist, function(index,dg){
				        	 events.push({
				                title: "[조퇴]",
				                start: dg.start_date,
				               	end: dg.end_date, 
				                color : "#5FBE4B" //녹색
				                  });
				             });

				        callback(events);
				      },
				      error: function(){
				    	  alert("실패!");
				      }
    	    	  });

    	      	}
    	  });
  	});
    
    var className = 'attendance';
	$('div#menutab li.'+className).addClass('active');
	console.log($('div#menutab li.'+className));
	$('ul#side-menu').find('li.' + className).show();
	
	//실시간 시계
	function startTime() {
	    var today = new Date();
	    var h = today.getHours();
	    var m = today.getMinutes();
	    var s = today.getSeconds();
	    m = checkTime(m);
	    s = checkTime(s);
	    document.getElementById('clock').innerHTML =
	    h + ":" + m + ":" + s;
	    var t = setTimeout(startTime, 500);
	}
	function checkTime(i) {
	    if (i < 10) {i = "0" + i}; // 숫자가 10보다 작을 경우 앞에 0을 붙여줌
	    return i;
	}
	
	//출근버튼 퇴근버튼 클릭시 gtapplycontroller로
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
	
</script>
<style>
/* body {
	margin: 40px 10px;
	padding: 0;
	font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
	font-size: 14px;
} */


div.gtstyle {
	border: 2px solid #A6BED6;
	border-radius: 10px;
	padding: 20px;
}

div.gtstyle #gt1{
	float: left;
  	width: 50%;
}

div.btnstyle {
	
}

span#clock {
	border: 2px solid white;
	border-radius: 10px;
	padding: 20px;
	background-color: #C2E0FF;
}

#calendar {
	max-width: 900px;
	margin: 0 auto;
}
</style>
<%@include file="../container/footer.jsp" %>