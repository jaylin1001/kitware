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
<div>
	<form id="search">
		<label>검색분류</label><select id="cat" name="category">
			<option value="0" selected="selected">선택</option>
			<option value="1">사원번호</option>
			<option value="2">부서명</option>
			<option value="3">직급명</option>
			<option value="4">ID</option>
			<option value="5">이름</option>
		</select> <input style="display: none;" id="enumsearch" type="text"
			placeholder="사원번호" name="enumsearch"> <select id="depsearch"
			style="display: none;" name="deptsearch">
			<option value="0" selected="selected">부서명</option>
			<option value="1">개발부</option>
			<option value="2">기획부</option>
			<option value="3">인사부</option>
			<option value="4">영업부</option>
			<option value="5">사업부</option>
		</select> <select style="display: none;" id="grsearch" name="grsearch">
			<option value="0" selected="selected">직급명</option>
			<option value="1">사장</option>
			<option value="2">이사</option>
			<option value="3">부장</option>
			<option value="4">과장</option>
			<option value="5">대리</option>
			<option value="6">사원</option>
		</select> <input style="display: none;" id="idsearch" type="text"
			placeholder="id" name="idsearch"> <input
			style="display: none;" id="namesearch" type="text" placeholder="이름"
			name="namesearch">
		<button type="submit" class="btn btn-primary btn-sm" id="search">검색</button>
	</form>
</div>
<div class="panel panel-primary">
	<div class="panel-heading">사원정보</div>
	<table class="table table-hover">
		<thead>
			<tr>
				<th width="6%">사원번호</th>
				<th width="8%">부서명</th>
				<th width="9%">직급</th>
				<th width="10%">아이디</th>
				<th width="15%">이름</th>
				<th width="5%">성별</th>
				<th width="16%">이메일1</th>
				<th width="17%">전화번호</th>
				<th width="7%">상세정보</th>
				<th width="7%">정보수정</th>
			</tr>
		</thead>
		<c:set var="list" value="${pb.list}"></c:set>
		<tbody>
			<c:forEach items="${list}" var="b" varStatus="status">
				<c:if test="${status.index != 0}">
					<c:set var="firstPN" value="${firstPN -1}" />
				</c:if>
				<tr>
					<td class="emp_num"><a href="#" class="emp_num" id="emp_num">${b.emp_num}</a></td>
					<td class="dept_name">${b.dept_name}</td>
					<td class="position_name">${b.position_name}</td>
					<td class="id">${b.id}</td>
					<td class="name">${b.name}</td>
					<td class="seq">${b.gender}</td>
					<td class="email">${b.email1}</td>
					<td class="tel">${b.tel1}</td>
					<td class="modify"><button
							class="btn btn-primary btn-sm memberdetail">상세정보</button></td>
					<td class="modify"><button
							class="btn btn-primary btn-sm correctmember">정보수정</button></td>
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
		$('.memberdetail').click(function(){
			var emp_num = $(this).parents('td').siblings('.emp_num').children('a').html();	
			console.log(emp_num);
			$.ajax({				
				url:"${pageContext.request.contextPath}/memberdetail.do",
				method:"get",
				 data: "emp_num="+emp_num,
				success:function(data){							
					$('div.container').empty();
					$('div.container').html(data.trim());
				}
			})						
		})
		$('.correctmember').click(function(){
			var emp_num = $(this).parents('td').siblings('.emp_num').children('a').html();
			console.log(emp_num);
			$.ajax({
				url:"${pageContext.request.contextPath}/membermoddetail.do",
				method:"get",
				 data:"emp_num="+emp_num,
				success:function(data){
					$('div.container').empty();
					$('div.container').html(data.trim());
				}
			})					
		})
		$('#cat').change(function(){
			var catsel = $('#cat option:selected').val();
			switch(catsel){
			case '0' : $('#enumsearch').hide(); $('#depsearch').hide();$('#grsearch').hide();$('#idsearch').hide();
			$('#namesearch').hide();$('#gender').hide();$('#emailsearch').hide();$('#telsearch').hide();
			break;
			case '1' : $('#enumsearch').show(); $('#depsearch').hide();$('#grsearch').hide();$('#idsearch').hide();
			$('#namesearch').hide();$('#gender').hide();$('#emailsearch').hide();$('#telsearch').hide();
			break;
			case '2' : $('#enumsearch').hide(); $('#depsearch').show();$('#grsearch').hide();$('#idsearch').hide();
			$('#namesearch').hide();$('#gender').hide();$('#emailsearch').hide();$('#telsearch').hide();
			break;
			case '3' :$('#enumsearch').hide(); $('#depsearch').hide();$('#grsearch').show();$('#idsearch').hide();
			$('#namesearch').hide();$('#gender').hide();$('#emailsearch').hide();$('#telsearch').hide();
			break;
			case '4' :$('#enumsearch').hide();  $('#depsearch').hide();$('#grsearch').hide();$('#idsearch').show();
			$('#namesearch').hide();$('#gender').hide();$('#emailsearch').hide();$('#telsearch').hide();
			break;
			case '5' :$('#enumsearch').hide();  $('#depsearch').hide();$('#grsearch').hide();$('#idsearch').hide();
			$('#namesearch').show();$('#gender').hide();$('#emailsearch').hide();$('#telsearch').hide();
			break;		
			}			
		});
		
		$('#writeform').click(function() {			
			$.ajax({
				url:"${pageContext.request.contextPath}/addmember.do",
				method:"get",				
				success:function(data){
					location.href="${pageContext.request.contextPath}/addmember.do"
				}
			})				
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
					alert("첫페이지입니다");
				}
			}else if(selectPage == '»'){
				if(endPage == totalpage){  //총페이지와 끝페이지가 다르면 return
					return;
					alert("마지막페이지입니다");
				}else{
					page=endPage+1;
				}
			}else{
				page = selectPage;
			}
			$.ajax({
				url:"${pageContext.request.contextPath}/memberstatus.do",
				method: "get",
				data: "page="+page,
				success:function(data){				
					$('div.container').empty();
					$('div.container').html(data.trim());
				}
			});
			return false; 
		});
		 $('form#search').submit(function(){		
				var searchcat = $('#cat option:selected').val();
				var enumsearch = $('#enumsearch').val();
				var deptsearch = $('#depsearch option:selected').val();
				var grsearch = $('#grsearch option:selected').val();
				var idsearch = $('#idsearch').val();
				var namesearch = $('#namesearch').val();	
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
					 	alert("첫페이지입니다");
					}
				}else if(selectPage == '»'){
					if(endPage == totalpage){  //총페이지와 끝페이지가 다르면 return
						return;
						alert("마지막페이지입니다");
					}else{
						page=endPage+1;
					}
				}else{
					page = selectPage;
				}
				$.ajax({
					url:"${pageContext.request.contextPath}/memberstatus.do",
					method:"get",
					 data:{searchcat:searchcat,enumsearch:enumsearch,deptsearch:deptsearch,
					 grsearch:grsearch,idsearch:idsearch,namesearch:namesearch,page:page				 
					 },
					success:function(data){
						$('div.container').empty();
						$('div.container').html(data.trim());
					}
				})			 
			})
		
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
