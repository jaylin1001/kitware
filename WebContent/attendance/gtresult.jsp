<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kitware.guntae.vo.Gunte"%>
<%@page import="com.kitware.authorization.vo.DocGiganVO"%>     
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="listGunte" value="${requestScope.Gunte}"/>
<c:set var="chuljanglist" value="${requestScope.chuljanglist}"/>
<c:set var="yeonchlist" value="${requestScope.yeonchlist}"/>
<c:set var="bunggalist" value="${requestScope.bunggalist}"/>
<c:set var="jotaelist" value="${requestScope.jotaelist}"/>
<c:set var="rs" value="${requestScope.rs}"/>

{
<c:if test="${!empty listGunte}">
	"Gunte":[
	<c:forEach items="${listGunte}" var="item" varStatus="status">
	<c:if test="${status.index !=0}">,</c:if>
		{"emp_num":"${item.emp_num}",
		"in_day":"${item.in_day}",
		"in_time":"${item.in_time}",
		"out_time":"${item.out_time}",
		"doc_kind":"${item.doc_kind}"
		}
	</c:forEach>
	]
</c:if>

<c:if test="${!empty chuljanglist}">
	,"chuljanglist":[
	<c:forEach items="${chuljanglist}" var="item" varStatus="status">
	<c:if test="${status.index !=0}">,</c:if>
		{"start_date":"${item.start_date}",
		"end_date":"${item.end_date}"
		}
	</c:forEach>
	]
</c:if>

 <c:if test="${!empty yeonchlist}">
	,"yeonchlist":[
	<c:forEach items="${yeonchlist}" var="item" varStatus="status">
	<c:if test="${status.index !=0}">,</c:if>
		{"start_date":"${item.start_date}",
		"end_date":"${item.end_date}"
		}
	</c:forEach>
	]
</c:if>

<c:if test="${!empty bunggalist}">
	,"bunggalist":[
	<c:forEach items="${bunggalist}" var="item" varStatus="status">
	<c:if test="${status.index !=0}">,</c:if>
		{"start_date":"${item.start_date}",
		"end_date":"${item.end_date}"
		}
	</c:forEach>
	]
</c:if>

<c:if test="${!empty jotaelist}">
	,"jotaelist":[
	<c:forEach items="${jotaelist}" var="item" varStatus="status">
	<c:if test="${status.index !=0}">,</c:if>
		{"start_date":"${item.start_date}",
		"end_date":"${item.end_date}"
		}
	</c:forEach>
	]
</c:if>
}
<c:if test="${rs eq '1'||rs eq '-1'}">
<% response.sendRedirect("/kitware_v1/attendance/attendance.jsp"); %>
</c:if>
