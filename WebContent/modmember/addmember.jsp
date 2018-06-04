<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../container/header.jsp"%>
<script>
	var className = 'modmember';
	$('div#menutab li.' + className).addClass('active');
	console.log($('div#menutab li.' + className));
	$('ul#side-menu').find('li.' + className).show();
</script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
	$(function() {
		$('form#modmember').submit(function() {

			$.ajax({
				url : '${pageContext.request.contextPath}/modmember.do',
				method : 'post',
				data : $('form').serialize(),
				success : function(data) {
					data = data.trim();
					if (data == '1') {
						alert('사원추가완료');
						location.href = "${pageContext.request.contextPath}/memberstatus.do";
					} else if (data == '-1') {
						alert('입력실패');
					}

				}
			});
			return false;
		});
		$('#mdetailinput').click(function() {
			if ($('div#memberdetail').css('display') == 'none') {
				$('div#memberdetail').show();
			} else {
				$('div#memberdetail').hide();
			}
		})
		$('#cancel').click(function() {
			location.href = "${pageContext.request.contextPath}/memberstatus.do";
		})
	});
</script>
<style>
#submit, #cancel{
padding: 10px;

}
</style>


<body>	
	<div class="container">
		<h2 class="well">사원추가</h2>
		<div class="col-lg-12 well">
			<div class="row">
				<form id=modmember>
					<div class="col-sm-12">
						<div class="row">
							<div class="col-sm-6 form-group">
								<label>사원번호</label> <input type="text" class="form-control"
									name="emp_num">
							</div>
							<div class="col-sm-6 form-group">
								<label>부서번호</label> <input type="text" class="form-control"
									name="dept_num">
							</div>
							<div class="col-sm-6 form-group">
								<label>직급번호</label> <input type="text" class="form-control"
									name="position_num">
							</div>
							<div class="col-sm-6 form-group">
								<label>ID</label> <input type="text" class="form-control"
									name="id">
							</div>
						</div>

						<div class="row">
							<div class="col-sm-4 form-group">
								<label>비밀번호</label> <input type="text" class="form-control"
									name="pwd">

							</div>
							<div class="col-sm-4 form-group">
								<label>이름</label> <input type="text" class="form-control"
									name="name">
							</div>
							<div class="col-sm-4 form-group">
								<label>성별</label> <input type="text" class="form-control"
									name="gender" placeholder="M/F">
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6 form-group">
								<label>email 1</label> <input type="text" class="form-control"
									name="email1">
							</div>
							<div class="col-sm-6 form-group">
								<label>email 2</label> <input type="text" class="form-control"
									name="email2">
							</div>
							<div class="col-sm-6 form-group" >
								<label>전화번호</label> <input type="text" class="form-control"
									name="tel1" placeholder="010" > <input type="text"
									class="form-control" name="tel2" placeholder="xxxx" > <input
									type="text" class="form-control" name="tel3" placeholder="xxxx">
							</div>
						</div>
						<input type="button" class="btn btn-lg btn-info" id="mdetailinput"
							value="상세정보입력"> 
						<div class="memberdetail" id="memberdetail" style="display: none">
							<table>
								<tr>
									<td><label>생년월일</label></td>
									<td><input name="birthyear" placeholder="yyyy"><input
										name="birthmonth" placeholder="mm"><input
										name="birthday" placeholder="dd"></td>
								</tr>
								<tr>
									<td><label>고용년월일</label></td>
									<td><input name="hireyear" placeholder="yyyy"><input
										name="hiremonth" placeholder="mm"><input
										name="hireday" placeholder="dd"></td>
								</tr>
								<tr>
									<td><label>퇴사년월일</label></td>
									<td><input name="outyear" placeholder="yyyy"><input
										name="outmonth" placeholder="mm"><input name="outday"
										placeholder="dd"></td>
								</tr>
								<tr>
									<td><label>우편번호</label></td>
									<td><input name="zip1"><input name="zip2"></td>
								</tr>
								<tr>
									<td><label>주소</label></td>
									<td colspan="3"><input name="addr1" style="width: 500px"></td>
								</tr>
								<tr>
									<td><label>상세주소</label></td>
									<td colspan="3"><input name="addr2" style="width: 500px"></td>
								</tr>
								<tr>
									<td><label>결혼여부</label></td>
									<td><input name="marriage"></td>
								</tr>
							</table>
						</div>														
					</div>
				</form>	
				&nbsp;&nbsp;
					<input type="submit" value="저장" form="modmember" class="btn btn-lg btn-info" id="submit"> <button id="cancel" class="btn btn-lg btn-info" id="cancel">취소</button>				
			</div>
		</div>
	</div>

</body>

<%@include file="../container/footer.jsp"%>