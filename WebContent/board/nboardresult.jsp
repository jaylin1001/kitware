<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<c:set var="pb" value="${requestScope.pagebean}"/>
<%-- 페이징 설정을 위한 변수들 --%>
<c:set var="totalCount" value="${pb.totalCount}"/>
<c:set var="currentPage" value="${pb.currentPage}"/>
<c:set var="cntPerPage" value="${pb.cntPerPage}"/>
<c:set var="firstPN" value="${totalCount - (cntPerPage * (currentPage-1))}"/>
<c:set var="list" value="${pb.list}"></c:set>
<div class="panel panel-primary">
  	<div class="panel-heading">공지사항</div>
 	 <table class="table table-hover">
		<thead>
			<tr>
				<th width="7%">번호</th>
				<th width="50%">제목</th>
				<th width="13%">작성자</th>
				<th width="20%">작성일</th>
				<th width="10%">조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="b" varStatus="status">
			  <c:if test="${status.index != 0}">
			   <c:set var="firstPN" value="${firstPN -1}"/>
			  </c:if>
			  <tr>
				<td>${firstPN}</td>
				<td><a href="#" class="title">${b.title}</a></td>
				<td class="name">${b.name}</td>
				<td class="log_time">${b.log_time}</td>
				<td class="hit">${b.hit}</td>
				<td hidden="hidden" class="seq">${b.seq}</td>
				<td hidden="hidden" class="content">${b.content}</td>
			  </tr>
		   </c:forEach>
		</tbody>
	</table>
</div>
	<button class="btn btn-primary btn-sm" id="writeform">글쓰기</button>
	 
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
<script>
	$(function() {
		$('#writeform').click(function() {
			var $targetObj = $("div.container");
			$targetObj.empty();
			$("#div1").load("board/write.jsp");
		});
		
		
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
			}
			$.ajax({
				url:"${pageContext.request.contextPath}/boardlist.do",
				method: "get",
				data: "page="+page,
				success:function(data){
					$('div.container').empty();
					$('div.container').html(data.trim());
				}
			});
			return false;
		});
		
		$('.pagination a').each(function(index, element){
			if($(element).text() == '${pb.currentPage}'){
				$(element).addClass('active');
			}
		});
		
		$('tbody>tr a.title').click(function(path,method){
			console.log($(this).text());
			path = "${pageContext.request.contextPath}/board/content_conf.jsp";
			var $form = $("<form></form>");
			$form.attr("method", "post");
			$form.attr("action", path);
			
			
			var hiddenField = document.createElement("input");
			hiddenField.setAttribute("type", "hidden");
			hiddenField.setAttribute("name", "title");
			hiddenField.setAttribute("value", $(this).text());
			$form.append(hiddenField);
			
			
			var hiddenField = document.createElement("input");
			hiddenField.setAttribute("type", "hidden");
			hiddenField.setAttribute("name", "writer");
			hiddenField.setAttribute("value",$(this).parent().siblings().eq(1).text());
			$form.append(hiddenField);
			
			var hiddenField = document.createElement("input");
			hiddenField.setAttribute("type", "hidden");
			hiddenField.setAttribute("name", "log_time");
			hiddenField.setAttribute("value",$(this).parent().siblings().eq(2).text());
			$form.append(hiddenField);
			
			var hiddenField = document.createElement("input");
			hiddenField.setAttribute("type", "hidden");
			hiddenField.setAttribute("name", "hit");
			hiddenField.setAttribute("value",$(this).parent().siblings().eq(3).text());
			$form.append(hiddenField);
			

			var hiddenField = document.createElement("input");
			hiddenField.setAttribute("type", "hidden");
			hiddenField.setAttribute("name", "seq");
			hiddenField.setAttribute("value",$(this).parent().siblings().eq(4).text());
			$form.append(hiddenField);
			

			var hiddenField = document.createElement("input");
			hiddenField.setAttribute("type", "hidden");
			hiddenField.setAttribute("name", "content");
			hiddenField.setAttribute("value",$(this).parent().siblings().eq(5).html());
			$form.append(hiddenField);
			
			
			$(document.body).append($form);  <%--동적으로 만든 form을 document.body에 append--%>

			$form.submit();
			
			return false;
		});
		
		var className = 'board';
		$('div#menutab li.'+className).addClass('active');
		console.log($('div#menutab li.'+className));
		$('ul#side-menu').find('li.' + className).show();
	});
</script>	  