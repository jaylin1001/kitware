<%@page import="com.kitware.guntae.vo.Yeoncha"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../container/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="yc" value="${requestScope.Yeoncha}"/>
<c:set var="yg" value="${requestScope.Yeoncha_gigan}"/>
<% List<List<String>> yg = (List)request.getAttribute("Yeoncha_gigan"); 
	String years = (String) request.getParameter("years");
%>

<%-- <%List<Yeoncha> list = (List)request.getAttribute("Yeoncha"); %>  --%>
<div>
   	<div class="title" align="left"><h2>내 연차 현황</h2></div>
	<br>
	<div class="ycbtn" align="center">
		<button class="btn btn-primary" id="prev"><</button>
		<h2 style="display:inline-block;" id="years"><%=years %></h2>
		<button class="btn btn-primary" id="next">></button>
	</div>
   	<div class="table">
      <table class="table table-bordered">
      	<tr>
			<th>총연차</th>
			<th>사용 연차</th>
			<th>잔여 연차</th>
		</tr>
		<tr>
			<td>${yc.all_yeoncha}</td>
			<td>${yc.use_yeoncha}</td>
			<td>${yc.all_yeoncha-yc.use_yeoncha}</td>
		</tr>
     </table>	
     <table class="table table-bordered">
     	<tr>
     		<th>기간</th>
     		<th>사용일수</th>
     	</tr>
     	<c:forEach var="ggg" items="${yg}">
     	<tr>
     		<!-- <td colspan="3">사용한 연차가 없습니다.</td> -->
     		<%-- <td><%=yg.get(0).get(0) %> ~ <%=yg.get(0).get(1) %></td>
     		<td> <%=yg.get(0).get(2) %></td> --%>
     		<td>${ggg[0] } ~ ${ggg[1] }</td>
     		<td>${ggg[2] }</td>
     	</tr>
     	</c:forEach>
     </table>
   </div>
</div>
<style>
/* body {
	margin: 40px 10px;
	padding: 0;
	font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
	font-size: 14px;
} */
th {
	text-align: center;
}
table {
	text-align: center;
	table-layout: fixed;
}
</style>
<script>
	var className = 'attendance';
	$('div#menutab li.'+className).addClass('active');
	console.log($('div#menutab li.'+className));
	$('ul#side-menu').find('li.' + className).show();
	
	//<>버튼 클릭시 연도 바꾸기
	$(function() {
		$('.ycbtn button').click(function() {
			var years = $('#years').html();
			if ($(this).text() == '<') {
				years = years-1;
			}else if($(this).text() == '>') {
				years = years*1+1;
			}
			location.href = "ycdist.do?years="+years;
			return false;
		});
	});
</script>
<%@include file="../container/footer.jsp" %>