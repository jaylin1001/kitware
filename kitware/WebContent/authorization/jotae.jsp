<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../container/header.jsp"%>
<div>
	<div class="title" align="center">
		<h2>조퇴계 작성</h2>
	</div>
	<div class="table">
		<table class="table table-bordered">
			<tr>
				<th>문서번호</th>
				<td>1</td>
				<th rowspan="2">결재</th>
				<td rowspan="2">
					<div>승인자1</div> <input type="button" value="선택"
					style="display: block">
				</td>
				<td rowspan="2">
					<div>승인자2</div> <input type="button" value="선택"
					style="display: block">
				</td>
				<td rowspan="2">
					<div>승인자3</div> <input type="button" value="선택"
					style="display: block">
				</td>
			</tr>
			<tr>
				<th>문서종류</th>
				<td>조퇴계</td>
			</tr>
			<tr>
				<th>기안일</th>
				<td><input type="text">년 <input type="text">월 <input
					type="text">일</td>
				<th>수신부서</th>
				<td colspan="3"><input type="text"> <input
					type="button" value="수신부서지정"></td>
			</tr>
			<tr>
				<th>기안자</th>
				<td>사원 김지웅</td>
				<th>부서</th>
				<td colspan="3">개발부</td>
			</tr>
			<tr>
				<th>참조자</th>
				<td colspan="5"><input type="text">
					<button>참조자 지정</button></td>
			</tr>
			<tr>
				<th>제목</th>
				<td colspan="5"><input type="text" name="doc_title"
					style="width: 700px"></td>
			</tr>
			<tr>
				<th>기간</th>
				<td><input type="text" id="testDatepicker" value="시작일">
					&nbsp;~&nbsp; <input type="text" id="testDatepicker2" value="종료일">
				</td>
			</tr>

			<tr>
				<th>대체근무자</th>
				<td colspan="5"><input type="text" style="width: 500px">
					<button>대체근무자 선택</button></td>
			</tr>

			<tr>
				<th>사유</th>
				<td colspan="5"><textarea rows="4" cols="100">
				</textarea></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td colspan="5"><input type="text">

					<button>첨부파일</button></td>
			</tr>


			<tr>
				<td colspan="6" align="center">상기와 같은 사유로 인하여 조퇴계를 제출하오니
					재가바랍니다.</td>
			</tr>
			<tr>
				<td colspan="6" align="center"><input type="button" value="제출">
					<input type="button" value="취소"></td>
			</tr>
		</table>
	</div>
</div>
<script>
var className = 'authorization';
$('div#menutab li.'+className).addClass('active');
console.log($('div#menutab li.'+className));
$('ul#side-menu').find('li.' + className).show();
</script>
<%@include file="../container/footer.jsp"%>