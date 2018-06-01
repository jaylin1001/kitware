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

				<tr>
	               <td colspan="6"><textarea name="content" id="content"
	                     class="summernote" style="width: 100%">
	                     <p style="text-align: center; "><br></p><table class="table table-bordered" style="text-align: center;"><tbody><tr><td>no</td><td>&nbsp; &nbsp; &nbsp; &nbsp; 품명&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;</td><td>&nbsp;규격&nbsp; &nbsp;&nbsp;</td><td>단위</td><td>수량</td><td>단가&nbsp; &nbsp;</td><td>금액&nbsp; &nbsp;&nbsp;</td><td>비고&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;</td></tr><tr><td><br></td><td><br></td><td><br></td><td><br></td><td><br></td><td><br></td><td><br></td><td><br></td></tr><tr><td><br></td><td><br></td><td><br></td><td><br></td><td><br></td><td><br></td><td><br></td><td><br></td></tr><tr><td><br></td><td><br></td><td><br></td><td><br></td><td><br></td><td><br></td><td><br></td><td><br></td></tr><tr><td><br></td><td><br></td><td><br></td><td><br></td><td><br></td><td><br></td><td><br></td><td><br></td></tr><tr><td><br></td><td><br></td><td><br></td><td><br></td><td><br></td><td><br></td><td><br></td><td><br></td></tr><tr><td><br></td><td><br></td><td><br></td><td><br></td><td><br></td><td><br></td><td><br></td><td><br></td></tr><tr><td><br></td><td><br></td><td><br></td><td><br></td><td><br></td><td><br></td><td><br></td><td><br></td></tr></tbody></table><p style="text-align: center;"><br></p><table class="table table-bordered"><tbody><tr><td><p style="text-align: center;">계</p></td><td style="text-align: center;">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;</td><td style="text-align: center;">&nbsp; 금액</td><td style="text-align: right;">원</td></tr></tbody></table><p style="text-align: center;"><br></p>
	                     </textarea></td>
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
		  $('.summernote').summernote({
		       height : 300, // 기본 높이값
		       minHeight : null, // 최소 높이값(null은 제한 없음)
		       maxHeight : null, // 최대 높이값(null은 제한 없음)
		       focus : true, // 페이지가 열릴때 포커스를 지정함
		       lang : 'ko-KR' // 한국어 지정(기본값은 en-US)
		    });
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