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
.outtable{
	padding:30px;
}
</style>

</head>
<%-- 이전글 , 다음글 눌렀을 때는 requestScope으로 받는다.
목록에서 눌렀을 시 param으로 받는값. --%>
<c:set var="prePost" value="${requestScope.prePost}"></c:set>
<c:set var="error" value="${requestScope.error}"></c:set>
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
					<c:choose>
						<c:when test="${!empty prePost}">
						${prePost.title}
						</c:when>
						<c:when test="${!empty nextPost}">
						${nextPost.title}
						</c:when>
						<c:otherwise>
						${param.title}
						</c:otherwise>
					</c:choose>
					</td>
				</tr>

				<tr>
					<th>작성일</th>
					<td>
					<c:choose>
						<c:when test="${!empty prePost}">
						${prePost.log_time}
						</c:when>
						<c:when test="${!empty nextPost}">
						${nextPost.log_time}
						</c:when>
						<c:otherwise>
						${param.log_time}
						</c:otherwise>
					</c:choose>
					</td>
					<th>작성자</th>
					<td class="writer">
					<c:choose>
						<c:when test="${!empty prePost}">
						${prePost.name}
						</c:when>
						<c:when test="${!empty nextPost}">
						${nextPost.name}
						</c:when>
						<c:otherwise>
						${param.writer}
						</c:otherwise>
					</c:choose>
					</td>
					<th>조회수</th>
					<td class="hit">
					<c:choose>
						<c:when test="${!empty prePost}">
						${prePost.hit}
						</c:when>
						<c:when test="${!empty nextPost}">
						${nextPost.hit}
						</c:when>
						<c:otherwise>
						${param.hit}
						</c:otherwise>
					</c:choose>
					</td>
				</tr>
			</table>
			<table class="table table-bordered">
				<tr>
					<td width="500px" height="500px" class="content">
					<c:choose>
						<c:when test="${!empty prePost}">
						${prePost.content}
						</c:when>
						<c:when test="${!empty nextPost}">
						${nextPost.content}
						</c:when>
						<c:otherwise>
						${param.content}
						</c:otherwise>
					</c:choose>
					</td>
					<td hidden="hidden" class="seq">
					<c:choose>
						<c:when test="${!empty prePost}">
						${prePost.seq}
						</c:when>
						<c:when test="${!empty nextPost}">
						${nextPost.seq}
						</c:when>
						<c:otherwise>
						${param.seq}
						</c:otherwise>
					</c:choose>
					</td>
				</tr>
			</table>
			<table class="table table-bordered">
				<tr>
					<th width="100px">첨부파일</th>
					<td width="600px" style="style="word-break:break-all;">
					    <c:choose>
					      <c:when test="${!empty prePost}">
					      <c:set var="path" value="${prePost.path}"/>
					      <c:set var="originFName" value="${prePost.originFileName}"/>
					      </c:when>
					      <c:when test="${!empty nextPost}">
					      <c:set var="path" value="${nextPost.path}"/>
					      <c:set var="originFName" value="${nextPost.originFileName}"/>
					      </c:when>
						  <c:otherwise>
						  <c:set var="path" value="${param.path}"/>
						  <c:set var="originFName" value="${param.originFName}"/>						  
						  </c:otherwise>
					    </c:choose>
					
						<a href="#" class="${path}">${originFName}</a>
					</td>
				</tr>
			</table>
			<br>
			<table>
				<tr>
					 <td colspan="6" align="center">
						<button class="btn_edit" style="display:none;">수정</button>
						<button class="prePost">이전글</button>
						<button class="nextPost">다음글</button>
						<button class="btn_list">글목록</button>
					 </td>
				</tr>
			</table>
		</div>
		<form id="formDownload">
		  <input name="path" type="hidden">
		  <input name="originFName" type="hidden">
		</form>
	</div>
	<c:set var="loginInfo" value="${sessionScope.loginInfo}"/>
	<script>
		$(function() {
			<%-- 본인이 작성한 글에만 수정버튼이 뜬다.--%>
			if($('td.writer').text().trim() == "${loginInfo.name}"){
					$('button.btn_edit').css("display","inline-block");  <%--처음에 display:non >> display:inline-block으로 바꾼다.--%>
			}
			
			<%--목록 버튼 누르면 일정 목록으로 간다.--%>
			$('.btn_list').click(function() {
				if("${param.flag}" == "1"){
					location.href="${pageContext.request.contextPath}/imgboardlist.do";
				}else{
					location.href="${pageContext.request.contextPath}/boardlist.do";
				};
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
				hiddenField.setAttribute("name", "flag");
				hiddenField.setAttribute("value", "${param.flag}");
				$form.append(hiddenField);
				
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
			
			<%--이전글 눌렀을 때  flag값을 일반게시판0 이미지게시판3--%>
			$('button.prePost').click(function(){
				if("${param.flag}" == "1"){<%--param flag가 1일때는 이미지로 쿼리스트링 flag를 3으로 보낸다.--%>
					location.href="${pageContext.request.contextPath}/boardprenext.do?prenext=3&seq="+$('td.seq').text();
				}else{
					location.href="${pageContext.request.contextPath}/boardprenext.do?prenext=0&seq="+$('td.seq').text();
				}
				
			});
			
			<%--다음글 눌렀을 때 flag값을 일반게시판 1 이미지게시판4 --%>
			$('button.nextPost').click(function(){
				if("${param.flag}" == "1"){<%--param flag가 1일때는 이미지로 쿼리스트링 flag를 4으로 보낸다.--%>
					location.href="${pageContext.request.contextPath}/boardprenext.do?prenext=4&seq="+$('td.seq').text();
				}else{
					location.href="${pageContext.request.contextPath}/boardprenext.do?prenext=1&seq="+$('td.seq').text();
				}
				
			});
		});
		var className = 'board';
		$('div#menutab li.'+className).addClass('active');
		console.log($('div#menutab li.'+className));
		$('ul#side-menu').find('li.' + className).show();
	</script>
	<%@ include file="../container/footer.jsp"%>