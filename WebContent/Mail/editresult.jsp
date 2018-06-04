<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% if(request.getAttribute("result").equals("1")){
	response.sendRedirect("/kitware_v1/maillist.do");
}else{%>
-1
<%}%>