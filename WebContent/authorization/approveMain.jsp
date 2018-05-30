<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../container/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="list0" value="${requestScope.docvo_list0}" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<body onload="document.refresh();">
<div id="list"></div>
<div class="container">
	<p>
	<h2>전자결재</h2>
	<h4>결재대기문서</h4>
	<!-- 	수정 필요함 -->

	<table class="table table-border table-hover">
	<thead>
		<tr class="table-primary">
			<td>기안일</td>
			<td>문서제목</td>
			<td>문서번호</td>
			<td>문서상태</td>
			<td>문서 이름</td>
		</tr>
		</thead>
		<c:forEach var="doc0" items="${list0}">
		<tr>
				<td>${doc0.start_date}</td>
				<td><a href="javascript:functionrt(${doc0.doc_kind},'${doc0.doc_num}');">${doc0.doc_title}</a></td>
				<td>${doc0.doc_num}</td>
				<c:choose>
      			 <c:when test="${doc0.doc_state eq '1'}">
      			 <td>진행</td>
      			 </c:when>
      			 <c:when test="${doc0.doc_state eq '2'}">
      			 <td>완료<td>
      			 </c:when>
      			 </c:choose>
				<td>${doc0.doc_kindvo.doc_name}</td>
		</tr>
	</c:forEach>
	</table>


	<c:set var="list" value="${requestScope.docvo_list}" />
	<c:set var="result" value="${requestScope.result}" />
	<%-- ${requestScope.docvo_list}
	 ${result} --%>
	<h4>기안진행문서</h4>
	
	<table class="table table-border table-hover">
	<thead>
		<tr class="table-primary">
			<td>기안일</td>
			<td>문서제목</td>
			<td>문서번호</td>
			<td>문서상태</td>
			<td>문서 이름</td>
		</tr>
	</thead>
		<c:forEach var="doc" items="${list}">
			<tr>
				<td>${doc.start_date}</td>
				<td><a href="javascript:functionrt(${doc.doc_kind},'${doc.doc_num}');">${doc.doc_title}</a></td>
				<td>${doc.doc_num}</td>
				<c:choose>
      			 <c:when test="${doc.doc_state eq '1'}">
      			 <td>진행</td>
      			 </c:when>
      			 </c:choose>
				<td>${doc.doc_kindvo.doc_name}</td>
		</tr>
		</c:forEach>
	</table>


	<c:set var="list2" value="${requestScope.docvo_list2}" />
	<h4>완료 문서</h4>
	<table class="table table-border table-hover">
	<thead>
		<tr class="table-primary">
			<td>기안일</td>
			<td>문서제목</td>
			<td>문서번호</td>
			<td>문서상태</td>
			<td>문서 이름</td>
		</tr>
	</thead>
		<c:forEach var="doc2" items="${list2}">
			<tr>
				<td>${doc2.start_date}</td>
				<td><a href="javascript:functionrt(${doc2.doc_kind},'${doc2.doc_num}');">${doc2.doc_title}</a></td>
				<td>${doc2.doc_num}</td>
      			<td>완료</td>
				<td>${doc2.doc_kindvo.doc_name}</td>
			</tr>
		</c:forEach>
	</table>

</div>
</body>
<style>
.container {
	padding-right: 350px;
	padding-left: 15px;
	margin-right: auto;
	margin-left: auto;
}

body {
	width: 100%;
	margin: 0;
	padding: 0;
}

button {
	background-color: #337ab7; /* Green */
	border: none;
	color: white;
	padding: 8px 10px;
	border-radius: 5px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 4px 2px;
	cursor: pointer;
}
thead {
	background-color: #337ab7;
	color: white;
	font-weight: bold;
}

/* h4 {
	background-color: #337ab7; 
	color: white;
}*/
</style>
<script>
function functionrt(data, data1) {
	console.log(data);
	console.log(data1);
		location.href = "docread.do?doc_num=" + data1 + "&doc_kind=" + data;
	}

</script>
<script>
	var className = 'authorization';
	$('div#menutab li.' + className).addClass('active');
	$('ul#side-menu').find('li.' + className).show();
</script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<%@include file="../container/footer.jsp"%>