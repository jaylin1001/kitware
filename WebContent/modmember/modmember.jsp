<%@page import="com.kitware.member.vo.StatusDetailBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		$('form#modmember').submit(function() {
			$.ajax({
				url : '${pageContext.request.contextPath}/correctmember.do',
				method : 'post',
				data : $('form').serialize(),
				success : function(data) {
					data = data.trim();
					if (data == '1') {
						alert('정보수정완료');
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
		var emp_num=$('#emp_num').val();
		$('#delete').click(function(){
			if (confirm("정말 삭제하시겠습니까??") == true) { //확인
				$.ajax({
					url:"${pageContext.request.contextPath}/deletemember.do",
					method:"get",
					data:"emp_num="+emp_num,
					success:function(data){
						data = data.trim();
						if(data == '1'){
							alert('삭제완료');
							location.href="${pageContext.request.contextPath}/memberstatus.do";							
						}else if(data == '-1'){
							alert('삭제실패')
						}
						
					}
				})
			} else { //취소
				return;
			}
		})
	});
</script>
<style>
#submit, #cancel, #mdetailinput, #delete {
	padding: 5px;
}
</style>


<body>
	<div class="container">
		<h2 class="well">사원정보수정</h2>
		<div class="col-lg-12 well">
			<div class="row">
				<form id=modmember>
					<div class="col-sm-12">
						<div class="row">
							<div class="col-sm-6 form-group">
								<label>사원번호</label> <input type="text" class="form-control"
									name="emp_num" value="${rs.emp_num}" id="emp_num">
							</div>
							<div class="col-sm-6 form-group">
								<label>부서번호</label> <input type="text" class="form-control"
									name="dept_num" value="${rs.dept_num}">
							</div>
							<div class="col-sm-6 form-group">
								<label>직급번호</label> <input type="text" class="form-control"
									name="position_num" value="${rs.position_num}">
							</div>
							<div class="col-sm-6 form-group">
								<label>ID</label> <input type="text" class="form-control"
									name="id" value="${rs.id}">
							</div>
						</div>

						<div class="row">
							<div class="col-sm-4 form-group">
								<label>비밀번호</label> <input type="text" class="form-control"
									name="pwd" value="${rs.pwd}">

							</div>
							<div class="col-sm-4 form-group">
								<label>이름</label> <input type="text" class="form-control"
									name="name" value="${rs.name}">
							</div>
							<div class="col-sm-4 form-group">
								<label>성별</label> <input type="text" class="form-control"
									name="gender" placeholder="M/F" value="${rs.gender}">
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6 form-group">
								<label>email 1</label> <input type="text" class="form-control"
									name="email1" value="${rs.email1}">
							</div>
							<div class="col-sm-6 form-group">
								<label>email 2</label> <input type="text" class="form-control"
									name="email2" value="${rs.email2}">
							</div>
							<div class="col-sm-6 form-group">
								<table>
									<tr>
										<td style="width: 70px"><label>전화번호</label></td>
										<td><input type="text" style="display: inline;"
											class="form-control" name="tel1" placeholder="010"
											value="${rs.tel1}"></td>
										<td><input type="text" style="display: inline;"
											class="form-control" name="tel2" placeholder="xxxx"
											value="${rs.tel2}"></td>
										<td><input style="display: inline;" type="text"
											class="form-control" name="tel3" placeholder="xxxx"
											value="${rs.tel3}"></td>
									</tr>
								</table>
							</div>
						</div>
						<input type="button" class="btn btn-lg btn-info" id="mdetailinput"
							value="상세정보수정">
						<div class="memberdetail" id="memberdetail" style="display: none">
							<table>
								<tr>
									<td><label>생년월일</label></td>
									<td><input name="birth1" placeholder="yyyy"
										class="form-control" value="${rs.birth1}"></td>
									<td><input name="birth2" placeholder="mm"
										class="form-control" value="${rs.birth2}"></td>
									<td><input name="birth3" placeholder="dd"
										class="form-control" value="${rs.birth3}"></td>
								</tr>
								<tr>
									<td><label>고용년월일</label></td>
									<td><input class="form-control" name="hire_date1"
										placeholder="yyyy" value="${rs.hire_date1}"></td>
									<td><input class="form-control" name="hire_date2"
										placeholder="mm" value="${rs.hire_date2}"></td>
									<td><input class="form-control" name="hire_date3"
										placeholder="dd" value="${rs.hire_date3}"></td>
								</tr>
								<tr>
									<td><label>퇴사년월일</label></td>
									<td><input class="form-control" name="out_date1"
										placeholder="yyyy" value="${rs.out_date1}"></td>
									<td><input class="form-control" name="out_date2"
										placeholder="mm" value="${rs.out_date2}"></td>
									<td><input class="form-control" name="out_date3"
										placeholder="dd" value="${rs.out_date3}"></td>
								</tr>
								<tr>
									<td><label>우편번호</label></td>
									<td><input class="form-control" name="zip1"
										value="${rs.zip1}"><input class="form-control"
										name="zip2" value="${rs.zip2}"></td>
								</tr>
								<tr>
									<td><label>주소</label></td>
									<td colspan="3"><input class="form-control" name="addr1"
										style="width: 500px" value="${rs.addr1}"></td>
								</tr>
								<tr>
									<td><label>상세주소</label></td>
									<td colspan="3"><input class="form-control" name="addr2"
										style="width: 500px" value="${rs.addr2}"></td>
								</tr>
								<tr>
									<td><label>결혼여부</label></td>
									<td><input class="form-control" name="marriage"
										value="${rs.marriage}"></td>
								</tr>
							</table>
						</div>
					</div>
				</form>
				<input type="submit" form="modmember" value="저장"
					class="btn btn-lg btn-info" id="submit"> <input
					type="button" id="delete"
					class="btn btn-lg btn-info" value="회원삭제">
				<button id="cancel" class="btn btn-lg btn-info" id="cancel">취소</button>
			</div>
		</div>
	</div>
</body>
</html>
<%@include file="../container/footer.jsp"%>