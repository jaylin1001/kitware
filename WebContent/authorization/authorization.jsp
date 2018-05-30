<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- <jsp:include page="../container/header.jsp" flush="true"></jsp:include> --%>
<%@ include file="../container/header.jsp" %>
<form class="form-inline">
	<div class="center-block">
		<div class="title" align="center">
			<h2>기안서 작성</h2>
		</div>
		<div class="form-group">
			<table class="table table-bordered">
				<tr>
					<th>문서번호</th>
					<td>1</td>
					<th rowspan="2">결재</th>
					<td rowspan="2" style="text-align: center;">
						<div>승인자1</div>
						<button class="btn btn-success" style="background: gray;">선택</button>
						<!-- <input type="button" value="선택" style="display:block"> -->
					</td>
					<td rowspan="2" style="text-align: center;">
						<div>승인자2</div>
						<button class="btn btn-success" style="background: gray;">선택</button>
						<!-- <input type="button" value="선택" style="display:block"> -->
					</td>
					<td rowspan="2" style="text-align: center;">
						<div>승인자3</div>
						<button class="btn btn-success" style="background: gray;">선택</button>
						<!-- <input type="button" value="선택" style="display:block"> -->
					</td>
				</tr>
				<tr>
					<th>문서종류</th>
					<td>기안서</td>
				</tr>
				<tr>
					<th>기안일</th>
					<td class="date"><input class="form-control" type="text">년
						<input class="form-control" type="text">월 <input
						class="form-control" type="text">일</td>
					<th>수신부서</th>
					<td colspan="3"><input class="form-control" type="text">
						<button class="btn btn-success" style="background: gray;">수신부서지정</button>
						<!-- <input class="form-control" type="button" value="수신부서지정"> -->
					</td>
				</tr>
				<tr>
					<th>기안자</th>
					<td>사원 김지웅</td>
					<th>부서</th>
					<td colspan="3">개발부</td>
				</tr>
				<tr>
					<th>제목</th>
					<td colspan="5"><input type="text" class="form-control"
						placeholder="제목입력"></td>
				</tr>
				<tr>
					<td colspan="6"><textarea name="content" id="content"
							class="summernote" style="width: 100%"></textarea></td>
				</tr>
				<tr>
					<td colspan="6" align="center">
						<button class="btn btn-success" style="background: gray;">제출</button>
						<button class="btn btn-success" style="background: gray;">취소</button>
						<!-- <input type="button" value="제출">
					<input type="button" value="취소"> -->
					</td>
				</tr>
			</table>
		</div>
	</div>
</form>
<script>
	$('.summernote').summernote({
		height : 300, // 기본 높이값
		minHeight : null, // 최소 높이값(null은 제한 없음)
		maxHeight : null, // 최대 높이값(null은 제한 없음)
		focus : true, // 페이지가 열릴때 포커스를 지정함
		lang : 'ko-KR' // 한국어 지정(기본값은 en-US)
	});
	var className = 'authorization';
	$('div#menutab li.'+className).addClass('active');
	console.log($('div#menutab li.'+className));
	$('ul#side-menu').find('li.' + className).show();
</script>
<%@ include file="../container/footer.jsp" %>
<%-- <jsp:include page="../container/footer.jsp" flush="true"></jsp:include> --%>