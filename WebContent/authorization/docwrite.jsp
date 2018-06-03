<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="com.kitware.member.vo.Members"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <jsp:include page="../container/header.jsp" flush="true"></jsp:include> --%>
<%@ include file="../container/header.jsp"%>
<%
   SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd");
%>
<c:set var="session" value="${sessionScope.loginInfo }"/>

<div class="center-block">
   <form class="form-group">
      <div class="title" align="center">
         <h2>기안서 작성</h2>
      </div>
      <div>
         <table class="table table-bordered">
            <tr>
               <th>문서번호</th>
               <td><input type="text" name="doc_num"
                  value="${requestScope.doc_num}" readonly></td>
               <th rowspan="2">결재</th>
               <td rowspan="2" style="text-align: center;">
                  <div id="grantor1_grade"></div>
                  <div id="grantor1">승인자1</div> <a
                  href="${pageContext.request.contextPath}/addgrantor.do?id=1"
                  class="btn btn-default g1" data-toggle="modal"
                  data-target="#myModal" id="addgrantor1">선택</a>
               </td>
               <td rowspan="2" style="text-align: center;">
                  <div id="grantor2_grade"></div>
                  <div id="grantor2">승인자2</div> <a
                  href="${pageContext.request.contextPath}/addgrantor.do?id=2"
                  class="btn btn-default g2" data-toggle="modal"
                  data-target="#myModal" id="addgrantor2">선택</a>
               </td>
               <td rowspan="2" style="text-align: center;">
                  <div id="grantor3_grade"></div>
                  <div id="grantor3">승인자3</div> <a
                  href="${pageContext.request.contextPath}/addgrantor.do?id=3"
                  class="btn btn-default g3" data-toggle="modal"
                  data-target="#myModal" id="addgrantor3">선택</a>
               </td>
            </tr>
            <tr>
               <th>문서종류</th>
               <td>${ }</td>
            </tr>
            <tr>
               <th>기안일</th>
               <td class="date">
                  <!-- <input class="form-control" type="text">년
               <input class="form-control" type="text">월 <input
               class="form-control" type="text">일</td> --> <!-- <input type="text"
               id="testDatepicker" placeholder="날짜를 선택하세요" readonly> -->
                  <div id="date"><%=(String) dformat.format(new Date())%></div>
               </td>
               <th>수신부서</th>
               <td colspan="3">
                  <!-- <input class="form-control" type="text"> --> <!-- <button class="btn btn-default">수신부서지정</button> -->
                  <!-- <input class="form-control" type="button" value="수신부서지정"> -->
                  <select name="dept">
                     <c:forEach var="dept" items="${requestScope.deptlist }">
                        <option value="${dept.dept_num}">${dept.dept_name }</option>
                     </c:forEach>
               </select>
               </td>
            </tr>
            <tr>
               <th>기안자</th>
               <td>${session.gradeinfo.position_name }&nbsp;${session.name }</td>
               <th>부서</th>
               <td colspan="3">${session.deptinfo.dept_name }</td>
            </tr>
            <tr>
				<th>대체근무자</th>
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
							<c:forEach var="emp" items="${requestScope.memberlist }">
								<option id="${emp.dept_num }${emp.position_num}" style="display: none" value="${emp.emp_num }">${emp.name }</option>
							</c:forEach>
						</select>
					</div>
				</td>
			</tr>
            <tr>
               <th>제목</th>
               <td colspan="5"><input type="text" class="form-control"
                  placeholder="제목입력" name="title"></td>
            </tr>
            <tr>
               <td colspan="6"><textarea name="content" id="content"
                     class="summernote" style="width: 100%"></textarea></td>
            </tr>
            <tr>
               <td colspan="6" align="center">
                  <button class="btn btn-success" style="background: gray;" id="go">제출</button>
                  <!-- <input type="submit" class="btn btn-success" value="제출"> -->
                  <button class="btn btn-success" style="background: gray;">취소</button>
                  <!-- <input type="button" value="제출">
               <input type="button" value="취소"> -->
               </td>
            </tr>
         </table>
      </div>
   </form>
</div>

<div id="myModal" class="modal fade">
   <div class="modal-dialog">
      <div class="modal-content"></div>
   </div>
</div>

<script>
   $(function() {
      $('.summernote').summernote({
         height : 300, // 기본 높이값
         minHeight : null, // 최소 높이값(null은 제한 없음)
         maxHeight : null, // 최대 높이값(null은 제한 없음)
         focus : true, // 페이지가 열릴때 포커스를 지정함
         lang : 'ko-KR' // 한국어 지정(기본값은 en-US)
      });
      var className = 'authorization';
      $('div#menutab li.' + className).addClass('active');
      console.log($('div#menutab li.' + className));
      $('ul#side-menu').find('li.' + className).show();

      $('#go').click(function() {
         $.ajax({
            url :'docwritegian.do?kind=10',
            method:'POST',
            data : {
               doc_num:$('input[name=doc_num]').val(),
               date:$('div#date').html(),
               dept:$('select[name=dept]').val(),
               title:$('input[name=title]').val(),
               content:$('textarea').val(),
               g1_grade:$('#grantor1_grade').html().trim(),
               g1:$('#grantor1').html().trim(),
               g2_grade:$('#grantor2_grade').html().trim(),
               g2:$('#grantor2').html().trim(),
               g3_grade:$('#grantor3_grade').html().trim(),
               g3:$('#grantor3').html().trim(),
               refer:$('.name').val()
            },
            success : function(data) {
               if(data==1){
                  alert("기안서 제출 성공");
                  location.href="/kitware_v1/doclist.do";
               }else if(data==-1){
                  alert("실-패");
               }
            }
         });
         return false;
      });
      
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
      
      

   });
</script>
<%@ include file="../container/footer.jsp"%>
<%-- <jsp:include page="../container/footer.jsp" flush="true"></jsp:include> --%>