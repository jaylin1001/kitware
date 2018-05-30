<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../container/header.jsp"%>
<c:set var="doc" value="${requestScope.docvo_list}" />
<c:set var="doc_conf" value="${requestScope.doc_detail_list}"/>
<c:set var="session" value="${sessionScope.loginInfo}"></c:set>	
<c:set var="rs" value="${requestScope.result}" />	
<form id="formwrite" onsubmit="return false;">
<div>
	<div class="title" align="center">
		<h2>조퇴/병가 작성</h2>
	</div>
	<div class="table">
		<table class="table table-bordered">
			<tr>
				<th>문서번호</th>
				<td><input type ="text" name ="doc_num" value ="${doc.doc_num}" readonly></td>
				<th rowspan="2">결재</th>
				<td rowspan="2">
						<div>${doc_conf[0].members.name}</div> 
						${doc_conf[0].acs_yn}
					</td>
					
					<td rowspan="2">
						<div>${doc_conf[1].members.name}</div>
						${doc_conf[1].acs_yn}
					</td>
					<td rowspan="2">
						<div>${doc_conf[2].members.name}</div> 
						${doc_conf[2].acs_yn}
					</td>
				</tr>
			<tr>
				<th>문서종류</th>
				<td>${doc.doc_kindvo.doc_name}</td>
			</tr>
			<tr>
				<th>기안일</th>
				<td><td>${doc.start_date}</td>
				<th>수신부서</th>
				<td>${doc.rcv_dept}</td>
			</tr>
			<tr>
				<th>기안자</th>
				<td>${session.name}</td>
				<th>부서</th>
				<td colspan="3">${session.gradeinfo.position_name}</td>
			</tr>
			<tr>
				<th>참조자</th>
				<td colspan="5">${doc.refer}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td colspan="5">
					<input type="text" name = "title" value="${doc.doc_title}"></td>
			</tr>
			<tr>
				<th>기간</th>
					<td><input type="text" id="testDatepicker" value="${doc.doc_gigan.start_date}" name= "start_date">
						&nbsp;~&nbsp; <input type="text" id="testDatepicker2" value="${doc.doc_gigan.end_date}" name = "end_date">
					</td>
			</tr>

			<tr>
				<th>대체근무자</th>
				<td colspan="5"><input type="text" style="width: 500px">
					<button>대체근무자 선택</button></td>
			</tr>

			<tr>
				<th>사유</th>
				<td colspan="5"><textarea rows="10" cols="130" name = "chuljang_textarea">${doc.doc_content}</textarea> 
			</td>
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
			<td colspan="6" align="center">
						<input type="submit" value="확인" onclick = "submitfn('${doc.doc_num}')"> 
						<input type="button" value="취소" id="back">
						<input type="button" value="삭제" id="del">
					</td>
				</tr>
			</table>
		</div>
	</div>
</form>
<script>
function submitfn(data) {
	location.href= "doceditjt.do"
	document.getElementById("formwrite").submit();
	//document.form.submit();//이부분 post로 데이터 못가져와서 null이 docvd에 set됨
	console.log(data);
}

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
	
	/* $("#submit").click(function() {
		location.href= "doceditcj.do?doc_num="
	return false;
	}); */
	
	$("#back").click(function() {
		history.back();
	return false;
	});
	
	
	var className = 'authorization';
	$('div#menutab li.' + className).addClass('active');
	console.log($('div#menutab li.' + className));
	$('ul#side-menu').find('li.' + className).show();
});

/* $('form').submit(function() {
$.ajax({
		url : 'doceditcj.do',
		data : $('form').serialize(),
		method:'POST',
		/* success : function(data) {
			data = data.trim();
			if (data == '1') { //글쓰기 성공
				alert('수정 성공');
				location.href = "docreadcj.do?doc_num=1805-0008" //임시로 page 09번꺼 줌
			} else if (data == '-1') { //글쓰기 실패
				alert('수정 실패');
			}
		}  
	});
	return false;
}); */
</script>

<%@ include file="../container/footer.jsp"%>