<%@page import="java.util.List"%>
<%@page import="com.kitware.schedule.vo.Schedule"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<%-- el , jstl 문법 변경--%>    
<c:set var="listSchedule" value="${requestScope.schedule}"/>
{
<c:if test="${!empty listSchedule}">
	"schedule":[
	<c:forEach items="${listSchedule}" var="item" varStatus="status">
	<c:if test="${status.index !=0}">,</c:if>
	{"schno":"${item.sch_no}",
	 "empno":"${item.emp_num}",
	 "title":"${item.sch_name}",
	 "type":"${item.sch_type}",
	 "startdate":"${item.sch_startdate}",
	 "starthour":"${item.sch_starthour}",
	 "startmin":"${item.sch_startmin}",
	 "enddate":"${item.sch_enddate}",
	 "endhour":"${item.sch_endhour}",
	 "endmin":"${item.sch_endmin}",
	 "contents":"${item.sch_contents}",
	 "code":"${item.sch_code}"
	}
	</c:forEach>
	]
</c:if>
}


<%-- jsp 스크립틀릿 , 익스프레션 문법

{
<%List<Schedule> listSchedule = (List)request.getAttribute("schedule");
if(listSchedule != null){
%>"schedule":[
<%
int i=0;
for(Schedule sc : listSchedule){
%><%=i==0?"":","%>
{"title":"<%=sc.getSch_name()%>",
 "type":"<%=sc.getSch_type() %>",
 "startdate":"<%=sc.getSch_startdate()%>",
 "starthour":"<%=sc.getSch_starthour() %>",
 "startmin":"<%=sc.getSch_startmin() %>",
 "enddate":"<%=sc.getSch_enddate() %>",
 "endhour":"<%=sc.getSch_endhour()%>",
 "endmin":"<%=sc.getSch_endmin()%>",
 "contents":"<%=sc.getSch_contents() %>",
 "code":"<%=sc.getSch_code() %>"
}
<%
i++;
}//end for
%>
]
<%
}//end if
%>
}
 --%>