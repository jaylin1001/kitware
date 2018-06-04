<%@page import="com.kitware.member.vo.StatusDetailBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<c:set var="rs" value="${requestScope.result}" />
<style>
#submit, #cancel, #mdetailinput, #modify {
	padding: 5px;
}
</style>
<body>
	<div class="container">
		<h2 class="well">상세정보</h2>
		<div class="col-lg-12 well">
			<div class="row">
				<form id=modmember>
					<div class="col-sm-12">
						<div class="row">
							<div class="col-sm-6 form-group">
								<label>사원번호</label> <input type="text" class="form-control"
									name="emp_num" readonly value="${rs.emp_num}" id="emp_num">
							</div>
							<div class="col-sm-6 form-group">
								<label>부서번호</label> <input type="text" class="form-control"
									name="dept_num" readonly value="${rs.dept_num}">
							</div>
							<div class="col-sm-6 form-group">
								<label>직급번호</label> <input type="text" class="form-control"
									name="position_num" readonly value="${rs.position_num}">
							</div>
							<div class="col-sm-6 form-group">
								<label>ID</label> <input type="text" class="form-control"
									name="id" readonly value="${rs.id}">
							</div>
						</div>

						<div class="row">
							<div class="col-sm-4 form-group">
								<label>비밀번호</label> <input type="text" class="form-control"
									name="pwd" readonly value="${rs.pwd}">

							</div>
							<div class="col-sm-4 form-group">
								<label>이름</label> <input type="text" class="form-control"
									name="name" readonly value="${rs.name}">
							</div>
							<div class="col-sm-4 form-group">
								<label>성별</label> <input type="text" class="form-control"
									name="gender" placeholder="M/F" readonly value="${rs.gender}">
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6 form-group">
								<label>email 1</label> <input type="text" class="form-control"
									name="email1" readonly value="${rs.email1}">
							</div>
							<div class="col-sm-6 form-group">
								<label>email 2</label> <input type="text" class="form-control"
									name="email2" readonly value="${rs.email2}">
							</div>
							<div class="col-sm-6 form-group">
								<table>
									<tr>
										<td style="width: 70px"><label>전화번호</label></td>
										<td><input type="text" style="display: inline;"
											class="form-control" name="tel1" placeholder="010" readonly
											value="${rs.tel1}"></td>
										<td><input type="text" style="display: inline;"
											class="form-control" name="tel2" placeholder="xxxx" readonly
											value="${rs.tel2}"></td>
										<td><input style="display: inline;" type="text"
											class="form-control" name="tel3" placeholder="xxxx" readonly
											value="${rs.tel3}"></td>
									</tr>
								</table>
							</div>
						</div>
						<input type="button" class="btn btn-lg btn-info" id="mdetailinput"
							value="상세정보보기">
						<div class="memberdetail" id="memberdetail" style="display: none">
							<table>
								<tr>
									<td><label>생년월일</label></td>
									<td><input name="birthyear" placeholder="yyyy"
										class="form-control" readonly value="${rs.birth1}"></td>
									<td><input name="birthmonth" placeholder="mm"
										class="form-control" readonly value="${rs.birth2}"></td>
									<td><input name="birthday" placeholder="dd"
										class="form-control" readonly value="${rs.birth3}"></td>
								</tr>
								<tr>
									<td><label>고용년월일</label></td>
									<td><input class="form-control" name="hireyear"
										placeholder="yyyy" readonly value="${rs.hire_date1}"></td>
									<td><input class="form-control" name="hiremonth"
										placeholder="mm" readonly value="${rs.hire_date2}"></td>
									<td><input class="form-control" name="hireday"
										placeholder="dd" readonly value="${rs.hire_date3}"></td>
								</tr>
								<tr>
									<td><label>퇴사년월일</label></td>
									<td><input class="form-control" name="outyear"
										placeholder="yyyy" readonly value="${rs.out_date1}"></td>
									<td><input class="form-control" name="outmonth"
										placeholder="mm" readonly value="${rs.out_date2}"></td>
									<td><input class="form-control" name="outday"
										placeholder="dd" readonly value="${rs.out_date3}"></td>
								</tr>
								<tr>
									<td><label>우편번호</label></td>
									<td><input class="form-control" name="zip1" readonly
										value="${rs.zip1}"><input class="form-control"
										name="zip2" readonly value="${rs.zip2}"></td>
								</tr>
								<tr>
									<td><label>주소</label></td>
									<td colspan="3"><input class="form-control" name="addr1"
										style="width: 500px" readonly value="${rs.addr1}"></td>
								</tr>
								<tr>
									<td><label>상세주소</label></td>
									<td colspan="3"><input class="form-control" name="addr2"
										style="width: 500px" readonly value="${rs.addr2}"></td>
								</tr>
								<tr>
									<td><label>결혼여부</label></td>
									<td><input class="form-control" name="marriage" readonly
										value="${rs.marriage}"></td>
								</tr>
							</table>
						</div>
					</div>
				</form>
				<button id="cancel" class="btn btn-lg btn-info">뒤로가기</button>
				<button id="modify" class="btn btn-lg btn-info">정보수정</button>
			</div>
		</div>
	</div>
</body>
<script>
	$(function() {
		$('#mdetailinput').click(function() {
			if ($('div#memberdetail').css('display') == 'none') {
				$('div#memberdetail').show();
			} else {
				$('div#memberdetail').hide();
			}
		})
		var emp_num = $('#emp_num').val();
		$('#modify').click(function() {
			$.ajax({
				url : "${pageContext.request.contextPath}/membermoddetail.do",
				method : "get",
				data : "emp_num=" + emp_num,
				success : function(data) {
					$('div.container').empty();
					$('div.container').html(data.trim());
				}
			})
		})	
		$('#cancel').click(function() {
			location.href = "${pageContext.request.contextPath}/memberstatus.do";
		});
	});
</script>