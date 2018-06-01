<%@page import="com.kitware.member.vo.PageBean"%>
<%@page import="com.kitware.member.vo.StatusBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pb" value="${requestScope.pagebean}" />
<%-- 페이징 설정을 위한 변수들 --%>
<c:set var="totalCount" value="${pb.totalCount}" />
<c:set var="currentPage" value="${pb.currentPage}" />
<c:set var="cntPerPage" value="${pb.cntPerPage}" />
<c:set var="firstPN"
	value="${totalCount - (cntPerPage * (currentPage-1))}" />

<div class="panel panel-primary">
	<div class="panel-heading">사원정보
	<select>
	<option>
	<option>
	
	</select>
	<button class="btn btn-primary btn-sm" id="search">검색</button> 
	</div>
	<table class="table table-hover">
		<thead>
			<tr>
				<th width="10%">사원번호</th>
				<th width="10%">부서명</th>
				<th width="10%">직급</th>
				<th width="15%">아이디</th>
				<th width="10%">이름</th>
				<th width="10%">성별</th>
				<th width="15%">이메일1</th>
				<th width="10%">전화번호</th>
			</tr>
		</thead>
		<c:set var="list" value="${pb.list}"></c:set>
		<tbody>
			<c:forEach items="${list}" var="b" varStatus="status">
				<c:if test="${status.index != 0}">
					<c:set var="firstPN" value="${firstPN -1}" />
				</c:if>
				<tr>		
					<td><a href="#" class="emp_num">${b.emp_num}</a></td>
					<td class="dept_name">${b.dept_name}</td>
					<td class="position_name">${b.position_name}</td>
					<td class="id">${b.id}</td>
					<td class="name">${b.name}</td>
					<td class="seq">${b.gender}</td>
					<td class="email">${b.email1}</td>
					<td class="tel">${b.tel1}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<button class="btn btn-primary btn-sm" id="writeform">사원추가</button>

<c:set var="startPage" value="${pb.startPage}" />
<c:set var="endPage" value="${pb.endPage}" />
<ul class="pagination">
	<li class="page-item"><a class="page-link" href="#"
		aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
	</a></li>
	<c:forEach begin="${startPage}" end="${endPage}" var="i">
		<li class="page-item"><a class="page-link" href="#">${i}</a></li>
	</c:forEach>


	<li class="page-item"><a class="page-link" href="#"
		aria-label="Next"> <span aria-hidden="true">&raquo;</span>
	</a></li>
</ul>
<script>
	$(function() {		
		$('#writeform').click(function() {			
			location.href="addmember.jsp";			
		});		
		
		 $('.pagination a').click(function(){		
			var page;
			var selectPage = $(this).text().trim();		
			var startPage =  ${pb.startPage};
			var endPage = ${pb.endPage};
			var totalpage = ${pb.totalPage};
				
			if(selectPage == '«'){ //  시작페이지가 1인 경우 return
				if(startPage != '1'){
					page=startPage-1;
				}else{
					return;	
				}
			}else if(selectPage == '»'){
				if(endPage == totalpage){  //총페이지와 끝페이지가 다르면 return
					return;
				}else{
					page=endPage+1;
				}
			}else{
				page = selectPage;
			}
			$.ajax({
				url:"/memberstatus.do",
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
		
		var className = 'modmember';
		$('div#menutab li.'+className).addClass('active');
		console.log($('div#menutab li.'+className));
		$('ul#side-menu').find('li.' + className).show();
	});
</script>
