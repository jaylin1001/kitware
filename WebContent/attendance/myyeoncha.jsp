<%@page import="com.kitware.guntae.vo.Yeoncha"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../container/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="yclist" value="${requestScope.yclist}" />
<c:set var="use" value="${requestScope.use }"/>
<%-- <fmt:parseNumber value="${use}" var="num"/> --%>

<%-- <%List<Yeoncha> list = (List)request.getAttribute("Yeoncha"); %>  --%>
<div>
	<div class="title" align="left">
		<span><i class="fas fa-plane"></i></span> <label>연차현황</label>
	</div>
	<br>
	<div class="ycbtn" align="center">
		<button class="btn btn-primary" id="prev"><</button>
		&nbsp;
		<h2 style="display: inline-block;" id="years">${param.years }</h2>
		&nbsp;
		<button class="btn btn-primary" id="next">></button>
	</div>
	&nbsp;
	<div class="table">
		<table class="table table-bordered">
			<tr>
				<th>총연차</th>
				<th>사용 연차</th>
				<th>잔여 연차</th>
			</tr>
			<tr>
				<td>15</td>
				<td>${use }</td>
				<td>${15-use }</td>
			</tr>
		</table>
		<table class="table table-bordered">
			<tr>
				<th>기간</th>
				<th>사용일수</th>
			</tr>
			<c:forEach var="yc" items="${yclist}">
				<tr>
					<td>${yc.start_date } ~ ${yc.end_date }</td>
					<td>${yc.use_yeoncha }</td>
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
div.title {
	font-size: 30px;
}

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
	$('div#menutab li.' + className).addClass('active');
	console.log($('div#menutab li.' + className));
	$('ul#side-menu').find('li.' + className).show();

	//<>버튼 클릭시 연도 바꾸기
	$(function() {
		$('.ycbtn button').click(function() {
			var years = $('#years').html();
			if ($(this).text() == '<') {
				years = years - 1;
			} else if ($(this).text() == '>') {
				years = years * 1 + 1;
			}
			location.href = "ycdist.do?years=" + years;
			return false;
		});
	});
</script>
<%@include file="../container/footer.jsp"%>