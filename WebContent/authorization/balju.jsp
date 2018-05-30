<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <jsp:include page="../container/header.jsp" flush="true"></jsp:include>
 --%>
 <%@ include file="../container/header.jsp" %>
<form class="form-inline">
	<div class="center-block">
		<div class="title" align="center">
			<h2>발주서</h2>
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
					<td>발주서</td>
				</tr>
				<tr>
					<th>기안일</th>
					<td><input type="text">년 <input type="text">월
						<input type="text">일</td>
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
					<th>수신부서</th>
					<td colspan="5"><input type="text" style="width: 500px">
						<button>수신부서 선택</button></td>
				</tr>

				<div class="container">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th>품명</th>
								<th>발주수량</th>
								<th>규격</th>
								<th>단가</th>
								<th>금액</th>
								<th>비고</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><input type="text"></td>
								<td><input type="text"></td>
								<td><input type="text"></td>
								<td><input type="text"></td>
								<td><input type="text"></td>
								<td><input type="text"></td>

							</tr>
							<tr>
								<td><input type="text"></td>
								<td><input type="text"></td>
								<td><input type="text"></td>
								<td><input type="text"></td>
								<td><input type="text"></td>
								<td><input type="text"></td>
							</tr>
							<tr>
								<td><input type="text"></td>
								<td><input type="text"></td>
								<td><input type="text"></td>
								<td><input type="text"></td>
								<td><input type="text"></td>
								<td><input type="text"></td>
							</tr>
							<tr>

								<td><input type="text"></td>
								<td><input type="text"></td>
								<td><input type="text"></td>
								<td><input type="text"></td>
								<td><input type="text"></td>
								<td><input type="text"></td>

							</tr>
							<tr>
								<td><input type="text"></td>
								<td><input type="text"></td>
								<td><input type="text"></td>
								<td><input type="text"></td>
								<td><input type="text"></td>
								<td><input type="text"></td>
							</tr>
							<tr>
								<td><input type="text"></td>
								<td><input type="text"></td>
								<td><input type="text"></td>
								<td><input type="text"></td>
								<td><input type="text"></td>
								<td><input type="text"></td>

							</tr>

							<tr>
								<th>소계</th>
								<td></td>
								<th>세액</th>
								<td></td>
								<th>합계</th>
								<td></td>

							</tr>


							<tr>
								<th>특이사항</th>
								<td colspan="5"><textarea rows="4" cols="100">
				</textarea></td>
							</tr>
							<tr>
								<th>첨부파일</th>
								<td colspan="5"><input type="text">

									<button>첨부파일</button></td>
							</tr>
							<tr>
								<td colspan="6" align="center"><input type="button"
									value="제출"> <input type="button" value="취소"></td>
							</tr>

						</tbody>
					</table>
				</div>
			</table>
		</div>
	</div>
</form>
<script type="text/javascript">
	$(function() {
		$("#testDatepicker").datepicker({
			showOn : "both",
			/* buttonImage: "button.png", 
			buttonImageOnly: true  */
			dateFormat : "yy-mm-dd"
		});
		$("#testDatepicker2").datepicker({
			showOn : "both",
			/* buttonImage: "button.png", 
			buttonImageOnly: true  */
			dateFormat : "yy-mm-dd"
		});
		
		var className = 'authorization';
		$('div#menutab li.'+className).addClass('active');
		console.log($('div#menutab li.'+className));
		$('ul#side-menu').find('li.' + className).show();
	});
</script>
<%@ include file="../container/footer.jsp" %>
<%-- <jsp:include page="../container/footer.jsp"></jsp:include> --%>