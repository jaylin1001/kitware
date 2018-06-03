<%@page import="java.io.File"%>
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
.outtable{
	padding:20px;
}
</style>

</head>
<body>
	<div>
		<div class="outtable">
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
					<td colspan="5">
						<a href="#" class="${param.path}">${param.originFName}</a>
					</td>
				</tr>
			</table>
			<br>
			<tr>
				<td colspan="6" align="center">
					<button class="btn_edit" style="display:none;" >수정</button>
					<button>이전글</button>
					<button>다음글</button>
					<button class="btn_list">글목록</button>
				</td>
			</tr>
		</div>
		
		<form id="formDownload">
		  <input name="path" type="hidden">
		  <input name="originFName" type="hidden">
		</form>
	</div>
	<c:set var="loginInfo" value="${sessionScope.loginInfo}"/>
	<script>
		$(function() {
			<%-- 관리자 아이디 일때만 수정 버튼을 보이게 한다.--%>
			if($('td.writer').text() == "${loginInfo.name}"){
				$('button.btn_edit').css("display","inline-block");  <%--처음에 display:non >> display:inline-block으로 바꾼다.--%>
			}
			
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
				$form.attr("method", "post");
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
				
				var hiddenField = document.createElement("input");
				hiddenField.setAttribute("type", "hidden");
				hiddenField.setAttribute("name", "originFName");
				hiddenField.setAttribute("value",$('td>a').text());
				$form.append(hiddenField);
				
				
				$(document.body).append($form);  <%--동적으로 만든 form을 document.body에 append--%>

				$form.submit();
				
				return false;
			});
			
			<%-- 첨부파일 클릭했을 때--%>
			$('td>a').click(function(){
				var classValue = $(this).attr("class").trim();
				var textValue = $(this).text();
				
				$('#formDownload>input[name=path]').val(classValue);
				$('#formDownload>input[name=originFName]').val(textValue);
				$formObj = $("#formDownload");
				$formObj.attr('action','${pageContext.request.contextPath}/boarddown.do');
				$formObj.attr('method', 'post');
				$formObj.submit();
				
			});
		});
		var className = 'board';
		$('div#menutab li.'+className).addClass('active');
		console.log($('div#menutab li.'+className));
		$('ul#side-menu').find('li.' + className).show();
	</script>
	<%@ include file="../container/footer.jsp"%>