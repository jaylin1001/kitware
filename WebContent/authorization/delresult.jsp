<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% if(request.getAttribute("result").equals("1")){%>
	<!-- response.sendRedirect("/kitware_v1/doclist.do");-->
	<script> 
	/* window.alter('삭제되었습니다'); */
	 /* location.href = document.referrer; */ 
	  window.history.go(-2); 
	/* parent.document.location.reload(); */
	/* window.location.reload(true);  */
	/* window.location.reload(history.go(-2)); */ </script>
<%
}else{%>
-1	
<%}%>