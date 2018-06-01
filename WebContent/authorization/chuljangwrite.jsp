<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../container/header.jsp"%>
<c:set var="session" value="${sessionScope.loginInfo}"></c:set>	
<form id="formwrite">
	<div>
		<div class="title" align="center">
			<h2>출장신청서 작성</h2>
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
					<td>출장</td>
				</tr>
				<tr>
					<th>기안일</th>
					<td><input type="text" style="width: 60px">년 <input
						type="text" style="width: 40px">월 <input type="text"
						style="width: 40px">일</td>
					<th>수신부서</th>
					<td colspan="3"><input type="text"> <input
						type="button" value="수신부서지정"></td>
				</tr>
				<tr>
					<th>기안자</th>
					<td>${session.loginInfo.name}</td>
					<th>부서</th>
					<td colspan="3">${session.loginInfo.gradeinfo.position_name}</td>
				</tr>
				<tr>
					<th>참조자</th>
					<td colspan="5"><input type="text">
						<button id= "chamjo">참조자 지정</button></td>
				</tr>
				<tr>
					<th>제목</th>
					<td colspan="5"><input type="text" name="doc_title"
						style="width: 700px"></td>
				</tr>
				<tr>
					<th>기간</th>
					<td><input type="text" id="testDatepicker" value="시작일" name= "start_date">
						&nbsp;~&nbsp; <input type="text" id="testDatepicker2" value="종료일" name = "end_date">
					</td>
				</tr>

				<tr>
					<th>출장지</th>
					<td colspan="5"><input type="text" style="width: 500px" id= "chuljang_space">
						<button id ="chuljang_select">출장지 선택</button> <!--지도 api써도 좋을거같음 --></td>
				</tr>

				<tr>
					<th>출장목적</th>
					<td colspan="5"><textarea rows="4" cols="100" id= "chuljang_textarea">
				</textarea></td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td colspan="5"><input type="text">

						<button>첨부파일</button></td>
				</tr>


				<tr>
					<td colspan="6" align="center">상기와 같이 출장 신청서를 제출하오니 재가바랍니다.</td>
				</tr>
				<tr>
					<td colspan="6" align="center"><input type="button" value="제출" id ="submit">
						<input type="button" value="취소" id="cancle"></td>
				</tr>
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
<script>
$(function(){
  $('form#formwrite').submit(function(){
	  $.ajax({
		  url:'docwritecj.do',
		  method:'post',
		  data:$('form').serialize(),
		  success:function(data){
			  data = data.trim();
			  if(data == '1'){ //글쓰기 성공
				  alert('글쓰기 성공');
				 var $triggerObj = $("nav>ul li.board!!수정	해야함");
				 $triggerObj.trigger('click');
			  }else if(data == '-1'){ //글쓰기 실패
				 alert('글쓰기 실패'); 
			  }
		  }
	  });
	  return false;
  });
});
</script>
<%@ include file="../container/footer.jsp"%>