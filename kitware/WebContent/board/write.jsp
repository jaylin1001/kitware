<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../container/header.jsp"%>
<div id="div1"></div>
<div class="container">
	<h1 class="write">&nbsp;글쓰기</h1>
	<div class="col-lg-12 write">
		<div class="row">
			<form>
				<div class="col-sm-12">
					<div class="row">
						<div class="col-sm-2 form-group">
							<label>작성자</label> <input type="text" class="form-control">
						</div>
						<div class="col-sm-2 form-group">
							<label>비밀번호</label> <input type="text" class="form-control">
						</div>
						<div class="col-sm-3 form-group">
							<label>수정일</label> <input type="text" class="form-control">
						</div>
						<div class="col-sm-9 form-group">
							<label>글 제목</label> <input type="text" class="form-control">
						</div>
						<div class="col-sm-12 form-group">
							<label>글 내용</label>
						</div>
					</div>

					<div id="summernote">
						<script>
							$(document).ready(function() {
								$('#summernote').summernote({
									height : 450,
									width : 850
								});
							});
						</script>
					</div>
				</div>
			</form>

		</div>
		<div class="col-sm-4 form-group">
			<label>첨부파일</label>
			<button type="button">...</button>
			&nbsp; <input type="text" class="form-control">
			<div>&nbsp;</div>
			<div>
				<button class="btn_save">완료</button>
				&nbsp;
				<button>취소</button>
			</div>
		</div>
	</div>
</div>
<style>
button {
	background-color: #337ab7; /* Green */
	border: none;
	color: white;
	padding: 8px 11px;
	border-radius: 5px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 14px;
	margin: 4px 2px;
	cursor: pointer;
}
</style>
<script>
	$(function() {
		$('.btn_save').click(function() {
			var $targetObj = $("div.container");
			$targetObj.empty();
			$("#div1").load("board/board.jsp");
		});
	});
	var className = 'board';
	$('div#menutab li.'+className).addClass('active');
	console.log($('div#menutab li.'+className));
	$('ul#side-menu').find('li.' + className).show();
</script>
<%@include file="../container/footer.jsp"%>
