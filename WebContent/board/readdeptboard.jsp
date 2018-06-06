<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../container/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<style>
th {
	text-align: center;
	vertical-align: middle;
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

.outtable {
	padding: 30px;
}
</style>

</head>
<%-- 이전글 , 다음글 눌렀을 때는 requestScope으로 받는다.
목록에서 눌렀을 시 param으로 받는값. --%>
<c:set var="error" value="${requestScope.error}"></c:set>
<c:set var="loginInfo" value="${sessionScope.loginInfo }"/>
<c:set var="type" scope="request" value="dept" />
<c:if test="${!empty error}">
	<script>
		alert("${error}");
		history.back(); <%-- 이전글 및 다음글 없을 때는 이전 페이지로 돌아가라!!! --%>
	</script>
</c:if>

<body>
	<div>
		<div class="outtable">
			<table class="table table-bordered">
				<tr>
					<th>제목</th>
					<td colspan="5" class="title">
						${param.title}
				</tr>

				<tr>
					<th>작성일</th>
					<td>
						${param.log_time}
					<th>작성자</th>
					<td class="writer">
						${param.writer}
					</td>
					<th>조회수</th>
					<td class="hit">
						${param.hit}
						</td>
				</tr>
			</table>
			<table class="table table-bordered">
				<tr>
					<td width="500px" height="500px" class="content">
						${param.content}
						</td>
					<td hidden="hidden" class="seq">
						${param.seq}
					</td>
				</tr>
			</table>
			<table class="table table-bordered">
				<tr>
					<th width="100px">첨부파일</th>
					<td width="600px" style=""word-break:break-all;">
						<c:set var="path" value="${param.path}"/>
						 <c:set var="originFName" value="${param.originFName}"/>
						<a href="#" class="${path}">${originFName}</a></td>
				</tr>
			</table>
			<br>
			<div width="100%">
				<div>댓글 쓰기</div>
				<div class="comment">
					<textarea class="cmcontent"></textarea>
					<button id="cmgo">댓글작성</button>
				</div>
			</div>
			<div class="cmtlist"></div>
			<table>
				<tr>
					<td colspan="6" align="center">
						<button class="btn_edit" style="display: none;">수정</button>
						<button class="btn_list">글목록</button>
						<button class="btn_rep">답글</button>
					</td>
				</tr>
			</table>
		</div>
		<form id="formDownload">
			<input name="path" type="hidden"> <input name="originFName"
				type="hidden">
		</form>
	</div>
	<c:set var="loginInfo" value="${sessionScope.loginInfo}" />
	<script>
	
	 var f = function(){
		$.ajax({
			method:'POST',
			data : {
				type:"read",
				seq:"${param.seq}"
				},
			url:'../commentlist.do?type=read',
			success:function(data){
				$('div.cmtlist').html(data);
				$('.cmname').each(function(){
					if($(this).attr('id').trim()=="${loginInfo.emp_num}"){
						$(this).siblings('.del').show();
					}
				});
			},
			error:function(){
				alert("error!");
			}
		});
		return false;
	}
	
		$(function() {
			<%-- 작성자 아이디 일때만 수정 버튼을 보이게 한다.--%>
			f();
			$('#cmgo').click(function(){
				if($('.cmcontent').val().trim()==""){
					return;
				}else{
					$.ajax({
						method:'POST',
						data : {
							type:"write",
							seq:"${param.seq}",
							content:$('.cmcontent').val()
							},
						url:'../commentlist.do',
						success:function(data){
							$('div.cmtlist').html(data);
							$('.cmcontent').val('');
						},
						error:function(){
							alert("error!");
						}
					});
					
					f();
				}
			});
			
			$(document).on('click','a.delbtn',function(){
				$.ajax({
					method:'POST',
					data : {
						type:"del",
						seq:$(this).attr('id'),
						},
					url:'../commentlist.do',
					success:function(data){
						f();
					},
					error:function(){
						alert("error!");
					}
				});
			});
			
			
			if($('td.writer').text().trim() == "${loginInfo.name}"){
				$('button.btn_edit').css("display","inline-block");  <%--처음에 display:non >> display:inline-block으로 바꾼다.--%>
			}
			
			$('.btn_list').click(function() {
				location.href="${pageContext.request.contextPath}/boardlist.do?type=dept";
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
				hiddenField.setAttribute("value", $('td.title').text().trim());
				$form.append(hiddenField);
				
				var hiddenField = document.createElement("input");
				hiddenField.setAttribute("type", "hidden");
				hiddenField.setAttribute("name", "seq");
				hiddenField.setAttribute("value",$('td.seq').text().trim());
				$form.append(hiddenField);

				var hiddenField = document.createElement("input");
				hiddenField.setAttribute("type", "hidden");
				hiddenField.setAttribute("name", "content");
				hiddenField.setAttribute("value",$('td.content').html().trim());
				$form.append(hiddenField);
				
				var hiddenField = document.createElement("input");
				hiddenField.setAttribute("type", "hidden");
				hiddenField.setAttribute("name", "originFName");
				hiddenField.setAttribute("value",$('td>a').text().trim());
				$form.append(hiddenField);
				
				var hiddenField = document.createElement("input");
				hiddenField.setAttribute("type", "hidden");
				hiddenField.setAttribute("name", "mode");
				hiddenField.setAttribute("value","dept");
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
			
			$('.btn_rep').click(function(){
				path = "${pageContext.request.contextPath}/board/writerep.jsp";
				var $form = $("<form></form>");
				$form.attr("method", "post");
				$form.attr("action", path);
				
				
				var hiddenField = document.createElement("input");
				hiddenField.setAttribute("type", "hidden");
				hiddenField.setAttribute("name", "title");
				hiddenField.setAttribute("value", $('td.title').text().trim());
				$form.append(hiddenField);
				
				var hiddenField = document.createElement("input");
				hiddenField.setAttribute("type", "hidden");
				hiddenField.setAttribute("name", "seq");
				hiddenField.setAttribute("value",$('td.seq').text().trim());
				$form.append(hiddenField);

				var hiddenField = document.createElement("input");
				hiddenField.setAttribute("type", "hidden");
				hiddenField.setAttribute("name", "content");
				hiddenField.setAttribute("value",$('td.content').html().trim());
				$form.append(hiddenField);
				
				var hiddenField = document.createElement("input");
				hiddenField.setAttribute("type", "hidden");
				hiddenField.setAttribute("name", "originFName");
				hiddenField.setAttribute("value",$('td>a').text().trim());
				$form.append(hiddenField);
				
				
				$(document.body).append($form);  <%--동적으로 만든 form을 document.body에 append--%>

				$form.submit();
				
				return false;
				/* location.href="${pageContext.request.contextPath}/deptrepwrite.do" */
			});
			
		});
		var className = 'board';
		$('div#menutab li.'+className).addClass('active');
		console.log($('div#menutab li.'+className));
		$('ul#side-menu').find('li.' + className).show();
		
	</script>
	<%@ include file="../container/footer.jsp"%>