<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="loginInfo" value="${sessionScope.loginInfo}"></c:set>
<c:set var="error" value="${requestScope.result}"></c:set>
<c:choose>
	<c:when test="${!empty loginInfo}"><%-- 로그인 성공시 1 반환 --%>
	1
	</c:when>
	<c:when test="${error == '-1'}"><%-- 로그인 실패시 -1 반환 --%>
	-1
	</c:when>
	<c:otherwise>시스템상 오류가 발생했습니다.</c:otherwise><%-- 서버 오류 시 메시지 반환 --%>
</c:choose>