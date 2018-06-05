<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../container/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<form id="formwrite">
<c:set var="doc" value="${requestScope.docvo_list}" />
<c:set var="doc_conf" value="${requestScope.doc_detail_list}"/>
<c:set var="session" value="${sessionScope.loginInfo}"></c:set>	
<c:set var="exp" value="${param.exp}"/>	

	<div>
		<div class="title" align="center">
			<h2>출장신청서</h2>
		</div>
		<div class="table">
			<table class="table table-bordered">
				<tr>
					<th>문서번호</th>
					<td>${doc.doc_num}</td>
					<th rowspan="2">결재</th>
					<c:forEach var="cn" items="${doc_conf}" >
					<td rowspan="2">
						<div>${cn.members.name}</div>
						<c:choose>
					 	<c:when test="${cn.acs_yn eq '0'}">
						<div>${cn.acs_yn} 대기</div> 
						</c:when>
					 	<c:when test="${cn.acs_yn eq '1'}">
						<span style="font-weight:bold;color:red">${cn.acs_yn}결재</span>
						</c:when>
						<c:when test="${cn.acs_yn eq '3'}">
						<div>${cn.acs_yn} 반려</div></c:when>
						</c:choose>
					</td>
						</c:forEach>
						</tr>
				<tr>
					<th>문서종류</th>
					<td>${doc.doc_kindvo.doc_name}	</td>
				</tr>
				<tr>
					<th>기안일</th>
					<td>${doc.start_date}</td>
					<th>수신부서</th>
					<td>${doc.rcv_dept}</td>
				</tr>
				<tr>
					<th>기안자</th>
					<td>${doc.members.name}(${doc.gradeinfo.position_name})</td>
					<th>부서</th>
					<td colspan="3">${doc.deptinfo.dept_name}</td>
				</tr>
				<tr>
					<th>참조자</th>
					<td colspan="5">${doc.refer}</td>
				</tr>
				<tr>
					<th>제목</th>
					<td colspan="5">${doc.doc_title}</td>
				</tr>
				<tr>
					<th>기간</th>
					<td>${doc.doc_gigan.start_date} &nbsp;~&nbsp; ${doc.doc_gigan.end_date}</td>
				</tr>

				<tr>
					<th>출장지</th>
					<td colspan="5"> null<!--지도 api써도 좋을거같음 --></td>
				</tr>

				<tr>
					<th>출장목적</th>
					<td colspan = "12" height ="200">${doc.doc_content}</td>		
				</tr>
				<tr>
					<th>첨부파일</th>
					<td colspan="5"><input type="text">
						<button>첨부파일</button></td>
				</tr>

				<tr>
					<td colspan="6" align="center">상기와 같이 출장 신청서를 제출하오니 재가바랍니다.</td>
				</tr>
				<tr>	
					<td colspan="6" align="center">
					<c:set var="sname" value="${session.name}" />
				<c:set var="dname" value="${doc.members.name}" />
				<c:choose>
					<c:when test="${doc.doc_state ne '2'||doc.doc_state ne '3'}">
						<c:if test="${sname eq dname}">
						<input type="button" value="수정" id="edit" onclick = "editdocnum('${doc.doc_num}')"> 
					    <input type="button" value="삭제" id="del" onclick = "deldocnum('${doc.doc_num}')">
						</c:if>
						</c:when>
					</c:choose>
					<c:set var="snum" value="${session.emp_num}" />
					<c:if test="${exp eq null}">
					<c:forEach items="${doc_conf}" var="item" varStatus="status">
					  <c:if test="${item.conf_num eq snum}">
					  <c:if test="${item.acs_yn eq '0'}">
						<input type="button" value="승인" id="ok" onclick = "gjdocnum(${doc_conf[0].acs_yn}${doc_conf[1].acs_yn}${doc_conf[2].acs_yn},'${doc.doc_num}','${doc.doc_state}','${fn:length(doc_conf)} ')">
						<input type="button" value="반려" id="down" onclick = "downdocnum(${doc_conf[0].acs_yn}${doc_conf[1].acs_yn}${doc_conf[2].acs_yn},'${doc.doc_num}')">
					 </c:if>
					  </c:if>
					  </c:forEach>
					  </c:if>
						<input type="button" value="뒤로가기" id="back">
					</td>
				</tr>
			</table>
		</div>
	</div>
</form>
<script>

	function editdocnum(data) {
		location.href= "doceditcj.do?doc_num="+data+"&mode=read"
		console.log(data);
	}
	function deldocnum(data) {
		location.href= "docdelcj.do?doc_num="+data;
		console.log(data);
	}
	function gjdocnum(data, data2, data3, data4) {
		console.log(data);
		console.log(data2);
		location.href= "docgjupdate.do?doc_num="+data2+"&mode="+data+"&kind=up"+"&smode="+data3+"&count="+data4;
		console.log(data);
	}
	function downdocnum(data, data2) {
		console.log(data);
		console.log(data2);
		location.href= "docgjupdate.do?doc_num="+data2+"&mode="+data+"&kind=down";
		console.log(data);
	}
	$(function() {
		$("#testDatepicker").datepicker({
			showOn : "both",
			/* buttonImage: "button.png", 
			buttonImageOnly: true  */
			dateFormat : "yy-mm-dd"
		});

		$("#testDatepicker2").datepicker({
			showOn : "both",
			dateFormat : "yy-mm-dd"
		});
		
		$("#back").click(function() {
			history.back();
		return false;
		});
		var className = 'authorization';
		$('div#menutab li.' + className).addClass('active');
		console.log($('div#menutab li.' + className));
		$('ul#side-menu').find('li.' + className).show();
	});

 	/*$(function() {
		$('form#formwrite').submit(function() {
			$.ajax({
				url : 'docwritecj.do',
				method : 'post',
				data : $('form').serialize(),
				success : function(data) {
					data = data.trim();
					if (data == '1') { //글쓰기 성공
						alert('글쓰기 성공');
						var $triggerObj = $("nav>ul li.board!!수정	해야함");
						$triggerObj.trigger('click');
					} else if (data == '-1') { //글쓰기 실패
						alert('글쓰기 실패');
					}
				}
			});
			return false;
		});
	}); */
</script>
<%@ include file="../container/footer.jsp"%>