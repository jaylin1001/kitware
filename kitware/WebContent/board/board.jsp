<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../container/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="div1"></div>
<div class="container">
	<p>
	<h2>공지사항</h2>
	<table class="table table-striped table-hover table-bordered">
		<thead>
			<tr>
				<th width="10%">글 번호</th>
				<th width="55%">제목</th>
				<th width="10%">작성자</th>
				<th width="15%">작성일</th>
				<th width="10%">조회수</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>1</td>
				<td><a href="#">글1제목</a></td>
				<td>김태태</td>
				<td>05.05</td>
				<td>2</td>
			</tr>
			<tr>
				<td>2</td>
				<td><a href="#">글2제목</a></td>
				<td>망개</td>
				<td>05.05</td>
				<td>2</td>
			</tr>
			<tr>
				<td>3</td>
				<td><a href="#">글3제목</a></td>
				<td>치미니</td>
				<td>05.05</td>
				<td>2</td>
			</tr>
			<tr>
				<td>4</td>
				<td><a href="#">글4제목</a></td>
				<td>침치미</td>
				<td>05.05</td>
				<td>2</td>
			</tr>
			<tr>
				<td>5</td>
				<td><a href="#">글5제목</a></td>
				<td>박망개</td>
				<td>05.05</td>
				<td>2</td>
			</tr>
			<c:forEach var="article" items="${articles}" varStatus="status">
				<tr>
					<td>${article.articleNumber}</td>
					<td id="title"><c:if test="${article.depth > 0}">
                  &nbsp;&nbsp;
                </c:if> <a
						href="/bbs/content.bbs?articleNumber=${article.articleNumber}&pageNum=${pageNum}">${article.title}</a>
						<c:if test="${article.hit >= 20}">
							<span class="hit">hit!</span>
						</c:if></td>
					<td>${article.id}</td>
					<td>${article.writeDate}</td>
					<td>${article.hit}</td>
				<tr>
			</c:forEach>
		</tbody>
	</table>
	<button class="btn_write" id="writeform">글쓰기</button>
	<div class="text-center">
		<button type="button">1</button>
		<button type="button">2</button>
		<button type="button">3</button>
		<button type="button">4</button>
		<button type="button">5</button>
	</div>
</div>
<style>
.container {
	padding-right: 350px;
	padding-left: 15px;
	margin-right: auto;
	margin-left: auto;
}

p {
	padding-top: 50px;
	padding-right: 30px;
	padding-bottom: 50px;
	padding-left: 80px;
}

body {
	width: 100%;
	margin: 0;
	padding: 0;
}

pd {
	padding-right: 200px;
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

<script>
	$(function() {
		$('.btn_write').click(function() {
			var $targetObj = $("div.container");
			$targetObj.empty();
			$("#div1").load("board/write.jsp");
		});

	});

	$('div.container>table a').click(function() {
		var $targetObj = $("div.container");
		$targetObj.empty();
		$("#div1").load("board/content_conf.jsp");
	});
	var className = 'board';
	$('div#menutab li.'+className).addClass('active');
	console.log($('div#menutab li.'+className));
	$('ul#side-menu').find('li.' + className).show();
</script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<%@include file="../container/footer.jsp"%>