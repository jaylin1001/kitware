<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<<jsp:include page="container/header.jsp"></jsp:include>
<style>
.active{
	display: block;
}
</style>
<script>
	$(function() {
		var className = '<%=request.getParameter("page")%>';
		if(className=='schedule'){
			location.href="schedule/schedulecalendar.jsp?list=개인일정";
		}else{
			location.href=className+"/"+className+".jsp";
		}
	});
</script>
<%@include file="container/footer.jsp" %>
