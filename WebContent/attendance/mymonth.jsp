<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../container/header.jsp" %>
	<div id="gt1">
		<div class="title" align="left"><h2>근태현황</h2></div>
		<!-- <input type="button" value="월간" onclick="location.href='mymonth.html'">
		<input type="button" value="연간" onclick="location.href='myyear.html'"> -->
		<!-- <a href='mymonth.html' class="btn btn-success" id='mtbtn'>월간</a>
		<a href='myyear.html' class="btn btn-success" id='yrbtn'>연간</a> -->
		<button>출근</button>
        <button>퇴근</button>
        <br><hr>
	<div id='calendar'></div>
	</div>
	
<script type="text/javascript">	
    jQuery(document).ready(function() {
    	
    	 $('#calendar').fullCalendar({
    		  theme: true,
    		  themeSystem:'bootstrap4',
    	      header: {
    	    	left : false,
    	        right: 'prev,next today',
    	        center: 'title'
    	      },
    	      defaultDate: '2018-05-01',
    	      //navLinks: true, // can click day/week names to navigate views
    	      editable: true,
    	      eventLimit: true // allow "more" link when too many events
    	    });
  	});
    
    $(function(){
    	$('#mtbtn').click(function(){
    		var $divObj = $('#gt1');
    		$divObj.empty();
    		$("#gt1").load("mymonth.html");	
    	});
    }); 
    
    $(function(){
    	$('#yebtn').click(function(){
    		var $divObj = $('#gt1');
    		$divObj.empty();
    		$("#gt1").load("myyear.html");		
    	});
    });
    var className = 'attendance';
	$('div#menutab li.'+className).addClass('active');
	console.log($('div#menutab li.'+className));
	$('ul#side-menu').find('li.' + className).show();
</script>
<style>
/* body {
	margin: 40px 10px;
	padding: 0;
	font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
	font-size: 14px;
} */

#calendar {
	max-width: 900px;
	margin: 0 auto;
}
</style>
<%@include file="../container/footer.jsp" %>