<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:forEach var="com" items="${requestScope.comlist }">
<table class="table table-borderd">
<tr><th>작성자</th><td class="cmname" id="${com.emp_num}">${com.name }</td><th>시간</th><td>${com.log_time }</td>
<td style="display:none;" class="del"><a href="#" id="${com.c_seq }" class="delbtn"><span class="glyphicon glyphicon-remove"></span></a></td></tr>
<tr><th>내용</th><td rowspan="2">${com.content }</td></tr>
</table>
</c:forEach>