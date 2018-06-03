<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../container/header.jsp"%>
<div class="container">
	<h1 class="well">회원가입</h1>
	<div class="col-lg-12 well">
		<div class="row">
			<form>
				<div class="col-sm-12">
					<div class="row">
						<div class="col-sm-6 form-group">
							<label>이름</label> <input type="text" class="form-control">
						</div>
						<div class="col-sm-6 form-group">
							<label>생년월일</label> <input type="text" class="form-control">
						</div>
						<div class="col-sm-6 form-group">
							<label>id</label> <input type="text" class="form-control">
						</div>
						<div class="col-sm-6 form-group">
							<label>Password</label> <input type="text" class="form-control">
						</div>
					</div>

					<div class="row">
						<div class="col-sm-4 form-group">
							<label>우편번호</label>
							<button>찾기</button>
							<input type="text" class="form-control">

						</div>
						<div class="col-sm-4 form-group">
							<label>상세주소1</label> <input type="text" class="form-control">
						</div>
						<div class="col-sm-4 form-group">
							<label>상세주소2</label> <input type="text" class="form-control">
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6 form-group">
							<label>회사</label> <input type="text" class="form-control">
						</div>
						<div class="col-sm-6 form-group">
							<label>부서</label> <input type="text" class="form-control">
						</div>
						<div class="col-sm-6 form-group">
							<label>직급</label> <input type="text" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label>휴대전화</label> <input type="text" class="form-control">
					</div>
					<div class="form-group">
						<label>Email</label> <input type="text" class="form-control">
					</div>
					<div class="form-group">
						<label>주소</label> <input type="text" class="form-control">
					</div>
					<button type="button" class="btn btn-lg btn-info">Submit</button>
				</div>
			</form>
		</div>
	</div>
</div>
<script>
var className = 'board';
$('div#menutab li.'+className).addClass('active');
console.log($('div#menutab li.'+className));
$('ul#side-menu').find('li.' + className).show();
</script>
<%@include file="../container/footer.jsp"%>
