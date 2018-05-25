<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../container/header.jsp"%>
<style>
th {
	align: center;
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

</head>
<body>
	<div>
		<div class="title" align="center">
			<h2></h2>
		</div>
		<div class="table">
			<table class="table table-bordered">

				<tr>
					<th>제목</th>
					<td colspan="5">첫번째 글입니다</td>
				</tr>

				<tr>
					<th>작성일</th>
					<td>2018-05-08</td>
					<th>작성자</th>
					<td>김태태</td>
					<th>조회수</th>
					<td>2</td>
				</tr>
			</table>
			<table class="table table-bordered">
				<tr>
					<td width="500px" height="500px">뭐뭐 어쩌고저쩌고</td>
				</tr>
			</table>
			<table class="table table-bordered">
				<tr>
					<th>첨부파일</th>
					<td colspan="5"><input type="text">

						<button>첨부파일</button></td>
				</tr>
			</table>
			<div>
				<br>
			</div>
			<tr>
				<td colspan="6" align="center">
					<button class="btn_edit">수정</button>
					<button>이전글</button>
					<button>다음글</button>
					<button class="btn_list">글목록</button>
				</td>
			</tr>
		</div>
	</div>
	<script>
		$(function() {
			$('.btn_list').click(function() {
				var $targetObj = $("div.container");
				$targetObj.empty();
				$("#div1").load("board/board.jsp");
				console.log('ddd');

			});
		});
		$(function() {
			$('.btn_edit').click(function() {
				var $targetObj = $("div.container");
				$targetObj.empty();
				$("#div1").load("board/edit.jsp");
				console.log('ddd');
			});
		});
		var className = 'board';
		$('div#menutab li.'+className).addClass('active');
		console.log($('div#menutab li.'+className));
		$('ul#side-menu').find('li.' + className).show();
	</script>
	<%@ include file="../container/footer.jsp"%>