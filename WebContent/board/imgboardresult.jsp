<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../container/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
var className = 'board';
$('div#menutab li.'+className).addClass('active');
console.log($('div#menutab li.'+className));
$('ul#side-menu').find('li.' + className).show();	
</script>
<c:set var="pb" value="${requestScope.pagebean}"></c:set>
<%-- 페이징 설정을 위한 변수들 --%>
<c:set var="totalCount" value="${pb.totalCount}"/>
<c:set var="currentPage" value="${pb.currentPage}"/>
<c:set var="cntPerPage" value="${pb.cntPerPage}"/>
<c:set var="firstPN" value="${totalCount - (cntPerPage * (currentPage-1))}"/>
<c:set var="list" value="${pb.list}"></c:set>
<style>
.pagination a.active {
    background-color: #237abc;
    color: white;
}
h4.card-text:hover{
	text-decoration: underline;
}
h4.card-text{
	margin-top:auto;
	margin-bottom:auto;
	text-align: center;
	margin:auto;
	padding-top: 40px;
	color:#585858;
}
div.pagination{
	margin:auto;
} 
#writeform{
	 float:right;
	 clear:both;
}
.card {
 	margin:auto;
	border: 1px solid lightgray;
	box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
    transition: 0.3s;
    width: 603px;
    height: 500px;
    border-radius: 5px;
    margin-top:50px;
    margin-bottom: 30px;
}
.card:hover {
    box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
    
}
.card:hover .card-img-top{
  opacity: 0.6;
}
.card img{
	border-radius: 5px 5px 0 0;
}
</style>
<div class="imagecontainer">
<i class="far fa-images" style="font-size: 24px">&nbsp;사진게시판</i>
<br>
<button class="btn btn-primary" id="writeform">글쓰기</button>
<ul class="list_type_tile" id="prdList">
<c:forEach items="${list}" var="b" varStatus="status">
<c:if test="${status.index != 0}">
	<c:set var="firstPN" value="${firstPN -1}"/>
</c:if>
<div class="card">
  <img class="card-img-top" src="/upload/thumbnail/${b.saveFileName}" alt="Card image cap" >
  <div class="card-body">
    <h4 class="card-text">${b.title}</h4>
    <%--hidden으로 값 넘겨줄 부분--%>
    <input type="hidden" class="seq" value="${b.seq}">
	<textarea hidden="hidden" class="content">${b.content}</textarea>
	<input type="hidden" class="originFName" value="${b.originFileName}">
	<input type="hidden" class="path" value="${b.path}">
	<input type="hidden" class="saveFName" value="${b.saveFileName}">
	<input type="hidden" class="name" value="${b.name}">
	<input type="hidden" class="log_time" value="${b.log_time}">
	<input type="hidden" class="hit" value="${b.hit}">
  </div>
</div>

</c:forEach>
</ul>
	<c:set var="startPage" value="${pb.startPage}"/>
 	<c:set var="endPage" value="${pb.endPage}"/>
 		<div class="col-md-5">
 		</div> 
		  <ul class="pagination">
		    <li class="page-item">
		      <a class="page-link" href="" aria-label="Previous">
		        <span aria-hidden="true">&laquo;</span>
		      </a>
		    </li>
		    <c:forEach begin="${startPage}" end="${endPage}" var="i">  
			  <li class="page-item"><a class="page-link" href="#">${i}</a></li> 
			</c:forEach>
		    <li class="page-item">
		      <a class="page-link" href="" aria-label="Next">
		        <span aria-hidden="true">&raquo;</span>
		      </a>
		    </li>
		  </ul>
</div>
<script>

$(function() {
	<%--글 상세 보기--%>
	$('h4.card-text').click(function(){
		
		<%-- 조회수 증가 시키는 부분--%>
		<%-- 이미지 게시판 데이터를 보낼때는 flag 를 1값을 줄것이다.--%>
		$.ajax({
			url:'${pageContext.request.contextPath}/boardedit.do',
			data: {"hitseq":$(this).siblings().eq(0).val(),"flag":"1"},
			<%-- enctype:'multipart/form-data',
			processData: false,  파일 업로드시 필요하다.
	        contentType: false,   파일 업로드시 필요하다.
	        cache: false, --%>
			type: 'get',
			success:function(data){
			}
		});
		
		<%--글 상세보기--%>
		path = "${pageContext.request.contextPath}/board/content_conf.jsp";
		var $form = $("<form></form>");
		$form.attr("method", "post");
		$form.attr("action", path);
		
		<%-- 이미지 게시판은 flag 1을 추가적으로 전송.--%>
		var hiddenField = document.createElement("input");
		hiddenField.setAttribute("type","hidden");
		hiddenField.setAttribute("name","flag");
		hiddenField.setAttribute("value","1");
		$form.append(hiddenField);
		
		var hiddenField = document.createElement("input");
		hiddenField.setAttribute("type", "hidden");
		hiddenField.setAttribute("name", "title");
		hiddenField.setAttribute("value", $(this).text().trim());
		$form.append(hiddenField);
		
		
		var hiddenField = document.createElement("input");
		hiddenField.setAttribute("type", "hidden");
		hiddenField.setAttribute("name", "writer");
		hiddenField.setAttribute("value",$(this).siblings().eq(5).val().trim());
		$form.append(hiddenField);
		
		var hiddenField = document.createElement("input");
		hiddenField.setAttribute("type", "hidden");
		hiddenField.setAttribute("name", "log_time");
		hiddenField.setAttribute("value",$(this).siblings().eq(6).val().trim());
		$form.append(hiddenField);
		
		var hiddenField = document.createElement("input");
		hiddenField.setAttribute("type", "hidden");
		hiddenField.setAttribute("name", "hit");
		hiddenField.setAttribute("value",$(this).siblings().eq(7).val().trim());
		$form.append(hiddenField);
		

		var hiddenField = document.createElement("input");
		hiddenField.setAttribute("type", "hidden");
		hiddenField.setAttribute("name", "seq");
		hiddenField.setAttribute("value",$(this).siblings().eq(0).val().trim());
		$form.append(hiddenField);
		

		var hiddenField = document.createElement("input");
		hiddenField.setAttribute("type", "hidden");
		hiddenField.setAttribute("name", "content");
		hiddenField.setAttribute("value",$(this).siblings().eq(1).text().trim());
		$form.append(hiddenField);
		
		var hiddenField = document.createElement("input");
		hiddenField.setAttribute("type", "hidden");
		hiddenField.setAttribute("name", "originFName");
		hiddenField.setAttribute("value",$(this).siblings().eq(2).val().trim());
		$form.append(hiddenField);
		
		var hiddenField = document.createElement("input");
		hiddenField.setAttribute("type", "hidden");
		hiddenField.setAttribute("name", "path");
		hiddenField.setAttribute("value",$(this).siblings().eq(3).val().trim());
		$form.append(hiddenField);
		
		$(document.body).append($form);  //동적으로 만든 form을 document.body에 append

		$form.submit();
		
		return false;
	});
	<%--글쓰기--%>
	$('#writeform').click(function() {
		var $targetObj = $("div.imagecontainer");
		$targetObj.empty();
		$("div.imagecontainer").load("board/write.jsp?flag=1");
	});
	
	<%-- 페이징 처리--%>
	$('.pagination a').click(function(){
		var page;
		var selectPage = $(this).text().trim();
		if(selectPage == '«'){   <%-- 시작페이지가 1인 경우 return--%>
			if(${pb.startPage} != '1'){
				page=${pb.startPage}-1;
			}else{
				return;	
			}
		}else if(selectPage == '»'){
			if(${pb.endPage} == ${pb.totalPage}){  <%--총페이지와 끝페이지가 다르면 return--%>
				return;
			}else{
				page=${pb.endPage}+1;
			}
		}else{
			page = selectPage;
			console.log(page);
		}
		$.ajax({
			url:"${pageContext.request.contextPath}/imgboardlist.do",
			method: "get",
			data: "page="+page,
			dataType :"html",
			success:function(data){
				$('body').empty();
				$('body').html(data.trim()); 
			}
		});
		return false;
	});
	
	$('.pagination a').each(function(index, element){
		if($(element).text().trim() == '${pb.currentPage}'){
			$(element).addClass('active');
		}
	});
});	
</script>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<%@include file="../container/footer.jsp"%>