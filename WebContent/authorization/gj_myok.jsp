<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../container/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pb" value="${requestScope.pagebean }"/> 
<div id="div1"></div>
<div class="container">
	<div>&nbsp;</div>
	<h2>내가 한 결재 완료</h2>
	<div>&nbsp;</div>
	<table class="table table-striped table-hover">
	<thead class = "thead-light">
			<tr class="table-primary">
				<td>문서번호</td>
				<td>문서제목</td>
				<td>문서상태</td>
				<td>기안일</td>
				<td>문서이름</td>
			</tr>
	</thead>
			<c:set var="list" value="${pb.list}"/>
			<c:forEach var="b" items="${list}" >
			<%-- <c:forEach begin="${1}" end="${totalCount}" var="j" > --%>
			<%-- <c:forEach begin="1" end="${b.level}">▷</c:forEach> --%>
		<tr class = "doc_list_content">					
								<td>${b.doc_num}</td>
								<td><!--  onclick="docContent()" -->
								<a href="docreadcj.do?doc_num=${b.doc_num}">${b.doc_title}</a></td>
								<td>${b.doc_state}</td>
								<td>${b.start_date}</td>
							    <td>${b.doc_kindvo.doc_name}</td>
		</tr>
			  </c:forEach>
	</table>
	
		
	
<div class="pagination">
 <c:set var="startPage" value="${pb.startPage}"/>
 <c:set var="endPage" value="${pb.endPage}"/>
 <c:if test="${startPage > 1}">  
  <Button>이전</Button>
 </c:if>
 <c:forEach begin="${startPage}" end="${endPage}" var="i" >  
  <button type="button">${i}</button>	  
 </c:forEach> 
 <c:if test="${endPage < pb.totalPage}" >  
  <Button>다음</Button> 
  </c:if>
</div> 
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
thead{
	background-color: #337ab7;
	color: white;
	font-weight: bold;
}
</style>
<c:set var="prePage" value="${requestScope.prePage}"/> 
<c:set var="nextPage" value="${requestScope.nextPage}"/> 
<script>

function docContent() {
	location.href="docreadcj.do"
	console.log('asdfasdfadf');
	}
	
$(function(){	
		$('.pagination button').click(function(){
			var page;
			if($(this).text() == '이전'){
				page=${prePage};
				location.href="mygjoklist.do?page="+page;
			}else if($(this).text() == '다음'){
				page=${nextPage};
				location.href="mygjoklist.do?page="+page;
			}else{
				page = $(this).text();
				location.href="mygjoklist.do?page="+page;
			}
			
			return false;
		});
		
		/* $('.doc_list_content').click(function(){
			var doc_num = $('.doc_list_content a').text();
				location.href="mygjoklist.do?doc_num="+doc_num;
		}); */
		//문서번호도 보내야함
		
		$('.pagination a').each(function(index, element){
		if($(element).text() == '${pb.currentPage}'){
			$(element).addClass('active');
		}
		});    
		
		
		
	 var className = 'authorization';
		$('div#menutab li.'+className).addClass('active');
		console.log($('div#menutab li.'+className));
		$('ul#side-menu').find('li.' + className).show();
});
</script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<%@include file="../container/footer.jsp"%>