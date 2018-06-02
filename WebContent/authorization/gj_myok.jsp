<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../container/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pb" value="${requestScope.pagebean }" />
<c:set var="totalCount" value="${pb.totalCount}"/>
<c:set var="currentPage" value="${pb.currentPage}"/>
<c:set var="cntPerPage" value="${pb.cntPerPage}"/>
<c:set var="firstPN" value="${totalCount - (cntPerPage * (currentPage-1))}"/>
<c:set var="list" value="${pb.list}" />

<div id="div1"></div>
<div class="container">
	<div>&nbsp;</div>
	<h2>내가 결재한 문서함</h2>
	${totalCount}
	<div>&nbsp;</div>
	<div class="selectbutton">
		&nbsp;
		<button class='all'>전체</button>
		<button class='prog'>진행</button>
		<button class='comp'>완료</button>
		<button class='cancle'>취소</button>
	</div>
	<table class="table table-striped table-hover">
		<thead class="thead-light">
			<tr class="table-primary">
				<td>문서번호</td>
				<td>문서제목</td>
				<td>문서상태</td>
				<td>기안일</td>
				<td>문서이름</td>
			</tr>
		</thead>
		<c:forEach var="b" items="${list}">
			<%-- <c:forEach begin="${1}" end="${totalCount}" var="j" > --%>
			<%-- <c:forEach begin="1" end="${b.level}">▷</c:forEach> --%>
			<tr class="doc_list_content">
				<td>${b.doc_num}</td>
				<td>
					<!--  onclick="docContent()" --> <%-- <a href="docreadcj.do?doc_num=${b.doc_num}">${b.doc_title}</a></td> --%>
					<a href="javascript:functionrt(${b.doc_kind},'${b.doc_num}');"<%-- onclick="functionrt(${b.doc_kind},${b.doc_num});" --%>
					>${b.doc_title}</a>
				</td>
				<td>${b.doc_state}</td>
				<td>${b.start_date}</td>
				<td>${b.doc_kindvo.doc_name}</td>
			</tr>
		</c:forEach>
	</table>
	<c:set var="startPage" value="${pb.startPage}"/>
 	<c:set var="endPage" value="${pb.endPage}"/>
	  <ul class="pagination">
	    <li class="page-item">
	      <a class="page-link" href="#" aria-label="Previous">
	        <span aria-hidden="true">&laquo;</span>
	      </a>
	    </li>
	    <c:forEach begin="${startPage}" end="${endPage}" var="i" >  
		  <li class="page-item"><a class="page-link" href="#">${i}</a></li> 
		</c:forEach> 
			    
	    
	    <li class="page-item">
	      <a class="page-link" href="#" aria-label="Next">
	        <span aria-hidden="true">&raquo;</span>
	      </a>
	    </li>
	  </ul>

</div>
<style>
.container {
	padding-right: 350px;
}

p {
	padding-top: 50px;
	padding-right: 30px;
	padding-bottom: 50px;
	padding-left: 80px;
}

body {
	width: 100%;
	margin: 0;
	padding: 0;
}

pd {
	padding-right: 200px;
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
</style>
<%-- <c:set var="prePage" value="${requestScope.prePage}" />
<c:set var="nextPage" value="${requestScope.nextPage}" /> --%>
<script>
function functionrt(data, data1) {
	console.log(data);
	console.log(data1);
		location.href = "docread.do?doc_num=" + data1 + "&doc_kind=" + data;
	}
	
	$(function() {
		$('.selectbutton button').click(function() {
			var page;
			if ($(this).text() == '진행') {
				location.href = "mygjoklist.do?mode=ing&page=1"
			} else if ($(this).text() == '완료') {
				location.href = "mygjoklist.do?mode=ok&page=1"
			} else if ($(this).text() == '취소') {
				location.href = "mygjoklist.do?mode=cancel&page=1";
			} else {
				location.href = "mygjoklist.do?mode=all&page=1"
			}
			return false;
		});
	
		
		$('.pagination a').click(function(){
			var page;
			var mode = '${requestScope.mode}';
			var selectPage = $(this).text().trim();
			if(selectPage == '«'){   <%-- 시작페이지가 1인 경우 return--%>
				if(${pb.startPage} != '1'){
					page=${pb.startPage}-1;
					location.href = "mygjoklist.do?page=" + page + "&mode=" + mode;
				}else{
					return;	
				}
			}else if(selectPage == '»'){
				if(${pb.endPage} != ${pb.totalPage}){  <%--총페이지와 끝페이지가 다르면 return--%>
					return;
				}else{
					page=${pb.endPage}+1;
					location.href = "mygjoklist.do?page=" + page + "&mode=" + mode;
				}
			}else{
				page = selectPage;
				location.href = "mygjoklist.do?page=" + page + "&mode=" + mode;
			}
			
		});
		
		$('.pagination a').each(function(index, element) {
			if ($(element).text() == '${pb.currentPage}') {
				$(element).addClass('active');
			}
		});

		var className = 'authorization';
		$('div#menutab li.' + className).addClass('active');
		console.log($('div#menutab li.' + className));
		$('ul#side-menu').find('li.' + className).show();
	});
</script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<%@include file="../container/footer.jsp"%>