<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="container/header.jsp"></jsp:include>
<style>
.active{
	display: block;
}
</style>
<script>
	$(function() {
		var className = '<%=request.getParameter("page")%>';
		
		if(className=='schedule'){
			location.href="${pageContext.request.contextPath}/schedule/schedulecalendar.jsp?list=개인일정";
		}
		else if(className=='authorization'){
			location.href="${pageContext.request.contextPath}/doclist.do";
		}
		else if(className=='attendance'){
			location.href="${pageContext.request.contextPath}/attendance/attendance.jsp";
		}
		else if(className=='board'){
			location.href="${pageContext.request.contextPath}/boardlist.do?mode=notice";
		}
		else if(className=='home'){
			location.href="${pageContext.request.contextPath}/mainview.do";
		}
		else if(className=='modmember'){
			location.href="${pageContext.request.contextPath}/memberstatus.do";
		}else{
			 location.href=className+"/"+className+".jsp"; 
		}
	});
</script>
<%@include file="container/footer.jsp" %>
