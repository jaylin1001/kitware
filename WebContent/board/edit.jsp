<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../container/header.jsp"%>
<div id="div1"></div>
<div class="container">
	<h1 class="write">글 수정</h1>
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
			<div>
				<button class="submit">저장</button>
				&nbsp;
				<button>취소</button>
				<Button id="myBtn">삭제</Button>
			</div>

		</div>
	</div>
</div>
<!-- The Modal -->
<div id="myModal" class="modal">

	<!-- Modal content -->
	<div class="modal-content">
		<span class="close">&times;</span>
		<h3>삭제하시겠습니까?</h3>
		<br> <br> <br> <br> <br> <a href='board.jsp'><button
				class="button">삭제</button></a>
		<button class="button1">취소</button>
	</div>

</div>
<script>
	// Get the modal
	var modal = document.getElementById('myModal');

	// Get the button that opens the modal
	var btn = document.getElementById("myBtn");

	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];

	// When the user clicks the button, open the modal 
	btn.onclick = function() {
		modal.style.display = "block";
	}

	// When the user clicks on <span> (x), close the modal
	span.onclick = function() {
		modal.style.display = "none";
	}

	// When the user clicks anywhere outside of the modal, close it
	window.onclick = function(event) {
		if (event.target == modal) {
			modal.style.display = "none";
		}
	}

	//board 에서 클릭한 글번호 가져올것
	//그 글번호로 DB에서 select 해 데이터 가져온 후 뿌려줘야함 
	//수정시 본인이 쓴거 아니면 수정 안됨

	$(function() {
		$('.submit').click(function() {
			var $targetObj = $("div.container");
			$targetObj.empty();
			$("#div1").load("board/board.jsp");
			console.log('ddd');

		});
		function write_btn() {

			alter("저장되었습니다");
		}
	});
	var className = 'board';
	$('div#menutab li.'+className).addClass('active');
	console.log($('div#menutab li.'+className));
	$('ul#side-menu').find('li.' + className).show();
</script>
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

/* The Modal (background) */
.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	padding-top: 100px; /* Location of the box */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
	background-color: #fefefe;
	margin: auto;
	padding: 20px;
	border: 1px solid #888;
	width: 80%;
}

/* The Close Button */
.close {
	color: #aaaaaa;
	float: right;
	font-size: 28px;
	font-weight: bold;
}

.close:hover, .close:focus {
	color: #000;
	text-decoration: none;
	cursor: pointer;
}

.button {
	background-color: #337ab7; /* Green */
	border: none;
	color: white;
	padding: 10px 15px;
	border-radius: 5px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 4px 2px;
	cursor: pointer;
}

.button1 {
	background-color: #4CAF50; /* Green */
	border: none;
	color: white;
	padding: 10px 15px;
	border-radius: 5px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 4px 2px;
	cursor: pointer;
}

.modal-content.modal-70size {
	height: auto;
	min-height: 70%;
}

button {
	background-color: #337ab7; /* Green */
	border: none;
	color: white;
	padding: 8px 10px;
	border-radius: 5px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 4px 2px;
	cursor: pointer;
}
</style>
<%@ include file="../container/footer.jsp"%>