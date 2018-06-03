<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../container/header.jsp"%>
<div class="container">
	<h3 class="write">글 수정</h3>
	<div class="col-lg-12 write">
		<div class="row">
			<form method="post" enctype="multipart/form-data">
				<div class="col-sm-12">
					<div class="row">
						<div class="col-sm-7 form-group">
							<label>글 제목</label>
							<input name="title" type="text" class="form-control" value="${param.title}">
						</div>
						<div class="col-sm-12 form-group">
							<label>글 내용</label>
						</div>
					</div>

					<div id="summernote">
					</div>
						<textarea hidden="hidden" name="content"></textarea>
						<input class="seq" type = "text" hidden="hidden" name="seq" value="${param.seq}">
				</div>
				
				<div class="col-sm-5 form-group fileupload">
					<label>변경할 첨부파일</label>
					<input type="file" class="form-control-file" name="file1"><br>
					<c:if test="${!empty param.originFName}">
						<label>기존에 첨부된 파일</label>
						<input type="text" name="preFName" class="form-control" readonly="readonly" value="${param.originFName}">
					</c:if>
				</div>
				<div class="col-lg-12">
					<button type="submit" class="btn btn-primary update">저장</button>
					<input type="button" class="btn btn-primary delete" value="삭제">
					<button type="button" class="btn btn-primary">취소</button>
				</div>
			</form>

		</div>
		
	</div>
</div>
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog modal-sm">
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">삭제</h4> 
        </div>
        <div class="modal-body">
          <p>삭제하시겠습니까?</p>
        </div>
        <div class="modal-footer">
          <button type="submit" class="btn btn-default" id="boardDelete">삭제</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
        </div>
      </div>
      
    </div>
  </div>
  

<style>
.container{
	padding:20px;
}
h3{
	padding:12px;
}
.fileupload{
	padding:15px;
	margin-bottom: 15px;
}
</style>
<script>
	$(function() {
		$(document).ready(function() {
			<%-- 섬머노트 관련 부분--%>
			$('#summernote').summernote({
				height : 300,
				width : 800,
				lang : 'ko-KR'
				
			});
			$('#summernote').summernote('code', '${param.content}');
			
		});
		
		<%-- 수정버튼을 눌렀을 때 게시글을 수정한다.--%>
		$('button[type=submit]').click(function() {
			var markupStr = $('#summernote').summernote('code').trim();
			console.log(markupStr);
		    $('textarea').text(markupStr);
		    
		    <%-- ajax Form 전송시 multipart/form-data를 사용하기 위해서 선언.--%>
		    var formData = new FormData();
	  	  	formData.append("title", $("input[name=title]").val());
	  	    formData.append("content", $('textarea[name=content]').text());
	  	    formData.append("seq",$('input[name=seq]').val());
	  	 	formData.append("file1", $("input[name=file1]")[0].files[0]);
		
	  	 	$.ajax({
				  url: '${pageContext.request.contextPath}/boardedit.do',
				  type:'post',
				  enctype:'multipart/form-data',
				  processData: false,  <%--파일 업로드시 필요하다.--%>
		          contentType: false,   <%--파일 업로드시 필요하다.--%>
		          cache: false,
				  data:formData,
				  success:function(data){
					  var result = data.trim();
					  if(result == '1'){
						  alert("수정성공!!");
						  location.href = "${pageContext.request.contextPath}/boardlist.do";
					  }else{
						  alert("수정실패!!");
					  }
				  }
			  });
			  return false;
		});
		
		<%-- 삭제버튼을 눌렀을 때 모달을 띄운다.--%>
		$('input.delete').click(function(){
			
			$('#myModal').modal();
		});
		<%--모달의 삭제버튼을 누르면 게시글을 삭제한 후 모달을 닫는다.--%>
		$('button#boardDelete').click(function(){
			$.ajax({
				url: '${pageContext.request.contextPath}/boardedit.do',
				type: 'get',
				data:{"seq":$('input.seq').val()},
				success:function(data){
					var result = data.trim();
					  if(result == '1'){
						  location.href = "${pageContext.request.contextPath}/boardlist.do";
					  }else{
						  alert("삭제실패!!");
					  }
				}
			});
			$('#myModal').modal('toggle');
		});
		
	});
	var className = 'board';
	$('div#menutab li.'+className).addClass('active');
	console.log($('div#menutab li.'+className));
	$('ul#side-menu').find('li.' + className).show();
</script>
<%@ include file="../container/footer.jsp"%>