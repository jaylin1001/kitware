<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../container/header.jsp" %>
<div>
   	<div class="title" align="left"><h2>내 연차 현황</h2></div>
	<br>
	<div align="center"><h2>2018</h2></div>
   	<div class="table">
      <table class="table table-bordered">
      	<tr>
			<th>총연차</th>
			<th>사용 연차</th>
			<th>잔여 연차</th>
		</tr>
		<tr>
			<td>15</td>
			<td>0</td>
			<td>0</td>
		</tr>
     </table>
     <table class="table table-bordered">
     	<tr>
     		<th>기간</th>
     		<th>사용일수</th>
     		<th>분류</th>
     	</tr>
     	<tr>
     		<td colspan="3">사용한 연차가 없습니다.</td>
     	</tr>
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
</script>
<%@include file="../container/footer.jsp" %>