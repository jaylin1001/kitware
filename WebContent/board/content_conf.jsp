<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../container/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
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
		</div>
		<div class="table">
			<table class="table table-bordered">
				
				<tr>
					<th>제목</th>
					<td colspan="5" class="title">${param.title}</td>
				</tr>

				<tr>
					<th>작성일</th>
					<td>${param.log_time}</td>
					<th>작성자</th>
					<td class="writer">${param.writer}</td>
					<th>조회수</th>
					<td>${param.hit}</td>
				</tr>
			</table>
			<table class="table table-bordered">
				<tr>
					<td width="500px" height="500px" class="content">${param.content}</td>
					<td hidden="hidden" class="seq">${param.seq}</td>
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
					<button class="btn_edit" >수정</button>
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
				location.href="${pageContext.request.contextPath}/boardlist.do";
			});
			return false;
		});
		
		
		<%--글 수정버튼 눌렀을때 할 일--%>
		$(function() {
			$('.btn_edit').click(function(path,method) {
				path = "${pageContext.request.contextPath}/board/edit.jsp";
				var $form = $("<form></form>");
				$form.attr("method", "get");
				$form.attr("action", path);
				
				
				var hiddenField = document.createElement("input");
				hiddenField.setAttribute("type", "hidden");
				hiddenField.setAttribute("name", "title");
				hiddenField.setAttribute("value", $('td.title').text());
				$form.append(hiddenField);
				
				var hiddenField = document.createElement("input");
				hiddenField.setAttribute("type", "hidden");
				hiddenField.setAttribute("name", "seq");
				hiddenField.setAttribute("value",$('td.seq').text());
				$form.append(hiddenField);

				var hiddenField = document.createElement("input");
				hiddenField.setAttribute("type", "hidden");
				hiddenField.setAttribute("name", "content");
				hiddenField.setAttribute("value",$('td.content').html());
				$form.append(hiddenField);
				
				
				$(document.body).append($form);  <%--동적으로 만든 form을 document.body에 append--%>

				$form.submit();
				
				return false;
			});
		});
		var className = 'board';
		$('div#menutab li.'+className).addClass('active');
		console.log($('div#menutab li.'+className));
		$('ul#side-menu').find('li.' + className).show();
	</script>
	<%@ include file="../container/footer.jsp"%>