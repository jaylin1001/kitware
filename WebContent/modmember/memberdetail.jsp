<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../container/header.jsp" %>
<script>
	var className = 'modmember';
	$('div#menutab li.'+className).addClass('active');
	console.log($('div#menutab li.'+className));
	$('ul#side-menu').find('li.' + className).show();
		
$(function(){

});
</script>   
<h2>사원상세정보</h2>
<form action="modmember.jsp">
<button id="modmember">수정</button>
</form>
<%@include file="../container/footer.jsp" %>