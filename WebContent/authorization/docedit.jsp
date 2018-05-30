<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../container/header.jsp" %>
<c:set var="doc" value="${requestScope.docvo_list}" />
<c:set var="doc_conf" value="${requestScope.doc_detail_list}"/>
<c:set var="session" value="${sessionScope.loginInfo}"></c:set>	
<form id="formwrite">
	<div>
		<div class="title" align="center">
			<h2>기안서</h2>
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
					<td>${doc.start_date}</td>
					<th>수신부서</th>
					<td>${doc.rcv_dept}</td>
				</tr>
				<tr>
					<th>기안자</th>
					<td>${doc.members.name}(${doc.gradeinfo.position_name})</td>
					<th>부서</th>
					<td colspan="3">${doc.deptinfo.dept_name}</td>
				</tr>
				<tr>
					<th>제목</th>
					<td colspan="5">
					<input type="text" name = "title" value="${doc.doc_title}"></td>
				</tr>
			<tr>
			<td colspan="6"><textarea name="chuljang_textarea" id="content"
							class="summernote" style="width: 100%">${doc.doc_content}</textarea></td>
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
<script type="text/javascript">
	function submitfn(data) {
		location.href= "doceditgian.do"
		document.getElementById("formwrite").submit();
		//document.form.submit();//이부분 post로 데이터 못가져와서 null이 docvd에 set됨
		console.log(data);
	}
	
	$(function() {
	$('.summernote').summernote({
		height : 300, // 기본 높이값
		minHeight : null, // 최소 높이값(null은 제한 없음)
		maxHeight : null, // 최대 높이값(null은 제한 없음)
		focus : true, // 페이지가 열릴때 포커스를 지정함
		lang : 'ko-KR' // 한국어 지정(기본값은 en-US)
	});
	
	$("#back").click(function() {
		history.back();
	return false;
	});

	var className = 'authorization';
	$('div#menutab li.'+className).addClass('active');
	console.log($('div#menutab li.'+className));
	$('ul#side-menu').find('li.' + className).show();
});

</script>
<%@ include file="../container/footer.jsp" %>
