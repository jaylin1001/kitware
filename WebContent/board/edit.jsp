<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../container/header.jsp"%>
<div id="div1"></div>
<div class="container">
	<h3 class="write">글 수정</h3>
	<div class="col-lg-12 write">
		<div class="row">
			<form>
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
						<textarea hidden="hidden" name="content"></textarea>
					</div>
					<input class="seq" type = "text" hidden="hidden" name="seq" value="${param.seq}">
				</div>
				
				<div class="col-sm-4 form-group">
					<label>첨부파일</label>
					<button type="button">...</button>
					&nbsp; <input type="text" class="form-control">
					<div>
						<input type="button" class="btn btn-primary update" value="저장">
						<input type="button" class="btn btn-primary delete" value="삭제">
						<button>취소</button>
					</div>
				</div>
			</form>

		</div>
		
	</div>
</div>
 <!-- Modal -->
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
  


<script>
	//board 에서 클릭한 글번호 가져올것
	//그 글번호로 DB에서 select 해 데이터 가져온 후 뿌려줘야함 
	//수정시 본인이 쓴거 아니면 수정 안됨

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
		$('input.update').click(function() {
			var markupStr = $('#summernote').summernote('code').trim();
		    $('textarea').text(markupStr);
			
			$.ajax({
				  url: '${pageContext.request.contextPath}/boardedit.do',
				  type:'post',
				  data:$('form').serialize(),
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
<style>
<%@ include file="../container/footer.jsp"%>