<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../container/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="toDay" class="java.util.Date" />
<fmt:formatDate value='${toDay}' pattern='yyyyMMdd' var="nowDate"/>
<c:set var="session" value="${sessionScope.loginInfo }"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form id="formwrite" name="test">
<h1>쪽지작성</h1>
<label>보낸사람:</label>
<input type ="text" name ="namesend" value="${session.name}" readonly>&nbsp; &nbsp; 
 <table>
<tr>
				<th>받는사람:&nbsp;</th>
				<td colspan="5">
					<div class="replace">
						<select name="dept" class="dept">
							<option>부서 선택</option>
							<c:forEach var="dept" items="${requestScope.deptlist}">
								<option value="${dept.dept_num }">${dept.dept_name }</option>
							</c:forEach>
						</select> <select name="grade" class="grade">
							<option id="init">직급 선택</option>
							<c:forEach var="grade" items="${requestScope.gradelist}">
								<option style="display: none" value="${grade.position_num }">${grade.position_name }</option>
							</c:forEach>
						</select> <select name="name" class="name">
							<option id="init">사원 선택</option>
							<c:forEach var="emp" items="${requestScope.memberlist}">
								<option id="${emp.dept_num }${emp.position_num}" style="display: none" value="${emp.emp_num}">${emp.name}</option>
							</c:forEach>
						</select>
					</div>
				</td>
			</tr>
			</table>
<br>

  <label>제목 : </label>
   <input type ="text" name ="mail_title" size="35">
  <hr>
  <div style="width:400px">
<textarea class="form-control" id="txarea" rows="10"
	name = "mail_content">



${session.name}/${toDay}
----------------------------------------------------
</textarea>
</div>

 <br>
<button class="btn btn-success" style="background: gray;" id="go">제출</button>
<button class="btn btn-success" style="background: gray;" id="back">뒤로가기</button>
</form>
</body>
<style>
.container {
	padding-right: 350px;
	padding-left: 15px;
	margin-right: auto;
	margin-left: auto;
}

body {
	width: 100%;
	margin: 0;
	padding: 0;
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
thead {
	background-color: #337ab7;
	color: white;
	font-weight: bold;
}


</style>
<script>
	
	var txarea = document.getElementById('txarea')
	document.test.txarea.focus();
	document.test.txarea.value = txarea.value;

$(function() {

 
 $('.dept').change(function() {
		if ($(this).val() == '부서 선택') {
			$(this).siblings('.grade').children('option#init').show();
			$(this).siblings('.grade').children('option').hide();
		} else {
			$(this).siblings('.grade').children('option').show();
		}
		$(this).siblings('.grade').children('option#init').prop('selected', true);
	});

	$('.grade').change(function() {
		
		if ($(this).val() == '직급 선택') {
			$(this).siblings('.name').children('option').hide();
			$(this).siblings('.name').children('option#init').show();
		} else {
			$(this).siblings('.name').children('option').hide();
			var dept = $(this).siblings('.dept').val();
			var grade = $(this).val();
			$(this).siblings('.name').children('option').each(function() {
				if($(this).prop('id').trim()==dept+grade){
					$(this).show();
				}
			});
		}
		$(this).siblings('.name').children('option#init').prop('selected',true);
	});
	
	$('#go').click(function() {
	    $.ajax({
	       url :'mailwrite.do?mode=write',
	       method:'POST',
	       data : {
	          mail_title : $('input[name=mail_title]').val(),
	          mail_content : $('textarea').val(),
	          replace : $("select[name=name]").val()
	       },
	       success : function(data) {
	          if(data==1){
	             alert("쪽지보내기 성공");
	             location.href="/kitware_v1/maillist.do";
	          }else if(data==-1){
	             alert("실-패");
	          }
	       }
	    });
	    
	    return false;
	 });//ajax close
	 $('#back').click(function() {
		    location.href="maillist.do";
		    return false;
		 });

});
	var className = 'mail';
	$('div#menutab li.' + className).addClass('active');
	$('ul#side-menu').find('li.' + className).show();
</script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<%@include file="../container/footer.jsp"%>
</html>
