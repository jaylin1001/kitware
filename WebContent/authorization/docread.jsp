<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- <jsp:include page="../container/header.jsp" flush="true"></jsp:include> --%>
<%@ include file="../container/header.jsp" %>
<form class="form-inline">
	<div class="center-block">
		<div class="title" align="center">
			<h2>기안서</h2>
		</div>
		<div class="form-group">
			<table class="table table-bordered">
				<tr>
					<th>문서번호</th>
					<td>1</td><!-- 값 받아올거임 -->
					<th rowspan="2">결재</th>
					<td rowspan="2" style="text-align: center;">
						<div>승인자1</div><!-- 값 받아올거임 -->
						<div></div>
					</td>
					<td rowspan="2" style="text-align: center;">
						<div>승인자2</div><!-- 값 받아올거임 -->
						<div></div>
					</td>
					<td rowspan="2" style="text-align: center;">
						<div>승인자3</div><!-- 값 받아올거임 -->
						<div></div>
					</td>
				</tr>
				<tr>
					<th>문서종류</th>
					<td>기안서</td><!-- 값 받아올거임 -->
				</tr>
				<tr>
					<th>기안일</th>
					<td class="date"><input class="form-control" type="text">년<!-- 값 받아올거임 -->
						<input class="form-control" type="text">월 <input
						class="form-control" type="text">일</td>
					<th>수신부서</th>
					<td colspan="3">
						수신부서<!-- 값 받아올거임 -->
					</td>
				</tr>
				<tr>
					<th>기안자</th>
					<td>사원 김지웅</td><!-- 값 받아올거임 -->
					<th>부서</th>
					<td colspan="3">개발부</td><!-- 값 받아올거임 -->
				</tr>
				<tr>
					<th>제목</th>
					<td colspan="5"></td><!-- 값 받아올거임 -->
				</tr>
				<tr>
					<td colspan="6">
							<%=request.getParameter("content") %>
							</td><!-- 값 받아올거임 -->
				</tr>
				<tr>
					<td colspan="6" align="center">
						<button class="btn btn-success" style="background: gray;">승인</button>
						<button class="btn btn-success" style="background: gray;">반려</button>
						<!-- <input type="button" value="제출">
					<input type="button" value="취소"> -->
					</td>
				</tr>
			</table>
		</div>
	</div>
</form>
<script>
	var className = 'authorization';
	$('div#menutab li.'+className).addClass('active');
	console.log($('div#menutab li.'+className));
	$('ul#side-menu').find('li.' + className).show();
</script>
<%@ include file="../container/footer.jsp" %>
<%-- <jsp:include page="../container/footer.jsp" flush="true"></jsp:include> --%>