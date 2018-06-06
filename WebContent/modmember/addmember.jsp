<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../container/header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="rs" value="${requestScope.result}" />
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
		$('#idcheck').click(function() {
			$.ajax({
				method : 'post',
				data : 'id=' + $('#id').val(),
				url : '${pageContext.request.contextPath}/idcheck.do',
				success : function(data) {					
					data = data.trim();
					if (data == '1') {					
							alert('사용가능한 ID입니다.');
					} else if (data == '-1') {						
							alert('이미사용중인ID입니다.');
					}
				}
			});
		});


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
		$('#cancel').click(function() {
			location.href = "${pageContext.request.contextPath}/memberstatus.do";
		})
	});
</script>
<style>
#submit, #cancel {
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
								<label>사원번호</label> <input required="required" type="text"
									class="form-control" name="emp_num" value="${rs.emp_num}"
									readonly="readonly">
							</div>
							<div class="col-sm-6 form-group">
							<label>부서번호</label><select required="required"
									class="form-control" name="dept_num" style="display: inline;">
							<option value="100">개발부</option>
							<option value="200">기획부</option>
							<option value="300">인사부</option>
							<option value="400">영업부</option>
							<option value="100">사업부</option>							
							</select>
							</div>
							<div class="col-sm-6 form-group">
							<label>직급번호</label><select required="required"
									class="form-control" name="position_num">
							<option value="60">사원</option>	
							<option value="50">대리</option>	
							<option value="40">과장</option>
							<option value="30">부장</option>
							<option value="20">이사</option>
							<option value="10">사장</option>												
							</select>
							</div>
							<div class="col-sm-6 form-group">
								<label>ID</label> <input required="required" type="text"
									class="form-control" name="id" id="id">
								<input type="button" id="idcheck" value="ID중복확인">
							</div>
						</div>

						<div class="row">
							<div class="col-sm-4 form-group">
								<label>비밀번호</label> <input required="required" type="text"
									class="form-control" name="pwd">

							</div>
							<div class="col-sm-4 form-group">
								<label>이름</label> <input required="required" type="text"
									class="form-control" name="name">
							</div>
							<div class="col-sm-4 form-group">
								<label>성별</label> <input required="required" type="text"
									class="form-control" name="gender" placeholder="M/F">
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6 form-group">
								<label>email 1</label> <input required="required" type="text"
									class="form-control" name="email1">
							</div>
							<div class="col-sm-6 form-group">
								<label>email 2</label> <input required="required" type="text"
									class="form-control" name="email2">
							</div>
							<div class="col-sm-6 form-group">
								<label>전화번호</label> <input type="text" class="form-control"
									required="required" name="tel1" placeholder="010"> <input
									type="text" class="form-control" required="required"
									name="tel2" placeholder="xxxx"> <input type="text"
									class="form-control" required="required" name="tel3"
									placeholder="xxxx">
							</div>
						</div>

						<h4>---상세정보---</h4>
						<div class="memberdetail" id="memberdetail">
							<table>
								<tr>
									<td><label>생년월일</label></td>
									<td><input required="required" name="birthyear"
										placeholder="yyyy"><input required="required"
										name="birthmonth" placeholder="mm"><input
										required="required" name="birthday" placeholder="dd"></td>
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
									<td><input required="required" name="zip1"><input
										name="zip2"></td>
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
				&nbsp;&nbsp; <input type="submit" value="저장" form="modmember"
					class="btn btn-lg btn-info" id="submit">
				<button id="cancel" class="btn btn-lg btn-info" id="cancel">취소</button>
			</div>
		</div>
	</div>

</body>

<%@include file="../container/footer.jsp"%>